/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.order;

import java.io.Serializable;

/**
 *
 * @author Chuot Bach
 */
public class OrderDTO implements Serializable{
    private String id;

    public OrderDTO() {
    }

    public OrderDTO(String id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
