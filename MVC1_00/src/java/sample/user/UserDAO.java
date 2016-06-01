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
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author Chuot Bach
 */
public class UserDAO implements Serializable {

    private List<UserDTO> list = null;    
    
    public boolean checkLogin(String username, String password) 
            throws SQLException, NamingException {
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

    
    public boolean update(String username, String password, boolean isAdmin) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // 1. Tao truy van
                String sql = "Update [Users] Set password = ?, isAdmin = ? Where username = ?";
                // 2. Tao statement 
                stm = con.prepareStatement(sql);
                System.out.println(username + " - " + password + " - " + isAdmin );

                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                // 3. Tra KQ
                int rows = stm.executeUpdate();
                if (rows > 0) {
                    System.out.println("OK OK");
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean deleteByUser(String username) throws  SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // 1. Tao truy van
                String sql = "Delete From Users where username = ?";
                // 2. Xu li 
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                // 3. Tra KQ
                int rows = stm.executeUpdate();
                if (rows > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void searchByLastName(String searchString) throws  SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // 1. Tao truy van
                String sql = "select * from Users where lastname like ?";
                // 2. Xu li 
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchString + "%");
                rs = stm.executeQuery();
                // 3. Tra KQ
                list = new ArrayList<UserDTO>();
                while (rs.next()) {
                    // a. get Results
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastName = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin"); //role
                    // b. add new DTO to list
                    UserDTO dto = new UserDTO(username, password, lastName, isAdmin);
                    list.add(dto);
                }
                System.out.println(list.size());
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean addUser(String username, String password, String lastName, boolean admin) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            String sql = "insert into Users(username,password,lastname,isAdmin) values (?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, lastName);
            stm.setBoolean(4, admin);
            int rows = stm.executeUpdate();
            if (rows>0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<UserDTO> getList() {
        return list;
    }
}
