
--NHỮNG CÂU KỆNH ALTER CŨNG CHẠY NHE
-- CHẠY THEO THỨ TỰ KHÔNG THÌ TOANG

create database RESTRMANAGER

Create table Employee(
	MaNhanVien nvarchar(15) not null primary key,
	MatKhau nvarchar(50),
	HoTen nvarchar(100),
	GioiTinh bit,
	NgaySinh date,
	DiaChi nvarchar(max),
	SoDienThoai nvarchar(10),
	VaiTro bit,
	NgayDangKy date default getDate()
)
alter table Employee add Hinh nvarchar(100)

Create table Category(
	MaLoai int identity(1,1) not null primary key,
	TenLoai nvarchar(100)
)

Create  table Menu(
	MaMon int identity(1,1) not null primary key,
	TenMon nvarchar(max),
	Maloai int ,
	DonGia decimal(10,2),
	DVT nvarchar(20),
	constraint fk_Menu_Cetegory foreign key(MaLoai) references Category
)

Create table Ban(
	MaBan int identity(1,1) not null primary key,
	TenBan nvarchar(20),
	TrangThai nvarchar(30)
)

Create table HoaDon(
	MaHD int identity(1,1) not null primary key,
	MaBan int not null,
	DonGia decimal(10,2),
	MaNhanVien nvarchar(15) not null,
	NgayLap date default getDate(),
	constraint fk_HoaDon_Employee foreign key(MaNhanVien) references Employee,
	constraint fk_HoaDon_Ban foreign key(MaBan) references Ban
)
Alter table HoaDon drop column DonGia
ALTER TABLE HoaDon 


Create table DatBan(
	MaKh int identity(1,1) not null primary key,
	TenKh nvarchar(50),
	Sdt nvarchar(10),
	MaBan int,
	TenBan nvarchar(20),
	TenMon nvarchar(Max),
	TTThanhToan nvarchar(30),
	NgayDat date default getDate(),
	constraint fk_DatBan_Ban foreign key(MaBan) references Ban,
)
alter table DatBan drop column TenBan
alter table DatBan drop column TenMon

Create table HangNhap(
	MaHN int identity(1,1) not null primary key,
	TenHang nvarchar(100),
	SoLuong int not null,
	DVT nvarchar(20),
	DonGia decimal(10,2),
	NgayNhap date default getdate(),
	MaNhanVien nvarchar(15),
	NCC nvarchar(max),
	DiaChi nvarchar(max),
	constraint fk_Emlpoyee_HangNhap foreign key(MaNhanVien) references Employee
)

create table HoaDonChiTiet(
	MaHD int not null ,
	MaMon int not null ,
	SoLuong int not null,
	DonGia decimal(10,2),
	constraint pk_HoaDonChiTiet primary key (MaHD, MaMon),
	constraint fk_Menu_HoaDonChiTiet foreign key(MaMon) references Menu,
	constraint fk_HoaDon_HoaDonChiTiet foreign key(MaHD) references HoaDon
)
alter table HoaDonChiTiet add Gio nvarchar(10)
alter table  HoaDonChiTiet drop column Gio

Create table HangXuat(
	MaHX int identity(1,1) not null  primary key,
	MaMon int ,
	SoLuong int not null,
	DVT nvarchar(30),
	DonGia decimal(10,2),
	NgayDat date default getDate(),
	NgayGiao date ,
	DiaChi nvarchar(max),
	TrangThai nvarchar(50),
	MaNhanVien nvarchar(15),
	constraint fk_HangXuat_Menu foreign key(MaMon) references Menu,
	constraint fk_HangXuat_Emlpoyee foreign key(MaNhanVien) references Employee
)
-- HẾT PHẦN TẠO BẢNG
-- BÊN DƯỚI LÀ NHẬP LIỆU CHẠY TỪNG BẢNG 1. NẾU SAI THÌ KIỂM TRA LẠI TRONG BẢNG Ý CÓ CỘT GÌ, HOẶC LÀ DO THIẾU CỘT
-- VÌ LÚC LÀM SỬA ĐI SỬA LẠI LÊN KHÔNG ĐỂ Ý PHẦN NHẬP LIỆU

-- Bảng nhân viên
Insert into Employee values ( N'hieuvm', N'000000', N'Vũ Minh Hiếu', 1, '11/28/2000', N'Hà Nội', N'0944572000', 1, getdate())
Insert into Employee values ( N'tungtt', N'000000', N'Trần Thanh Tùng', 0, '05/25/1995', N'Thạch Thất', N'0944572001', 0, getdate())

-- Bảng Loại
insert into Category values (N'Seafood')
insert into Category values (N'Soup')
insert into Category values (N'Rice')

-- Bảng Thực đơn
insert into Menu values (N'Tôm hùm hấp', 1, 270000, N'kg')
insert into Menu values (N'Soup gà 1', 2, 30000, N'bát')
insert into Menu values (N'Cơm gà rang', 3, 45000, N'đĩa')

--Bảng Bàn
insert into Ban values (N'Bàn 1', N'Trống')
insert into Ban values (N'Bàn 2', N'Trống')
insert into Ban values (N'Bàn 3', N'Trống')
insert into Ban values (N'Bàn 4', N'Trống')
insert into Ban values (N'Bàn 5', N'Trống')
insert into Ban values (N'Bàn 6', N'Trống')
insert into Ban values (N'Bàn 7', N'Trống')
insert into Ban values (N'Bàn 8', N'Trống')
insert into Ban values (N'Bàn 9', N'Trống')
insert into Ban values (N'Bàn 10', N'Trống')
insert into Ban values (N'Bàn 11', N'Trống')
insert into Ban values (N'Bàn 12', N'Trống')
insert into Ban values (N'Bàn 13', N'Trống')
insert into Ban values (N'Bàn 14', N'Trống')
insert into Ban values (N'Bàn 15', N'Trống')
insert into Ban values (N'Bàn 16', N'Trống')
insert into Ban values (N'Bàn 17', N'Trống')
insert into Ban values (N'Bàn 18', N'Trống')
insert into Ban values (N'Bàn 19', N'Trống')
insert into Ban values (N'Bàn 20', N'Trống')
insert into Ban values (N'Bàn 21', N'Trống')
insert into Ban values (N'Bàn 22', N'Trống')
insert into Ban values (N'Bàn 23', N'Trống')
insert into Ban values (N'Bàn 24', N'Trống')
insert into Ban values (N'Bàn 25', N'Trống')

-- Bảng Hóa Đơn
insert into HoaDon values (15, N'hieuvm', getdate())
insert into HoaDon values (1,  N'tungtt', '06/25/2020')
insert into HoaDon values (5, N'tungtt', '06/24/2020')
insert into HoaDon values (21,  N'tungtt', '06/24/2020')
insert into HoaDon values (10, N'tungtt', '06/25/2020')
insert into HoaDon values (24,  N'tungtt', '06/23/2020')
insert into HoaDon values (6,  N'tungtt', getdate())

--Bảng Hàng nhập
insert into HangNhap values (N'Tôm hùm', 15, N'kg', 200000, '06/20/2020', N'hieuvm', N'CT Hải sản biển', N'Vũng Tàu')
insert into HangNhap values (N'Gà', 50, N'kg',150000, '06/20/2020', N'hieuvm', N'Gia đình Ông Hiếu', N'Đan Phượng')
insert into HangNhap values (N'Gạo tẻ', 2, N'bao', 200000, '06/20/2020', N'hieuvm', N'CT Xuất Khẩu gạo HH', N'Thọ An')

--Bảng hóa đơn chi tiết
insert into HoaDonChiTiet values (15, 16, 5, 45000)
insert into HoaDonChiTiet values (15, 23, 10, 20000)
insert into HoaDonChiTiet values (16, 1, 5, 27000)
insert into HoaDonChiTiet values (17, 3, 5, 45000)
insert into HoaDonChiTiet values (6, 3, 5, 45000)
insert into HoaDonChiTiet values (7, 3, 5, 45000)

--Bảng Hàng Xuất
insert into HangXuat values (1, 2, N'kg', 300000, getDate(),N'Hoàn Kiếm - Hà Nội', N'tungtt')
--insert into HangXuat values (2, 6, N'chén', 50000, '06/27/2020',N'Phố Mai Dich - Hà Nội', N'tungtt')
--insert into HangXuat values (3, 2, N'đĩa', 75000, getDate(),N'Ngã Tư Nhổn', N'tungtt')
--insert into HangXuat values (2, 2, N'đĩa', 50000, '06/25/2020',N'Đông Anh - Hà Nội', N'tungtt')


select * from Employee where MaNhanVien = N'dungdt'
select * from Category
select * from Menu where Maloai = 2 and TenMon like N'%soup%'
select * from Ban
select * from HoaDon --where MaBan = 4 --order by MaHD DESC
--select * from HangNhap where month(NgayNhap) = 6
select * from HoaDonChiTiet
select HoaDon.MaHD, MaBan, MaMon, SoLuong from HoaDon join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD where MaBan = 7
select count(Distinct(MaHD) ) from HoaDonChiTiet
select sum(SoLuong * DonGia) from HoaDonChiTiet
select * from HangXuat
--delete from Ban where MaBan = 27
select * from DatBan 
delete from HangXuat

delete from Employee
delete from HangNhap
delete from HangNhap where MaHN = 5
delete from HoaDonChiTiet where MaHD = 148
delete from HoaDon where MaHD = 148
delete from DatBan
drop table Employee

update Employee set VaiTro = 0, SoDienThoai = N'0123654789' where MaNhanVien = N'dungdt'


select HoaDonChiTiet.MaHD, HoaDonChiTiet.MaMon, Menu.TenMon, HoaDon.MaBan, Ban.TenBan, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, MaNhanVien, NgayLap, Gio
from Ban join HoaDon on Ban.MaBan = HoaDon.MaBan
join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD
join Menu on HoaDonChiTiet.MaMon = Menu.MaMon where month(HoaDon.NgayLap) = 6 order by HoaDonChiTiet.MaHD DESC

select count(HoaDonChiTiet.MaHD), HoaDonChiTiet.MaMon, Menu.TenMon, HoaDon.MaBan, Ban.TenBan, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, MaNhanVien, NgayLap, Gio
from Ban join HoaDon on Ban.MaBan = HoaDon.MaBan
join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD
join Menu on HoaDonChiTiet.MaMon = Menu.MaMon where HoaDonChiTiet.MaHD = 147
group by HoaDonChiTiet.MaMon, Menu.TenMon, HoaDon.MaBan, Ban.TenBan, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, MaNhanVien, NgayLap, Gio
delete from HoaDonChiTiet where MaHD = 51 and MaMon = 16

select * from Ban where MaBan = 1
select * from HoaDon  where MaBan = 1
