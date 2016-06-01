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
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author Chuot Bach
 */
public class UserDAO implements Serializable {
    
    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            // Make Connection
            con = DBUtils.getConnection();
            if (con!=null) {
                // Create query 
                String sql = "select * from tbl_account "
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
    
}
