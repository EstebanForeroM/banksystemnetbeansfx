package com.finalproject.frameworks.UILogic.controllers;


import com.finalproject.entities.Client;
import com.finalproject.entities.Product;
import com.finalproject.entities.products.CDT;
import com.finalproject.entities.products.ProductType;
import com.finalproject.entities.products.UninitializedProduct;
import com.finalproject.frameworks.Services;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ProductWindowController implements Initializable {

    @FXML
    public TextField searchForIDTextField;
    @FXML
    private TextField searchForID;
    @FXML
    private Button seeAllButton;
    @FXML
    private DatePicker openingDateDatePicker;
    @FXML
    private TextField numProductTextField;
    @FXML
    private Button createButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button eliminateButton;
    @FXML
    private ChoiceBox<String> productsOfClientChoiceBox;
    @FXML
    private TextField balanceTextField;
    @FXML
    private TextField termMonthsTextField;
    @FXML
    private TextField saldoTextField;
    @FXML
    private Label balanceText;
    @FXML
    private Label productNameText;

    private String ownerId;
    private Product selectedProduct;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createButton.setDisable(true);
        numProductTextField.setDisable(true);
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

    @FXML
    public void searchForOwnerId(ActionEvent actionEvent) {

        productsOfClientChoiceBox.getItems().clear();

        System.out.println("Searching for owner ID");

        ownerId = searchForIDTextField.getText();

        String id = searchForIDTextField.getText();

        try {
            Set<Product> productSet = Services.productSearcher.getProductsByUniqueOwner(id);

            if (productSet.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "This client has no products.",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            Iterator<Product> products = productSet.iterator();

            String[] productNames = new String[productSet.size()];

            int i = 0;

            while (products.hasNext()) {
                Product product = products.next();
                String productName = product.getProductName();
                if (product instanceof UninitializedProduct) {
                    productName = ((UninitializedProduct) product).getProductType().getName();
                }
                productNames[i] = productName;
                i++;
            }

            productsOfClientChoiceBox.getItems().addAll(productNames);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error! " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @FXML
    public void ProductSelected(ActionEvent actionEvent) {

        createButton.setDisable(true);
        modifyButton.setDisable(true);

        String productName = productsOfClientChoiceBox.getValue();

        for (Product product: Services.productSearcher.getProductsByUniqueOwner(ownerId)) {
            if (product instanceof UninitializedProduct && ((UninitializedProduct) product).getProductType().getName().equals(productName)) {
                numProductTextField.setText(product.getId());
                JOptionPane.showMessageDialog(
                        null,
                        "This product is not initialized yet.",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE
                );

                labelConfig(((UninitializedProduct) product).getProductType());

                createButton.setDisable(false);

                selectedProduct = product;

                break;
            }

            if (product.getProductName().equals(productName)) {
                numProductTextField.setText(product.getId());
                modifyButton.setDisable(false);
                selectedProduct = product;
                showProductInfo(product);
                break;
            }
        }
    }

    private void showProductInfo(Product product) {
        termMonthsTextField.setDisable(true);
        numProductTextField.setText(product.getId());
        balanceTextField.setText(String.valueOf(product.getBalance()));

        termMonthsTextField.setDisable(true);

        Date date = product.getOpeningDate();

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        openingDateDatePicker.setValue(localDate);
        numProductTextField.setText(product.getId());
        balanceTextField.setText(String.valueOf(product.getBalance()));

        if (product instanceof CDT) {
            termMonthsTextField.setDisable(false);
            termMonthsTextField.setText(String.valueOf(((CDT) product).getExpirationMonths()));
        }

        labelConfig(ProductType.getProductType(product.getProductName()));
    }


    public void createProduct(ActionEvent actionEvent) {
        verifyAllFieldsAreComplete();
        if (selectedProduct instanceof UninitializedProduct) {
            UninitializedProduct uninitializedProduct = (UninitializedProduct) selectedProduct;
            createButton.setDisable(false);

            String productId;
            LocalDate localDate = openingDateDatePicker.getValue();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            switch (uninitializedProduct.getProductType()) {
                case CDT:
                    productId = Services.productCreationService.initializeCDT(uninitializedProduct.getId(), date, Integer.parseInt(termMonthsTextField.getText()));
                    break;

                case AMERICAN_EXPRESS:
                case MASTERCARD:
                case VISA_CARD:
                    productId = Services.productCreationService.initializeCard(uninitializedProduct.getId(), date);
                    break;

                case CHECKING_ACCOUNT:
                case SAVINGS_ACCOUNT:
                    productId =Services.productCreationService.initializeAccount(uninitializedProduct.getId(), date);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + uninitializedProduct.getProductType());
            }

            Services.productModificationService.modifyProductBalance(productId, Double.parseDouble(balanceTextField.getText()));

            JOptionPane.showMessageDialog(
                    null,
                    "Product created successfully.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE
            );

            createButton.setDisable(true);
        }
    }

    private void verifyAllFieldsAreComplete() {
        if (balanceTextField.getText().isEmpty() || openingDateDatePicker.getValue() == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error! YOU MUST COMPLETE ALL THE FIELDS.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException("Error! YOU MUST COMPLETE ALL THE FIELDS.");
        }
    }

    private void labelConfig(ProductType productType) {
        productNameText.setText(productType.getName());
        termMonthsTextField.setDisable(true);
        switch (productType) {
            case CDT:
                termMonthsTextField.setDisable(false);
                balanceText.setText("Inverted money");
                break;

            case MASTERCARD:
            case AMERICAN_EXPRESS:
            case VISA_CARD:
                balanceText.setText("Credit available");
                break;

            case SAVINGS_ACCOUNT:
                balanceText.setText("Balance");
        }
    }

    @FXML
    public void modifyProduct(ActionEvent actionEvent) {
        verifyAllFieldsAreComplete();
        LocalDate localDate = openingDateDatePicker.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Services.productModificationService.modifyProductBalance(selectedProduct.getId(), Double.parseDouble(balanceTextField.getText()));
        Services.productModificationService.modifyProductOpeningDate(selectedProduct.getId(), date);
        if (selectedProduct instanceof CDT) {
            Services.productModificationService.modifyCDTTimePeriod(selectedProduct.getId(), Integer.parseInt(termMonthsTextField.getText()));
        }
    }

    @FXML
    private void handlereturnWindow(ActionEvent event) throws IOException {
        String fxml = "initialWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateToRemplaceScene("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
}