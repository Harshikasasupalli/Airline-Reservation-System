/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.system;

/**
 *
 * @author HP
 */
public class AuthenticationSystem {
    private static UserDetails loggedInUser;

    public static void setLoggedInUser(UserDetails user) {
        loggedInUser = user;
    }

    public static UserDetails getLoggedInUser() {
        return loggedInUser;
    }

    // Other authentication-related methods
}
