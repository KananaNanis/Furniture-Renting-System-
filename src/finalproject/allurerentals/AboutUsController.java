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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Study
 */
public class AboutUsController implements Initializable {

    @FXML
    private Button back;
    Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void backAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
            scene = new Scene(root);
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
    
}
