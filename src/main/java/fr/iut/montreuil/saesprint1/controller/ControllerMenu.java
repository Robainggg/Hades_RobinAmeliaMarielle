package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.HelloApplication;
import fr.iut.montreuil.saesprint1.MainMenu;
import fr.iut.montreuil.saesprint1.vue.VueMenu;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class ControllerMenu implements Initializable {


    @FXML
    private StackPane background;
    @FXML
    private Button launchApp2Button;

    @FXML
    private ImageView im;

    private Stage primaryStage; // Référence à la Stage principale de App1


    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VueMenu v = new VueMenu(im, background);
        launchApp2Button.setOnAction(event -> {
            HelloApplication app2 = new HelloApplication();
            try {
                app2.start(new Stage());
                primaryStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
/*
    @FXML
    private Pane background;
    @FXML
    private ImageView im;
    @FXML
    private Button play;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VueMenu v = new VueMenu(im, background);
    }

    @FXML
    void play(ActionEvent event){
        System.out.println("...");
    }*/













