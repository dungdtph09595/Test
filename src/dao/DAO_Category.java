/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Category;
import jdbcHelper.JdbcHelper;

/**
 *
 * @author Admin
 */
public class DAO_Category {

    public void insert(Category model) {
        String sql = "insert into Category values (?)";
        JdbcHelper.executeUpdate(sql, model.getTenLoai());
    }

    public void update(Category model) {
        String sql = "update Category set TenLoai = ? where MaLoai = ?";
        JdbcHelper.executeUpdate(sql, model.getTenLoai(),
                model.getMaLoai());
    }

    public void delete(int maLoai) {
        String sql = "delete from Category where MaLoai = ?";
        JdbcHelper.executeUpdate(sql, maLoai);
    }

    public Category findById(int id) {
        String sql = "select * from Category where MaLoai = ?";
        ArrayList<Category> list = select(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public ArrayList<Category> select() {
        String sql = "select * from Category";
        return select(sql);
    }

    private ArrayList<Category> select(String sql, Object... args) {
        ArrayList<Category> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Category model = readFromResultSet(rs);
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

    private Category readFromResultSet(ResultSet rs) throws SQLException {
        Category model = new Category();
        try {
            model.setMaLoai(rs.getInt(1));
            model.setTenLoai(rs.getString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
