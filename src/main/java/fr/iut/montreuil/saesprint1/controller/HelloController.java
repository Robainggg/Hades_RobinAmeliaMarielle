package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    private ListObsProjectile listenersProjectiles;
    private ListObsTours listenersTours;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.terrain=new Terrain();
        this.vueTerrain = new VueTerrain(tilePane, terrain);
        this.environnement = new Environnement(this.terrain);
        this.listenersProjectiles = new ListObsProjectile(panePrincipal);
        this.listenersTours = new ListObsTours(panePrincipal);
        this.environnement.getProjectiles().addListener(listenersProjectiles);
        this.environnement.getTours().addListener(listenersTours);

        //Pour le test
        ennemi = new Ennemi(terrain);
        testCercleEnnemi.translateXProperty().bind(ennemi.coordXProperty());
        testCercleEnnemi.translateYProperty().bind(ennemi.coordYProperty());
        Tour tour = new Artémis(12,10,environnement);
        Tour tour1 = new Artémis(17,8,environnement);

        this.environnement.ajouterTour(tour);
        this.environnement.ajouterTour(tour1);
        this.environnement.ajouterEnnemi(ennemi);
        
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
                    else if (temps%4 == 0){
                        if(ennemi.estArrivé()) {
                            if(ennemi.getBfs().getParcours().size()==0)
                                gameLoop.stop();
                            ennemi.changeProchaineCase();
                            if(ennemi.getProchaineCase() != null)
                                ennemi.definirDirection();
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
                    }

                    for (Tour tour: this.environnement.getTours()) {
                        if(temps%tour.getNbAttaques() == 0){
                            tour.attaque();
                        }
                    }

                    for (Projectile projectile : this.environnement.getProjectiles()) {
                        projectile.avance();
                    }
                    
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
    

}