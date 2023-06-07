package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.vue.VueInventaire;
import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

// Créer une BufferedImage de 100 pixels de


import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.control.Tooltip;





public class HelloController implements Initializable {


    @FXML
    private Pane panePrincipal;

    @FXML
    private TilePane tilePane;

    @FXML
    private Circle testCercleEnnemi;

    @FXML
    private ImageView imageTourArthemis;

    @FXML
    private RadioButton boutonArthemis;

    @FXML
    private Button boutonAjouterTour;

    @FXML
    private Label argent;

    @FXML
    private Label pv;

    @FXML
    private VBox vboutique;

    @FXML
    private ImageView boutique_bg;

    private int temps;

    private Terrain terrain;

    private Environnement evt;

    private VueTerrain vueTerrain;

    private VueInventaire vueInventaire;
    private Timeline gameLoop;
    
    private ListObsEnnemis listenerEnnemis;

    private Ennemi ennemi;

    private ListObsProjectile listenersProjectiles;
    private ListObsTours listenersTours;

    private Tour tourEnCoursAjout ;
    private boolean ajoutTourEnCours = false;
    private String typeTourSelectionne;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Chargement de l'environnement et du Terrain
        this.evt = new Environnement();
        this.vueTerrain = new VueTerrain(tilePane, evt.getTerrain());

        //Chargement de l'inventaire
        this.vueInventaire = new VueInventaire(imageTourArthemis, boutonArthemis, boutonAjouterTour, panePrincipal, tilePane, vboutique, boutique_bg, evt);

        //Listeners
        listenerEnnemis = new ListObsEnnemis(panePrincipal);
        this.listenersProjectiles = new ListObsProjectile(panePrincipal);
        this.listenersTours = new ListObsTours(panePrincipal);

        this.evt.getEnnemis().addListener(listenerEnnemis);
        this.evt.getProjectiles().addListener(listenersProjectiles);
        this.evt.getTours().addListener(listenersTours);

        this.pv.textProperty().bind(this.evt.getJoueur().pvProperty().asString());
        this.argent.textProperty().bind(this.evt.getJoueur().argentProperty().asString());

        //Test pour affichage de base
        Tour tour = new Artémis(12 * 32, 13 * 32, evt);
        //Tour tour1 = new Artémis(17*32,8*32,evt);
        ennemi = new Ennemi(evt);
        evt.ajouterEnnemi(ennemi);
        this.evt.ajouterTour(tour);
        //this.evt.ajouterTour(tour1);


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
//                        if (evt.getEnnemis().get(i).estArriveAuBout()) {
//                            System.out.println("fini");
//                            gameLoop.stop();
//                        }
//                        else{
                        evt.getEnnemis().get(i).seDeplace();
//                        }
                 }
                    for (Tour tour: this.evt.getTours()) {
                        if(temps% 100 == 0)
                            tour.attaque();
                    }

                    for (int i = this.evt.getProjectiles().size()-1; i >= 0 ;  i--) {
                       this.evt.getProjectiles().get(i).avance();
                    }

                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


}