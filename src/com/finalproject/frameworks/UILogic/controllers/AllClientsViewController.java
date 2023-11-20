package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import com.finalproject.entities.Product;
import com.finalproject.entities.products.ProductType;
import com.finalproject.frameworks.Services;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.IOException;
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
    private Button returnWindow;
    @FXML
    private TextField searchForName;
    @FXML
    private TableView<Client> tableClient;
    @FXML
    private TableColumn<Client, String> columnID;
    @FXML
    private TableColumn<Client, String> columnName;
    @FXML
    private TableColumn<Gender, String> columnGender;
    @FXML
    private ChoiceBox<String> searchByGender;
    @FXML
    private ChoiceBox<String> searchByProduct;

    private String[] searchForGender;
    private String[] searchForProduct;

    private ObservableList<Client> clients = FXCollections.observableArrayList();

    // Initialize method if needed
    @FXML
    public void initialize() {
        // Set up the table columns
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        // Populate the table with data
        clients.addAll(Services.userSearcher.getClients());
        tableClient.setItems(clients);
        Gender[] genders = Gender.values();
        String[] genderNames = new String[genders.length];

        for (int i = 0; i < genderNames.length; i++) {
            genderNames[i] = genders[i].getGenderName();
        }
        searchByGender.getItems().addAll(genderNames);

        ProductType[] productTypes = ProductType.values();
        String[] productTypeNames = new String[productTypes.length];

        for (int i = 0; i < productTypeNames.length; i++) {
            productTypeNames[i] = productTypes[i].getName();
        }
        searchByProduct.getItems().addAll(productTypeNames);

    }
    // Event handlers

    @FXML
    private void searchByGender(ActionEvent event) {
        System.out.println("Me estoy ejecutando");
        // Search item selected
        String selectedGender = searchByGender.getValue();
        // Filtered table
        Set<Client> clientsByGender = Services.userSearcher.getClientsByGender(Gender.getGenderFromGenderName(selectedGender));
        ObservableList<Client> filteredClients = FXCollections.observableArrayList(clientsByGender);

        tableClient.setItems(filteredClients);
    }

    @FXML
    private void handlesearcByProduct(ActionEvent event) {
        // Get the selected product type
        String selectedProductType = searchByProduct.getValue();

        if (selectedProductType != null && !selectedProductType.isEmpty()) {
            // Filtered table
            Set<Product> productsByType = Services.productSearcher.getProductsByType(ProductType.getProductType(selectedProductType));
            ObservableList<Product> filteredProducts = FXCollections.observableArrayList(productsByType);
            // Table refresh
            //tableProduct.setItems(filteredProducts);
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
    @FXML
    private void handlereturnWindow(ActionEvent event) throws IOException {
        String fxml = "initialWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateToRemplaceScene("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
}
