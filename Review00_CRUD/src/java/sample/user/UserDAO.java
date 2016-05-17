/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Chuot Bach
 */
public class UserDAO implements Serializable{
    
    public List search(String key) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // 0. conn
            con = DBUtils.getConnection();
            // 1. Tao truy van
            String sql = "select * from Users where lastname like ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, "%"+key+"%");
            // 2. Thuc hien
            rs = stm.executeQuery();            
            // 3. Tra kq
            List result = new ArrayList();
            while(rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String lastName = rs.getString("lastname");
                boolean admin = rs.getBoolean("isAdmin");
                result.add(new UserDTO(username, password, lastName, admin));
            }
            return result;
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
    }
    
    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // 0. Get conn
            con = DBUtils.getConnection();
            // 1. Make Query
            String sql = "select * from Users where username=? and password=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            // 2. Exec
            rs = stm.executeQuery();
            // 3. Return
            if (rs.next()) {
                return true;
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
