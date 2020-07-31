/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.Table;
import jdbcHelper.JdbcHelper;

/**
 *
 * @author Admin
 */
public class DAO_Table {
    public void insert(Table model){
        String sql = "insert into Ban values (? , ?)";
        JdbcHelper.executeUpdate(sql, model.getTenBan(),
                model.getTrangThai());
    }
    
    public void update(Table model){
        String sql = "update Ban set TenBan = ?, TrangThai = ? where MaBan = ?";
        JdbcHelper.executeUpdate(sql, model.getTenBan(),
                model.getTrangThai(),
                model.getMaBan());
    }
    
    public void delete(int maBan){
        String sql = "delete from Ban where MaBan = ?";
        JdbcHelper.executeUpdate(sql, maBan);
    }
    
    
    public Table findById(int maBan){
        String sql = "select * from Ban where MaBan = ?";
        ArrayList<Table> list = select(sql, maBan);        
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public ArrayList<Table> select(){
        String sql = "select * from Ban";
        return select(sql);
    }
    
    public ArrayList<Table> select(String sql, Object...args){
        ArrayList<Table> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {                    
                    Table table = new Table();
                    table.setMaBan(rs.getInt(1));
                    table.setTenBan(rs.getString(2));
                    table.setTrangThai(rs.getString(3));
                    list.add(table);
                }
            } finally{
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
