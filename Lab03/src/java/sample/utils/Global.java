/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.utils;

import java.io.Serializable;

/**
 *
 * @author Chuot Bach
 */
public class Global implements Serializable{
    
    // Servlets
    public static final String SL_PROCESS = "ProcessServlet";
    public static final String SL_SHOP = "ShopServlet";
    public static final String SL_CART = "CartServlet";
    public static final String SL_BUY = "BuyServlet";
    public static final String SL_CHECKOUT = "CheckoutServlet";
    
    // Params 
    public static final String PR_ACTION = "btAction";
    public static final String PR_PRODUCT_ID = "productId";
    public static final String PR_CART = "Cart";
    
    // Values
    public static final String VL_BUY = "Buy";
    public static final String VL_CHECKOUT = "Checkout";
        
    // DB
    public static final String DB_DATABASE_NAME = "Lab03DB";
    public static final String DB_TABLE_PRODUCTS = "Products";             
    public static final String DB_TABLE_ORDERDETAILS = "OrderDetails";             
    public static final String DB_TABLE_ORDERS = "Orders";
    public static final String DB_ID = "id";             
    public static final String DB_NAME = "name";             
    public static final String DB_UNIT = "unit";             
    public static final String DB_PRICE = "price";             
    
}
