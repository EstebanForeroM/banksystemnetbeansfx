package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import com.finalproject.entities.Product;
import com.finalproject.entities.products.ProductType;
import com.finalproject.frameworks.Services;
import com.finalproject.useCases.ProductSearcher;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.security.Provider;
import java.util.HashSet;
import java.util.Set;

public class AllClientsViewController {

    @FXML
    private SplitMenuButton Back;
    @FXML
    private MenuItem InitialWindow;
    @FXML
    private MenuItem ProductsMangement;
    @FXML
    private MenuItem Transferents;
    @FXML
    private SplitMenuButton searchForProducts;
    @FXML
    private TextField searchForName;
    @FXML
    private TableView<Client> tableClient;
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TableColumn<Client, String> columnID;
    @FXML
    private TableColumn<Client, String> columnName;
    @FXML
    private TableColumn<Gender, String> columnGender;
    @FXML
    private ChoiceBox<String> searchForGender;
    @FXML
    private ChoiceBox<String> searchForProduct;

    private String[] SearchForGender;
    private String[] SearchForProduct;

    private ObservableList<Client> clients = FXCollections.observableArrayList();

    // Initialize method if needed
    @FXML
    public void initialize() {
        // Set up the table columns
        columnID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        columnName.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        columnGender.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getGenderName()));

        // Populate the table with data
        tableClient.setItems(clients);
        Gender[] genders = Gender.values();
        String[] genderNames = new String[genders.length];

        for (int i = 0; i < genderNames.length; i++) {
            genderNames[i] = genders[i].getGenderName();
        }
        searchForGender.getItems().addAll(genderNames);

        ProductType[] productTypes = ProductType.values();
        String[] productTypeNames = new String[productTypes.length];

        for (int i = 0; i < productTypeNames.length; i++) {
            productTypeNames[i] = productTypes[i].getName();
        }
        searchForProduct.getItems().addAll(productTypeNames);
    }
    // Event handlers

    @FXML
    private void handleSearchForGender(ActionEvent event) {
        // Search item selected
        String selectedGender = searchForGender.getValue();

        if (selectedGender != null && !selectedGender.isEmpty()) {
            // Filtered table
            Set<Client> clientsByGender = Services.userSearcher.getClientsByGender(Gender.getGenderFromGenderName(selectedGender));
            ObservableList<Client> filteredClients = FXCollections.observableArrayList(clientsByGender);

            tableClient.setItems(filteredClients);
        } else {
            // Table refresh
            ObservableList<Client> filteredClients = FXCollections.observableArrayList(Services.userSearcher.getClientsByGender(Gender.getGenderFromGenderName(selectedGender)));
            tableClient.setItems(filteredClients);
        }
    }

    @FXML
    private void handleSearchForProduct(ActionEvent event) {
        // Get the selected product type
        String selectedProductType = searchForProduct.getValue();

        if (selectedProductType != null && !selectedProductType.isEmpty()) {
            // Filtered table
            Set<Product> productsByType = Services.productSearcher.getProductsByType(ProductType.getProductType(selectedProductType));
            ObservableList<Product> filteredProducts = FXCollections.observableArrayList(productsByType);
            // Table refresh
            tableProduct.setItems(filteredProducts);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Error! PRODUCT NOT FOUND.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @FXML
    private void handleSearchForName(ActionEvent event) {
        //Search item selected
        String name = searchForName.getText();
        //filtered table
        Set<Client> clientsByName = Services.userSearcher.getClientsByName(name);
        ObservableList<Client> filteredClients = FXCollections.observableArrayList(clientsByName);
        //table refresh
        tableClient.setItems(filteredClients);
    }
}
