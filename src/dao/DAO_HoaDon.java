/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbcHelper.DateHelper;
import jdbcHelper.JdbcHelper;
import model.HoaDon;


/**
 *
 * @author Admin
 */
public class DAO_HoaDon {
    public void insert(HoaDon model) {
        String sql = "insert into HoaDon values (?, ?, ?)";
        JdbcHelper.executeUpdate(sql, model.getMaBan(),
                model.getMaNhanVien(),
                model.getNgayLap());               
    }

    public void update(HoaDon model) {
        String sql = "update Menu set MaBan = ?, MaNhanVien = ?, NgayLap = ? where MaHD = ?";
        JdbcHelper.executeUpdate(sql, model.getMaBan(),
                model.getMaNhanVien(),
                model.getNgayLap(),
                model.getMaHoaDon());
    }

    public void deleteAll(){
        String sql = "delete from HoaDon";
        JdbcHelper.executeUpdate(sql);
    }
    
     public void deleteAllById(int maHD){
        String sql = "delete from HoaDon where MaHD = ?";
        JdbcHelper.executeUpdate(sql, maHD);
    }
    
    public HoaDon findByMaHD(int maHD){
         String sql = "select * from HoaDon where MaHD = ?";
         ArrayList<HoaDon> list = select(sql, maHD);
         return list.size() > 0 ? list.get(0) : null;
    }
    
    public HoaDon findByMaBan(int maBan){
         String sql = "select * from HoaDon where MaBan = ? order by MaHD DESC";
         ArrayList<HoaDon> list = select(sql, maBan);
         return list.size() > 0 ? list.get(0) : null;
    }
    public ArrayList<HoaDon> select(){
        String sql = "select * from HoaDon order by MaHD DESC";
        return select(sql);
    }
    
    public ArrayList<HoaDon> select(String sql, Object...args){
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {                    
                    HoaDon model = readFromResultSet(rs);
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
    
    private HoaDon readFromResultSet(ResultSet rs) throws SQLException{
        HoaDon model = new HoaDon();
        model.setMaHoaDon(rs.getInt(1));
        model.setMaBan(rs.getInt(2));
        model.setMaNhanVien(rs.getString(3));
        model.setNgayLap(DateHelper.toString(rs.getDate(4)));
        return model;
    }
}
