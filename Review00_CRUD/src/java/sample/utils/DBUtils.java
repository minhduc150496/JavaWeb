/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Chuot Bach
 */
public class DBUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {        
        // 1. Load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // 2. Conn String
        String s = "jdbc:sqlserver://localhost:1433;databaseName=ItemDB;instanceName=DUCBMSE61791";
        String id = "sa";
        String pw = "123456";
        // 3. Open Conn
        Connection con = DriverManager.getConnection(s,id,pw);          
        return con;
    }
}
