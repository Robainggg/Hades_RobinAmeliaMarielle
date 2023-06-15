package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.HelloApplication;
import fr.iut.montreuil.saesprint1.vue.VueDéfaite;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDéfaite implements Initializable {

    @FXML
    private StackPane paneDéfaite;
    @FXML
    private Button recommencerAprèsDéfaite;
    @FXML
    private ImageView imageDéfaite;
    private Stage primaryStage; // Référence à la Stage principale de App1

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VueDéfaite v = new VueDéfaite(imageDéfaite, paneDéfaite);

        recommencerAprèsDéfaite.setOnAction(event -> {
            HelloApplication app2 = new HelloApplication();
            try {
                app2.start(new Stage());
                this.primaryStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

