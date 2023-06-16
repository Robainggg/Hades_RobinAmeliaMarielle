package fr.iut.montreuil.saesprint1;

import fr.iut.montreuil.saesprint1.controller.fenetres.ControllerVictoire;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PanneauVictoire extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(PanneauVictoire.class.getResource("victoire.fxml"));
        Parent root = fxmlLoader.load();

        ControllerVictoire controller = fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root, 1160, 740);

        primaryStage.setTitle("Les Enfers sont sous contrôle ! ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
