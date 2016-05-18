/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.orderdetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sample.utils.DBUtils;
import sample.utils.Global;

/**
 *
 * @author Chuot Bach
 */
public class OrderDetailsDAO implements Serializable{
    
    public boolean insert(OrderDetailsDTO dto) throws ClassNotFoundException, SQLException {        
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql = "Insert Into "+
                        Global.DB_TABLE_ORDERDETAILS +
                        " (orderId,productId,quantity) Values (?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getId());
                stm.setString(2, dto.getProductId());
                stm.setInt(3, dto.getQuantity());

                int rows = stm.executeUpdate();
                return rows > 0;
            }
        } finally {
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
