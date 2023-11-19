package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.entities.Client;
import com.finalproject.entities.Product;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AllProductsViewController {

    @FXML
    private SplitMenuButton navigation;
    @FXML
    private MenuItem clientsManagement;
    @FXML
    private MenuItem productsManagement;
    @FXML
    private MenuItem tranferents;
    @FXML
    private SplitMenuButton searchProductByproduct;
    @FXML
    private TextField searchForID;
    @FXML
    private SplitMenuButton searchProductByIDclient;

    @FXML
    private TableView<Product> productsTableView;
    @FXML
    private TableColumn<Product, String> columIDproduct;
    @FXML
    private TableColumn<Client, String> columIDclient;
    @FXML
    private TableColumn<Product, String> typeOfProduct;
    @FXML
    private TableColumn<Product, String> opening;
    @FXML
    private TableColumn<Product, String> columAvailable;
    @FXML
    private TableView<Product> productsTableViews;
    private ObservableList<Product> productsData = FXCollections.observableArrayList();

    // Additional fields and methods as needed
    @FXML
        public void initialize() {
            // Initialize your controller here. This method is called after the FXML file has been loaded.
            // You can set up the table columns and add sample data if needed
            columIDproduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getOwnerId()));
            columIDclient.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
            typeOfProduct.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getProductName()));
            opening.setCellValueFactory(cellData -> {
                Date openingDate = cellData.getValue().getOpeningDate();
                    String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(openingDate);
                    return new ReadOnlyObjectWrapper<>(formattedDate);
                });
            //columAvailable.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAvailable()));
            // Agregar un getter para saber si el producto esta disponible 
            
            productsTableViews.setItems(productsData);
        }
    // Event handlers
    @FXML
    private void handleClientManagementButtonClick() {
        // Handle the Clients Management button click here
    }

    @FXML
    private void handleProductsManagementButtonClick() {
        // Handle the Products Management button click here
    }

    @FXML
    private void handleTranferentsButtonClick() {
        // Handle the Tranferents button click here
    }

    // Additional event handlers for SplitMenuButtons if needed
    // ...

    // Additional methods for supporting the functionality of the UI
    // ...

    // You need to define the Product class and the getSampleProducts method
    // ...

}
