package com.system;

/**
 *
 * @author HP
 */
public class UserDetails {
    
    String User;
    private String password;

    public UserDetails( String User, String password) {

        this.User = User;
        this.password = password;
    }

    

    public String getUser() {
        return User;
    }

   public String getPassword(){
       return password;
   }
}
