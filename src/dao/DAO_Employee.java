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
import model.Employee;
import jdbcHelper.JdbcHelper;

/**
 *
 * @author Admin
 */
public class DAO_Employee {

    public void insert(Employee model) {
        String sql = "Insert into Employee values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        JdbcHelper.executeUpdate(sql, model.getMaNV(),
                model.getMatKhau(),
                model.getName(),
                model.isSex(),
                model.getNgaySinh(),
                model.getDiaChi(),
                model.getSdt(),
                model.isRole(),
                model.getNgayDK(),
                model.getHinh());
    }

    public void update(Employee model){
        String sql = "update Employee set VaiTro = ?, SoDienThoai = ? where MaNhanVien = ?";
        JdbcHelper.executeUpdate(sql, model.isRole(),
                model.getSdt(),
                model.getMaNV());
    }
    
    public ArrayList<Employee> select() {
        String sql = "select * from Employee";
        return select(sql);
    }

    public void delete(String maNV){
        String sql = "delete from Employee where MaNhanVien = ?";
        JdbcHelper.executeQuery(sql, maNV);
    }
    
    public Employee findById(String maNV) {
        String sql = "select * from Employee where MaNhanVien = ?";
        ArrayList<Employee> list = select(sql, maNV);
        return list.size() > 0 ? list.get(0) : null;
    }

    public ArrayList<Employee> select(String sql, Object... args) {
        ArrayList<Employee> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Employee model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Employee readFromResultSet(ResultSet rs) throws SQLException {
        Employee model = new Employee();
        model.setMaNV(rs.getString(1));
        model.setMatKhau(rs.getString(2));
        model.setName(rs.getString(3));
        model.setSex(rs.getBoolean(4));
        Date date1 = DateHelper.addDays(rs.getDate(5), 2);
        model.setNgaySinh(DateHelper.toString(date1));
        model.setDiaChi(rs.getString(6));
        model.setSdt(rs.getString(7));
        model.setRole(rs.getBoolean(8));
        Date date = DateHelper.addDays(rs.getDate(9), 2);
        model.setNgayDK(DateHelper.toString(date));
        model.setHinh(rs.getString(10));
        return model;
    }
}
