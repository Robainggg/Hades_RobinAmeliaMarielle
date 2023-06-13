package fr.iut.montreuil.saesprint1;

import fr.iut.montreuil.saesprint1.controller.ControllerMenu;
import fr.iut.montreuil.saesprint1.vue.VueMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class MainMenu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = fxmlLoader.load();

        ControllerMenu controller = fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root, 1160, 740);

        primaryStage.setTitle("App1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }


}
    /*





    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1160, 740);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}*/

/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Créer le contenu de la fenêtre
        Button button = new Button("Lancer App2");
        button.setOnAction(event -> {
            // Créer une instance de la classe App2 et appeler sa méthode start()
            HelloApplication app2 = new HelloApplication();
            try {
                app2.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("App1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }*/



