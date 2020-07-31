/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Menu;
import jdbcHelper.JdbcHelper;

/**
 *
 * @author Admin
 */
public class DAO_Menu {

    public void insert(Menu model) {
        String sql = "insert into Menu values (?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql, model.getTenMon(),
                model.getMaLoai(),
                model.getDonGia(),
                model.getDVT());
    }

    public void update(Menu model) {
        String sql = "update Menu set TenMon = ?, MaLoai = ?, DonGia = ?, DVT = ? where MaMon = ?";
        JdbcHelper.executeUpdate(sql, model.getTenMon(),
                model.getMaLoai(),
                model.getDonGia(),
                model.getDVT(),
                model.getMaMon());
    }

    public void delete(int maMon) {
        String sql = "delete from Menu where MaMon = ?";
        JdbcHelper.executeUpdate(sql, maMon);
    }

    public Menu findById(int maMon) {
        String sql = "select * from Menu where MaMon = ?";
        ArrayList<Menu> list = select(sql, maMon);
        return list.size() > 0 ? list.get(0) : null;
    }

    public ArrayList<Menu> findByTenMon(int maLoai, String tenMon) {
        String sql = "select * from Menu where TenMon like ? and MaLoai = ? ";
        return select(sql, "%" + tenMon + "%", maLoai);
    }
   
    public ArrayList<Menu> findByMaLoai(int maLoai) {
        String sql = "select * from Menu where MaLoai = ?";
        return select(sql, maLoai);
    }

    public ArrayList<Menu> select() {
        String sql = "select * from Menu";
        return select(sql);
    }

    public ArrayList<Menu> select(String sql, Object... args) {
        ArrayList<Menu> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Menu model = readFrmResult(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list;
    }

    private Menu readFrmResult(ResultSet rs) throws SQLException {
        Menu model = new Menu();
        try {
            model.setMaMon(rs.getInt(1));
            model.setTenMon(rs.getString(2));
            model.setMaLoai(rs.getInt(3));
            model.setDonGia(rs.getDouble(4));
            model.setDVT(rs.getString(5));
        } catch (Exception e) {
        }
        return model;
    }
}
