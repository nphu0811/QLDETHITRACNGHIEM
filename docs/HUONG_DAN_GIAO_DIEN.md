# Giao diện thi trắc nghiệm theo đề

Đã đọc toàn bộ `C:/Users/admin/Downloads/De2-ThiTN.doc`, gồm phần yêu cầu và cấu trúc bảy bảng. Bộ giao diện gồm 18 JFrame: 16 form mới và hai form đăng nhập đã có. Mỗi form có `.java` và `.form` để tiếp tục chỉnh trong NetBeans Design.

Chỉ dựng giao diện. Các handler của nút nghiệp vụ mới để trống, có TODO để bạn viết sau. Không triển khai xác thực, phân quyền, SQL, kiểm tra ràng buộc dữ liệu, thêm/xóa/sửa/ghi, đăng ký thi thật, chọn câu ngẫu nhiên, chấm điểm, đồng hồ đếm ngược, in hoặc sao lưu/phục hồi thật. Các thao tác chuyển giữa hai form đăng nhập và thoát vốn có vẫn giữ nguyên.

## Mở và chạy từng form

1. Trong NetBeans, mở **Source Packages** rồi chọn package theo danh sách dưới đây.
2. Nhấp đúp file `.java`, chọn **Design** để chỉnh giao diện. Giữ nguyên file `.form` đi kèm; không sửa trực tiếp vùng mã được khóa.
3. Nhấp phải file `.java` → **Run File** hoặc **Shift + F6** để xem riêng màn hình đó. Mỗi form có `main()` độc lập.
4. Nếu NetBeans đang mở file trước khi nó được cập nhật, đóng/mở lại tab và chọn **Reload** khi được hỏi.
5. Các nút mới chưa chuyển màn hình hoặc thực hiện tác vụ. Để xem một màn hình khác, dùng **Run File** của màn hình đó.

Không chuyển tới trang chủ bằng đăng nhập giả. Trang chủ hiện toàn bộ nhóm chức năng để bạn xem và chỉnh thiết kế; chưa có kiểm tra hoặc ẩn/hiện theo vai trò.

## Package và các form

Đường dẫn gốc: `src/main/java`.

| Package | Form | Công dụng giao diện |
| --- | --- | --- |
| `dangNhapdangKy` | `DangNhap` | Tài khoản và mật khẩu giáo viên, chuyển sang sinh viên, thoát |
| `dangNhapdangKy` | `DangNhapSV` | Chỉ nhập mã sinh viên, chuyển sang giáo viên, thoát |
| `trangchu` | `TrangChu` | Một trang chủ chung; nhóm quản lý, thi/kết quả, người dùng/hệ thống |
| `quanly` | `NhapMonHoc` | Mã, tên môn học; danh sách, tìm kiếm và thanh thao tác |
| `quanly` | `NhapLopSinhVien` | Lớp bên trái; subform thông tin và danh sách sinh viên bên phải |
| `quanly` | `NhapGiaoVien` | Mã GV, họ, tên, điện thoại, địa chỉ; danh sách và thao tác |
| `quanly` | `NhapBoDe` | Môn, trình độ, mã GV, số câu, nội dung, A/B/C/D, đáp án; danh sách câu hỏi |
| `quanly` | `ChuanBiThi` | Đăng ký lớp, môn, trình độ, lần, ngày, số câu, thời gian; khu vực kiểm tra số câu |
| `thi` | `ChonCaThi` | Thông tin sinh viên/lớp; chọn môn, ngày, lần; thông tin ca thi; Bắt đầu thi |
| `thi` | `LamBaiThi` | Thông tin bài thi, đồng hồ hiển thị, nội dung câu, bốn lựa chọn, danh sách câu, điều hướng, nộp bài |
| `thi` | `KetQuaThi` | Điểm trên thang 10, số câu đúng/tổng số câu và thông tin bài thi |
| `baocao` | `XemKetQua` | Tra cứu bài đã thi theo login/mã SV, lớp, môn, trình độ, ngày, lần |
| `baocao` | `InBaiThi` | Bản xem trước phiếu bài thi theo các thông tin và cột trong đề |
| `baocao` | `BangDiemMonHoc` | Chọn lớp, môn, lần; danh sách điểm thi hết môn |
| `baocao` | `InBangDiem` | Bản xem trước bảng điểm có tiêu đề học viện, thông tin kỳ thi và phần ký tên |
| `taikhoan` | `TaoTaiKhoan` | Chọn giáo viên; login, mật khẩu, xác nhận; danh sách tài khoản giáo viên |
| `taikhoan` | `DoiMatKhau` | Tài khoản giáo viên, mật khẩu hiện tại/mới/xác nhận |
| `hethong` | `SaoLuuPhucHoi` | Máy chủ, database, tệp .bak, nút chọn đường dẫn, sao lưu/phục hồi, trạng thái và nhật ký |

`connectionSQLServer` và file logo `src/main/resources/ptit-logo.png` giữ nguyên vị trí. Các form mới dùng logo này làm biểu tượng cửa sổ. Các package trên dành cho giao diện; bạn có thể tạo riêng `model`, `dao`, `service` khi bắt đầu làm nghiệp vụ, nhưng đợt này chưa tạo lớp hoặc mã nghiệp vụ.

## Đối chiếu yêu cầu trong đề

| Mục trong đề | Form đáp ứng | Chi tiết đã có trên giao diện |
| --- | --- | --- |
| 1. Đăng nhập và đăng ký trước khi sử dụng | `DangNhap`, `DangNhapSV`, `NhapLopSinhVien`, `TaoTaiKhoan` | Giáo viên dùng login/mật khẩu; sinh viên chỉ dùng MASV do giảng viên nhập trong quản lý sinh viên; tạo tài khoản giáo viên từ trang chủ |
| 2. Nhập môn học | `NhapMonHoc` | Thêm, Xóa, Hiệu chỉnh, Phục hồi, Tìm, Ghi; mã/tên môn và bảng danh sách |
| 3. Nhập lớp và sinh viên dạng subform | `NhapLopSinhVien` | Hai khu vực master/detail trên cùng form; mỗi khu vực đủ Thêm, Xóa, Hiệu chỉnh, Phục hồi, Tìm, Ghi |
| 4. Nhập giáo viên | `NhapGiaoVien` | Thêm, Xóa, Hiệu chỉnh, Tìm, Ghi; đầy đủ các trường giáo viên |
| 5. Nhập đề / Bo_de | `NhapBoDe` | Câu số, môn, trình độ A/B/C, nội dung, phương án A/B/C/D, đáp án đúng, mã giáo viên |
| 6. Chuẩn bị thi / GiaoVien_DangKy | `ChuanBiThi` | Giáo viên, lớp, môn, trình độ, lần, số câu, ngày, thời gian; ô số câu sẵn có và nút kiểm tra đủ câu |
| 7. Thi | `ChonCaThi`, `LamBaiThi`, `KetQuaThi` | Mã/tên lớp, sinh viên; môn/ngày/lần; số câu/thời gian/trình độ; Bắt đầu; chọn và xem lại câu; nộp; thông báo điểm |
| Lưu ý của phần thi | `LamBaiThi`, `KetQuaThi`, `ChonCaThi`, `XemKetQua` | Có đồng hồ hiển thị, bốn lựa chọn độc quyền qua ButtonGroup, danh sách câu và nút chọn lại/xem bài lần trước; điểm trên 10. Chưa xử lý thuật toán hoặc thời gian |
| 7. Xem kết quả | `XemKetQua`, `InBaiThi` | Bộ lọc lớp/môn/trình độ/login; lớp, họ tên, mã số, môn, ngày, lần; cột Câu số trong bộ đề, Đã chọn, Đáp án |
| 8. Bảng điểm môn học | `BangDiemMonHoc`, `InBangDiem` | Chọn lớp/môn/lần; danh sách sinh viên và điểm; nút xem bản in và in |
| Phân quyền hai loại user | `TrangChu` | Có đủ nhóm chức năng để viết phân quyền sau. Không triển khai hoặc kiểm tra quyền theo yêu cầu của bạn |
| 9. Tạo tài khoản giáo viên | `TaoTaiKhoan` | Chọn giáo viên, login, mật khẩu, xác nhận; bảng tài khoản không hiển thị mật khẩu |
| Backup & Restore Database | `SaoLuuPhucHoi` | Đủ hai khu vực sao lưu và phục hồi, chọn tệp và theo dõi trạng thái |
| Đổi mật mã cho giáo viên có tài khoản | `DoiMatKhau` | Combo tài khoản giáo viên và ba ô mật khẩu; chưa truy vấn danh sách hoặc kiểm tra điều kiện |

## Đối chiếu trường dữ liệu

| Bảng trong đề | Trường | Form |
| --- | --- | --- |
| `Lop` | MALOP, TENLOP | `NhapLopSinhVien` |
| `Monhoc` | MAMH, TENMH | `NhapMonHoc` |
| `Sinhvien` | MASV, HO, TEN, NGAYSINH, DIACHI, MALOP | `NhapLopSinhVien` |
| `Giaovien` | MAGV, HO, TEN, SODTLL, DIACHI | `NhapGiaoVien` |
| `Giaovien_Dangky` | MAGV, MALOP, MAMH, TRINHDO, NGAYTHI, LAN, SOCAUTHI, THOIGIAN | `ChuanBiThi` |
| `BODE` | MAMH, CAUHOI, TRINHDO, NOIDUNG, A, B, C, D, DAP_AN, MAGV | `NhapBoDe` |
| `BangDiem` | MASV, MAMH, LAN, NGAYTHI, DIEM, BAITHI | `KetQuaThi`, `XemKetQua`, `BangDiemMonHoc`; chi tiết BAITHI nằm trong bảng xem bài |

Các tên và tooltip ghi giới hạn ở những trường cần thiết. Combo trình độ có A/B/C và combo lần thi có 1/2. Số câu 10–100 và thời gian 5–60 phút được thể hiện ngay trên nhãn. Chưa viết kiểm tra độ dài, khóa chính/ngoại, uniqueness hoặc giá trị nhập.

## Các điểm của đề cần quyết định khi làm nghiệp vụ

- Theo yêu cầu của bạn, sinh viên không tự đăng ký. Giảng viên nhập sinh viên trong `NhapLopSinhVien`; sinh viên dùng MASV đã được nhập để đăng nhập. Không có form đăng ký sinh viên hoặc nút đăng ký ở hai form đăng nhập. Chức năng tạo tài khoản giáo viên vẫn nằm trên trang chủ. Chưa triển khai xác thực.
- Đề yêu cầu mẫu bảng điểm giống của trường nhưng không cung cấp mẫu cụ thể. `InBangDiem` hiện là bố cục bảng điểm học viện có đủ thông tin và chữ ký. Có thể chỉnh bố cục khi có mẫu thật.
- Tỷ lệ chọn câu 7/3 theo trình độ, không trùng câu, điểm bằng nhau, tự kết thúc khi hết giờ và ghi BangDiem là nghiệp vụ cần làm sau, không phải chức năng đã được triển khai.

## Viết code tiếp theo

Nhấp đúp nút trong Design để mở handler `<tên nút>ActionPerformed`. Viết code trong phần TODO, không chỉnh `initComponents()` hoặc khối khai báo biến bị khóa. Các bảng hiện không có bản ghi mẫu; các combobox môn/lớp/giáo viên hiện chỉ có dòng gợi ý để bạn gắn dữ liệu sau.

`tools/generate_ui_forms.py` là công cụ đã dùng để dựng các form mới lần đầu. Không chạy lại nó sau khi bạn chỉnh form, vì nó sẽ ghi lại 16 form mới. `tools/check_ui_forms.py` chỉ đọc và kiểm tra tính đồng bộ, không ghi đè file.
