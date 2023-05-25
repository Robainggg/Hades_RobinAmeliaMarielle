package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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

    private int temps;

    private Terrain terrain;

    private VueTerrain vueTerrain;

    private Timeline gameLoop;

    private Environnement environnement;

    private Ennemi ennemi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.terrain=new Terrain();
        this.vueTerrain = new VueTerrain(tilePane, terrain);
        ennemi = new Ennemi(terrain);
        testCercleEnnemi.translateXProperty().bind(ennemi.coordXProperty());
        testCercleEnnemi.translateYProperty().bind(ennemi.coordYProperty());

        //position sur le terrain * 32 pour la vue
        this.environnement = new Environnement(this.terrain);
        Tour tour = new Artémis(12*32,2*32,environnement);

        environnement.ajouterTour(tour);
        creerUneTour(tour);
        environnement.ajouterEnnemi(ennemi);


        initAnimation();
        gameLoop.play();

        this.terrain.afficheTableau();

    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
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
                        if(ennemi.estArrivé()) {
                            if(ennemi.getBfs().getParcours().size()==0)
                                gameLoop.stop();
                            ennemi.changeProchaineCase();
                            System.out.println(ennemi.getProchaineCase());
                            if(ennemi.getProchaineCase() != null)
                                ennemi.definirDirection();
                            System.out.println(ennemi.getDirection());
                        }
                        if(ennemi.getDirection() == 'd'){
                            ennemi.setCoordX(ennemi.getCoordX()+2);
                        }
                        else if (ennemi.getDirection() == 'g'){
                            ennemi.setCoordX(ennemi.getCoordX()-2);
                        }
                        else if (ennemi.getDirection() == 'h'){
                            ennemi.setCoordY(ennemi.getCoordY()-2);
                        }
                        else if (ennemi.getDirection() == 'b'){
                            ennemi.setCoordY(ennemi.getCoordY()+2);
                        }
                        //System.out.println(ennemiTesté.estArrivé() + " ennemi a pour coordonnées: " + ennemiTesté.getCoordX() + " , " + ennemiTesté.getCoordY() + " et pour destination " + (ennemiTesté.getProchaineCase().getI()*32-16) + " ," + (ennemiTesté.getProchaineCase().getJ()*32-16));

                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

    void creerUneTour(Tour tour){

        Rectangle t = new Rectangle(32,32);

        if(tour instanceof Artémis){
            Artémis artémis = (Artémis) tour;

            Circle c = new Circle(((Artémis) tour).getPortée()*32);
            c.setOpacity(0.2);
            c.setFill(Color.PINK);
            c.translateXProperty().bind(tour.centreTourX());
            c.translateYProperty().bind(tour.centreTourY());
            panePrincipal.getChildren().add(c);

            t.setFill(Color.PINK);
            t.translateXProperty().bind(tour.getXProperty());
            t.translateYProperty().bind(tour.getYProperty());
            panePrincipal.getChildren().add(t);
            t.setId(tour.getId());

        }

        else{
            t.setFill(Color.PINK);
            t.translateXProperty().bind(tour.getXProperty());
            t.translateYProperty().bind(tour.getYProperty());
            panePrincipal.getChildren().add(t);
            t.setId(tour.getId());
        }

    }




}