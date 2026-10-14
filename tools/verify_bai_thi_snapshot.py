"""Real SQL race: delete selected B questions while lower C selection is blocked."""
import subprocess
import time

COMMAND = ["sqlcmd", "-S", r"localhost\SQLEXPRESS", "-d", "THITRACNGHIEM_VERIFY",
           "-E", "-C", "-b", "-f", "65001", "-h", "-1", "-W"]


def sql(query):
    result = subprocess.run(COMMAND + ["-Q", "SET NOCOUNT ON; " + query],
                            capture_output=True, encoding="utf-8", errors="replace",
                            creationflags=subprocess.CREATE_NO_WINDOW, timeout=35)
    if result.returncode:
        raise AssertionError(result.stdout + result.stderr)
    return result.stdout.strip()


blocker = None
exam = None
try:
    sql("""
    IF OBJECT_ID('dbo.VFY_BODE_BACKUP') IS NOT NULL THROW 51000,'Leftover race fixture',1;
    DELETE CHITIETBAITHI WHERE MASV='VFY00003';
    DELETE BANGDIEM WHERE MASV='VFY00003';
    DELETE TRANGTHAITHI WHERE MASV='VFY00003';
    SELECT * INTO dbo.VFY_BODE_BACKUP FROM dbo.BODE;
    -- C đọc trước B khi JOIN cuối dùng khóa câu hỏi.
    UPDATE dbo.BODE SET CAUHOI=-CAUHOI WHERE TRINHDO='C' AND CAUHOI>0;
    CREATE INDEX VFY_Candidate ON dbo.BODE(MAMH,TRINHDO)
        INCLUDE(CAUHOI,NOIDUNG,A,B,C,D,DAP_AN);
    IF NOT EXISTS(SELECT 1 FROM SINHVIEN WHERE MASV='VFY00003')
        INSERT SINHVIEN(MASV,HO,TEN,MALOP) VALUES('VFY00003',N'Race',N'Test','VFY_THI');
    UPDATE GIAOVIEN_DANGKY SET TRINHDO='B',SOCAUTHI=10,
        NGAYTHI=CONVERT(date,DATEADD(hour,7,SYSUTCDATETIME())) WHERE MAMH='VFY01' AND LAN=1;
    """)
    blocker = subprocess.Popen(COMMAND + ["-Q", """
        SET NOCOUNT ON; SET CONTEXT_INFO 0x564659424C4F434B;
        BEGIN TRAN; UPDATE BODE WITH(ROWLOCK) SET NOIDUNG=N'Locked C '+NOIDUNG WHERE TRINHDO='C';
        WAITFOR DELAY '00:00:12'; COMMIT;
        """], stdout=subprocess.DEVNULL, stderr=subprocess.PIPE,
        creationflags=subprocess.CREATE_NO_WINDOW)
    last_statement = ""
    for _ in range(10):
        if sql("SELECT COUNT(*) FROM sys.dm_exec_requests r WHERE SUBSTRING(r.context_info,1,8)=0x564659424C4F434B AND r.wait_type='WAITFOR'") == "1":
            break
        time.sleep(0.1)
    else:
        raise AssertionError("Race fixture did not lock C questions")

    exam = subprocess.Popen(COMMAND + ["-Q", """
        SET NOCOUNT ON; SET CONTEXT_INFO 0x5646594558414D;
        EXEC SP_MoBaiThi 'VFY00003','VFY01',1;
        """], stdout=subprocess.DEVNULL, stderr=subprocess.PIPE,
        creationflags=subprocess.CREATE_NO_WINDOW)
    for _ in range(50):
        statement = sql("""
        SELECT SUBSTRING(t.text,r.statement_start_offset/2+1,
            (CASE WHEN r.statement_end_offset=-1 THEN DATALENGTH(t.text)
                ELSE r.statement_end_offset END-r.statement_start_offset)/2+1)
        FROM sys.dm_exec_requests r JOIN sys.dm_exec_sessions s ON s.session_id=r.session_id
        CROSS APPLY sys.dm_exec_sql_text(r.sql_handle) t
        WHERE SUBSTRING(r.context_info,1,8)=0x5646594558414D AND r.wait_type LIKE 'LCK_M_%';
        """)
        if statement: last_statement = statement
        if "@ThapHon" in statement or "INSERT dbo.TRANGTHAITHI" in statement:
            break
        time.sleep(0.1)
    else:
        raise AssertionError("Race fixture did not reach lower-question selection; " + last_statement)

    sql("DELETE BODE WHERE TRINHDO='B';")
    if blocker.wait(timeout=20) or exam.wait(timeout=20):
        raise AssertionError("SQL race process failed")
    count = sql("SELECT COUNT(*) FROM TRANGTHAITHI WHERE MASV='VFY00003';")
    if count != "10":
        raise AssertionError("Deleting selected BODE questions left " + count + " rows; expected 10")
    assert sql("SELECT COUNT(*) FROM TRANGTHAITHI WHERE MASV='VFY00003' AND TRINHDOCAU='B';") == "7"
    print("PASS SQL race: deleting BODE during creation preserves all 10 question snapshots.")
    submits = [subprocess.Popen(COMMAND + ["-Q", "EXEC SP_NopBaiThi 'VFY00003','VFY01',1;"],
               stdout=subprocess.DEVNULL, stderr=subprocess.PIPE,
               creationflags=subprocess.CREATE_NO_WINDOW) for _ in range(2)]
    for submit in submits:
        assert submit.wait(timeout=30) == 0, submit.stderr.read().decode(errors="replace")
    assert sql("SELECT COUNT(*) FROM BANGDIEM WHERE MASV='VFY00003';") == "1"
    assert sql("SELECT COUNT(*) FROM CHITIETBAITHI WHERE MASV='VFY00003';") == "10"
    assert sql("SELECT COUNT(*) FROM TRANGTHAITHI WHERE MASV='VFY00003';") == "0"
    print("PASS SQL race: concurrent submission writes one grade and one archived exam.")
finally:
    for process in [blocker, exam]:
        if process is not None and process.poll() is None:
            process.wait(timeout=30)
    sql("""
    IF OBJECT_ID('dbo.VFY_BODE_BACKUP') IS NOT NULL BEGIN
        UPDATE dbo.BODE SET CAUHOI=-CAUHOI WHERE TRINHDO='C' AND CAUHOI<0;
        INSERT BODE SELECT * FROM dbo.VFY_BODE_BACKUP x
            WHERE NOT EXISTS(SELECT 1 FROM BODE b WHERE b.CAUHOI=x.CAUHOI);
        UPDATE b SET NOIDUNG=x.NOIDUNG FROM BODE b JOIN dbo.VFY_BODE_BACKUP x ON b.CAUHOI=x.CAUHOI;
        DROP TABLE dbo.VFY_BODE_BACKUP;
    END;
    IF EXISTS(SELECT 1 FROM sys.indexes WHERE name='VFY_Candidate' AND object_id=OBJECT_ID('BODE'))
        DROP INDEX VFY_Candidate ON BODE;
    UPDATE dbo.BODE SET CAUHOI=-CAUHOI WHERE TRINHDO='C' AND CAUHOI<0;
    DELETE TRANGTHAITHI WHERE MASV='VFY00003';
    DELETE CHITIETBAITHI WHERE MASV='VFY00003';
    DELETE BANGDIEM WHERE MASV='VFY00003';
    """)
