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
public class RegistrationDAO implements Serializable{
    public boolean checkLogin(String username, String password) 
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // 1. Make connection to DB
            con = DBUtils.getConnection();
            // 2. Make query
            String sql = "select * from Users where username=? and password=?";
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            // 3. Do query
            rs = stm.executeQuery();
            
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
