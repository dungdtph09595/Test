/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import jdbcHelper.DateHelper;
import jdbcHelper.JdbcHelper;
import model.Statistical;

/**
 *
 * @author Admin
 */
public class DAO_Statistical {

    public ArrayList<Statistical> showHoaDon(int maHD) {
        String sql = "select HoaDonChiTiet.MaHD, HoaDonChiTiet.MaMon, Menu.TenMon, HoaDon.MaBan, Ban.TenBan, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, MaNhanVien, NgayLap, Gio\n"
                + "from Ban join HoaDon on Ban.MaBan = HoaDon.MaBan\n"
                + "join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD\n"
                + "join Menu on HoaDonChiTiet.MaMon = Menu.MaMon where HoaDonChiTiet.MaHD = ?";
        return select(sql, maHD);
    }

    public ArrayList<Statistical> select() {
        String sql = "select HoaDonChiTiet.MaHD, HoaDonChiTiet.MaMon, Menu.TenMon, HoaDon.MaBan, Ban.TenBan, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, MaNhanVien, NgayLap, Gio\n"
                + "from Ban join HoaDon on Ban.MaBan = HoaDon.MaBan\n"
                + "join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD\n"
                + "join Menu on HoaDonChiTiet.MaMon = Menu.MaMon order by HoaDonChiTiet.MaHD DESC";
        return select(sql);
    }

    public ArrayList<Statistical> findByMonth(int month) {
        String sql = "select HoaDonChiTiet.MaHD, HoaDonChiTiet.MaMon, Menu.TenMon, HoaDon.MaBan, Ban.TenBan, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, MaNhanVien, NgayLap, Gio\n"
                + "from Ban join HoaDon on Ban.MaBan = HoaDon.MaBan\n"
                + "join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD\n"
                + "join Menu on HoaDonChiTiet.MaMon = Menu.MaMon where month(HoaDon.NgayLap) = ? order by HoaDonChiTiet.MaHD DESC";
        return select(sql, month);
    }

    public ArrayList<Statistical> findByDay(int day) {
        String sql = "select HoaDonChiTiet.MaHD, HoaDonChiTiet.MaMon, Menu.TenMon, HoaDon.MaBan, Ban.TenBan, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, MaNhanVien, NgayLap, Gio\n"
                + "from Ban join HoaDon on Ban.MaBan = HoaDon.MaBan\n"
                + "join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD\n"
                + "join Menu on HoaDonChiTiet.MaMon = Menu.MaMon where day(HoaDon.NgayLap) = ? order by HoaDonChiTiet.MaHD DESC";
        return select(sql, day);
    }

    public ArrayList<Statistical> findByYear(int year) {
        String sql = "select HoaDonChiTiet.MaHD, HoaDonChiTiet.MaMon, Menu.TenMon, HoaDon.MaBan, Ban.TenBan, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, MaNhanVien, NgayLap, Gio\n"
                + "from Ban join HoaDon on Ban.MaBan = HoaDon.MaBan\n"
                + "join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD\n"
                + "join Menu on HoaDonChiTiet.MaMon = Menu.MaMon where year(HoaDon.NgayLap) = ? order by HoaDonChiTiet.MaHD DESC";
        return select(sql, year);
    }

    public ArrayList<Statistical> findByNV(String maNV) {
        String sql = "select HoaDonChiTiet.MaHD, HoaDonChiTiet.MaMon, Menu.TenMon, HoaDon.MaBan, Ban.TenBan, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, MaNhanVien, NgayLap, Gio\n"
                + "from Ban join HoaDon on Ban.MaBan = HoaDon.MaBan\n"
                + "join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD\n"
                + "join Menu on HoaDonChiTiet.MaMon = Menu.MaMon where HoaDon.MaNhanVien like ? order by HoaDonChiTiet.MaHD DESC";
        return select(sql, "%" + maNV + "%");
    }

    public ArrayList<Statistical> findByHoaDon(int maHD) {
        String sql = "select HoaDonChiTiet.MaHD, HoaDonChiTiet.MaMon, Menu.TenMon, HoaDon.MaBan, Ban.TenBan, HoaDonChiTiet.SoLuong, HoaDonChiTiet.DonGia, MaNhanVien, NgayLap, Gio\n"
                + "from Ban join HoaDon on Ban.MaBan = HoaDon.MaBan\n"
                + "join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD\n"
                + "join Menu on HoaDonChiTiet.MaMon = Menu.MaMon where HoaDonChiTiet.MaHD = ? order by HoaDonChiTiet.MaHD DESC";
        return select(sql, maHD);
    }

    public ArrayList<Statistical> select(String sql, Object... args) {
        ArrayList<Statistical> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Statistical model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private Statistical readFromResultSet(ResultSet rs) throws SQLException {
        Statistical model = new Statistical();
        model.setMaHD(rs.getInt(1));
        model.setMaMon(rs.getInt(2));
        model.setTenMon(rs.getString(3));
        model.setMaBan(rs.getInt(4));
        model.setTenBan(rs.getString(5));
        model.setSoLuong(rs.getInt(6));
        model.setDonGia(rs.getDouble(7));
        model.setMaNhanVien(rs.getString(8));
        Date date = DateHelper.addDays(rs.getDate(9), 2);
        model.setNgayLapHD(DateHelper.toString(date));
        model.setGio(rs.getString(10));
        return model;
    }
}
