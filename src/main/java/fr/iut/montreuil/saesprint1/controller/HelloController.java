package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import fr.iut.montreuil.saesprint1.modele.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

// Créer une BufferedImage de 100 pixels de


import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @FXML
    private Pane panePrincipal;

    @FXML
    private TilePane tilePane;

    @FXML
    private Circle testCercleEnnemi;

    private Environnement environnement;


    private Terrain terrain;

    private VueTerrain vueTerrain;

    private Timeline gameLoop;

    private int temps;

    private Ennemi ennemiTesté;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.terrain=new Terrain();
        this.vueTerrain = new VueTerrain(tilePane, terrain);
        ennemiTesté = new Ennemi(terrain);
        testCercleEnnemi.translateXProperty().bind(ennemiTesté.coordXProperty());
        testCercleEnnemi.translateYProperty().bind(ennemiTesté.coordYProperty());

        //position sur le terrain * 32 pour la vue
        this.environnement = new Environnement(this.terrain);
        Tour tour = new Artémis(12*32,2*32,environnement);

//        Circle c = new Circle(4*32);
//        c.setFill(Color.PINK);
//        c.translateXProperty().bind(tour.getXProperty());
//        c.translateYProperty().bind(tour.getYProperty());
//        panePrincipal.getChildren().add(c);

        environnement.ajouterTour(tour);
        creerUneTour(tour);
        environnement.ajouterEnnemi(ennemiTesté);

        initAnimation();
        gameLoop.play();
        this.terrain.afficheTableau();

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.017),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    if(temps==928){
                        System.out.println("fini");
                        gameLoop.stop();
                    }
                    else if(temps%2 == 0){
                        System.out.println("un tour");
                        System.out.println(this.environnement.getEnnemis().size());
                        ennemiTesté.setCoordX(ennemiTesté.getCoordX()+1);
                        for (Tour tour: this.environnement.getTours()) {
                            tour.attaque();
                        }
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    void creerUneTour(Tour tour){

        Rectangle t = new Rectangle(32,32);

        if(tour instanceof Artémis){}




        Circle c = new Circle(4*32);
        c.setOpacity(0.2);
        c.setFill(Color.PINK);
        c.translateXProperty().bind(tour.centreTourX());
        c.translateYProperty().bind(tour.centreTourY());
        panePrincipal.getChildren().add(c);

        t.setFill(Color.ORANGE);
        t.translateXProperty().bind(tour.getXProperty());
        t.translateYProperty().bind(tour.getYProperty());
        panePrincipal.getChildren().add(t);
        t.setId(tour.getId());

    }

}