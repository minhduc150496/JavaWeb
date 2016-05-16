/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.user;

import java.io.Serializable;

/**
 *
 * @author Chuot Bach
 */
public class UserDTO implements Serializable{
    private String username, password, lastName;
    private boolean admin;

    public UserDTO(String username, String password, String lastName, boolean admin) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
}
