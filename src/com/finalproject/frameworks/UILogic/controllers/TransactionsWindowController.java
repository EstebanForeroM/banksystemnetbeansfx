package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.frameworks.Services;
import com.finalproject.useCases.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsWindowController implements Initializable {

    @FXML
    private Button changePassword;
    @FXML
    private Button buy;
    @FXML
    private Button deposit;
    @FXML
    private Button payments;
    @FXML
    private Button withdrawals;
    @FXML
    private Button balance;
    @FXML
    private Button returnWindow;
    @FXML
    private Button advance;
    @FXML
    private ChoiceBox<String> productClient;
    @FXML
    private TextField textFieldDialog;

    @FXML
    private TextField ressposeDialog;

    @FXML
    private Button accept;

    @FXML
    private Button cancel;
    PasswordWindowController passwordWindowController = new PasswordWindowController();
    Token token = passwordWindowController.getUserToken();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buy.setDisable(true);
        payments.setDisable(true);
        balance.setDisable(true);
        advance.setDisable(true);
    }

    @FXML
    private void handlechangePassword(ActionEvent event) {
        try {
            String newPassword = textFieldDialog.getText();
            Services.userModificationService.modifyUserPassword(token, newPassword);
            String fxml = "DialogWindow";
            Navigation.getInstance().navigateToNewScene("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handlereturnWindow(ActionEvent event) throws Exception {
        String fxml = "initialWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateToRemplaceScene("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
}

