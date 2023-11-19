package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.util.List;

public class AllClientsViewController {

    @FXML
    private MenuItem clientsManagement;
    @FXML
    private MenuItem productsManagement;
    @FXML
    private MenuItem transfers;
    // Additional MenuItems for the Products SplitMenuButton if needed
    // ...

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Client> clientsTable;
    @FXML
    private TableColumn<Client, String> columnID;
    @FXML
    private TableColumn<Client, String> columnName;
    @FXML
    private TableColumn<Gender, String> columnGender;

    // Initialize method if needed
    @FXML
    public void initialize() {
        columnID.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        columnName.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getName()));
        columnGender.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getGenderName()));
    }

    // Event handlers
    @FXML
    private void handleClientManagementButtonClick(ActionEvent event) {
        // Handle Clients Management button click here
    }

    @FXML
    private void handleProductsManagementButtonClick(ActionEvent event) {
        // Handle Products Management button click here
    }

    @FXML
    private void handleTransfersButtonClick(ActionEvent event) {
        // Handle Transfers button click here
    }

    // Event handlers for Gender and Products SplitMenuButtons if needed
    // ...

    // Additional methods for supporting the functionality of the UI
    // ...

}
