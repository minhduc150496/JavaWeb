/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sample.product.ProductDTO;
import sample.utils.DBUtils;
import sample.utils.Global;

/**
 *
 * @author Chuot Bach
 */
public class OrderDAO implements Serializable{
    public int getAmount() throws ClassNotFoundException, SQLException {        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql = "Select Count(*) as total From " + Global.DB_TABLE_ORDERS;
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                int result = rs.getInt("total");
                return result;
            }
        } finally {
            if (rs!=null) {
                rs.close();
            }
            if (stm!=null) {
                stm.close();
            }
            if (con!=null) {
                con.close();
            }
        }
        return 0;
    }
    
    public boolean insert(String id) throws ClassNotFoundException, SQLException {        
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql = "Insert Into " + Global.DB_TABLE_ORDERS + " values (?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                int result = stm.executeUpdate();
                if (result>0) {
                    return true;
                }
            }
        } finally {
            if (rs!=null) {
                rs.close();
            }
            if (stm!=null) {
                stm.close();
            }
            if (con!=null) {
                con.close();
            }
        }
        return false;
    }
}
