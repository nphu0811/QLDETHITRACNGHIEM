# Lưu và tiếp tục bài thi

> **For agentic workers:** Thực hiện trực tiếp bằng superpowers:executing-plans; kiểm tra từng phần trước khi chuyển sang phần tiếp theo.

**Goal:** Sinh viên thi, đóng app rồi mở lại vẫn giữ câu hỏi, lựa chọn, vị trí và hạn giờ; nộp bài lưu điểm và chi tiết rồi xóa trạng thái.

**Architecture:** SQL Server quyết định ngày được bắt đầu, chọn câu ngẫu nhiên, giữ hạn giờ và chấm điểm. Java gọi SQL ở nền, hiển thị bài thi và đếm ngược theo thời gian SQL trả về. Giữ nguyên vùng giao diện do NetBeans tạo.

**Tech Stack:** Java 21, Swing, JDBC, SQL Server Express.

**Spec:** Yêu cầu đã thống nhất trong cuộc trò chuyện, ghi lại bên dưới.

## Global Constraints

- Dùng session sinh viên hiện tại; các field thông tin không được sửa.
- Một bảng TRANGTHAITHI lưu bài đang làm, một bảng CHITIETBAITHI lưu bản chụp khi nộp; BANGDIEM lưu điểm.
- Thời gian vẫn chạy khi đóng app; dùng giờ SQL, Java chỉ đếm ngược và kiểm tra định dạng ngày nhập.
- Câu hỏi không trùng; A/B lấy 70% đúng trình độ và 30% thấp hơn, C lấy toàn bộ C; cho phép gặp câu của lần trước.
- Điểm tối đa 10, mỗi câu bằng điểm nhau; nộp và chuyển dữ liệu trong một transaction, gọi lại không tạo điểm trùng.
- Không đổi logic giảng viên, không sửa vùng Generated Code hay layout .form.

## Review Focus

- Mở lại sau hạn giờ: giữ đáp án đã lưu và tự nộp, không khởi động đồng hồ mới.
- Nộp hai lần hoặc mất phản hồi: chỉ một điểm, một bản chi tiết.
- Đổi hoặc xóa câu trong BODE: chi tiết vẫn giữ nội dung và đáp án lúc thi.
- Lưu đáp án đang chạy khi hết giờ: hàng đợi SQL kết thúc lưu trước nộp; SQL từ chối đáp án muộn.
- Đổi bộ lọc, sắp xếp bảng, chạy form không session: không dùng nhầm dòng, không NullPointerException.

## Task 1: SQL lưu trạng thái và chấm điểm

Files: sql/migrations/20261010-luu-bai-thi.sql, sql/tests/verify-luu-bai-thi.sql.

Interfaces: SP_MoBaiThi(MASV,MAMH,LAN), SP_DocBaiThi(MASV,MAMH=NULL,LAN=NULL), SP_LuuTienDoThi(MASV,MAMH,LAN,THUTU,CAUTRALOI,CAUDANGXEM), SP_NopBaiThi(MASV,MAMH,LAN), SP_LayBaiThiDangDo(MASV). Đọc trả dòng câu hỏi cùng metadata, DANOP, CONLAI_MS, DIEM, SOCAUDUNG.

- [x] Viết và chạy test thiếu SP (RED).
- [x] Tạo migration thêm bảng và SP, thay kiểm tra ngày trong SP_LayCaThiSinhVien.
- [x] Chạy test trong database kiểm thử riêng: chọn 7/3, resume, lưu/xóa lựa chọn, hạn giờ, nộp idempotent, rollback, thiếu câu, ngày quá khứ/tương lai, lọc độc lập.
- [x] Áp dụng migration vào database app sau khi các test đạt; chỉ thêm bảng/SP/quyền thực thi cần thiết.

## Task 2: Java gọi SQL và form thi

Files: session/BaiThi.java, connectionSQLServer/ThiDAO.java, thi/LamBaiThi.java, thi/ChonCaThi.java, trangchu/TrangChu.java, tools/VerifyBaiThi.java.

Interfaces: BaiThi giữ CaThi, danh sách CauHoi, vị trí, millis còn lại, điểm; ThiDAO cung cấp mo/nop/xem/luu/dangDo. Form nhận session và CaThi.

- [x] Viết kiểm thử Swing lưu lựa chọn, đổi câu và mở lại (RED).
- [x] Thực hiện JDBC và hàng đợi nền, đồng hồ, handler, resume từ TrangChu; bỏ kiểm tra ngày nghiệp vụ ở Java.
- [x] Chạy kiểm thử real SQL với fixture riêng, dọn fixture sau khi chạy; kiểm tra hết giờ và lưu thất bại.

## Task 3: Kết quả, chi tiết và kiểm tra

Files: thi/KetQuaThi.java, baocao/XemKetQua.java, docs/luu-bai-thi.md.

- [x] Kiểm thử hiển thị điểm/chi tiết và readonly bằng session hiện tại (RED).
- [x] Thêm constructor nhận BaiThi và tải kết quả gần nhất khi mở từ TrangChu.
- [x] Maven clean test, kiểm tra NetBeans models/layout, tự rà soát các trường hợp Review Focus.
- [x] Ghi hướng dẫn file, SP và giới hạn: khi app đã đóng, bài quá giờ được nộp lúc đăng nhập lại.

## Execution record

- Không có Git: thực hiện tại workspace hiện tại, sao lưu các file sửa và định nghĩa SP trước khi áp dụng.
- Người dùng đã duyệt phương án trong hội thoại và yêu cầu thực hiện; không chờ duyệt lại kế hoạch.
- Task 1: RED thiếu SP_MoBaiThi; GREEN verify-luu-bai-thi.sql trên database riêng. Đã áp dụng migration và quyền EXECUTE vào THITRACNGHIEM; không ghi dữ liệu thi thử vào database ứng dụng.
- Task 2/3: Kiểm thử end-to-end viết trước implementation, RED không tải 10 câu; GREEN VerifyBaiThi (chọn/xóa/lưu lỗi/đổi câu/resume/timeout/kết quả/readonly). Maven clean test đạt.
- NetBeans: bộ đọc model/layout native đọc đúng 5 form; vùng initComponents/variables không đổi. VerifyAppLayout đạt sau khi cập nhật công cụ cũ đã tham chiếu nút btnLamBai mà người dùng yêu cầu bỏ trước đó. PreviewAllForms cập nhật bỏ qua control ẩn, preview 4 form đạt.
- Final review: reviewer độc lập chỉ ra 2 lỗi Important. Đã sửa cả hai: test race SQL RED tạo 3/10 câu → GREEN giữ 10/10 khi xóa BODE; test Swing RED EDT chậm cộng dư thời gian → GREEN trừ khoảng chờ. Kiểm thử nộp đồng thời đạt: 1 điểm, 10 dòng chi tiết, 0 trạng thái.
- Kết thúc: Maven clean test và Swing đạt với source cuối cùng; vùng Generated Code vẫn nguyên vẹn. Xóa database fixture THITRACNGHIEM_VERIFY sau kiểm thử, giữ source SQL và công cụ để chạy lại.
