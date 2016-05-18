/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;
import sample.utils.Global;

/**
 *
 * @author Chuot Bach
 */
public class ProductDAO implements Serializable{

    /**
     * get ProductDTO by id
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public ProductDTO getById(String id) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ProductDTO> result = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql = "Select * From " + Global.DB_TABLE_PRODUCTS + " Where id=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String unit = rs.getString("unit");
                    double price = rs.getDouble("price");
                    ProductDTO dto = new ProductDTO(id, name, unit, price);
                    return dto;
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
        return null;
    }
    /**
     * save dto to DB
     * @param dto
     * @return
     */
    public boolean save(ProductDTO dto) throws SQLException, NumberFormatException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql = "Insert Into "+
                        Global.DB_TABLE_PRODUCTS +
                        "(?,?,?,?) Values (?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, Global.DB_ID);
                stm.setString(2, Global.DB_NAME);
                stm.setString(3, Global.DB_UNIT);
                stm.setString(4, Global.DB_PRICE);
                stm.setString(5, dto.getId());
                stm.setString(6, dto.getName());
                stm.setString(7, dto.getUnit());
                stm.setDouble(8, dto.getPrice());

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

    /**
     * load all products from DB
     * @param con
     * @return
     */
    public List<ProductDTO> loadAll() throws SQLException, NumberFormatException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<ProductDTO> result = null;
        try {
            con = DBUtils.getConnection();
            if (con!=null) {
                String sql = "Select * From " + Global.DB_TABLE_PRODUCTS;
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                result = new ArrayList<ProductDTO>();
                while(rs.next()) {
                    String id = rs.getString(Global.DB_ID);
                    String name = rs.getString(Global.DB_NAME);
                    String unit = rs.getString(Global.DB_UNIT);
                    String sPrice = rs.getString(Global.DB_PRICE);
                    double price = Double.parseDouble(sPrice); // Num Format Exc
                    ProductDTO dto = new ProductDTO(id, name, unit, price);
                    result.add(dto);
                }
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
        return null;
    }
}
