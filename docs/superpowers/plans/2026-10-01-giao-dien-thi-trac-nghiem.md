# Kế hoạch tạo giao diện thi trắc nghiệm

Mục tiêu: tạo đủ giao diện theo De2-ThiTN.doc trong dự án Swing hiện có; chỉ dựng giao diện, không triển khai nghiệp vụ hoặc phân quyền.

Kiến trúc: mỗi màn hình là một JFrame độc lập, có nguồn Java và metadata NetBeans .form đồng bộ. Dùng GroupLayout với nhóm gốc song song ở cả hai chiều; không thêm thư viện. Giữ hai form đăng nhập và logo PTIT hiện tại.

Các bước:

- [x] Tạo trang chủ chung, các form quản lý môn học, lớp/sinh viên dạng master-detail, giáo viên, bộ đề và chuẩn bị thi.
- [x] Tạo chọn ca thi, làm bài, thông báo kết quả; xem lại bài và hai mẫu báo cáo in.
- [x] Tạo tài khoản giáo viên, đổi mật khẩu, sao lưu/phục hồi. Theo cập nhật của người dùng: bỏ đăng ký sinh viên và các nút đăng ký trong đăng nhập; sử dụng MASV do giảng viên nhập.
- [x] Lập bảng đối chiếu tất cả yêu cầu và trường dữ liệu với form, ghi rõ chỗ viết nghiệp vụ sau này.
- [x] Biên dịch, đọc mọi bố cục bằng parser NetBeans cài trên máy, kiểm tra model/component, dựng ảnh của từng màn hình để kiểm tra nội dung và kích thước.

Giới hạn: nút nghiệp vụ có handler trống. Không truy cập SQL, xác thực, ẩn/hiện theo quyền, ghi/sửa/xóa dữ liệu, chọn câu ngẫu nhiên, chạy đồng hồ, chấm điểm, in thật hoặc backup/restore thật. Bảng dữ liệu không có bản ghi giả.

Kiểm tra đặc biệt: đủ nút Phục hồi ở môn học và lớp/sinh viên; đủ A/B/C/D và đáp án trong bộ đề; đủ thông tin thi và xem lại bài; không thiếu tạo tài khoản, đổi mật khẩu và backup/restore; mỗi thành phần GroupLayout xuất hiện đúng một lần ở mỗi chiều.

Kết quả kiểm tra: Maven compile thành công với JDK release 21; đủ 18 cặp Java/form; parser bố cục NetBeans giữ nguyên cây bố cục; 31 model bảng/combobox đọc được bằng editor NetBeans; kiểm tra kích thước, không chồng thành phần/không cắt chữ/không cắt logo và ảnh dựng của tất cả form. Chưa kiểm tra trực tiếp trong cửa sổ Design vì kết nối điều khiển Windows không khả dụng.
