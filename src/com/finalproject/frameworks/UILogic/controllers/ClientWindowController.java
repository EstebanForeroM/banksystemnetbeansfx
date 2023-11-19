package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.entities.Client;
import com.finalproject.entities.Gender;
import com.finalproject.entities.Product;
import com.finalproject.entities.products.ProductType;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.finalproject.frameworks.Services;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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
import javafx.scene.control.CheckBox;

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
    private TextField clueTextField;
    @FXML
    private MenuButton genderMenuButton;
    @FXML
    private ImageView userImageView;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    // Initialization code here, if any

    private void saveClient() {

    }

    private Gender getSelectedGender() {
        String selectedGenderName = genderMenuButton.getText();
        return Gender.getGenderFromGenderName(selectedGenderName);
    }

    @FXML
    public void handleGenderMenuItemClicked(javafx.event.ActionEvent event) {
        MenuItem selectedMenuItem = (MenuItem) event.getSource();
        genderMenuButton.setText(selectedMenuItem.getText());
    }

        

    @FXML
    public void handleSearchPictureButtonClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        Window window = ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null) {
            try {
                // Set the selected image to the ImageView or any other control you want to display it
                userImageView.setImage(new Image(selectedFile.toURI().toString()));
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("User canceled image selection.");
        }
    }
    
    @FXML
    public void handleCleanButtonClicked(ActionEvent event) {
        this.SavingsAccount.setSelected(false);
        this.CurrentAccount.setSelected(false);
        this.CDT.setSelected(false);
        this.VisaCard.setSelected(false);
        this.AmericanCard.setSelected(false);
        
        clientIDTextField.setText("");
        nameTextField.setText("");
        clueTextField.setText("");
    }

    @FXML
    public void handleSaveChangesButtonClicked(ActionEvent event) {

    }

    private void addSelectedProducts(Client client) {

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

}
