/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class RegistrationPageController implements Initializable {
    Scene scene;
     RegisterUser register = new RegisterUser();
    //Scene scene;
    @FXML
    private Button signUpBtn;
    @FXML
    private Button RegBackBtn;
    @FXML
    private TextField regUsername;
    @FXML
    private PasswordField regPassword;
    @FXML
    private PasswordField regCPassword;
    @FXML
    private MenuBar ReglogOut;
    @FXML
    private TextField emailTxtField;
    @FXML
    private AnchorPane regAnchorPane;
    @FXML
    private Label checkPassword;
    @FXML
    private Label checkEmailReg;
    @FXML
    private Menu logOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signUpAction(ActionEvent event) throws SQLException {
        String checkEmail=emailTxtField.getText();
        
                register.addUser(regUsername.getText(), regPassword.getText());
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
            java.sql.Statement s = connection.createStatement();
            java.sql.ResultSet r = s.executeQuery("SELECT * FROM user");
            while (r.next()) {
                try {
                    if(checkEmail.contains("@")){
                    if ((regPassword.getText().equalsIgnoreCase(regCPassword.getText()))) {
                        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    scene = new Scene(root);
                    Stage stage = (Stage) signUpBtn.getScene().getWindow();
                    stage.setScene(scene);
                        
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setContentText("Password and Confirm password do not match");
                  alert.showAndWait();
                      
                    //checkPassword.setText("Password and Confirm password do not match!!");
                    }
                }else{
                        checkEmailReg.setText("The email is invalid");
                    }
               
                } catch (final Exception e) {
                }
            }
            r.close();
            s.close();
            connection.close();
    }

    @FXML
    private void RegBackAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) RegBackBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    @FXML
    private void logOutAction(ActionEvent event) {
        Platform.exit();
    }
    
}
