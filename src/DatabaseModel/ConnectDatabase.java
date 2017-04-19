/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Study
 */
public class ConnectDatabase {
    public Connection connect() throws InstantiationException, ClassNotFoundException, SQLException{
                             
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
            
           
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;  
    }
   
}
