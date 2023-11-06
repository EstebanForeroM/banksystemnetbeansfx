package com.finalproject.frameworks;

import com.finalproject.controllers.AppController;
import com.finalproject.controllers.ClientRepository;
import com.finalproject.controllers.ProductRepository;
import com.finalproject.frameworks.repositoryLogic.TextFileClientRepository;
import com.finalproject.frameworks.repositoryLogic.TextFileProductRepository;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFunctionality extends Application {

    private static Scene scene;
    public static AppController appController;

    public static void main(String[] args) {
        ClientRepository clientRepository = new TextFileClientRepository("src\\data\\users\\users.txt");
        ProductRepository productRepository = new TextFileProductRepository("src\\data\\products");
        appController = new AppController(clientRepository, productRepository);
        
        //here we start the app
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("initialWindow"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainFunctionality.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
