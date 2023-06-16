package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.HelloApplication;
import fr.iut.montreuil.saesprint1.vue.VueMenu;
import fr.iut.montreuil.saesprint1.vue.VueRegles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRegles implements Initializable {

    @FXML
    private StackPane background;

    @FXML
    private Pane pane;

    @FXML
    private ImageView fond;

    @FXML
    private Label regles;

    @FXML
    private Button lancerJeu;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        VueRegles vr = new VueRegles(background, pane, fond, regles, lancerJeu);
        lancerJeu.setOnAction(event -> {
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


