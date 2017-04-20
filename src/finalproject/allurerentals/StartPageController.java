/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Nanis
 */
public class StartPageController implements Initializable {

    @FXML
    private MenuItem view;
    @FXML
    private MenuItem login;
    @FXML
    private MenuItem close;
    Scene scene;
    @FXML
    private Label name;
    @FXML
    private AnchorPane startPage;
    @FXML
    private Menu rentals;
    @FXML
    private Menu acc;
    //@FXML
    //private Menu exit;
    @FXML
    private Label welcome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AboutUs.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) name.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    @FXML
    /**
     * loads the log in  page 
     * @throws IOException
     */
    private void logAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) name.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    @FXML
    /**
     * closes the page
     * @throws IOException
     */
    private void closeAction(ActionEvent event) {
        Platform.exit();
    }
    
}
