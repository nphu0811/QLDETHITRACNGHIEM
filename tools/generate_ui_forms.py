"""Build the initial UI-only Swing forms and their NetBeans metadata.

Generated Java files are self-contained and editable in NetBeans Design.
Do not rerun this script after manually changing the generated forms.
"""
from pathlib import Path
import json
import xml.etree.ElementTree as ET

ROOT = Path(__file__).resolve().parents[1]
BASE = ROOT / 'src/main/java'
WHITE = (255, 255, 255)
RED = (205, 32, 45)
INK = (36, 43, 54)
GRAY = (107, 114, 128)
PALE = (245, 246, 248)


def quote(value):
    return json.dumps(value, ensure_ascii=False)


class Component:
    def __init__(self, name, cls, **props):
        self.name, self.cls = name, 'javax.swing.' + cls
        self.props = props
        self.rows = []
        self.child = None
        self.columns = None
        self.options = None
        self.event = False


def label(name, text, size=14, bold=False, color=INK):
    return Component(name, 'JLabel', text=text, font=(size, int(bold)), foreground=color)


def panel(name, rows):
    p = Component(name, 'JPanel', background=WHITE)
    p.rows = rows
    return p


def row(*components, height=None, grow=False, gap=8):
    return {'components': list(components), 'height': height, 'grow': grow, 'gap': gap}


def button(name, text, primary=False):
    c = Component(name, 'JButton', text=text, font=(14, int(primary)),
                  background=RED if primary else PALE, foreground=WHITE if primary else INK)
    c.props.update(opaque=True, contentAreaFilled=True, focusPainted=False)
    c.event = True
    return c


def field(name, caption, kind='text', options=None, readonly=False, tip=None, value=None):
    title = label('lbl' + name[0].upper() + name[1:], caption, 13, True)
    cls = {'text': 'JTextField', 'password': 'JPasswordField', 'combo': 'JComboBox',
           'date': 'JFormattedTextField'}[kind]
    c = Component(name, cls, font=(14, 0), foreground=INK, background=WHITE)
    if cls in ('JTextField', 'JPasswordField', 'JFormattedTextField'):
        c.props['editable'] = not readonly
    if options is not None:
        c.options = options
    if tip:
        c.props['toolTipText'] = tip
    if value is not None:
        c.props['text'] = value
    return panel('pnl' + name[0].upper() + name[1:], [row(title), row(c, height=36)])


def area(name, caption, height=78, readonly=False):
    txt = Component(name, 'JTextArea', font=(14, 0), lineWrap=True, wrapStyleWord=True,
                    rows=3, columns=24, editable=not readonly)
    scroll = Component('scr' + name[0].upper() + name[1:], 'JScrollPane')
    scroll.child = txt
    return panel('pnl' + name[0].upper() + name[1:], [
        row(label('lbl' + name[0].upper() + name[1:], caption, 13, True)),
        row(scroll, height=height, grow=True)])


def table(name, columns):
    t = Component(name, 'JTable', font=(13, 0), rowHeight=28, autoCreateRowSorter=True,
                  fillsViewportHeight=True)
    t.columns = columns
    s = Component('scr' + name[0].upper() + name[1:], 'JScrollPane')
    s.child = t
    return s


def actions(prefix='', restore=True):
    names = [('Them', 'Thêm'), ('Xoa', 'Xóa'), ('HieuChinh', 'Hiệu chỉnh')]
    if restore:
        names.append(('PhucHoi', 'Phục hồi'))
    names.extend([('Tim', 'Tìm'), ('Ghi', 'Ghi')])
    return row(*(button('btn' + prefix + n, txt, n == 'Ghi') for n, txt in names), height=38)


def section(name, text):
    return row(label(name, text, 16, True, RED))


FORMS = []


def form(package, name, title, subtitle, rows, width=1040):
    FORMS.append((package, name, title, subtitle, rows, width))


form('trangchu', 'TrangChu', 'TRANG CHỦ', 'Hệ thống thi trắc nghiệm · Học viện Công nghệ Bưu chính Viễn thông', [
    row(field('txtMaNguoiDung', 'Mã người dùng', readonly=True), field('txtHoTen', 'Họ tên', readonly=True)),
    section('lblQuanLy', 'Quản lý và chuẩn bị thi'),
    row(button('btnMonHoc', 'Môn học'), button('btnLopSinhVien', 'Lớp và sinh viên'), button('btnGiaoVien', 'Giáo viên'), height=46),
    row(button('btnBoDe', 'Bộ đề trắc nghiệm'), button('btnChuanBiThi', 'Chuẩn bị thi'), height=46),
    section('lblThi', 'Thi và kết quả'),
    row(button('btnChonCaThi', 'Chọn ca thi'), button('btnLamBai', 'Làm bài thi'), button('btnKetQua', 'Kết quả thi'), height=46),
    row(button('btnXemBaiThi', 'Xem lại bài thi'), button('btnBangDiem', 'Bảng điểm môn học'), height=46),
    section('lblTaiKhoan', 'Người dùng và hệ thống'),
    row(button('btnTaoTaiKhoan', 'Tạo tài khoản giáo viên'), button('btnDoiMatKhau', 'Đổi mật khẩu'), height=46),
    row(button('btnSaoLuuPhucHoi', 'Sao lưu / Phục hồi'), button('btnDangXuat', 'Đăng xuất'), button('btnThoat', 'Thoát'), height=42),
])

form('quanly', 'NhapMonHoc', 'QUẢN LÝ MÔN HỌC', 'Danh mục môn học dùng trong bộ đề và các kỳ thi', [
    row(field('txtMaMH', 'Mã môn học', tip='MAMH · tối đa 5 ký tự'), field('txtTenMH', 'Tên môn học', tip='TENMH · tối đa 40 ký tự')),
    row(field('txtTuKhoa', 'Tìm môn học'), button('btnTimKiem', 'Tìm kiếm'), height=62),
    actions(), section('lblDanhSach', 'Danh sách môn học'),
    row(table('tblMonHoc', ['Mã môn học', 'Tên môn học']), height=270, grow=True),
])

lop = panel('pnlLop', [section('lblLop', 'Danh sách lớp'),
    row(field('txtMaLop', 'Mã lớp', tip='MALOP · tối đa 8 ký tự'), field('txtTenLop', 'Tên lớp', tip='TENLOP · tối đa 40 ký tự')),
    row(field('txtTimLop', 'Tìm lớp')),
    actions('Lop'), row(table('tblLop', ['Mã lớp', 'Tên lớp']), height=240, grow=True)])
sv = panel('pnlSinhVien', [section('lblSinhVien', 'Sinh viên của lớp'),
    row(field('txtMaSV', 'Mã sinh viên', tip='MASV · tối đa 8 ký tự'), field('txtLopSV', 'Mã lớp', readonly=True)),
    row(field('txtHoSV', 'Họ', tip='HO · tối đa 40 ký tự'), field('txtTenSV', 'Tên', tip='TEN · tối đa 10 ký tự')),
    row(field('txtNgaySinh', 'Ngày sinh', 'date', tip='dd/MM/yyyy'), field('txtDiaChiSV', 'Địa chỉ', tip='DIACHI · tối đa 40 ký tự')),
    row(field('txtTimSV', 'Tìm sinh viên')),
    actions('SV'), row(table('tblSinhVien', ['Mã SV', 'Họ', 'Tên', 'Ngày sinh', 'Địa chỉ', 'Mã lớp']), height=160, grow=True)])
form('quanly', 'NhapLopSinhVien', 'QUẢN LÝ LỚP VÀ SINH VIÊN', 'Thông tin lớp học và danh sách sinh viên của lớp', [row(lop, sv)], 1160)

form('quanly', 'NhapGiaoVien', 'QUẢN LÝ GIÁO VIÊN', 'Thông tin giáo viên tham gia biên soạn và tổ chức thi', [
    row(field('txtMaGV', 'Mã giáo viên', tip='MAGV · tối đa 8 ký tự'), field('txtHoGV', 'Họ'), field('txtTenGV', 'Tên')),
    row(field('txtSoDT', 'Số điện thoại liên lạc', tip='SODTLL · tối đa 12 ký tự'), field('txtDiaChiGV', 'Địa chỉ', tip='DIACHI · tối đa 50 ký tự')),
    row(field('txtTuKhoa', 'Tìm giáo viên')),
    actions(restore=False), row(table('tblGiaoVien', ['Mã GV', 'Họ', 'Tên', 'Số điện thoại', 'Địa chỉ']), height=245, grow=True),
])

form('quanly', 'NhapBoDe', 'BỘ ĐỀ TRẮC NGHIỆM', 'Biên soạn câu hỏi theo môn học và trình độ', [
    row(field('cboMonHoc', 'Môn học', 'combo', ['Chọn môn học']), field('cboTrinhDo', 'Trình độ', 'combo', ['A', 'B', 'C']), field('txtMaGV', 'Mã giáo viên')),
    row(field('txtCauHoi', 'Số câu trong bộ đề', readonly=True), field('cboDapAn', 'Đáp án đúng', 'combo', ['A', 'B', 'C', 'D']), field('txtTuKhoa', 'Tìm câu hỏi')),
    row(area('txtNoiDung', 'Nội dung câu hỏi (tối đa 200 ký tự)', 70)),
    row(field('txtPhuongAnA', 'Phương án A'), field('txtPhuongAnB', 'Phương án B')),
    row(field('txtPhuongAnC', 'Phương án C'), field('txtPhuongAnD', 'Phương án D')),
    actions(), row(table('tblBoDe', ['Câu số', 'Môn học', 'Trình độ', 'Nội dung', 'A', 'B', 'C', 'D', 'Đáp án', 'Mã GV']), height=140, grow=True),
    row(label('lblTrinhDo', 'A: Đại học chuyên ngành · B: Đại học không chuyên ngành · C: Cao đẳng', 12, color=GRAY)),
])

form('quanly', 'ChuanBiThi', 'CHUẨN BỊ THI', 'Đăng ký môn thi và cấu hình bài thi cho lớp', [
    row(field('txtMaGV', 'Mã giáo viên'), field('cboLop', 'Lớp', 'combo', ['Chọn lớp']), field('cboMonHoc', 'Môn học', 'combo', ['Chọn môn học'])),
    row(field('cboTrinhDo', 'Trình độ', 'combo', ['A', 'B', 'C']), field('cboLanThi', 'Lần thi', 'combo', ['1', '2']), field('txtNgayThi', 'Ngày thi', 'date', tip='dd/MM/yyyy')),
    row(field('txtSoCauThi', 'Số câu thi (10–100)'), field('txtThoiGian', 'Thời gian thi (5–60 phút)'), field('txtSoCauSanCo', 'Số câu hỏi sẵn có', readonly=True)),
    row(button('btnKiemTraCauHoi', 'Kiểm tra đủ câu hỏi'), label('lblTrangThaiCauHoi', 'Chưa kiểm tra số câu hỏi', 13, color=GRAY), height=38),
    actions(), row(table('tblDangKyThi', ['Mã GV', 'Mã lớp', 'Môn học', 'Trình độ', 'Ngày thi', 'Lần thi', 'Số câu', 'Thời gian (phút)']), height=240, grow=True),
])

form('thi', 'ChonCaThi', 'CHỌN CA THI', 'Thông tin sinh viên và ca thi đã được chuẩn bị', [
    row(field('txtMaSV', 'Mã sinh viên', readonly=True), field('txtHoTen', 'Họ tên', readonly=True)),
    row(field('txtMaLop', 'Mã lớp', readonly=True), field('txtTenLop', 'Tên lớp', readonly=True)),
    row(field('cboMonHoc', 'Môn thi', 'combo', ['Chọn môn thi']), field('txtNgayThi', 'Ngày thi', 'date', tip='dd/MM/yyyy'), field('cboLanThi', 'Lần thi', 'combo', ['1', '2'])),
    row(field('txtTrinhDo', 'Trình độ', readonly=True), field('txtSoCauThi', 'Số câu thi', readonly=True), field('txtThoiGian', 'Thời gian (phút)', readonly=True)),
    row(table('tblCaThi', ['Môn thi', 'Ngày thi', 'Lần thi', 'Trình độ', 'Số câu', 'Thời gian']), height=180, grow=True),
    row(button('btnBatDauThi', 'Bắt đầu thi', True), button('btnXemBaiLanTruoc', 'Xem bài thi lần trước'), button('btnTroVe', 'Trở về'), height=44),
])

question = panel('pnlCauHoi', [
    row(label('lblSoCau', 'Câu — / —', 16, True), label('lblSoCauBoDe', 'Câu số trong bộ đề: —', 13, color=GRAY)),
    row(area('txtNoiDungCauHoi', 'Nội dung câu hỏi', 88, readonly=True)),
    row(Component('rdoA', 'JRadioButton', text='A. Phương án A', font=(14, 0), background=WHITE), height=34),
    row(Component('rdoB', 'JRadioButton', text='B. Phương án B', font=(14, 0), background=WHITE), height=34),
    row(Component('rdoC', 'JRadioButton', text='C. Phương án C', font=(14, 0), background=WHITE), height=34),
    row(Component('rdoD', 'JRadioButton', text='D. Phương án D', font=(14, 0), background=WHITE), height=34),
    row(button('btnCauTruoc', 'Câu trước'), button('btnCauSau', 'Câu tiếp'), button('btnXoaLuaChon', 'Bỏ chọn'), height=38),
])
question_list = panel('pnlDanhSachCau', [section('lblDanhSachCau', 'Danh sách câu hỏi'),
    row(table('tblCauThi', ['Câu', 'Đã chọn']), height=280, grow=True),
    row(label('lblDaTraLoi', 'Đã trả lời: — / —', 13, color=GRAY)),
    row(button('btnChonLaiCau', 'Chọn lại câu đã làm'), height=38)])
form('thi', 'LamBaiThi', 'LÀM BÀI THI', 'Bài thi trắc nghiệm', [
    row(field('txtMaSV', 'Mã sinh viên', readonly=True), field('txtHoTen', 'Họ tên', readonly=True), field('txtLop', 'Lớp', readonly=True)),
    row(field('txtMonThi', 'Môn thi', readonly=True), field('txtLanThi', 'Lần thi', readonly=True), field('txtTrinhDo', 'Trình độ', readonly=True)),
    row(label('lblThoiGianConLai', 'Thời gian còn lại: — : —', 18, True, RED), label('lblNgayThi', 'Ngày thi: —', 14, color=GRAY)),
    row(question, question_list),
    row(button('btnNopBai', 'Nộp bài', True), button('btnXemLai', 'Xem lại câu đã làm'), height=44),
], 1120)

form('thi', 'KetQuaThi', 'KẾT QUẢ THI', 'Thông báo kết quả sau khi hoàn thành bài thi', [
    row(label('lblDiem', '— / 10', 38, True, RED)),
    row(field('txtMaSV', 'Mã sinh viên', readonly=True), field('txtHoTen', 'Họ tên', readonly=True)),
    row(field('txtLop', 'Lớp', readonly=True), field('txtMonThi', 'Môn thi', readonly=True)),
    row(field('txtNgayThi', 'Ngày thi', readonly=True), field('txtLanThi', 'Lần thi', readonly=True)),
    row(field('txtSoCauDung', 'Số câu đúng', readonly=True), field('txtTongSoCau', 'Tổng số câu', readonly=True)),
    row(button('btnXemChiTiet', 'Xem chi tiết bài thi', True), button('btnVeTrangChu', 'Về trang chủ'), height=44),
], 700)

form('baocao', 'XemKetQua', 'XEM LẠI BÀI THI', 'Tra cứu các câu đã thi và đối chiếu đáp án', [
    row(field('txtLogin', 'Login / mã sinh viên'), field('cboLop', 'Lớp', 'combo', ['Chọn lớp']), field('txtHoTen', 'Họ tên', readonly=True)),
    row(field('cboMonHoc', 'Môn thi', 'combo', ['Chọn môn thi']), field('cboTrinhDo', 'Trình độ', 'combo', ['A', 'B', 'C']), field('cboLanThi', 'Lần thi', 'combo', ['1', '2']), field('txtNgayThi', 'Ngày thi', 'date', tip='dd/MM/yyyy')),
    row(button('btnTraCuu', 'Tra cứu', True), button('btnBaiLanTruoc', 'Bài thi lần trước'), button('btnXemBanIn', 'Xem bản in'), button('btnIn', 'In bài thi'), height=40),
    row(table('tblChiTietBaiThi', ['Câu số (trong bộ đề)', 'Nội dung câu hỏi', 'Đã chọn', 'Đáp án']), height=270, grow=True),
    row(field('txtDiem', 'Điểm', readonly=True), field('txtSoCauDung', 'Số câu đúng', readonly=True), field('txtTongSoCau', 'Tổng số câu', readonly=True)),
])

form('baocao', 'InBaiThi', 'PHIẾU KẾT QUẢ BÀI THI', 'Học viện Công nghệ Bưu chính Viễn thông', [
    row(field('txtLop', 'Lớp', readonly=True)),
    row(field('txtHoTen', 'Họ tên', readonly=True), field('txtMaSo', 'Mã số', readonly=True)),
    row(field('txtMonThi', 'Môn thi', readonly=True), field('txtTrinhDo', 'Trình độ', readonly=True)),
    row(field('txtNgayThi', 'Ngày thi', readonly=True), field('txtLanThi', 'Lần thi', readonly=True)),
    row(table('tblBanIn', ['Câu số (trong bộ đề)', 'Đã chọn', 'Đáp án']), height=200, grow=True),
    row(field('txtDiem', 'Điểm', readonly=True)),
    row(button('btnIn', 'In phiếu', True), button('btnDong', 'Đóng'), height=40),
], 850)

form('baocao', 'BangDiemMonHoc', 'BẢNG ĐIỂM MÔN HỌC', 'Tra cứu điểm thi hết môn của lớp', [
    row(field('cboLop', 'Lớp', 'combo', ['Chọn lớp']), field('cboMonHoc', 'Môn học', 'combo', ['Chọn môn học']), field('cboLanThi', 'Lần thi', 'combo', ['1', '2'])),
    row(button('btnXemBangDiem', 'Xem bảng điểm', True), button('btnXemBanIn', 'Xem bản in'), button('btnIn', 'In bảng điểm'), height=40),
    row(table('tblBangDiem', ['STT', 'Mã sinh viên', 'Họ', 'Tên', 'Ngày thi', 'Lần thi', 'Điểm']), height=350, grow=True),
    row(field('txtTenLop', 'Tên lớp', readonly=True), field('txtTenMonHoc', 'Tên môn học', readonly=True), field('txtSoSinhVien', 'Số sinh viên', readonly=True)),
])

form('baocao', 'InBangDiem', 'BẢNG ĐIỂM THI KẾT THÚC MÔN', 'HỌC VIỆN CÔNG NGHỆ BƯU CHÍNH VIỄN THÔNG', [
    row(field('txtLop', 'Lớp', readonly=True), field('txtMonHoc', 'Môn học', readonly=True)),
    row(field('txtLanThi', 'Lần thi', readonly=True), field('txtNgayThi', 'Ngày thi', readonly=True)),
    row(table('tblBanInBangDiem', ['STT', 'Mã sinh viên', 'Họ và tên', 'Ngày sinh', 'Điểm', 'Ghi chú']), height=330, grow=True),
    row(label('lblNgayLap', 'Ngày ........ tháng ........ năm ........', 13, color=GRAY)),
    row(label('lblNguoiLap', 'Người lập bảng', 14, True), label('lblGiaoVien', 'Giáo viên', 14, True)),
    row(label('lblKyNguoiLap', '(Ký, ghi rõ họ tên)', 12, color=GRAY), label('lblKyGiaoVien', '(Ký, ghi rõ họ tên)', 12, color=GRAY)),
    row(button('btnIn', 'In bảng điểm', True), button('btnDong', 'Đóng'), height=40),
], 920)

form('taikhoan', 'TaoTaiKhoan', 'TẠO TÀI KHOẢN GIÁO VIÊN', 'Đăng ký tài khoản sử dụng chương trình cho giáo viên', [
    row(field('cboGiaoVien', 'Giáo viên', 'combo', ['Chọn giáo viên']), field('txtMaGV', 'Mã giáo viên', readonly=True)),
    row(field('txtHoTen', 'Họ tên giáo viên', readonly=True), field('txtLogin', 'Tên đăng nhập')),
    row(field('txtMatKhau', 'Mật khẩu', 'password'), field('txtXacNhanMatKhau', 'Nhập lại mật khẩu', 'password')),
    row(button('btnTaoTaiKhoan', 'Tạo tài khoản', True), button('btnLamMoi', 'Làm mới'), button('btnHuy', 'Hủy'), height=42),
    row(table('tblTaiKhoan', ['Mã giáo viên', 'Họ tên', 'Tên đăng nhập']), height=200, grow=True),
], 850)

form('taikhoan', 'DoiMatKhau', 'ĐỔI MẬT KHẨU', 'Giáo viên đã được tạo tài khoản', [
    row(field('cboGiaoVien', 'Giáo viên / tên đăng nhập', 'combo', ['Chọn tài khoản giáo viên'])),
    row(field('txtMatKhauCu', 'Mật khẩu hiện tại', 'password')),
    row(field('txtMatKhauMoi', 'Mật khẩu mới', 'password')),
    row(field('txtXacNhanMatKhau', 'Nhập lại mật khẩu mới', 'password')),
    row(button('btnDoiMatKhau', 'Đổi mật khẩu', True), button('btnHuy', 'Hủy'), height=44),
], 650)

backup = panel('pnlSaoLuu', [section('lblSaoLuu', 'Sao lưu dữ liệu'),
    row(field('txtTepSaoLuu', 'Tệp sao lưu (.bak)'), button('btnChonTepSaoLuu', 'Chọn đường dẫn'), height=62),
    row(Component('chkGhiDeBanSaoLuu', 'JCheckBox', text='Ghi đè tệp sao lưu đã có', background=WHITE, font=(13, 0))),
    row(button('btnSaoLuu', 'Sao lưu', True), height=40),
    row(Component('prgSaoLuu', 'JProgressBar', stringPainted=True)),
    row(label('lblTrangThaiSaoLuu', 'Chưa thực hiện sao lưu', 13, color=GRAY))])
restore = panel('pnlPhucHoi', [section('lblPhucHoi', 'Phục hồi dữ liệu'),
    row(field('txtTepPhucHoi', 'Tệp phục hồi (.bak)'), button('btnChonTepPhucHoi', 'Chọn tệp'), height=62),
    row(Component('chkXacNhanPhucHoi', 'JCheckBox', text='Xác nhận thay thế dữ liệu hiện tại', background=WHITE, font=(13, 0))),
    row(button('btnPhucHoi', 'Phục hồi', True), height=40),
    row(Component('prgPhucHoi', 'JProgressBar', stringPainted=True)),
    row(label('lblTrangThaiPhucHoi', 'Chưa thực hiện phục hồi', 13, color=GRAY))])
form('hethong', 'SaoLuuPhucHoi', 'SAO LƯU VÀ PHỤC HỒI', 'Cơ sở dữ liệu TRAC_NGHIEM', [
    row(field('txtMayChu', 'Máy chủ SQL Server'), field('txtCoSoDuLieu', 'Cơ sở dữ liệu', readonly=True, value='TRAC_NGHIEM')),
    row(backup), row(restore),
    row(area('txtNhatKy', 'Nhật ký thực hiện', 80, readonly=True)),
], 950)


class Builder:
    def __init__(self, package, name, title, subtitle, body, width):
        self.package, self.name = package, name
        self.declarations, self.code, self.handlers = [], [], []
        self.seen = set()
        self.form = ET.Element('Form', version='1.9', maxVersion='1.9', type='org.netbeans.modules.form.forminfo.JFrameFormInfo')
        properties = ET.SubElement(self.form, 'Properties')
        ET.SubElement(properties, 'Property', name='defaultCloseOperation', type='int', value='2')
        ET.SubElement(properties, 'Property', name='title', type='java.lang.String', value='PTIT | ' + title)
        p = ET.SubElement(properties, 'Property', name='iconImage', type='java.awt.Image', editor='org.netbeans.modules.form.RADConnectionPropertyEditor')
        ET.SubElement(p, 'Connection', type='code', code='new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage()')
        synthetic = ET.SubElement(self.form, 'SyntheticProperties')
        ET.SubElement(synthetic, 'SyntheticProperty', name='formSizePolicy', type='int', value='1')
        ET.SubElement(synthetic, 'SyntheticProperty', name='generateCenter', type='boolean', value='false')
        aux = ET.SubElement(self.form, 'AuxValues')
        settings = {'autoResourcing': ('Integer', '0'), 'autoSetComponentName': ('Boolean', 'false'),
                    'generateFQN': ('Boolean', 'true'), 'generateMnemonicsCode': ('Boolean', 'false'),
                    'i18nAutoMode': ('Boolean', 'false'), 'layoutCodeTarget': ('Integer', '1'),
                    'listenerGenerationStyle': ('Integer', '3'), 'variablesLocal': ('Boolean', 'false'),
                    'variablesModifier': ('Integer', '2')}
        for key, (typ, value) in settings.items():
            ET.SubElement(aux, 'AuxValue', name='FormSettings_' + key, type='java.lang.' + typ, value=value)
        title_label = label('lblTieuDe', title, 24, True, RED)
        subtitle_label = label('lblMoTa', subtitle, 13, color=GRAY)
        card = panel('pnlNoiDung', [row(title_label), row(subtitle_label)] + body)
        card.props['border'] = (24, 24, 24, 24)
        self.layout(self.form, 'layout', None, [row(card)], width=width, outer=True)
        self.render(card, ET.SubElement(self.form, 'SubComponents'))
        self.code.extend(['        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);',
                          '        setTitle(' + quote('PTIT | ' + title) + ');',
                          '        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/ptit-logo.png")).getImage());',
                          '        pack();'])

    def property(self, node, name, value, variable):
        props = node.find('Properties')
        if props is None:
            props = ET.SubElement(node, 'Properties')
        typ = 'java.lang.String'
        if name in ('background', 'foreground'):
            typ, editor = 'java.awt.Color', 'org.netbeans.beaninfo.editors.ColorEditor'
            p = ET.SubElement(props, 'Property', name=name, type=typ, editor=editor)
            ET.SubElement(p, 'Color', red=f'{value[0]:x}', green=f'{value[1]:x}', blue=f'{value[2]:x}', type='rgb')
            expr = 'new java.awt.Color(' + ', '.join(map(str, value)) + ')'
        elif name == 'font':
            p = ET.SubElement(props, 'Property', name=name, type='java.awt.Font', editor='org.netbeans.beaninfo.editors.FontEditor')
            ET.SubElement(p, 'Font', name='Segoe UI', size=str(value[0]), style=str(value[1]))
            expr = f'new java.awt.Font("Segoe UI", {value[1]}, {value[0]})'
        elif name == 'border':
            p = ET.SubElement(props, 'Property', name=name, type='javax.swing.border.Border', editor='org.netbeans.modules.form.editors2.BorderEditor')
            b = ET.SubElement(p, 'Border', info='org.netbeans.modules.form.compat2.border.EmptyBorderInfo')
            ET.SubElement(b, 'EmptyBorder', top=str(value[0]), left=str(value[1]), bottom=str(value[2]), right=str(value[3]))
            expr = 'javax.swing.BorderFactory.createEmptyBorder(' + ', '.join(map(str, value)) + ')'
        else:
            if isinstance(value, bool):
                typ, expr = 'boolean', str(value).lower()
            elif isinstance(value, int):
                typ, expr = 'int', str(value)
            else:
                expr = quote(value)
            ET.SubElement(props, 'Property', name=name, type=typ, value=str(value).lower() if isinstance(value, bool) else str(value))
        self.code.append(f'        {variable}.set{name[0].upper() + name[1:]}({expr});')

    def render(self, c, parent):
        assert c.name not in self.seen, c.name
        self.seen.add(c.name)
        self.declarations.append((c.cls, c.name))
        is_container = bool(c.rows) or c.child is not None
        node = ET.SubElement(parent, 'Container' if is_container else 'Component', {'class': c.cls, 'name': c.name})
        self.code.append('        ' + c.name + ' = new ' + c.cls + '();')
        for prop, value in c.props.items():
            self.property(node, prop, value, c.name)
        if c.options is not None:
            props = node.find('Properties')
            p = ET.SubElement(props, 'Property', name='model', type='javax.swing.ComboBoxModel', editor='org.netbeans.modules.form.editors2.ComboBoxModelEditor')
            strings = ET.SubElement(p, 'StringArray', count=str(len(c.options)))
            for index, option in enumerate(c.options):
                ET.SubElement(strings, 'StringItem', index=str(index), value=option)
            self.code.append(f'        {c.name}.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {{' + ', '.join(quote(x) for x in c.options) + '}));')
        if c.columns is not None:
            props = node.find('Properties')
            p = ET.SubElement(props, 'Property', name='model', type='javax.swing.table.TableModel', editor='org.netbeans.modules.form.editors2.TableModelEditor')
            table_node = ET.SubElement(p, 'Table', columnCount=str(len(c.columns)), rowCount='0')
            for column in c.columns:
                ET.SubElement(table_node, 'Column', editable='false', title=column, type='java.lang.Object')
            self.code.append(f'        {c.name}.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {{}}, new String[] {{' + ', '.join(quote(x) for x in c.columns) + '}) {\n            @Override public boolean isCellEditable(int row, int column) { return false; }\n        });')
        if c.event:
            events = ET.SubElement(node, 'Events')
            handler = c.name + 'ActionPerformed'
            ET.SubElement(events, 'EventHandler', event='actionPerformed', listener='java.awt.event.ActionListener', parameters='java.awt.event.ActionEvent', handler=handler)
            self.handlers.append(handler)
            self.code.append(f'        {c.name}.addActionListener(this::{handler});')
        if c.rows:
            children = ET.SubElement(node, 'SubComponents')
            for r in c.rows:
                for child in r['components']:
                    self.render(child, children)
            self.layout(node, c.name + 'Layout', c.name, c.rows)
        if c.child is not None:
            ET.SubElement(node, 'Layout', {'class': 'org.netbeans.modules.form.compat2.layouts.support.JScrollPaneSupportLayout'})
            self.render(c.child, ET.SubElement(node, 'SubComponents'))
            self.code.append(f'        {c.name}.setViewportView({c.child.name});')
        return node

    def layout(self, node, variable, container, rows, width=None, outer=False):
        layout_node = ET.SubElement(node, 'Layout')
        horizontal = ET.SubElement(ET.SubElement(layout_node, 'DimensionLayout', dim='0'), 'Group', type='103', groupAlignment='0', attributes='0')
        vertical = ET.SubElement(ET.SubElement(layout_node, 'DimensionLayout', dim='1'), 'Group', type='103', groupAlignment='0', attributes='0')
        vseq = ET.SubElement(vertical, 'Group', type='102', attributes='0')
        target = container or 'getContentPane()'
        lines = [f'        javax.swing.GroupLayout {variable} = new javax.swing.GroupLayout({target});',
                 f'        {target}.setLayout({variable});',
                 f'        {variable}.setHorizontalGroup({variable}.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)']
        vlines = [f'        {variable}.setVerticalGroup({variable}.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)',
                  f'            .addGroup({variable}.createSequentialGroup()']
        def xmlgap(parent, size):
            ET.SubElement(parent, 'EmptySpace', min='-2', pref=str(size), max='-2', attributes='0')
        if outer:
            xmlgap(vseq, 16)
            vlines.append('                .addGap(16, 16, 16)')
        for index, r in enumerate(rows):
            if index:
                xmlgap(vseq, 8)
                vlines.append('                .addGap(8, 8, 8)')
            hs = ET.SubElement(horizontal, 'Group', type='102', attributes='0')
            lines.append(f'            .addGroup({variable}.createSequentialGroup()')
            if outer:
                xmlgap(hs, 16)
                lines.append('                .addGap(16, 16, 16)')
            vs = ET.SubElement(vseq, 'Group', type='103', groupAlignment='0', attributes='0')
            vlines.append(f'                .addGroup({variable}.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)')
            for ci, c in enumerate(r['components']):
                if ci:
                    xmlgap(hs, r['gap'])
                    lines.append(f'                .addGap({r["gap"]}, {r["gap"]}, {r["gap"]})')
                ha = {'id': c.name, 'max': '32767', 'attributes': '0'}
                if width is not None:
                    ha['pref'] = str(width)
                ET.SubElement(hs, 'Component', ha)
                lines.append(f'                .addComponent({c.name}, javax.swing.GroupLayout.DEFAULT_SIZE, ' + (str(width) if width else 'javax.swing.GroupLayout.DEFAULT_SIZE') + ', Short.MAX_VALUE)')
                va = {'id': c.name, 'min': '-2', 'max': '32767' if r['grow'] else '-2', 'attributes': '0'}
                if r['height'] is not None:
                    va['pref'] = str(r['height'])
                ET.SubElement(vs, 'Component', va)
                vlines.append(f'                    .addComponent({c.name}, javax.swing.GroupLayout.PREFERRED_SIZE, ' + (str(r['height']) if r['height'] is not None else 'javax.swing.GroupLayout.DEFAULT_SIZE') + ', ' + ('Short.MAX_VALUE' if r['grow'] else 'javax.swing.GroupLayout.PREFERRED_SIZE') + ')')
            if outer:
                xmlgap(hs, 16)
                lines.append('                .addGap(16, 16, 16)')
            lines[-1] += ')'
            vlines[-1] += ')'
        if outer:
            xmlgap(vseq, 16)
            vlines.append('                .addGap(16, 16, 16)')
        lines[-1] += ');'
        vlines[-1] += '));'
        # Root layout must run after child construction, not before it.
        if outer:
            self.outer_layout = lines + vlines
        else:
            self.code.extend(lines + vlines)

    def save(self):
        folder = BASE / self.package
        folder.mkdir(parents=True, exist_ok=True)
        if self.name == 'LamBaiThi':
            nonvisual = ET.SubElement(self.form, 'NonVisualComponents')
            ET.SubElement(nonvisual, 'Component', {'class': 'javax.swing.ButtonGroup', 'name': 'grpDapAn'})
            self.declarations.append(('javax.swing.ButtonGroup', 'grpDapAn'))
            self.code.insert(0, '        grpDapAn = new javax.swing.ButtonGroup();')
            for n in ('rdoA', 'rdoB', 'rdoC', 'rdoD'):
                comp = self.form.find(f'.//*[@name="{n}"]')
                props = comp.find('Properties')
                p = ET.SubElement(props, 'Property', name='buttonGroup', type='javax.swing.ButtonGroup', editor='org.netbeans.modules.form.RADConnectionPropertyEditor')
                ET.SubElement(p, 'Connection', component='grpDapAn', type='bean')
                self.code.insert(-4, f'        grpDapAn.add({n});')
        # Place NetBeans metadata sections in their standard order.
        order = {'NonVisualComponents': 0, 'Properties': 1, 'SyntheticProperties': 2, 'AuxValues': 3, 'Layout': 4, 'SubComponents': 5}
        self.form[:] = sorted(self.form, key=lambda e: order[e.tag])
        init = self.code[:-4] + self.outer_layout + self.code[-4:]
        source = f'''package {self.package};

/**
 * Giao diện {self.name}. Nghiệp vụ sẽ được bổ sung trong các event handler.
 * Chỉnh bố cục bằng tab Design của NetBeans và giữ file .form đi kèm.
 */
public class {self.name} extends javax.swing.JFrame {{
    public {self.name}() {{
        initComponents();
        getContentPane().setBackground(new java.awt.Color(245, 246, 248));
        setLocationRelativeTo(null);
    }}

    @SuppressWarnings({{"unchecked", "rawtypes"}})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {{
''' + '\n'.join(init) + '\n    }// </editor-fold>//GEN-END:initComponents\n\n'
        for handler in self.handlers:
            source += f'''    private void {handler}(java.awt.event.ActionEvent evt) {{//GEN-FIRST:event_{handler}
        // TODO: Bổ sung xử lý cho nút này sau khi hoàn thành giao diện.
    }}//GEN-LAST:event_{handler}

'''
        source += f'''    public static void main(String[] args) {{
        try {{
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {{
                if ("Nimbus".equals(info.getName())) {{
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }}
            }}
        }} catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {{
            java.util.logging.Logger.getLogger({self.name}.class.getName()).log(java.util.logging.Level.WARNING, null, ex);
        }}
        java.awt.EventQueue.invokeLater(() -> new {self.name}().setVisible(true));
    }}

    // Variables declaration - do not modify//GEN-BEGIN:variables
'''
        source += ''.join('    private ' + cls + ' ' + name + ';\n' for cls, name in self.declarations)
        source += '    // End of variables declaration//GEN-END:variables\n}\n'
        (folder / (self.name + '.java')).write_text(source, encoding='utf-8', newline='\r\n')
        ET.indent(self.form, space='  ')
        (folder / (self.name + '.form')).write_text('<?xml version="1.0" encoding="UTF-8" ?>\n\n' + ET.tostring(self.form, encoding='unicode') + '\n', encoding='utf-8', newline='\r\n')


if __name__ == '__main__':
    for definition in FORMS:
        Builder(*definition).save()
    print(f'Created {len(FORMS)} UI-only Java / NetBeans form pairs.')
