/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;

import DatabaseModel.ConnectTheOperations;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
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
 * @author Nanis
 */
public class RentalsController implements Initializable {
    public static java.sql.Connection conn = null;

    ObservableList<RentalsJavaClass> myList= FXCollections.observableArrayList();
    Scene scene;
    
     
    private UpdateUserController connectController = null;
    
    
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
    
    
    private Stage detailStage = null;
      
    
    AddFXMLController detailController = null;
    UpdateUserController detailController2 = null;
    @FXML
    private TableColumn<RentalsJavaClass, String> dateCol;
    @FXML
    private TextField searchTxtField;
    @FXML
    private AnchorPane rentalsAchor;
    RentalsJavaClass selectedClient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 
        readFromDb();
        FilteredList<RentalsJavaClass> filteredData = new FilteredList<>(myList, p -> true);
        searchTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(client -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (client.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (client.getpNum().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }
                return false; 
            });
        });
     
        SortedList<RentalsJavaClass> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(clientsTable.comparatorProperty());
        clientsTable.setItems(sortedData);
        // TODO
    } 
    /**
     * A method to read details from the database
     * and display on the table  
     */
    public void readFromDb(){
        //myList = null;
        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        
        try {
            java.sql.Statement s = conn.createStatement();
            java.sql.ResultSet r = s.executeQuery("select *from theClients");
            while (r.next()) {
                myList.add(new RentalsJavaClass(r.getString("c_id"),r.getString("cname"),r.getString("phoneNo")
                        ,r.getString("address"),r.getString("items"),r.getString("quantity"),r.getString("theDate")));
                
                System.out.println("TABLE again");
                nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
                 pnumberCol.setCellValueFactory(cellData -> cellData.getValue().pNumProperty());
                addressCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
                itemsCol.setCellValueFactory(cellData -> cellData.getValue().itemsProperty());
                quantityCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
                dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
                
                clientsTable.setItems(myList);

            }
            
            r.close();
            s.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
        }       
    }
    
    /**
     * gets the details of the selected client for editing
     * @param theClient   
     * @param id 
     * @throws IOException
     */
    private void detailsDisplay(RentalsJavaClass theClient, boolean newClient,int id) throws IOException {
        
        if (detailStage == null) { 

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
           
            detailStage.initModality(Modality.APPLICATION_MODAL);
            detailStage.setScene(scene);
            

            connectController = loader.getController();
            connectController.passHandleOnStage(detailStage);
//

        connectController.displayClient(theClient, newClient,id);
        detailStage.showAndWait();
        
    }
    
    
    /**
     * show the details of the client
     * @param selectedClient is an employee
     * @param newClient is a boolean
     */
    
}
    
    @FXML
    /**
     * This method adds the added client to the table 
     * @throws IOException
     */
    private void addAction(ActionEvent event) throws IOException {      
        Stage prev = (Stage) backBtn.getScene().getWindow();
        prev.close();
        RentalsJavaClass selectedClient = null;
        boolean newClient = true;
        int id=-1;
        addClient(selectedClient,newClient,id);
            }


    @FXML
    /**
     * This method enables editing of the selected client 
     * @throws IOException
     */
    private void editAction(ActionEvent event) throws IOException {
        Stage prev = (Stage) backBtn.getScene().getWindow();
        prev.close();
        RentalsJavaClass selectedClient = null;
        Boolean newClient = false;
        int id;
        
        int selectedIndex = clientsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            selectedClient = clientsTable.getItems().get(selectedIndex);
            id =Integer.parseInt(selectedClient.getGetClientID());
            addClient(selectedClient,false,id);
            //clientsTable.getItems().remove(selectedIndex);
        }
        
    }
    
    /**
     * opens a pop to add a client's details
     * @param selectedClient
     * @param id 
     * @param newClient
     * @throws IOexception
     */
    private void addClient(RentalsJavaClass selectedClient, Boolean newClient, int id) throws IOException{
        detailsDisplay(selectedClient,newClient,id);
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Rentals.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
        }
        catch(IOException e) {
            System.err.println(e.toString());
        }

    }
    
    @FXML
    /**
     * This method deletes the selected client from the table and database
     * @throws SQLException,ClassNotFoundException,InstantiationException
     * @throws IllegalAccessException
     */
    private void deleteAction(ActionEvent event) throws SQLException,
            ClassNotFoundException, InstantiationException, IllegalAccessException {
      
      int selectedIndex = clientsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            selectedClient = clientsTable.getItems().get(selectedIndex);
            System.out.println(selectedIndex);
                
                ConnectTheOperations deleteOperations=new ConnectTheOperations();
                deleteOperations.deleteRecord(Integer.parseInt(selectedClient.getGetClientID()));
        }
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Rentals.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
        }
        catch(IOException e) {
            System.err.println(e.toString());
        }
        readFromDb();
    }
    
//        public void deleteRecord(String id){
//        java.sql.Connection conn = null;
//        //System.out.println("Connecting to the Database");
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            conn = java.sql.DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
//        }
//        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
//            System.err.println(e);
//            System.exit(0);
//        }
//        try{
//            PreparedStatement p;
//            p = conn.prepareStatement("Delete from theClients where c_id=?");
//            p.setString(1, id);
//            p.execute(); //use execute if no results expected back
//            }catch(SQLException e){
//                System.err.println("Error "+e.toString());
//                //return;
//            }
//    }   
    


    @FXML
    /**
     * loads the log in page 
     * @throws IOException
     */
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
    
    

}
