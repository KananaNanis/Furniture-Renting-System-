/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Study
 */


public class ConnectTheOperations {
    java.sql.Connection Connection = null;

    Connection conn = null;
    ResultSet rs= null;
    PreparedStatement statement = null;

    public boolean deleteRecord(int index) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ConnectDatabase theConnection=new ConnectDatabase();
        String query = "DELETE FROM theClients WHERE c_id=?";
        conn=theConnection.connect();
        statement = conn.prepareStatement(query);
        statement.setInt(1, index);
        return statement.execute();
    }
     public void updateRecord (String newName, String newAdd, String newNum, 
             String newItems, String newQua,String newDate,int cid)
             throws SQLException, ClassNotFoundException,
             InstantiationException, IllegalAccessException {
        
         ConnectDatabase theConnection=new ConnectDatabase();
        String updateRecord ="update theClients set " +
                    "cname=?, phoneNo=?, " +
                    " address=?, items =?" +
                     ", quantity =? ,theDate =? " +
                        " WHERE c_id =?";
        conn=theConnection.connect();
        statement = conn.prepareStatement(updateRecord);
        statement.setString(1, newName);
        statement.setString(2, newNum);
        statement.setString(3,newAdd);
        statement.setString(4,newItems);
        statement.setString(5, newQua);
        statement.setString(6,newDate);
        statement.setInt(7, cid);
        
         statement.execute();  
}
     
     public boolean addClient(String name, String add, String phone, String items, String quantity,String date) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ConnectDatabase theConnection=new ConnectDatabase();
         String query = "INSERT INTO theClients VALUES(?,?,?,?,?,?,?)";
        conn=theConnection.connect();
        statement = conn.prepareStatement(query);
        statement.setString(1, "0");
        statement.setString(2, name);
        statement.setString(3, add);
        statement.setString(4, phone);
        //statement.setString(5, chair);
        statement.setString(5, items);
        statement.setString(6, quantity);
        statement.setString(7, date);

        return statement.execute();
    }

    public boolean addAccount(String username, String passwad, String C_passwad) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ConnectDatabase theConnection=new ConnectDatabase();
        String query = "Insert Into theClients set u_id=?,username =  ? ,passwd =?";
        conn=theConnection.connect();
        statement = conn.prepareStatement(query);

        statement.setString(1, username);
        statement.setString(2, passwad);
        //statement.setString(3, C_passwad);
        return statement.execute();

    }
}
