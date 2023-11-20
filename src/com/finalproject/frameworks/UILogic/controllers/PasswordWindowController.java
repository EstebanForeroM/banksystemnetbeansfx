package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.frameworks.Services;
import com.finalproject.useCases.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PasswordWindowController implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Button enterToTransactions;

    @FXML
    private TextField passwordOfClient;

    @FXML
    private Button returnWindow;

    private static Token userToken;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    private void handlereturnWindow(ActionEvent event) throws Exception {
        String fxml = "initialWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateToRemplaceScene("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }

    public Token getUserToken() {
        return userToken;
    }
    @FXML
    private void handleenterToTransactions(ActionEvent event) throws Exception {
        String enteredPassword = passwordOfClient.getText();

        try {
            userToken = Services.tokenAuthenticationService.getToken(enteredPassword);
            String fxml = "TransactionsWindow";
            Node sourceNode = (Node) event.getSource();
            Navigation.getInstance().navigateToRemplaceScene("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error! YOU PASSWORD IS WRONG", JOptionPane.ERROR_MESSAGE);
        }
    }
}
