package com.finalproject.frameworks.UILogic.controllers;

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

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AllProductsViewController {

    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TableColumn<Product, String> ColumIDproduct;
    @FXML
    private TableColumn<Product, String> ColumIDclient;
    @FXML
    private TableColumn<Product, String> TypeOfProduct;
    @FXML
    private TableColumn<Product, String> opening;
    @FXML
    private TableColumn<Product, Boolean> ColumAvailable;
    @FXML
    private ChoiceBox<String> SearchByProducts;
    @FXML
    private ChoiceBox<String> searchByIDclients;
    @FXML
    private TextField SearchForID;

    private ObservableList<Product> products = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar columnas
        ColumIDproduct.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        ColumIDclient.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        TypeOfProduct.setCellValueFactory(new PropertyValueFactory<>("productType"));
        opening.setCellValueFactory(new PropertyValueFactory<>("openingDate"));
        ColumAvailable.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));

        // Cargar datos
        products.addAll(Services.productSearcher.getProductsByType(products));
        tableProduct.setItems(products);

        List<Product> products = Services.productSearcher.getProductsById();

        List<String> productIDs = products.stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        searchByIDclients.setItems(FXCollections.observableArrayList(productIDs));



        // Configurar ChoiceBoxes
        // Aquí debes cargar los datos en los ChoiceBoxes, similar a como se hace en AllClientsViewController
    }

    // Event handlers

    @FXML
    private void handleSearchByProductType(ActionEvent event) {
        // Implementar la lógica de búsqueda por tipo de producto
    }

    @FXML
    private void handleSearchByIdClient(ActionEvent event) {
        // Implementar la lógica de búsqueda por ID de cliente
    }

    @FXML
    private void handleSearchForId(ActionEvent event) {
        // Implementar la lógica de búsqueda por ID de producto
    }

    @FXML
    private void handleReturnWindow(ActionEvent event) throws IOException {
        String fxml = "initialWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateToRemplaceScene("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
}
