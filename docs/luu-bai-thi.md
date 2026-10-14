# Luồng thi và lưu tiến độ

Đã áp dụng `sql/migrations/20261010-luu-bai-thi.sql` vào SQL Server local, database `THITRACNGHIEM`.
Đã áp dụng thêm `sql/migrations/20261010-bang-diem-sinh-vien.sql` và kiểm tra tài khoản `sv` đọc được điểm. Script này không ghi hoặc sửa dữ liệu bài thi.

## Các file chính

- `session/CauHoi.java`: một câu hỏi; giữ các getter/setter bạn đã viết.
- `session/CaThi.java`: thông số của ca thi.
- `session/BaiThi.java`: cả bài thi, danh sách câu, vị trí đang xem, thời gian còn lại, kết quả.
- `session/DiemThi.java`: thông tin một dòng bảng điểm: mã/tên môn, ngày thi, lần thi, điểm.
- `connectionSQLServer/ThiDAO.java`: gọi SP bằng JDBC; mở và đóng kết nối trong mỗi lần gọi.
- `thi/LamBaiThi.java`: hiển thị câu, nhận lựa chọn, gọi lưu tiến độ, đồng hồ, nộp.
- `trangchu/TrangChu.java`: kiểm tra bài đang làm khi đăng nhập lại; có bài dở thì đổi nút “Chọn ca thi” thành “Làm bài tiếp”. Bấm nút mới mở lại bài.
- `thi/KetQuaThi.java`, `baocao/XemKetQua.java`: hiện điểm và chi tiết. Mở từ TrangChu thì lấy bài hoàn thành gần nhất của chính sinh viên.
- `baocao/BangDiemSinhVien.java` và `.form`: bảng điểm riêng cho sinh viên; chỉ đọc và có nút xem chi tiết bài được chọn.

## Bảng điểm sinh viên

Nút Bảng điểm môn học trên TrangChu mở `BangDiemSinhVien` nếu đang đăng nhập sinh viên; giảng viên vẫn mở `BangDiemMonHoc`.

Form lấy MASV từ session và gọi `ThiDAO.layBangDiem`, thông qua `SP_LayBangDiemSinhVien`. SP chỉ đọc các dòng BANGDIEM của mã sinh viên đó, nối MONHOC để lấy tên môn. Bài đang dở không xuất hiện trong bảng điểm.

Chọn dòng rồi bấm Xem chi tiết bài làm. Handler đổi chỉ số dòng bằng `convertRowIndexToModel` trước khi lấy MAMH và LAN, nên vẫn chọn đúng bài sau khi sắp xếp bảng. `ThiDAO.xem(MASV, MAMH, LAN)` đọc bài tương ứng qua SP_DocBaiThi; MASV luôn lấy từ session. XemKetQua nhận bài này và cờ `veBangDiem = true`, để nút Trở về quay lại bảng điểm sinh viên. Các luồng xem chi tiết cũ vẫn về TrangChu.

Script SP đọc điểm: `sql/migrations/20261010-bang-diem-sinh-vien.sql`. Không thêm bảng hoặc ghi/sửa điểm khi xem. Bảng điểm và chi tiết bài làm đều không cho sửa ô.

Form bảng điểm sinh viên có `.java` và `.form` đồng bộ để chỉnh Design trong NetBeans; logic nằm ngoài vùng `Generated Code`. Khi thêm bảng điểm sinh viên, vùng sinh mã và `.form` của các form cũ được giữ nguyên. Nếu đang mở source trước khi Codex sửa, hãy nạp lại file từ đĩa để tránh ghi đè bản mới.

## Bảng và stored procedure

`TRANGTHAITHI` có một dòng cho mỗi câu của bài đang làm. Khóa bài gồm `MASV + MAMH + LAN`; `THUTU` giữ thứ tự câu. Mỗi dòng lưu cả nội dung/phương án/đáp án đúng tại lúc bắt đầu, đáp án sinh viên, `CAUDANGXEM`, `BATDAU`, `HANKETTHUC`.

- `SP_MoBaiThi`: có trạng thái thì mở lại đề cũ; chưa có thì kiểm tra ca của lớp và đúng ngày, chọn đề ngẫu nhiên, lưu trạng thái trước khi trả về Java. Có bài khác đang làm thì tiếp tục bài đó. Bài quá hạn được nộp ngay khi mở lại.
- `SP_DocBaiThi`: đọc bài đang làm hoặc bài đã nộp. Không truyền môn/lần thì đọc kết quả hoàn thành gần nhất. Đáp án đúng chỉ được trả về sau khi đã nộp.
- `SP_LuuTienDoThi`: lưu lựa chọn và câu đang xem. `NULL` là chỉ đổi vị trí; chuỗi rỗng là xóa lựa chọn. SQL từ chối lựa chọn sau hạn giờ.
- `SP_NopBaiThi`: chấm điểm, ghi `BANGDIEM`, sao chép đầy đủ sang `CHITIETBAITHI`, xóa trạng thái trong cùng transaction. Gọi nộp lại trả kết quả cũ.
- `SP_LayBaiThiDangDo`: tìm bài đang làm của sinh viên.
- `SP_LayBangDiemSinhVien`: danh sách bài đã hoàn thành của sinh viên, đọc từ BANGDIEM và MONHOC.
- `SP_LayCaThiSinhVien`: lọc môn/ngày/lần độc lập; ẩn ca quá ngày và bài đã nộp; vẫn giữ bài đang làm dù qua ngày.

Trình độ A lấy 70% A và 30% trong B/C; B lấy 70% B và 30% C; C lấy toàn bộ C. Số câu cùng trình độ làm tròn đến số nguyên, phần còn lại là câu thấp hơn. Không đủ câu thì báo lỗi, không tự đổi tỷ lệ. Không loại câu từng có ở lần thi trước.

## Thời gian và mất kết nối

SQL dùng giờ UTC cho bắt đầu/hạn giờ và UTC+7 cho ngày thi Việt Nam. Điều kiện bắt đầu là đúng **ngày** đã đăng ký, chưa áp dụng giờ cụ thể trong NGAYTHI. Java kiểm tra ngày nhập có hợp lệ và hiển thị đồng hồ từ số millis SQL trả về; không tự quyết định ngày được thi.

Mỗi lựa chọn được lưu ở nền. Sau khi có phản hồi thành công, nhãn hiện “Đã lưu tiến độ”. Nếu lưu lỗi, giao diện khôi phục đáp án được xác nhận trước đó và báo thử lại. Chỉ những lựa chọn đã lưu thành công mới được bảo đảm sau cúp điện.

Đóng app không dừng thời gian. Đăng nhập lại ở TrangChu; bấm “Làm bài tiếp” để mở bài đang làm, giữ đề, lựa chọn, vị trí, hạn giờ. Nếu app đã đóng khi hết giờ, bài được chấm và chuyển sang kết quả khi bấm mở lại. Muốn ghi BANGDIEM đúng lúc hết giờ khi không có app nào chạy thì cần một tiến trình trên server; chưa thêm phần đó.

Nút X vẫn thoát app. Nút Trở về trong form thi về TrangChu và giữ trạng thái; TrangChu hiện nút “Làm bài tiếp” để mở lại bài đó. Không có bài dở thì giữ nút “Chọn ca thi”. Nộp lỗi thì bài bị khóa lựa chọn và cho bấm Nộp bài để thử lại.

## Kiểm tra

Đã kiểm tra SQL bằng `sql/tests/verify-luu-bai-thi.sql` và Swing bằng `tools/VerifyBaiThi.java` trên database riêng `THITRACNGHIEM_VERIFY`; không dùng sinh viên thật để ghi điểm thử. `tools/verify_bai_thi_snapshot.py` kiểm tra xóa bộ đề trong lúc tạo bài và hai lần nộp đồng thời. Swing kiểm tra cả lưu lỗi và giao diện chậm; đồng hồ không cộng thêm khoảng chờ SQL/giao diện.

Fixture SQL: tạo database kiểm thử, chạy `sql/tests/tao-fixture-bai-thi.sql`, migration, rồi test SQL. Chạy Java với `-Dthitracnghiem.database=THITRACNGHIEM_VERIFY`. Thuộc tính này chỉ đổi database của ThiDAO; các form cũ vẫn dùng cấu hình trong connection.java.

`tools/VerifyBangDiemSinhVien.java` kiểm tra bảng điểm bằng SQL và Swing thật: đúng sinh viên, loại bài dở, không sửa ô, xem đúng môn/lần sau khi sắp xếp, quay về bảng điểm, trạng thái chưa thi và mở form Design không có session. Chạy thêm migration bảng điểm trước bài kiểm tra này.

Database kiểm thử đã được xóa sau khi hoàn tất; muốn chạy lại kiểm thử thì tạo lại database đó trước. Database ứng dụng không có điểm/trạng thái/chi tiết do kiểm thử tạo ra.

Bản sao source trước khi sửa và SP_LayCaThiSinhVien cũ lưu ngoài `target` trong `.codex-backups/bai-thi-20261010/original`.
