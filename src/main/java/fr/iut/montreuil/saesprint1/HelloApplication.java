package fr.iut.montreuil.saesprint1;

import fr.iut.montreuil.saesprint1.controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1150, 740);

        HelloController controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);

        stage.setTitle("Aidez Hadès ! Empêchez les morts de sortir des enfers !");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}