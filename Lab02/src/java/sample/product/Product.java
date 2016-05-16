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

/**
 *
 * @author Chuot Bach
 */
public class Product implements Serializable{
    private String code;
    private String name;
    private String description;
    private float price;
    private String category;

    public Product() {
    }     
    
    public Product(String code, String name, String description, float price, String category) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }
    
    /**
     * save this Product to DB
     * @param con
     * @return 
     */
    public boolean save(Connection con) throws SQLException, NumberFormatException {
        PreparedStatement stm = null;
        try {
            if (con!=null) {
                String sql = "Insert Into Product"+
                        "(Code,Name,Description,Price,Category)"+
                        "Values (?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, code);
                stm.setString(2, name);
                stm.setString(3, description);
                stm.setFloat(4, price);
                stm.setString(5, category);

                int updatedRows = stm.executeUpdate();
                return updatedRows > 0;
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
    public static Product[] loadAll(Connection con) throws SQLException, NumberFormatException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Product> list = null;
        try {
            if (con!=null) {
                String sql = "Select * From dbo.Product";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                list = new ArrayList<Product>();
                while(rs.next()) {
                    String code = rs.getString("Code");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    float price = Float.parseFloat(rs.getString("Price"));
                    String category = rs.getString("Category");
                    Product product = new Product(code, name, description, price, category);
                    list.add(product);
                }
                Product[] result = new Product[list.size()];
                result = list.toArray(result);
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
