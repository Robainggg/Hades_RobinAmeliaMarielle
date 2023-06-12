package fr.iut.montreuil.saesprint1.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {
    @FXML
    private Pane background;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    this.background = new Pane();
    }
}
