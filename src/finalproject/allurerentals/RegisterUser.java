/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Study
 */
public class RegisterUser {
        public void addUser(String username, 
            String passwd){
        
        java.sql.Connection conn = null;
        System.out.println("Connecting to the Database");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.err.println(e);
            System.exit(0);
        }
        
        try{
            PreparedStatement p;
            p = conn.prepareStatement("Insert Into user set username=?, passwd=?");
            p.setString(1, username);
            p.setString(2, passwd);
            //p.setString(3, cpassword);
            //p.setString(3, account_type);
           
            p.execute(); //use execute if no results expected back
            }catch(SQLException e){
                System.err.println("Error "+e.toString());
                return;
            }
    }
    
}
