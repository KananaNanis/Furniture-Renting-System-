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
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class RentalsController implements Initializable {
    public static java.sql.Connection conn = null;

    ObservableList<RentalsJavaClass> myList= FXCollections.observableArrayList();
    Scene scene;
    
     //private UpdateUserController updateInfoController = null;
    private UpdateUserController userInfoController = null;
    
    //private RentalsJavaClass localClient;
    //private boolean isNew;
    //private int myClientID;
    @FXML
    private TableView<RentalsJavaClass> clientsTable;
    @FXML
    private TableColumn<RentalsJavaClass, String> nameCol;
    @FXML
    private TableColumn<RentalsJavaClass, String> pnumberCol;
    @FXML
    private TableColumn<RentalsJavaClass, String> addressCol;
    @FXML
    private TableColumn<RentalsJavaClass, String> itemsCol;
    @FXML
    private TableColumn<RentalsJavaClass, String> quantityCol;
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button backBtn;
    
    private Label lblDetail;
    private Stage detailStage = null;
    private IntegerProperty index=new SimpleIntegerProperty();
    
    //private Stage thisStage,detailStage = null;
    AddFXMLController detailController = null;
    UpdateUserController detailController2 = null;
    @FXML
    private TableColumn<RentalsJavaClass, String> dateCol;
    @FXML
    private TextField searchTxtField;
    @FXML
    private AnchorPane rentalsAchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                java.sql.Connection conn = null;
        System.out.println("This program demos DB connectivity");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        System.out.println("Connection established");
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM theClients");
            System.out.println("TABLE SELECTED");
            while (r.next()) {
                myList.add(new RentalsJavaClass(r.getString("cname"),r.getString("phoneNo")
                        ,r.getString("address"),r.getString("items"),r.getString("quantity"),r.getString("theDate")));

                System.out.println("TABLE again");
                nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
                 pnumberCol.setCellValueFactory(cellData -> cellData.getValue().pNumProperty());
                //pnumberCol.setCellValueFactory(cellData -> cellData.getValue().pNumProperty());
                addressCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
                itemsCol.setCellValueFactory(cellData -> cellData.getValue().itemsProperty());
                quantityCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
                dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
                
                clientsTable.setItems(myList);
                System.out.println("do sumn");

            }
            
            r.close();
            s.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
            
            
            
        }
        System.out.println("COPIED TO TABLE");
        
        FilteredList<RentalsJavaClass> filteredData = new FilteredList<>(myList, p -> true);
        searchTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(client -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (client.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (client.getpNum().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
     
        SortedList<RentalsJavaClass> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(clientsTable.comparatorProperty());
        clientsTable.setItems(sortedData);
        // TODO
    } 
    
    
private void showDetailStage(RentalsJavaClass selectedEmployee, boolean isNew,String id) {
        
        if (detailStage == null) { //so as not to recreate if needed in future

            FXMLLoader loader = new FXMLLoader();
            Parent detailPane = null;
            try {
                loader.setLocation(getClass().getResource("updateUser.fxml"));
                detailPane = loader.load();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            Scene scene = new Scene(detailPane);
            detailStage = new Stage();
           // detailStage.setTitle("Employee Details");
            detailStage.initModality(Modality.APPLICATION_MODAL);
            detailStage.setScene(scene);
            
//get the controller also:
            userInfoController = loader.getController();
            userInfoController.passHandleOnStage(detailStage); //to be able to close it.
//

        userInfoController.showEmployeeDetail(selectedEmployee, isNew,id);
        detailStage.showAndWait();
        
    }
    
    
    /**
     * show the details of an employee
     * @param selectedEmployee is an employee
     * @param isNew is a boolean
     */
    
}
    
    @FXML
    private void addAction(ActionEvent event) throws IOException {      
        
        RentalsJavaClass selectedClient = null;
        boolean isNew = true;
        String CID = " ";
        addAction(selectedClient,isNew,CID);
        //showDetailStage2(selectedClient, isNew);
        //data.clear();
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("updateUser.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) backBtn.getScene().getWindow();
//            stage.setScene(scene);
//        }
//        catch(IOException e) {
//            System.err.println(e.toString());
//        }

        //data.clear();
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("addFXML.fxml"));
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) backBtn.getScene().getWindow();
//            stage.setScene(scene);
//        }
//        catch(IOException e) {
//            System.err.println(e.toString());
//        }
    }
//        private void showDetailStage2(RentalsJavaClass selectedEmployee, boolean isNew) {
//        
//        if (detailStage == null) { //so as not to recreate if needed in future
////open another window in modal stae
//            FXMLLoader loader = new FXMLLoader();
//            Parent detailPane = null;
//            try {
////note the path to the resource:
////they are in the same package. not path specified.
//                loader.setLocation(getClass().getResource("updateUser.fxml"));
//                detailPane = loader.load();
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            }
//            Scene scene = new Scene(detailPane);
//            detailStage = new Stage();
//            detailStage.setTitle("Employee Details");
//            detailStage.initModality(Modality.APPLICATION_MODAL);
//            detailStage.setScene(scene);
////get the controller also:
//            userInfoController = loader.getController();
//            userInfoController.passHandleOnStage(detailStage); //to be able to close it.
////
//        }
////pass data to the target controller
///*
//        if (isNew) {
//            selectedEmployee = new Employee();
//        }
//        */
//        userInfoController.showEmployeeDetail2(selectedEmployee, isNew);
//        detailStage.showAndWait();
//        /*
//        if (isNew) {
//            System.out.println("adding new Employee");
//            userRecordsTable.getItems().add(selectedEmployee);
//        }
//       */
//    
//    }

    @FXML
    private void editAction(ActionEvent event) throws IOException {
        RentalsJavaClass selectedClient = null;
        Boolean isNew = true;
        String id;
        
        int selectedIndex = clientsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            selectedClient = clientsTable.getItems().get(selectedIndex);
            //deleteRecord(selectedClient.getClientId());
            id =selectedClient.getName();
            //showDetailStage(selectedClient,false,CID);
            addAction(selectedClient,false,id);
            //clientsTable.getItems().remove(selectedIndex);
        }
        
    }
    
    private void addAction(RentalsJavaClass selectedClient, Boolean isNew, String id){
        showDetailStage(selectedClient,isNew,id);
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("Rentals.fxml"));
//            scene = new Scene(root);
//            Stage stage = (Stage) backBtn.getScene().getWindow();
//            stage.setScene(scene);
//            //thisStage.close()
//        }
//        catch(IOException e) {
//            System.err.println(e.toString());
//        }
    }
    
    
//        private void showDetailStage3(RentalsJavaClass selectedClient,boolean isNew, String CID) {
//               if (detailStage == null) {
//            FXMLLoader loader = new FXMLLoader();
//            Parent detailPane = null;
//            try {
//                loader.setLocation(getClass().getResource("addFXML.fxml"));
//                detailPane = loader.load();
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            }
//            Scene scene = new Scene(detailPane);
//            detailStage = new Stage();
//            detailStage.setTitle("Client Details");
//            detailStage.initModality(Modality.APPLICATION_MODAL);
//            detailStage.setScene(scene);
//            //get the controller also:
//            detailController = loader.getController();
//            detailController.passHandleOnStage(detailStage);
//            //to be able to close it.
//        }
//        //pass data to the target controller
//        detailController.showEmployeeDetail2(selectedClient, isNew,CID);
//        detailStage.showAndWait();
//    }

    @FXML
    private void deleteAction(ActionEvent event) {
      
      RentalsJavaClass selectedClient = null;
        int selectedIndex = clientsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            selectedClient = clientsTable.getItems().get(selectedIndex);
            //add additional confirmation before deletion
            
            deleteRecord(selectedClient.getName());
            clientsTable.getItems().remove(selectedIndex);
        }  
        
    }
    
        public void deleteRecord(String cname){
        java.sql.Connection conn = null;
        //System.out.println("Connecting to the Database");
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
            p = conn.prepareStatement("Delete from theClients where cname=?");
            p.setString(1, cname);
            p.execute(); //use execute if no results expected back
            }catch(SQLException e){
                System.err.println("Error "+e.toString());
                //return;
            }
    }   
    


    @FXML
    private void backAction(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
        }
        catch(IOException e) {
            System.err.println(e.toString());
        }
}
    
//    private void printData(RentalsJavaClass oldData, RentalsJavaClass newData) {
//        int index;
//        index = clientsTable.getSelectionModel().getSelectedIndex();
//        Alert a = new Alert(Alert.AlertType.INFORMATION);
//        a.setContentText("Seleted index=" + index);
//        a.showAndWait();
//        String s = new String();
//        if (oldData != null) {
//            s = oldData.toString();
//            s += "\n";
//        }
//        if (newData != null) {
//            s += newData.toString();
//        }
//        lblDetail.setText(s);
//    }

//                   
    

}
