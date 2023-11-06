package com.finalproject.frameworks.UILogic.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 */
public class InitialWindow implements Initializable {

    @FXML
    private Button ClientsManagement; // Button for Clients Management

    @FXML
    private Button ProductsManagement; // Button for Products Management

    @FXML
    private Button Transferences; // Button for Transferences

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code here
    }    

    @FXML
    private void handleClientManagementButtonClick(ActionEvent event) {
        // Handler for Clients Management button click
        // TODO: Implement your logic here
    }
    
    // Additional methods for handling other button clicks can be added below
}
