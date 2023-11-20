package com.finalproject.frameworks.UILogic.controllers;


import com.finalproject.entities.Client;
import com.finalproject.entities.Product;
import com.finalproject.frameworks.Services;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Set;

public class ProductWindowController {

    @FXML
    private TextField SearcheForId;
    @FXML
    private Button seeAllButton;
    @FXML
    private DatePicker openingDateDatePicker;
    @FXML
    private TextField numProductTextField;
    @FXML
    private TextArea productDetailsTextArea1;
    @FXML
    private TextField anotherTextField;
    @FXML
    private TextArea productDetailsTextArea2;
    @FXML
    private Button createButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button eliminateButton;
    @FXML
    private Button returnButton;
    @FXML
    private Button searchByIdOclient;
    @FXML
    private ChoiceBox<String> productsOfClient;
    @FXML
    private TextField saldo;
    @FXML
    private TextField balance;
    @FXML
    private TextField NumProduct;
    @FXML
    private TextField termmonths;
    private String idClient = null;


    @FXML
    public void initialize() {
    }

    @FXML
    private void handleOpeningDateDatePicker(ActionEvent event) {
        LocalDate selectedDate = openingDateDatePicker.getValue();
        // Verify that the new value is not null and is not before today
        if (selectedDate != null && !selectedDate.isBefore(LocalDate.now())) {
            openingDateDatePicker.show();
        } else {
            // Show an error message
            JOptionPane.showMessageDialog(
                    null,
                    "Error! YOU CAN'T SELECT A DATE LATER THAN TODAY'S TODAY.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Event handling method for "See All" button click
    @FXML
    private void handleSeeAllButtonClick(ActionEvent event) {
        String fxml = "AllProductsView.fxml";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateToRemplaceScene("/com/finalproject/frameworks/UILogic/view/"+ fxml +".fxml", sourceNode);
    }

    @FXML
    private void SearcheForID(ActionEvent event) {
        String id = SearcheForId.getText();
        Client clienteEncontrado = Services.userSearcher.getClientById(id);
        if (clienteEncontrado != null) {
            // show client details
            idClient = id;
        } else {
            // show message client no found
            JOptionPane.showMessageDialog(
                    null,
                    "Error! CLIENT NOT FOUND.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @FXML
    private void ProductsOfClient(ActionEvent event) {
        Set<Product> productsOfClient =  Services.productSearcher.getProductsById(idClient);
        // Clean the menu
    }

    @FXML
    private void handleProductMenuItemClick(Product product) {
        //Cases for type of product
    }

        // Event handling method for "Create" button click
        @FXML
        private void handleCreateButtonClick(ActionEvent event) {

        }

        // Event handling method for "Modify" button click
        @FXML
        private void handleModifyButtonClick(ActionEvent event) {
            // Your code here
        }

        // Event handling method for "Eliminate" button click
        @FXML
        private void handleEliminateButtonClick(ActionEvent event) {
            // Your code here
        }

        // Initialize method, if needed


        // Add additional methods for other menu items and actions as necessary
    }
