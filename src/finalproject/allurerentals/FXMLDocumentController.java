/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
// http://www.maineventtent.com/event-rentals/tables-chairs/

/**
 *
 * @author Study
 */
public class FXMLDocumentController implements Initializable {
    Scene scene;
    
    private Label label;
    @FXML
    private Label userName;
    @FXML
    private Label password;
    @FXML
    private TextField userNameTxtField;
    @FXML
    private PasswordField passwordTxtField;
    @FXML
    private Button logInBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private AnchorPane logInPage;
    private Label checkValidation;
    @FXML
    private Menu exit;
    @FXML
    private MenuItem close;
    @FXML
    private ImageView imagepre1;
    @FXML
    private ImageView imagepre2;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
        
    }
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logInAction(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String checkUser = userNameTxtField.getText();
        String checkPass = passwordTxtField.getText();
        String sql = "SELECT username, passwd from user WHERE username = ? AND passwd = ?";

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        conn = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost:3300/allureRentals?user=root&password=nash@15492");
        ps = conn.prepareStatement(sql);

        ps.setString(1, userNameTxtField.getText());
        ps.setString(2, passwordTxtField.getText());
        rs = ps.executeQuery("SELECT * FROM user");

        while (rs.next()) {
            try {
                if ((userNameTxtField.getText().equalsIgnoreCase(rs.getString("username")))
                        && (passwordTxtField.getText().equalsIgnoreCase(rs.getString("passwd")))) {
                    
                    Parent root = FXMLLoader.load(getClass().getResource("Rentals.fxml"));
                        scene = new Scene(root);
                        Stage stage = (Stage) logInBtn.getScene().getWindow();
                        stage.setScene(scene);

                  

                } else {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                   alert.setContentText("Login failure!!Please Register");
//                   alert.showAndWait();
//                  Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == ButtonType.OK){
//                alert.close();
//            }
                 
//                    //checkValidation.setText("Login failure!!Please Register");
                    Parent root = FXMLLoader.load(getClass().getResource("popUp.fxml"));
                        scene = new Scene(root);
                        Stage stage = (Stage) logInBtn.getScene().getWindow();
                        stage.setScene(scene);
                }
            } catch (final Exception e) {
            }

        }
    }

    @FXML
    private void registerAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RegistrationPage.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) registerBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private void viewOurs(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AboutUs.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) registerBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private void logAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) registerBtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    @FXML
    private void closeAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void exitAction(ActionEvent event) {
    }
    
}
