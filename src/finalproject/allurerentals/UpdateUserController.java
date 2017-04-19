/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;

import DatabaseModel.ConnectTheOperations;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class UpdateUserController implements Initializable {
public static List<String> items = new ArrayList<>();
    Scene scene;
    @FXML
    private TextField editName;
    @FXML
    private  TextField editNum;
    @FXML
    private TextField editAddress;
    @FXML
    private TextField editItems;
    @FXML
    private TextField editQuantity;
    @FXML
    private DatePicker editDate;
    @FXML
    private Button saveButton;
    @FXML
    private Button backBtn;

    private Stage thisStage;
    private RentalsJavaClass updateRecords;
    private boolean isNew;
    //private RentalsController userRecords = null;
    private String theID;

    /**
     * Initializes the controller class.
     */
    java.sql.Connection conn = null;
    @FXML
    private AnchorPane updateAnchor;
    @FXML
    private Label checkNumLen;
    String cost;

    @Override
    //java.sql.Connection conn = null;
    public void initialize(URL url, ResourceBundle rb) {
        java.sql.Connection conn = null;
        System.out.println("Connecting...");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | SQLException e) {
            System.err.println(e);
            System.exit(0);
        }
        System.out.println("Connection established");// TODO
    }

    public void passHandleOnStage(Stage s) {
        thisStage = s;
    }

    @FXML
    private void saveAction(ActionEvent event) throws IOException, FileNotFoundException, SQLException, ClassNotFoundException {
        String checkNum=editNum.getText();
        if(checkNum.length()<10||checkNum.length()>10){
            checkNumLen.setText("Phone Number should be 10 digits");
            
        }else{
       if (isNew == true) {
                        try {
                            theSaveAction();
                            try {
                                Parent root = FXMLLoader.load(getClass().getResource("Rentals.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) backBtn.getScene().getWindow();
                                stage.setScene(scene);
                            }
                            catch(IOException e) {
                                System.err.println(e.toString());
                            }
                            
                        }
        catch(InstantiationException ex) {
               Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (IllegalAccessException ex) {
               Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        } else {
            updateAction(theID);
            //editName.setText(editName.getText());
            try {
            Parent root = FXMLLoader.load(getClass().getResource("Rentals.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
        }
        catch(IOException e) {
            System.err.println(e.toString());
        }
       }
        }
    }

    private RentalsJavaClass theSaveAction() throws FileNotFoundException, IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        LocalDate dt = editDate.getValue();
        String name = editName.getText();
        String num = editNum.getText();
        String addr = editAddress.getText();
        String item = editItems.getText();
        String qua = editQuantity.getText();
        String date = dt.toString();
        
        updateRecords = new RentalsJavaClass(editName.getText(), editNum.getText(),
                 editAddress.getText(), editItems.getText(), editQuantity.getText(),
                 dt.toString());
        items.add(name + " " + num + " " + addr + " " + item+" "+qua+" "+date); 
   
        if (isNew==true) {
            ConnectTheOperations addOpeartion=new ConnectTheOperations();
                addOpeartion.addClient(name,num,addr,item,qua,date);
//            java.sql.Connection conn = null;
//            System.out.println("Connecting to the Database");
//            try {
//                Class.forName("com.mysql.jdbc.Driver").newInstance();
//                conn = java.sql.DriverManager.getConnection(
//                        "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
//            } catch (Exception e) {
//                System.err.println(e);
//                System.exit(0);
//            }
//            try {
//                PreparedStatement p;
//                
//                p = conn.prepareStatement("Insert Into theClients set"
//                        + " cname=?, phoneNo=?,"
//                        + " address=?, items =? "
//                        + ",quantity =? ,theDate =?"
//                );
//                //p2 = conn.prepareStatement("delete from Clients where cname=? and phoneNo =?");
//                p.setString(1, name);
//                p.setString(2, num);
//                p.setString(3, addr);
//                p.setString(4, item);
//                p.setString(5, qua);
//                p.setString(6, date);
//                //p.setInt(7, _clientID);
//                p.execute(); //use execute if no results expected back
                writeToFile(items, "cost.txt",cost);
//            } catch (SQLException e) {
//                System.err.println("Error " + e.toString());
//            }
//
        }
        return updateRecords;
    }
    
    
    private String writeToFile(java.util.List list, String path,String myCost) {
        String strI = null;
            BufferedWriter out = null;
            try {
                    File file = new File(path);
                    out = new BufferedWriter(new FileWriter(file, true));
                    //items.add(editItems.getText());
                    String myItems=editItems.getText();
                    String[] words = myItems.split(",");
                    

        //String theItems=editItems.getText();
            for(String theItem:words){
                if(theItem.startsWith("tabl")||theItem.startsWith("Tabl")){
                    String theQuants=editQuantity.getText();
                    String[] quants=theQuants.split(",");
                    //int i = (Integer) myCost;
                    //int newCost=Integer.parseInt(myCost);
                    
                    int newCost=Integer.parseInt(quants[0])*200;
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(newCost);
                     strI = sb.toString();
                    //String theCost=Integer.valueOf(newCost);
                    System.out.println("Cost for Tables:"+" "+items.add(strI));
                    //return cost;
                    
                }
                else if(theItem.startsWith("chair")||theItem.startsWith("Chair")){
                    String theQuants=editQuantity.getText();
                    String[] quants=theQuants.split(",");
                     //int newCost=Integer.parseInt(myCost);
                    
                     int newCost=Integer.parseInt(quants[1])*100;
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(newCost);
                     strI = sb.toString();
                    //String theCost=Integer.valueOf(newCost);
                    System.out.println("Cost for Chairs:"+" "+items.add(strI));
                    
                }
                else if(theItem.startsWith("tent")||theItem.startsWith("Tent")){
                    String theQuants=editQuantity.getText();
                    String[] quants=theQuants.split(",");
                     //int newCost=Integer.parseInt(myCost);
                    
                    int newCost=Integer.parseInt(quants[2])*350;
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(newCost);
                     strI = sb.toString();
                    //String theCost=Integer.valueOf(newCost);
                    System.out.println("Cost for Tents:"+" "+items.add(strI));
                    
                }
                //int totalCost
            
            }
            //return myCost;
                    for (Object s : list) {
                            out.write((String) s);
                            out.newLine();

                    }
                    //return myCost;
                    
                    out.close();
            } catch (IOException e) {
            }
            return strI;
            
    }

    
    private RentalsJavaClass updateAction(String clientId) {
        LocalDate dt = editDate.getValue();
       //int id=0;
        String name = editName.getText();
        String num = editNum.getText();
        String addr = editAddress.getText();
        String item = editItems.getText();
        String qua = editQuantity.getText();
        String date = dt.toString();
        updateRecords = new RentalsJavaClass(editName.getText(), editNum.getText(),
                 editAddress.getText(), editItems.getText(), editQuantity.getText(),
                 dt.toString());

        if (isNew == false) {
            try {
                ConnectTheOperations updateOperation=new ConnectTheOperations();
                updateOperation.updateRecord(name,num,addr,item,qua,date);
//            java.sql.Connection conn = null;
//            System.out.println("Connecting to the Database");
//            try {
//                Class.forName("com.mysql.jdbc.Driver").newInstance();
//                conn = java.sql.DriverManager.getConnection(
//                        "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
//            } catch (Exception e) {
//                System.err.println(e);
//                System.exit(0);
//            }
//
//            try {
//                PreparedStatement p;
//                
//                p = conn.prepareStatement("update theClients set"
//                        + " cname=?, phoneNo=?,"
//                        + " address=?, items =? "
//                        + ",quantity =? ,theDate =?");
//                p.setString(1, name);
//                p.setString(2, num);
//                p.setString(3, addr);
//                p.setString(4, item);
//                p.setString(5, qua);
//                p.setString(6, date);
//               // p.setInt(7, _clientID);
//                p.execute(); //use execute if no results expected back
//            } catch (SQLException e) {
//                System.err.println("Error " + e.toString());
//            }
            } catch (SQLException ex) {
                Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return updateRecords;
    }

    public void showEmployeeDetail(RentalsJavaClass e, boolean isNew, String id) {
        this.updateRecords = e;
        this.isNew = isNew;
        this.theID = id;

        LocalDate dt = editDate.getValue();
        //dt.toString();
        if (e != null) {
            editName.setText(e.getName());
            editNum.setText(e.getpNum());
            editAddress.setText(e.getAddress());
            editItems.setText(e.getItems());
            editQuantity.setText(e.getQuantity());
            //dt.set(e.getDate());
        } else {
            editName.setText("");
            editNum.setText("");
            editAddress.setText("");
            editItems.setText("");
            editQuantity.setText("");
            //editDate.setText("");
        }
    }

    public void showEmployeeDetail2(RentalsJavaClass e,boolean isNew) {
        this.updateRecords = e;
        this.isNew = isNew;
       //this.myClientID = myCID;

        //LocalDate dt = editDate.getValue();
        //dt.toString();
        if (e != null) {
            editName.setText(e.getName());
            editNum.setText(e.getpNum());
            editAddress.setText(e.getAddress());
            editItems.setText(e.getItems());
            editQuantity.setText(e.getQuantity());
            //dt.setText(e.getDate());
        } else {
            editName.setText("");
            editNum.setText("");
            editAddress.setText("");
            editItems.setText("");
            editQuantity.setText("");
            //editDate.setText("");
        }
    }

    @FXML
    private void backAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Rentals.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

}
