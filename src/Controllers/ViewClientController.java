/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author esteb
 */
public class ViewClientController implements Initializable {

    @FXML
    private AnchorPane LeftPanel;
    @FXML
    private Button ClientsManagement;
    @FXML
    private Button ProductsManagement;
    @FXML
    private Button Transferences;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
