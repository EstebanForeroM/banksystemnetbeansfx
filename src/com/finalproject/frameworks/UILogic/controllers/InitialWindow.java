package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.frameworks.MainFunctionality;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    public void eventClientManagementButtonClick(ActionEvent event) throws IOException {
        String fxml = "ClientWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateTo("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
    
    @FXML 
    public void eventProductsManagementButtonClick(ActionEvent event) throws IOException{
        String fxml = "ProductWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateTo("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
    
    @FXML
    public void eventTransferencesButtonClick(ActionEvent event) throws IOException {
        String fxml = "PasswordWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateTo("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
}
