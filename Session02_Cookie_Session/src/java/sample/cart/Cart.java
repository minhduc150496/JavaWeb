/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Chuot Bach
 */
public class Cart implements Serializable{
    private String customerID;
    private Map<String, Integer> items;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    
    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItem(String title) {
        if (items == null) {
            items = new HashMap<String, Integer>();
        }
        int quantity = 1;
        if (items.containsKey(title)) {
            quantity = items.get(title) + 1;
        }
        items.put(title, quantity);
    }
    
    public void removeItem(String title) {
        if (items.containsKey(title)) {
            items.remove(title);
            // if items == 0 
        }
    }
}
