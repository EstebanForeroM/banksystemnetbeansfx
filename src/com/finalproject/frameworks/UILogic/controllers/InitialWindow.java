package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.frameworks.MainFunctionality;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InitialWindow implements Initializable {

    @FXML
    public Button ClientsManagement; // Button for Clients Management

    @FXML
    private Button ProductsManagement; // Button for Products Management

    @FXML
    private Button Transferences; // Button for Transferences

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    public void eventClientManagementButtonClick() {
        String fxml = "ClientWindow";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) this.ClientsManagement.getScene().getWindow();

            currentStage.setScene(scene);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML 
    public void eventProductsManagementButtonClick() throws IOException{
        String fxml = "ProductWindow";
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage currentStage = (Stage) this.ProductsManagement.getScene().getWindow();

            currentStage.setScene(scene);
            currentStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void eventTransferencesButtonClick() throws IOException {
        String fxml = "initialWindow";
        MainFunctionality.setRoot(fxml);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }
    
    // Additional methods for handling other button clicks can be added below
}
