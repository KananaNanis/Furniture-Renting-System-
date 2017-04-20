/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;

import DatabaseModel.ConnectTheOperations;
import static finalproject.allurerentals.UpdateUserController.items;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private boolean newClient;
    private int theID;
   
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
    private void addRecordAction(ActionEvent event) {
         //if(isNew==true)
            addRecordAction2();
            try {
                                Parent root = FXMLLoader.load(getClass().getResource("Rentals.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) backBtnAdd.getScene().getWindow();
                                stage.setScene(scene);
                            }
                            catch(IOException e) {
                                System.err.println(e.toString());
                            }

        //else
            //updateAction(myClientID);
        
        //thisStage.close();
    }
    

    private RentalsJavaClass addRecordAction2() {
                    
        try {
            LocalDate dt = addDate.getValue();
            String name=addName.getText();
            String num=addPNum.getText();
            String addr= addAddress.getText();
            String item= addItems.getText();
            String qua=addQuantity.getText();
            String date=dt.toString();
            localClient = new RentalsJavaClass(addName.getText(),addPNum.getText()
                    ,addAddress.getText(),addItems.getText(),addQuantity.getText(),dt.toString());
            
            
            // items.add(name + " " + num + " " + addr + " " + item+" "+qua+" "+date);
            
            //if (newClient==true) {
            ConnectTheOperations addOpeartion=new ConnectTheOperations();
            addOpeartion.addClient(name,num,addr,item,qua,date);
            
            //writeToFile(items, "cost.txt",cost);
            
            //}
            //}
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(AddFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AddFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return localClient;

    }
    
    public void passHandleOnStage(Stage s) {
        thisStage = s;
        //btnClose.getScene().setWindow(s);						
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

     public void displayClient2(RentalsJavaClass client,int id) {
        this.localClient = client;
        //this.newClient = newClient;
        this.theID = id;

        //LocalDate dt = editDate.getValue();
        //dt.toString();
        if (client != null) {
            addName.setText(client.getName());
            addPNum.setText(client.getpNum());
            addAddress.setText(client.getAddress());
            addItems.setText(client.getItems());
            addQuantity.setText(client.getQuantity());
            //dt.set(e.getDate());
        } else {
            addName.setText("");
            addPNum.setText("");
            addAddress.setText("");
            addItems.setText("");
            addQuantity.setText("");
            //editDate.setText("");
        }
    }
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
