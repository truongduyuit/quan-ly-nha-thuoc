/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import ConnectSQLSever.ConnectToMSSQL;
import DTO.DTO_NhaCungCap;
import DTO.DTO_NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAL_NhaCungCap {
    Connection conn = null;
    ResultSet rs=null;
    PreparedStatement ps=null;
    Statement st=null;
    
    public ArrayList<DTO_NhaCungCap> layDanhSachNhaCungCap()
    {
        ArrayList<DTO_NhaCungCap> list = new ArrayList<>();
        ConnectToMSSQL a = new ConnectToMSSQL();
        conn = a.getConnection();
        String sql = "SELECT * FROM NhaCungCap";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                DTO_NhaCungCap ncc = new DTO_NhaCungCap();
                ncc.setId(rs.getInt("id"));
                ncc.setTenNCC(rs.getString("tenNCC"));
                ncc.setDiaChi(rs.getString("diaChi"));
                ncc.setEmail(rs.getString("email"));
                ncc.setSdt(rs.getString("sdt"));
                ncc.setTongTienMua(rs.getDouble("tongTienMua"));
                ncc.setNoCanTra(rs.getDouble("noCanTra"));
                ncc.setGhiChu(rs.getString("ghiChu"));
                
                list.add(ncc);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAL_NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                if(null != conn) { 
                    // cleanup resources, once after processing
                    rs.close();
                    ps.close();
                    // and then finally close connection
                    conn.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
        return list;
    }
    public int themNhaCungCap(DTO_NhaCungCap ncc)
    {
        int themNCC=0;
        String sql = "INSERT INTO NhaCungCap(tenNCC,sdt,email,diaChi,ghiChu,tongTienMua,noCanTra)"
                + " VALUES(?,?,?,?,?,?,?)";
        ConnectToMSSQL a = new ConnectToMSSQL();
        conn = a.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getSdt());
            ps.setString(3, ncc.getEmail());
            ps.setString(4, ncc.getDiaChi());
            ps.setString(5, ncc.getGhiChu());
            ps.setDouble(6, ncc.getTongTienMua());
            ps.setDouble(7, ncc.getNoCanTra());
            //ps.execute();
            themNCC = ps.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Thêm thành công!");
            
        } catch (SQLException ex) {
            Logger.getLogger(DAL_NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Thêm thất bại!");
        }
        finally
        {
            try {
                if(null != conn) { 
                    // cleanup resources, once after processing
                    //rs.close();
                    ps.close();
                    // and then finally close connection
                    conn.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
        return themNCC;
    }
    public void xoaNhaCungCap(int id)
    {
        String sql = "Delete NhaCungCap where id = "+ id;
        ConnectToMSSQL a = new ConnectToMSSQL();
        conn = a.getConnection();
        try {
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAL_NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                if(null != conn) { 
                    // cleanup resources, once after processing
                    //rs.close();
                    st.close();
                    // and then finally close connection
                    conn.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
        
    }
    public void suaNhaCungCap(DTO_NhaCungCap ncc)
    {
        String sql = "Update NhaCungCap set tenNCC= ? ,sdt= ?,email=?,diaChi=?,ghiChu=? where id=?";
        ConnectToMSSQL a = new ConnectToMSSQL();
        conn = a.getConnection();
        try {
            //st=conn.createStatement();
            //st.executeUpdate(sql);
            ps = conn.prepareStatement(sql);
            ps.setString(1, ncc.getTenNCC());
            ps.setString(2, ncc.getSdt());
            ps.setString(3, ncc.getEmail());
            ps.setString(4, ncc.getDiaChi());
            ps.setString(5, ncc.getGhiChu());
            ps.setInt(6, ncc.getId());
            
            ps.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Thêm thành công!");
            
        } catch (SQLException ex) {
            Logger.getLogger(DAL_NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Thêm thất bại!");
        }
        finally
        {
            try {
                if(null != conn) { 
                    // cleanup resources, once after processing
                    //rs.close();
                    ps.close();
                    // and then finally close connection
                    conn.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
    }
    public ArrayList<DTO_NhaCungCap> timKiemNhaCungCap(String timKiem)
    {
        ArrayList<DTO_NhaCungCap> list = new ArrayList<>();
        ConnectToMSSQL a = new ConnectToMSSQL();
        conn = a.getConnection();
        String sql = "Select * from NhaCungCap where tenNCC like ?";
        
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+timKiem+"%");
            rs=ps.executeQuery();
            while(rs.next())
            {
                DTO_NhaCungCap ncc = new DTO_NhaCungCap();
                ncc.setId(rs.getInt("id"));
                ncc.setTenNCC(rs.getString("tenNCC"));
                ncc.setDiaChi(rs.getString("diaChi"));
                ncc.setEmail(rs.getString("email"));
                ncc.setSdt(rs.getString("sdt"));
                ncc.setTongTienMua(rs.getDouble("tongTienMua"));
                ncc.setNoCanTra(rs.getDouble("noCanTra"));
                ncc.setGhiChu(rs.getString("ghiChu"));
                list.add(ncc);
                
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAL_NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                if(null != conn) { 
                    // cleanup resources, once after processing
                    rs.close();
                    ps.close();
                    // and then finally close connection
                    conn.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
        return list;
    }
    
    
    
}
