/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAO_HoaDon;
import dao.DAO_HoaDonCT;
import dao.DAO_Table;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import jdbcHelper.ShareHelper;
import model.HoaDon;
import model.Table;

/**
 *
 * @author Admin
 */
public final class PalHome extends javax.swing.JPanel {

    /**
     * Creates new form PalHome
     */
    public static PalHome home;
    
    ImageIcon icon = new ImageIcon(getClass().getResource("/icon/icons8_table_100px_2.png")); // trống
    ImageIcon icon1 = new ImageIcon(getClass().getResource("/icon/icons8_restaurant_table_100px.png")); // có khách
    ImageIcon icon2 = new ImageIcon(getClass().getResource("/icon/icons8_tableware_80px.png"));
    DAO_Table daoTable = new DAO_Table();
    DAO_HoaDon daoHD = new DAO_HoaDon();
    DAO_HoaDonCT daoHDCT = new DAO_HoaDonCT();
    String maBan;
    String ban;
    String maHD;
    //public JLabel lblMaBan;

    public PalHome() {
        initComponents();
        home = this;
        loadTable();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PalTable = new javax.swing.JPanel();

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));

        jPanel5.setBackground(new java.awt.Color(102, 102, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 564, Short.MAX_VALUE)
        );

        PalTable.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PalTableLayout = new javax.swing.GroupLayout(PalTable);
        PalTable.setLayout(PalTableLayout);
        PalTableLayout.setHorizontalGroup(
            PalTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );
        PalTableLayout.setVerticalGroup(
            PalTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(PalTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1112, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void loadTable() {
        // lấy số bàn
        ArrayList<Table> list = daoTable.select();
        //Chia layout
        PalTable.setLayout(new GridLayout((list.size() / 5) + 1, 5, 10, 10));
        //Tạo mảng bàn
        JLabel lbl[] = new JLabel[list.size()];
        
        PalTable.removeAll();
        PalTable.updateUI();
        
        for (int i = 0; i < list.size(); i++) {
            
            if (icon != null) {
                if (list.get(i).getTrangThai().trim().equals("Trống")) {
                    lbl[i] = new JLabel(list.get(i).getTenBan(), icon, JLabel.CENTER);
                } else if (list.get(i).getTrangThai().trim().equals("Đang phục vụ")) {
                    lbl[i] = new JLabel(list.get(i).getTenBan(), icon1, JLabel.CENTER);
                } else if (list.get(i).getTrangThai().trim().equals("Đã đặt")) {
                    lbl[i] = new JLabel(list.get(i).getTenBan(), icon2, JLabel.CENTER);
                }
                
                //lbl[i].setHorizontalAlignment(JLabel.CENTER);
                lbl[i].setHorizontalTextPosition(JLabel.CENTER);
                lbl[i].setIconTextGap(-15);
                lbl[i].setVerticalTextPosition(JLabel.BOTTOM);
                lbl[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
                
                lbl[i].setToolTipText(Integer.toString(list.get(i).getMaBan()));
                lbl[i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        System.out.println("Thực hiện khi click vào bàn");
                        ban = ((JLabel) e.getSource()).getText();
                        maBan = ((JLabel) e.getSource()).getToolTipText();
                        System.out.println("dã lấy dc mã bàn " + maBan);
                 
                        Table table = daoTable.findById(Integer.parseInt(maBan));
                        if (table.getTrangThai().trim().equals("Đang phục vụ")) {
                             HoaDon hd = daoHD.findByMaBan(table.getMaBan());
                             maHD = Integer.toString(hd.getMaHoaDon());
                             System.out.println("Đang phục vụ");
                             System.out.println("MHD khi click gọi thêm " + maHD);
                            openorder(ban, maBan, maHD, table.getTrangThai());
                        } else if(table.getTrangThai().trim().equals("Trống")){
                            openorder(ban, maBan, null, table.getTrangThai());
                            System.out.println("Trống");
                        } else if( table.getTrangThai().trim().equals("Đã đặt")){
                            Table model = getModel();
                            daoTable.update(model);
                            openorder(ban, maBan, null, table.getTrangThai());
                            System.out.println("Đã đặt");
                        }

                    }
                });
                
                
                PalTable.add(lbl[i]);
            } else {
                System.out.println("null");
            }
        }
    }
    
    public void openorder(String ban, String maBan, String maHD, String stt) {
        new FrmOrder(ban, maBan, maHD, stt).setVisible(true);
    }
    
    public void reloadPal(){
        loadTable();
    }
    
    public Table getModel(){
        Table model = new Table();
        model.setMaBan(Integer.parseInt(maBan));
        model.setTenBan(ban);
        model.setTrangThai("Trống");
        return model;
    }
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PalTable;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
