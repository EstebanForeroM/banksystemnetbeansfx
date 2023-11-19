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

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class AllClientsViewController {

    @FXML
    private SplitMenuButton searchForGender;
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
        //filtered table
        Set<Client> clientsByGender = Services.userSearcher.getClientsByGender(Gender.getGenderFromGenderName(selectedGender));
        ObservableList<Client> filteredClients = FXCollections.observableArrayList(clientsByGender);
        //table refresh
        tableClient.setItems(filteredClients);
    }

    @FXML
    private void handleSearcherForProducts(ActionEvent event) {
        // Search item selected
        ProductType selectedProductType = ProductType.getProductType(((MenuItem) event.getSource()).getText());
        // Have the ProductSearcher find all products of the selected type
        Set<Product> productsOfType = Services.productSearcher.getProductsByType(selectedProductType);
        // Have the UserSearcher find all clients that own the products
        Set<Client> clientsByProduct = new HashSet<>();
        for (Product product : productsOfType) {
            Client client = Services.userSearcher.getClientByProduct(product);
            clientsByProduct.add(client);
        }
        ObservableList<Client> filteredClients = FXCollections.observableArrayList(clientsByProduct);
        //table refresh
        tableClient.setItems(filteredClients);
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

    @FXML
    public void handleBack(ActionEvent event) {
        // Search item selected
        MenuItem selectedMenuItem = (MenuItem) event.getSource();
        String selectedMenu = selectedMenuItem.getText().toLowerCase();
        // Switch scene based on the selected menu
        if (selectedMenu.equals("Initial Window")) {
            String fxml = "ClientWindow";
            Node sourceNode = (Node) event.getSource();
            Navigation.getInstance().navigateTo("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
        } else if (selectedMenu.equals("products management")) {
            String fxml = "ProductWindow";
            Node sourceNode = (Node) event.getSource();
            Navigation.getInstance().navigateTo("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
        } else if (selectedMenu.equals("transferents")) {
            String fxml = "PasswordWindow";
            Node sourceNode = (Node) event.getSource();
            Navigation.getInstance().navigateTo("/com/finalproject/frameworks/UILogic/view/"+ fxml +".fxml", sourceNode);
        }
    }

}
