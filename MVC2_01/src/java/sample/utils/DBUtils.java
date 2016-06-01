/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Chuot Bach
 */
public class DBUtils implements Serializable {
    public static Connection getConnection() throws SQLException, NamingException {
        
        Context context = new InitialContext();
        Context ctTomcat = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) ctTomcat.lookup("SE1066DS");
        Connection con = ds.getConnection();
        return con;
        /**/
        
        /*
        // 1. Load driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // 2. Create connection string
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ItemDB;instanceName=DUCBMSE61791";
        // 3. Open connection
        Connection con = DriverManager.getConnection(url,"sa","123456");
        return con;
        /**/
    }
}
