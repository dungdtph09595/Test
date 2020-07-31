/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import jdbcHelper.DateHelper;
import model.Ship;
import jdbcHelper.JdbcHelper;

/**
 *
 * @author Admin
 */
public class DAO_Ship {
    
    public static SimpleDateFormat DATE_FOMATER = new SimpleDateFormat("MM/dd/yyyy");
    
    public String toString(Date date){
        return DATE_FOMATER.format(date);
    }
    public void insert(Ship model){
        String sql = "insert into HangXuat values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql, model.getMaMon(),
                model.getSoLuong(),
                model.getDVT(),
                model.getDonGia(),
                model.getNgayDat(),
                model.getNgayGiao(), 
                model.getDiaChi(),
                model.getStt(),
                model.getMaNV());
    }
    
    public void update(Ship model){
        String sql = "update HangXuat set MaMon = ?, SoLuong = ?, DVT = ?, DonGia = ?, NgayDat = ?, NgayGiao = ?, TrangThai = ?, DiaChi = ?, MaNhanVien = ? where MaHX = ? ";
        JdbcHelper.executeUpdate(sql, model.getMaMon(),
                model.getSoLuong(),
                model.getDVT(),
                model.getDonGia(),
                model.getNgayDat(),
                model.getNgayGiao(),    
                model.getStt(),
                model.getDiaChi(),
                model.getMaNV(),
                model.getMaHX());
    }
    
    public void delete(int maHX){
        String sql = "delete from HangXuat where MaHX = ?";
        JdbcHelper.executeUpdate(sql, maHX);
    }
    
    public void deleteAll(){
        String sql = "delete from HangXuat";
        JdbcHelper.executeUpdate(sql);
    }
    
    public Ship finfById(int maHX){
        String sql = "select * from HangXuat where MaHX = ?";
        ArrayList<Ship> list = select(sql, maHX);
        return list.size() > 0 ? list.get(0) : null;
    } 
    
    public ArrayList<Ship> select(){
        String sql = "select * from HangXuat order by MaHX DESC";
       return select(sql);
    }
    
    public ArrayList<Ship> select(String sql, Object...args){
        ArrayList<Ship> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {                    
                   Ship model = readFromResultSet(rs);
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
    
    private Ship readFromResultSet(ResultSet rs) throws SQLException{
        Ship model = new Ship();
        model.setMaHX(rs.getInt(1));
        model.setMaMon(rs.getInt(2));
        model.setSoLuong(rs.getInt(3));
        model.setDVT(rs.getString(4));
        model.setDonGia(rs.getDouble(5));
        model.setNgayDat(toString(rs.getDate(6)));
        Date date = DateHelper.addDays(rs.getDate(7), 2);
        model.setNgayGiao(toString(date));
        model.setDiaChi(rs.getString(8));
        model.setStt(rs.getString(9));
        model.setMaNV(rs.getString(10));
        return model;
    }
}
