package fr.iut.montreuil.saesprint1;

import fr.iut.montreuil.saesprint1.controller.ControllerDéfaite;
import fr.iut.montreuil.saesprint1.controller.ControllerMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PanneauDéfaite extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("defaite.fxml"));
        Parent root = fxmlLoader.load();

        ControllerDéfaite controller = fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root, 1160, 740);

        primaryStage.setTitle("Hadès a perdu son royaume");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
