/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author Chuot Bach
 */
public class RegistrationDAO implements Serializable {
    
    public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // Make Connection
            con = DBUtils.getConnection();
            if (con!=null) {
                // Create query 
                String sql = "select * from Users "
                        + "where username = ? and password = ?";
                // Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                // Execute Query, Return Result
                rs = stm.executeQuery();
                if (rs.next()) {
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

    public boolean updateProfile(String username, String password, String confirm, String lastName, boolean isAdmin) throws SQLException, ClassNotFoundException  {
        if (!password.equals(confirm)) return false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            // 1. Make Conn
            con = DBUtils.getConnection();
            // 2. Make Query
            String sql = "UPDATE [ItemDB].[dbo].[Users]"
                    + "SET [username] = ? "
                    + ",[password] = ? "
                    + ",[lastname] = ? "
                    + ",[isAdmin] = ? "
                    + "WHERE [username] = ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, lastName);
            stm.setBoolean(4, isAdmin);
            stm.setString(5, username);
            // 3. Tra KQ
            return stm.executeUpdate()==1;
        } finally {
            if (stm!=null) {
                stm.close();
            }
            if (con!=null) {
                con.close();
            }
        }
    }
}
