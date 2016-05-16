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
        Connection con = null;
        // 1. Load Driver        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // 2. Make Conn String
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ItemDB;instanceName=DUCBMSE61791";
        // 3. Open Conn
        con = DriverManager.getConnection(url,"sa","123456");        
        return con;
    }
}
