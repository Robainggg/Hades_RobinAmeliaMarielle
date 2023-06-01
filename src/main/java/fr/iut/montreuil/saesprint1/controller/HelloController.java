package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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


public class HelloController implements Initializable {

    @FXML
    private TilePane tilePane;

    @FXML
    private Pane panePrincipal;

    @FXML
    private ImageView boutonPause;

    @FXML
    private ImageView boutonDepause;


     @FXML
    private Image spriteennemi;


    @FXML
    private Circle testCercleEnnemi;

    private int temps;

    private Terrain terrain;
    private Environnement evt;

    private VueTerrain vueTerrain;

    private Timeline gameLoop;

    private ListObsEnnemis listenerEnnemis;

    private Ennemi ennemi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boutonPause.setOnMouseClicked(e -> gameLoop.stop());
        boutonDepause.setOnMouseClicked(e -> gameLoop.play());
        this.evt = new Environnement();
        this.vueTerrain = new VueTerrain(tilePane, evt.getTerrain());
        listenerEnnemis = new ListObsEnnemis(panePrincipal);
        this.evt.getEnnemis().addListener(listenerEnnemis);
        ennemi = new Ennemi(evt);
        evt.ajouterEnnemi(ennemi);


        //position sur le terrain * 32 pour la vue
        Tour tour = new Artémis(12*32,10*32,evt);

        evt.ajouterTour(tour);
        //creerUneTour(tour);


        initAnimation();
        gameLoop.play();

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
                (ev -> {
                    if(temps%100 == 0)
                        evt.ajouterEnnemi(new Ennemi(evt));
                    for(int i = 0; i<this.evt.getEnnemis().size();i++){
                        if (evt.getEnnemis().get(i).estArriveAuBout()) {
                            System.out.println("fini");
                            gameLoop.stop();
                    } else {
                        evt.getEnnemis().get(i).seDeplace();
                        }

                    }
                    for(int i = 0 ; i< this.evt.getTours().size();i++)
                        evt.getTours().get(i).attaque();

                    //System.out.println(ennemiTesté.estArrivé() + " ennemi a pour coordonnées: " + ennemiTesté.getCoordX() + " , " + ennemiTesté.getCoordY() + " et pour destination " + (ennemiTesté.getProchaineCase().getI()*32-16) + " ," + (ennemiTesté.getProchaineCase().getJ()*32-16));


                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

//    void creerUneTour(Tour tour){
//
//        Rectangle t = new Rectangle(32,32);
//
//        if(tour instanceof Artémis){
//            Artémis artémis = (Artémis) tour;
//
//            Circle c = new Circle(((Artémis) tour).getPortée()*32);
//            c.setOpacity(0.2);
//            c.setFill(Color.PINK);
//            c.translateXProperty().bind(tour.centreTourX());
//            c.translateYProperty().bind(tour.centreTourY());
//            panePrincipal.getChildren().add(c);
//
//            t.setFill(Color.PINK);
//            t.translateXProperty().bind(tour.getXProperty());
//            t.translateYProperty().bind(tour.getYProperty());
//            panePrincipal.getChildren().add(t);
//            t.setId(tour.getId());
//
//        }
//
//        else{
//            t.setFill(Color.PINK);
//            t.translateXProperty().bind(tour.getXProperty());
//            t.translateYProperty().bind(tour.getYProperty());
//            panePrincipal.getChildren().add(t);
//            t.setId(tour.getId());
//        }

    }




    