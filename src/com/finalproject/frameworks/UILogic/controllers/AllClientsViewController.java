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
import javafx.scene.control.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllClientsViewController {

    @FXML
    private SplitMenuButton searchForGender;
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
    }

    // Event handlers
    @FXML
    private void handleSearchForGender(ActionEvent event) {
        //Search item selected
        MenuItem selectedMenuItem = (MenuItem) event.getSource();
        String selectedGender = selectedMenuItem.getText().toLowerCase();
        //Refresh table whit date filtered
        Set<Client> clientsByGender = Services.userSearcher.getClientsByGender(Gender.getGenderFromGenderName(selectedGender));
        ObservableList<Client> filteredClients = FXCollections.observableArrayList(clientsByGender);
        tableClient.setItems(filteredClients);
    }

    @FXML
    private void handleProductsButtonClick(ActionEvent event) {
        // search item selected
        ProductType selectedProductType = ProductType.getProductType(((MenuItem) event.getSource()).getText());
        ProductSearcher productSearcher = Services.productSearcher;
        Product selectedProduct = (Product) productSearcher.getProductsByType(selectedProductType);

        // Continúa con el siguiente paso...
    }

    @FXML
    private void handleSearchForName(ActionEvent event) {
        // Handle search for name here
        // You can filter the table based on the entered name
    }

    // Additional event handlers for SplitMenuButtons if needed
    // ...

    // Additional methods for supporting the functionality of the UI
    // ...

}
