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
        this.evt = new Environnement();
        this.vueTerrain = new VueTerrain(tilePane, evt.getTerrain());
        this.vueInventaire = new VueInventaire(imageTourArthemis);
        listenerEnnemis = new ListObsEnnemis(panePrincipal);
        this.listenersProjectiles = new ListObsProjectile(panePrincipal);
        //this.listenersTours = new ListObsTours(panePrincipal);

        this.evt.getEnnemis().addListener(listenerEnnemis);
        ennemi = new Ennemi(evt);
        evt.ajouterEnnemi(ennemi);
        this.evt.getProjectiles().addListener(listenersProjectiles);
        //this.evt.getTours().addListener(listenersTours);

        Tour tour = new Artémis(12,10,evt);
        Tour tour1 = new Artémis(17,8,evt);

        this.evt.ajouterTour(tour);
        this.evt.ajouterTour(tour1);


        initAnimation();
        gameLoop.play();

        this.evt.getTerrain().afficheTableau();

        //Placer des tours
        boutonArthemis.setOnAction(event -> {
            if (boutonArthemis.isSelected()) {
                typeTourSelectionne = "Arthémis";
            }
        });


        /*imageTourArthemis.setOnMousePressed(event -> {
            if (event.isSecondaryButtonDown()) {
                Tooltip tooltip = new Tooltip();
                tooltip.setText("Caractéristiques de la tour Arthémis :\nAttaque : 10\nPortée : 4");
                Tooltip.install(imageTourArthemis, tooltip);
                tooltip.show(imageTourArthemis, event.getScreenX(), event.getScreenY());
                event.consume();
            }
        });
        imageTourArthemis.setOnMouseReleased(event -> {
            if (event.isSecondaryButtonDown()) {
                Tooltip tooltip = new Tooltip("Caractéristiques de la tour"); // Remplacez par les caractéristiques spécifiques de la tour
                Tooltip.install(imageTourArthemis, tooltip);
                tooltip.show(imageTourArthemis.getScene().getWindow(), event.getScreenX(), event.getScreenY());
                event.consume();
            }
        });*/


        Tooltip tooltip = new Tooltip();
        tooltip.setText("Caractéristiques de la tour Arthémis :\nAttaque : 10\nPortée : 4");
        final boolean[] tooltipVisible = {false};

        imageTourArthemis.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (!tooltipVisible[0]) {
                    Tooltip.install(imageTourArthemis, tooltip);
                    tooltip.show(imageTourArthemis, event.getScreenX(), event.getScreenY());
                    tooltipVisible[0] = true;

                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> {
                        tooltip.hide();
                        Tooltip.uninstall(imageTourArthemis, tooltip);
                        tooltipVisible[0] = false;
                    });
                    pause.play();
                }
                event.consume();
            }
        });


        boutonAjouterTour.setOnAction(event -> {
            ajoutTourEnCours = true;

        });

        tilePane.setOnMouseClicked(event -> {
            if (ajoutTourEnCours) {
                double mouseX = event.getX();
                double mouseY = event.getY();

                // Convertir les coordonnées du clic de souris en position sur le TilePane
                int tourX = (int) (mouseX / tilePane.getTileWidth());
                int tourY = (int) (mouseY / tilePane.getTileHeight());

                // Calculer les coordonnées réelles du coin supérieur gauche de la tuile
                double tileX = tourX * tilePane.getTileWidth();
                double tileY = tourY * tilePane.getTileHeight();

                // Créer la tour à l'emplacement du clic
                if (evt.getTerrain().get(tourY * 30 + tourX) == 114) {
                    Tour t;

                    if (typeTourSelectionne.equals("Arthémis")) {
                        t = new Artémis((int) tileX, (int) tileY, evt);
                        // Ajouter la tour au modèle
                        evt.ajouterTour(t);
                        // Créer l'élément graphique de la tour
                        creerUneTour(t);
                    } else {
                        System.out.println("les autres tours");
                        // Créez d'autres types de tours en fonction de la sélection
                    }
                }
                ajoutTourEnCours = false; // Réinitialiser l'état d'ajout de tour
            }
        });

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
                        }
                        else{
                            evt.getEnnemis().get(i).seDeplace();
                            //System.out.println(ennemiTesté.estArrivé() + " ennemi a pour coordonnées: " + ennemiTesté.getCoordX() + " , " + ennemiTesté.getCoordY() + " et pour destination " + (ennemiTesté.getProchaineCase().getI()*32-16) + " ," + (ennemiTesté.getProchaineCase().getJ()*32-16));
                        }
                    }
                    for (Tour tour: this.evt.getTours()) {
                        if(temps% 50 == 0)
                            tour.attaque();
                    }

                    for (Projectile projectile : this.evt.getProjectiles()) {
                        projectile.avance();
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