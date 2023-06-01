package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

// Créer une BufferedImage de 100 pixels de


import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;


public class HelloController implements Initializable {


    @FXML
    private Pane panePrincipal;

    @FXML
    private TilePane tilePane;


    @FXML
    private Circle testCercleEnnemi;


    private Environnement evt;

    private VueTerrain vueTerrain;

    private Timeline gameLoop;

    private int temps;

    private ListObsEnnemis listenerEnnemis;

    private Ennemi ennemi;

    private ListObsProjectile listenersProjectiles;
    private ListObsTours listenersTours;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.evt = new Environnement();
        this.vueTerrain = new VueTerrain(tilePane, evt.getTerrain());
        listenerEnnemis = new ListObsEnnemis(panePrincipal);
        this.listenersProjectiles = new ListObsProjectile(panePrincipal);
        this.listenersTours = new ListObsTours(panePrincipal);

        this.evt.getEnnemis().addListener(listenerEnnemis);
        ennemi = new Ennemi(evt);
        evt.ajouterEnnemi(ennemi);
        this.evt.getProjectiles().addListener(listenersProjectiles);
        this.evt.getTours().addListener(listenersTours);

        Tour tour = new Artémis(12,10,evt);
        Tour tour1 = new Artémis(17,8,evt);

        this.evt.ajouterTour(tour);
        this.evt.ajouterTour(tour1);

        initAnimation();
        gameLoop.play();

        this.evt.getTerrain().afficheTableau();

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
                    if(this.temps % 100 == 0)
                        if(this.evt.getEnnemis().size() < 10) {
                            System.out.println("taille liste ennemis : " + evt.getEnnemis().size());
                            this.evt.ajouterEnnemi(new Ennemi(evt));
                        }
                    for(int i = 0; i < evt.getEnnemis().size();i++) {
                        if (evt.getEnnemis().get(i).estArriveAuBout()) {
                            System.out.println("fini");
                            gameLoop.stop();
                        } else {
                            evt.getEnnemis().get(i).seDeplace();
                            //System.out.println(ennemiTesté.estArrivé() + " ennemi a pour coordonnées: " + ennemiTesté.getCoordX() + " , " + ennemiTesté.getCoordY() + " et pour destination " + (ennemiTesté.getProchaineCase().getI()*32-16) + " ," + (ennemiTesté.getProchaineCase().getJ()*32-16));
                        }
                    }
                    for (Tour tour: this.evt.getTours()) {
                        if(temps%tour.getNbAttaques() == 0){
                            tour.attaque();
                        }
                    }

                    for (Projectile projectile : this.evt.getProjectiles()) {
                        projectile.avance();
                    }

                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


}