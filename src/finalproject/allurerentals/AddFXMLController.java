/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class AddFXMLController implements Initializable {

    private Stage thisStage;
    private boolean isNew;
    private RentalsJavaClass localClient;
   
    Scene scene;
    @FXML
    private DatePicker addDate;
    @FXML
    private TextField addName;
    @FXML
    private TextField addPNum;
    @FXML
    private TextField addAddress;
    @FXML
    private TextField addItems;
    @FXML
    private TextField addQuantity;
    @FXML
    private Button addRecord;
    @FXML
    private Button backBtnAdd;
    
    java.sql.Connection conn = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        System.out.println("Connecting...");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
        }
        catch (ClassNotFoundException | IllegalAccessException |
                InstantiationException | SQLException e) {
            System.err.println(e);
            System.exit(0);
        }
        System.out.println("Connection established");
        // TODO
    } 
    
    @FXML
    //private void addRecordAction(ActionEvent event) {
//         if(isNew==true)
//            addAction();
//        else
//            //updateAction(myClientID);
//        
//        thisStage.close();
    //}
    

    private RentalsJavaClass addRecordAction(ActionEvent event) {
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
        }
        catch (Exception e) {
            System.err.println(e);
            System.exit(0);
        }
        
        LocalDate dt = addDate.getValue();
        String name=addName.getText();
        String num=addPNum.getText();
        String addr= addAddress.getText();
        String item= addItems.getText();
        String qua=addQuantity.getText();
        String date=dt.toString();
        localClient = new RentalsJavaClass(addName.getText(),addPNum.getText()
                ,addAddress.getText(),addItems.getText(),addQuantity.getText(),dt.toString());

        if (isNew) {
                  try {
                PreparedStatement p;
                p = conn.prepareStatement("Insert Into theClients set"
                        + " cname=?, phoneNo=?,"
                        + " address=?, items =? "
                        + ",quantity =? ,theDate =?"
                        );
                p.setString(1, name);
                p.setString(2, num);
                p.setString(3, addr);
                p.setString(4, item);
                p.setString(5, qua);
                p.setString(6, date);
                //p.setInt(7, _clientID);
                p.execute(); //use execute if no results expected back
            } catch (SQLException e) {
                System.err.println("Error " + e.toString());
            }

        }
        return localClient;
    }
    
    public void passHandleOnStage(Stage s) {
        thisStage = s;
        //btnClose.getScene().setWindow(s);						
    }
    
    public void showEmployeeDetail(RentalsJavaClass c, boolean isNew) {
        this.localClient = c;
        this.isNew = isNew;
        //this.myClientID = myCID;
        
        LocalDate dt = addDate.getValue();
        //dt.toString();
        if (c != null) {
            addName.setText(c.getName());
            addPNum.setText(c.getpNum());
            addAddress.setText(c.getAddress());
            addItems.setText(c.getItems());
            addQuantity.setText(c.getQuantity());
            //dt.set(c.getDate());
            //addDate.setText(c.getDate());
        } else {
            addName.setText("");
            addPNum.setText("");
            addAddress.setText("");
            addItems.setText("");
            addQuantity.setText("");
            //addDate.setText("");
        }
    }
    
    public void showEmployeeDetail2(RentalsJavaClass c, boolean isNew, String myCID) {
        this.localClient = c;
        this.isNew = isNew;
        //this.myClientID = myCID;
        
        LocalDate dt = addDate.getValue();
        //dt.toString();
        if (c != null) {
            addName.setText(c.getName());
            addPNum.setText(c.getpNum());
            addAddress.setText(c.getAddress());
            addItems.setText(c.getItems());
            addQuantity.setText(c.getQuantity());
            //dt.set(c.getDate());
            //addDate.setText(c.getDate());
        } else {
            addName.setText("");
            addPNum.setText("");
            addAddress.setText("");
            addItems.setText("");
            addQuantity.setText("");
            //addDate.setText("");
        }
    }
    
    
//        public void showEmployeeDetail(RentalsJavaClass e,boolean isNew) {
//        this.localClient = e;
//        this.isNew = isNew;
//        if (e != null) {
//            usersNameID.setText(e.getName());
//            accountTypeID.setText(e.getAccount_Type());
//            userPasswordID.setText(e.getPassword());
//        } else {
//            usersNameID.setText("");
//            accountTypeID.setText("");
//            userPasswordID.setText("");
//        }
//    }

    @FXML
    private void backAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Rentals.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) backBtnAdd.getScene().getWindow();
            stage.setScene(scene);
        }
        catch(IOException e) {
            System.err.println(e.toString());
        }
    }
    
}
