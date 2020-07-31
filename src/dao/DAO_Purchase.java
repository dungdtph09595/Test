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
import model.Purchase;
import jdbcHelper.JdbcHelper;
import model.Book;

/**
 *
 * @author Admin
 */
public class DAO_Purchase {

    public void insert(Purchase model) {
        String sql = "insert into HangNhap values (?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql, model.getTenHang(),
                model.getSoLuong(),
                model.getDVT(),
                model.getDonGia(),
                model.getNgayNhap(),
                model.getMaNhanVien(),
                model.getNCC(),
                model.getDiaChi());
    }

    public void delete(int maHN) {
        String sql = "delete from HangNhap where MaHN = ?";
        JdbcHelper.executeUpdate(sql, maHN);
    }

    public Purchase findById(int maHN) {
        String sql = "select * from HangNhap where MaHN = ?";
        ArrayList<Purchase> list = select(sql, maHN);
        return list.size() > 0 ? list.get(0) : null;
    }

    public ArrayList<Purchase> select() {
        String sql = "select * from HangNhap order by MaHN DESC";
        return select(sql);
    }

    public ArrayList<Purchase> selectAcodingMonth(int month) {
        String sql = "select * from HangNhap where month(NgayNhap) = ?";
        return select(sql, month);
    }

    public ArrayList<Purchase> selectAcodingDay(int day) {
        String sql = "select * from HangNhap where day(NgayNhap) = ?";
        return select(sql, day);
    }

    public ArrayList<Purchase> selectAcodingYear(int year) {
        String sql = "select * from HangNhap where year(NgayNhap) = ?";
        return select(sql, year);
    }

    public ArrayList<Purchase> select(String sql, Object... args) {
        ArrayList<Purchase> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Purchase model = readFromResultSet(rs);
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

    private Purchase readFromResultSet(ResultSet rs) throws SQLException {
        Purchase model = new Purchase();
        model.setMaHN(rs.getInt(1));
        model.setTenHang(rs.getString(2));
        model.setSoLuong(rs.getInt(3));
        model.setDVT(rs.getString(4));
        model.setDonGia(rs.getDouble(5));
        Date date = DateHelper.addDays(rs.getDate(6), 2);
        model.setNgayNhap(DateHelper.toString(date));
        model.setMaNhanVien(rs.getString(7));
        model.setNCC(rs.getString(8));
        model.setDiaChi(rs.getString(9));
        return model;
    }
}
