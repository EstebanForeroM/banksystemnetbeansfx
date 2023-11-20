package com.finalproject.frameworks.UILogic.controllers;

import com.finalproject.frameworks.Services;
import com.finalproject.useCases.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogWindowController implements Initializable {

    private TextField textFieldDialog;

    @FXML
    private TextField ressposeDialog;

    @FXML
    private Button accept;

    @FXML
    private Button cancel;
    PasswordWindowController passwordWindowController = new PasswordWindowController();
    Token token = passwordWindowController.getUserToken();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}