/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import sample.product.ProductDTO;

/**
 *
 * @author Chuot Bach
 */
public class Cart implements Serializable {
    private final Map<String, Integer> items = new HashMap<String, Integer>();

    public Cart() {
    }     
    
    public Map<String, Integer> getItems() {
        return items;
    }
    
    /**
     * add quantity of product with id in cart by 1
     * @param id 
     */
    public void add(String id) {
        int quantity = 0;
        if (items.containsKey(id)) {
            quantity = items.get(id);
        } 
        items.put(id, quantity+1);
    }
}
