/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.allurerentals;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class PopUpController implements Initializable {

    @FXML
    private Label success;
    @FXML
    private Button okayBtn;
    
    private Stage thisStage;
    Scene scene;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        success.setText("Login failure!! Please Register");
        // TODO
    }   
    
    public void passHandleOnStage(Stage s) {
        thisStage = s;
    }

    @FXML
    private void okayAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                        scene = new Scene(root);
                        Stage stage = (Stage) okayBtn.getScene().getWindow();
                        stage.setScene(scene);
    }
    
}
