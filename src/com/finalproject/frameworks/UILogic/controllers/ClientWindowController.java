package com.finalproject.frameworks.UILogic.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 */
public class ClientWindowController implements Initializable {

    @FXML
    private CheckBox savingsAccountCheckBox;
    
    @FXML
    private CheckBox currentAccountCheckBox;
    
    // ... other CheckBoxes

    @FXML
    private SplitMenuButton backButton;
    
    // ... other components like MenuItems, MenuBar, etc.
    
    @FXML
    private Button seeAllButton;
    
    @FXML
    private TextField clientIDTextField;
    
    // ... other TextFields and Buttons

    @FXML
    private MenuButton genderMenuButton;
    
    @FXML
    private ImageView userImageView;  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code here, if any
    }   
    
    @FXML
    private void handleClientsMangementAction(ActionEvent event) {
        // Your code here
    }
    
    @FXML
    private void handleProductsManagementAction(ActionEvent event) {
        // Your code here
    }
    
    @FXML
    private void handleTranferentsAction(ActionEvent event) {
        // Your code here
    }
    
    // ... any other MenuItems
    
    // Event handlers for MenuButton
    @FXML
    private void handleMaleAction(ActionEvent event) {
        // Your code here
    }
    
    @FXML
    private void handleFemaleAction(ActionEvent event) {
        // Your code here
    }
    
    // Event handler for 'See All' button
    @FXML
    private void handleSeeAllAction(ActionEvent event) {
        // Your code here
    }
    
    // Event handler for 'Save Changes' button
    @FXML
    private void handleSaveChangesAction(ActionEvent event) {
        // Your code here
    }
}
