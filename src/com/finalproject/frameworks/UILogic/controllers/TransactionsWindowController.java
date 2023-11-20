package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.frameworks.Services;
import com.finalproject.useCases.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.TextField;
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Shange Password");
            alert.setHeaderText("Add new password");
            alert.setHeight(225);
            alert.setWidth(440);
            // Crear un campo de texto dentro de la ventana de alerta
            TextInputDialog inputDialog = new TextInputDialog();
            inputDialog.setHeaderText("Ingrese un texto:");

            Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
            Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);

            okButton.setText("Aceptar");
            cancelButton.setText("Cancelar");

            inputDialog.showAndWait().ifPresent(texto -> {
                System.out.println("Texto ingresado: " + texto);
                Alert resultadoAlerta = new Alert(Alert.AlertType.INFORMATION);
                resultadoAlerta.setTitle("Resultado");
                resultadoAlerta.setHeaderText(null);
                resultadoAlerta.setContentText("Texto ingresado: " + texto);
                resultadoAlerta.showAndWait();
            });
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

