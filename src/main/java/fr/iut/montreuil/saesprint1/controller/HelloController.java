package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Terrain;
import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

// Créer une BufferedImage de 100 pixels de


import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @FXML
    private TilePane tilePane;

     @FXML
     private ImageView ennemiTest;


    @FXML
    private Circle testCercleEnnemi;

    private Terrain terrain;

    private VueTerrain vueTerrain;

    private Timeline gameLoop;

    private int temps;

    private Ennemi ennemi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.terrain=new Terrain();
        this.vueTerrain = new VueTerrain(tilePane, terrain);
        ennemi = new Ennemi(terrain);
        ennemiTest.translateXProperty().bind(ennemi.coordXProperty());
        ennemiTest.translateYProperty().bind(ennemi.coordYProperty());
        initAnimation();
        gameLoop.play();

        this.terrain.afficheTableau();

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(ennemi.estArriveAuBout()){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else{
                        ennemi.seDeplace();
                        //System.out.println(ennemiTesté.estArrivé() + " ennemi a pour coordonnées: " + ennemiTesté.getCoordX() + " , " + ennemiTesté.getCoordY() + " et pour destination " + (ennemiTesté.getProchaineCase().getI()*32-16) + " ," + (ennemiTesté.getProchaineCase().getJ()*32-16));

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


}