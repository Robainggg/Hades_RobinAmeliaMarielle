package fr.iut.montreuil.saesprint1;


import fr.iut.montreuil.saesprint1.controller.fenetres.ControllerRegles;
import fr.iut.montreuil.saesprint1.controller.fenetres.ControllerMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuRegles  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuRegles.fxml"));
        Parent root = fxmlLoader.load();

        ControllerRegles controller = fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root, 1160, 740);

        primaryStage.setTitle("Hadès vs ses morts");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
