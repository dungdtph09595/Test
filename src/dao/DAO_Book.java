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
import model.Book;
import jdbcHelper.JdbcHelper;
import jdbcHelper.ShareHelper;
import model.Ship;

/**
 *
 * @author Admin
 */
public class DAO_Book {
    public void insert(Book model){
        String sql = "insert into DatBan values (?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql, model.getTenKH(),
                model.getSdt(),
                model.getMaBan(),
                model.getSttTT(),
                model.getNgayDat());
    }
    
    public void update(Book model){
        String sql = "update DatBan set TenKh = ?, Sdt = ?, MaBan = ?, TTThanhToan = ?, NgayDat = ? where MaKh = ?";
        JdbcHelper.executeUpdate(sql, model.getTenKH(),
                model.getSdt(),
                model.getMaBan(),
                model.getSttTT(),
                model.getNgayDat(),
                model.getMaKH());
    }
    
    public void delete(int maKh){
        String sql = "delete from DatBan where MaKh = ?";
        JdbcHelper.executeUpdate(sql, maKh);
    }
    
    public Book findById(int maKh){
        String sql = "select * from DatBan where MaKh = ?";
        ArrayList<Book> list = select(sql, maKh);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public ArrayList<Book> select(){
        String sql = "select * from DatBan";
        return select(sql);
    }
    
     public ArrayList<Book> select(String sql, Object...args){
        ArrayList<Book> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {                    
                   Book model = readFromResultSet(rs);
                   list.add(model);
                }
            } finally{
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private Book readFromResultSet(ResultSet rs) throws SQLException{
        Book model = new Book();
        model.setMaKH(rs.getInt(1));
        model.setTenKH(rs.getString(2));
        model.setSdt(rs.getString(3));
        model.setMaBan(rs.getInt(4));
        model.setSttTT(rs.getString(5));
        Date date = DateHelper.addDays(rs.getDate(6), 2);
        model.setNgayDat(DateHelper.toString(date));
        return model;
    }
}
