package com.finalproject.frameworks.UILogic.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class InitialWindow implements Initializable {

    @FXML
    public Button ClientsManagement;

    @FXML
    private Button ProductsManagement;

    @FXML
    private Button Transferences;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handleClientManagementButtonClick(ActionEvent event) throws IOException {
        String fxml = "ClientWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateToRemplaceScene("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
    
    @FXML 
    private void handleProductsManagementButtonClick(ActionEvent event) throws IOException{
        String fxml = "ProductWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateToRemplaceScene("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
    
    @FXML
    private void handleTransferencesButtonClick(ActionEvent event) throws IOException {
        String fxml = "PasswordWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateToRemplaceScene("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
}
