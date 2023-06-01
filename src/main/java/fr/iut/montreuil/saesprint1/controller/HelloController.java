package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.vue.VueInventaire;
import fr.iut.montreuil.saesprint1.vue.VueTerrain;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
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

    private VueTerrain vueTerrain;

    private VueInventaire vueInventaire;
    private Timeline gameLoop;

    private Environnement environnement;

    private Ennemi ennemi;

    private Tour tourEnCoursAjout ;
    private boolean ajoutTourEnCours = false;
    private String typeTourSelectionne;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.terrain=new Terrain();
        this.vueTerrain = new VueTerrain(tilePane, terrain);
        this.vueInventaire = new VueInventaire(imageTourArthemis);
        ennemi = new Ennemi(terrain);
        testCercleEnnemi.translateXProperty().bind(ennemi.coordXProperty());
        testCercleEnnemi.translateYProperty().bind(ennemi.coordYProperty());

        //position sur le terrain * 32 pour la vue
        this.environnement = new Environnement(this.terrain);
        Tour tour = new Artémis(12*32,2*32,environnement);

        environnement.ajouterTour(tour);
        creerUneTour(tour);
        environnement.ajouterEnnemi(ennemi);
        //ajouter les images dans la boutique;




        initAnimation();
        gameLoop.play();

        this.terrain.afficheTableau();

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
                if (environnement.getTerrain().get(tourY * 30 + tourX) == 114) {
                    Tour t;

                    if (typeTourSelectionne.equals("Arthémis")) {
                        t = new Artémis((int) tileX, (int) tileY, environnement);
                        // Ajouter la tour au modèle
                        environnement.ajouterTour(t);
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

    private void afficherCaracteristiquesTour(double x, double y, String nom, int puissance, int portee) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(nom + "\nPuissance : " + puissance + "\nPortée : " + portee);
        Tooltip.install(imageTourArthemis, tooltip);
        tooltip.show(imageTourArthemis, x, y);
    }

}