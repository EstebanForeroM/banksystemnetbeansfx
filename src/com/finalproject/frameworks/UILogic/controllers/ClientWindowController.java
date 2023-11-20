package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import com.finalproject.entities.Product;
import com.finalproject.entities.products.ProductType;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import com.finalproject.frameworks.Services;
import com.finalproject.useCases.Token;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;

import javax.swing.*;

/**
 * FXML Controller class
 */
public class ClientWindowController implements Initializable {

    // ... other components like MenuItems, MenuBar, etc.

    @FXML
    private Button SeeAll;
    @FXML
    private TextField clientIDTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private ChoiceBox<String> gender;
    @FXML
    private ImageView userImageView;
    @FXML
    private TextField passwordTextField;
    @FXML
    private AnchorPane LeftPanel;
    @FXML
    private MenuItem ClientsMangement;
    @FXML
    private MenuItem ProductsManagement;
    @FXML
    private MenuItem Tranferents;
    @FXML
    private Button Clean;
    @FXML
    private Button SaveOrModify;
    @FXML
    private Button SearchPicture;
    @FXML
    private TextField Name;
    @FXML
    private TextField Clue;
    @FXML
    private MenuItem Male;
    @FXML
    private MenuItem Female;
    @FXML
    private Button SaveShanges;
    @FXML
    private CheckBox SavingsAccount;
    @FXML
    private CheckBox CurrentAccount;
    @FXML
    private CheckBox CDT;
    @FXML
    private CheckBox VisaCard;
    @FXML
    private CheckBox AmericanCard;
    @FXML
    private Button returnWindow;
    @FXML
    private Button Right;
    @FXML
    private Button Left;

    private String imagePath;

    private List<Client> actualClients;

    private int actualClientIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        actualClients = new ArrayList<>();

        Services.addOnClientAddedListener(this::clientListChanges);

        clientListChanges();

        imagePath = "@../../../../../img/defaultProfile.png";

        Gender[] genders = Gender.values();

        String[] genderNames = new String[genders.length];


        for (int i = 0; i < genderNames.length; i ++) {
            genderNames[i] = genders[i].getGenderName();
        }

        gender.getItems().addAll(genderNames);
    }

    private void clientListChanges() {
        actualClients = Services.userSearcher.getClients();
    }


    private void saveClient() {
        String clientName = nameTextField.getText();
        String clientId= clientIDTextField.getText();
        Gender clientGender = Gender.getGenderFromGenderName(gender.getValue());
        String clientPassword = passwordTextField.getText();

        try {
            Client client = Services.userSearcher.getClientById(clientId);

            Token clientToken = Services.tokenAuthenticationService.getToken(clientPassword);

            Services.userModificationService.modifyUser(clientToken, clientName, client.getPassword(), clientGender, imagePath);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleaddNewClientButtonClicked(ActionEvent event) {

        String clientName = nameTextField.getText();
        String clientId= clientIDTextField.getText();
        Gender clientGender = Gender.getGenderFromGenderName(gender.getValue());
        String clientPassword = passwordTextField.getText();

        try {
            Services.userCreationService.createClient(clientName, clientPassword, clientGender, clientId, imagePath);
            Token clientToken =  Services.tokenAuthenticationService.getToken(clientPassword);
            addSelectedProducts(clientToken);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cleaner() {
        this.SavingsAccount.setSelected(false);
        this.CurrentAccount.setSelected(false);
        this.CDT.setSelected(false);
        this.VisaCard.setSelected(false);
        this.AmericanCard.setSelected(false);

        clientIDTextField.setText("");
        nameTextField.setText("");
        passwordTextField.setText("");
    }

    private void addSelectedProducts(Token token) {
        if (SavingsAccount.isSelected()) {
            Services.productCreationService.addProduct(token, ProductType.SAVINGS_ACCOUNT);
        }
        if (CurrentAccount.isSelected()) {
            Services.productCreationService.addProduct(token, ProductType.CHECKING_ACCOUNT);
        }
        if (CDT.isSelected()) {
            Services.productCreationService.addProduct(token, ProductType.CDT);
        }
        if (VisaCard.isSelected()) {
            Services.productCreationService.addProduct(token, ProductType.VISA_CARD);
        }
        if (AmericanCard.isSelected()) {
            Services.productCreationService.addProduct(token, ProductType.AMERICAN_EXPRESS);
        }
    }

    private Gender getSelectedGender() {
        return null;
    }

    @FXML
    public void handleGenderMenuItemClicked(javafx.event.ActionEvent event) {

    }

    @FXML
    public void handleSearchPictureButtonClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Window window = ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null) {
            try {
                userImageView.setImage(new Image(selectedFile.toURI().toString()));

            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
                e.printStackTrace();
            }
            imagePath = selectedFile.getAbsolutePath();
        } else {
            System.out.println("User canceled image selection.");
        }
    }

    @FXML
    public void handleSeeAllButtonClicked(ActionEvent event) {
        // Verificar si userSearcher es nulo antes de llamar a getClients()
        if (Services.userSearcher == null) {
            JOptionPane.showMessageDialog(null, "There are no clients! Create a client", "Success", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String fxml = "AllClientsView";
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) this.SeeAll.getScene().getWindow();

            if (loader.getController() == null) {
                throw new IOException("Error loading " + fxml + ".fxml");
            }
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleRightButton(ActionEvent event) throws IOException {
        actualClientIndex = (actualClientIndex + 1) > actualClients.size() ? 0 : actualClientIndex + 1;

        cleaner();
        loadClient(actualClients.get(actualClientIndex));
        System.out.println(actualClientIndex);
    }

    @FXML
    private void handleLeftButton(ActionEvent event) throws IOException {
        actualClientIndex = (actualClientIndex - 1) < 0 ? actualClients.size() - 1 : actualClientIndex - 1;

        cleaner();
        loadClient(actualClients.get(actualClientIndex));
        System.out.println(actualClientIndex);
    }

    private void loadClient(Client client) {

        Set<Product> products = Services.productSearcher.getProductsByOwner(client.getId());
        for (Product product : products) {
            String productName = product.getProductName();

            if (productName.equals(ProductType.CDT.getName())) {
                CDT.setSelected(true);
            } else if (productName.equals(ProductType.CHECKING_ACCOUNT.getName())) {
                CurrentAccount.setSelected(true);
            } else if (productName.equals(ProductType.SAVINGS_ACCOUNT.getName())) {
                SavingsAccount.setSelected(true);
            } else if (productName.equals(ProductType.VISA_CARD.getName())) {
                VisaCard.setSelected(true);
            } else if (productName.equals(ProductType.AMERICAN_EXPRESS.getName())) {
                AmericanCard.setSelected(true);
            }

            String imagePath = client.getPhotoPath();

            if (imagePath == null) {
                imagePath = "@../../../../../img/defaultProfile.png";
            }

            userImageView.setImage(new Image(imagePath));
        }

        clientIDTextField.setText(client.getId());
        nameTextField.setText(client.getName());
        passwordTextField.setText("");
    }

    @FXML
    private void handlereturnWindow(ActionEvent event) throws IOException {
        String fxml = "initialWindow";
        Node sourceNode = (Node) event.getSource();
        Navigation.getInstance().navigateTo("/com/finalproject/frameworks/UILogic/view/" + fxml + ".fxml", sourceNode);
    }
}
