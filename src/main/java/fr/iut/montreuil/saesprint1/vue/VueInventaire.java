package fr.iut.montreuil.saesprint1.vue;

import eu.hansolo.tilesfx.Tile;
import fr.iut.montreuil.saesprint1.modele.Artémis;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Tour;
import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class VueInventaire {


    private ImageView imageTourArthemis;
    private RadioButton boutonArthemis;
    private Button boutonAjouterTour;
    private Pane panePrincipal;
    private TilePane tilePane;

    private Environnement evt;

    private ImageView imageView;

    private VBox vboutique;

    private Pane boutique_pane;

    private ImageView boutique_bg;


    private Tour tourEnCoursAjout;
    private boolean ajoutTourEnCours = false;
    private String typeTourSelectionne;

    public VueInventaire(ImageView imageTourArt, RadioButton boutonArt, Button boutonAjtTour, Pane pane, TilePane tp, VBox vboutique, ImageView boutique_bg, Environnement evt) {
        this.imageTourArthemis = imageTourArt;
        this.boutonArthemis = boutonArt;
        this.boutonAjouterTour = boutonAjtTour;
        this.panePrincipal = pane;
        this.tilePane = tp;
        this.boutique_bg = boutique_bg;
        this.evt = evt;
        chargerImage();
        this.placerDesTours();
        this.afficherCaractéristiquesArthémis();

    }

    public void chargerImage() {
        Image image = new Image(getClass().getResourceAsStream("/images/Tower-PNG-Image.png"));
        imageTourArthemis.setImage(image);
        Image image2 = new Image(getClass().getResourceAsStream("/images/boutique.png"));
        boutique_bg.setImage(image2);
    }


    public void placerDesTours() {
        selectionTypeDeTour();
        placerUneTour();
    }

    private void selectionTypeDeTour() {

        boutonArthemis.setOnAction(event -> {
            if (boutonArthemis.isSelected()) {
                typeTourSelectionne = "Arthémis";
            }
        });
    }







    private void placerUneTour() {
        panePrincipal.setOnMouseClicked(event -> {

            double mouseX = event.getX();
            double mouseY = event.getY();
            // Obtenir l'indice de la tuile à partir des coordonnées du clic de souris
            int tuileX = (int) (mouseX / 32);
            int tuileY = (int) (mouseY / 32);
            int tuileIndex = tuileY * 30 + tuileX;

            // Calculer les coordonnées réelles du coin supérieur gauche de la tuile
            double tileX = tuileX * 32;
            double tileY = tuileY * 32;

            if (ajoutTourEnCours) {

                        if (evt.getTerrain().get(tuileIndex) == 114) {
                            // Ajouter une nouvelle tour sur la tuile cliqué
                            Tour t;
                            if (typeTourSelectionne.equals("Arthémis")) {
                                t = new Artémis((int) tileX, (int) tileY, evt);
                                // Ajouter la tour au modèle
                                this.evt.ajouterTour(t);
                                // Créer l'élément graphique de la tour
                                //creerUneTour(t);
                            } else {
                                System.out.println("les autres tours");
                                // Créez d'autres types de tours en fonction de la sélection
                            }
                        }

                        ajoutTourEnCours = false; // Réinitialiser l'état d'ajout de tour
                    }

            event.consume();


        });
    }


                /*
                double mouseX = event.getX();
                double mouseY = event.getY();

                // Obtenir l'indice de la tuile à partir des coordonnées du clic de souris
                int tuileX = (int) (mouseX / 32);
                int tuileY = (int) (mouseY / 32);
                int tuileIndex = tuileY * 30 + tuileX;

                // Calculer les coordonnées réelles du coin supérieur gauche de la tuile
                double tileX = tuileX * 32;
                double tileY = tuileY * 32;

                // Vérifier s'il y a déjà une tour sur la tuile cliqué
                if (!tuileContientImage(tileX, tileY) && evt.getTerrain().get(tuileIndex) == 114) {
                    // Ajouter une nouvelle tour sur la tuile cliqué
                    Tour t;
                    if (typeTourSelectionne.equals("Arthémis")) {
                        t = new Artémis((int) tileX, (int) tileY, evt);
                        // Ajouter la tour au modèle
                        this.evt.ajouterTour(t);
                        // Créer l'élément graphique de la tour
                        //creerUneTour(t);
                    } else {
                        System.out.println("les autres tours");
                        // Créez d'autres types de tours en fonction de la sélection
                    }
                }

                ajoutTourEnCours = false; // Réinitialiser l'état d'ajout de tour
            }
        });

      /*  panePrincipal.setOnMouseClicked(event -> {
            if (ajoutTourEnCours) {
                double mouseX = event.getX();
                double mouseY = event.getY();

                int tourX = (int)((mouseX - panePrincipal.getLayoutX()) / 32);
                int tourY = (int)((mouseY - panePrincipal.getLayoutX()) / 32);
                ///int tourX = (int) (mouseX / tilePane.getTileWidth());
                //int tourY = (int) (mouseY / tilePane.getTileHeight());


                if (evt.getTerrain().get(tourY * 30 + tourX) != 114) {
                    System.out.println("Impossible de placer une tour sur cette tuile.");
                    return;
                }

                double tileX = tourX * tilePane.getTileWidth();
                double tileY = tourY * tilePane.getTileHeight();

                Tour t;

                if (typeTourSelectionne.equals("Arthémis")) {
                    t = new Artémis((int) tileX, (int) tileY, evt);
                    evt.ajouterTour(t);
                } else {
                    System.out.println("les autres tours");
                    // Créez d'autres types de tours en fonction de la sélection
                }

                ajoutTourEnCours = false;
            }
        });

        panePrincipal.setOnMouseClicked(event -> {
            if (ajoutTourEnCours) {
                double mouseX = event.getX();
                double mouseY = event.getY();

                // Obtenir l'indice de la tuile à partir des coordonnées du clic de souris
                int tuileX = (int) (mouseX / tilePane.getTileWidth());
                int tuileY = (int) (mouseY / tilePane.getTileHeight());
                int tuileIndex = tuileY * 30 + tuileX;

                // Calculer les coordonnées réelles du coin supérieur gauche de la tuile
                double tileX = tuileX * tilePane.getTileWidth();
                double tileY = tuileY * tilePane.getTileHeight();

                // Vérifier s'il y a déjà une tour sur la tuile cliqué
                if (evt.getTour(tuileX, tuileY) == null && evt.getTerrain().get(tuileY * 30 + tuileX) == 114) {
                    // Ajouter une nouvelle tour sur la tuile cliqué
                    Tour t;

                    if (typeTourSelectionne.equals("Arthémis")) {
                        t = new Artémis((int) tileX, (int) tileY, evt);
                        // Ajouter la tour au modèle
                        this.evt.ajouterTour(t);
                        // Créer l'élément graphique de la tour
                        //creerUneTour(t);
                    } else {
                        System.out.println("les autres tours");
                        // Créez d'autres types de tours en fonction de la sélection
                    }
                }

                ajoutTourEnCours = false; // Réinitialiser l'état d'ajout de tour
            }
        });

      /*  boutonAjouterTour.setOnAction(event -> {
            ajoutTourEnCours = true;

        });

        panePrincipal.setOnMouseClicked(event -> {
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
                    if  (evt.getTerrain().get(tourY * 30 + tourX) == 114 && !caseOccupee(tourX, tourY)) {
                        Tour t;

                        if (typeTourSelectionne.equals("Arthémis")) {
                            t = new Artémis((int) tileX, (int) tileY, evt);
                            // Ajouter la tour au modèle
                            this.evt.ajouterTour(t);
                            // Créer l'élément graphique de la tour
                            //creerUneTour(t);
                        } else {
                            System.out.println("les autres tours");
                            // Créez d'autres types de tours en fonction de la sélection
                        }
                    }
                    ajoutTourEnCours = false; // Réinitialiser l'état d'ajout de tour
                }

        });*/



    /*private void placerUneTour() {
        boutonAjouterTour.setOnAction(event -> {
            ajoutTourEnCours = true;
        });
        panePrincipal.setOnMouseClicked(event -> {
            if (ajoutTourEnCours) {
                double mouseX = event.getX();
                double mouseY = event.getY();
                int tourX = (int) (mouseX / tilePane.getTileWidth());
                int tourY = (int) (mouseY / tilePane.getTileHeight());

                if (isTourPresent(tilePane, tourX, tourY)) {
                    System.out.println("Une tour est déjà présente à cet emplacement.");
                    return;
                }

                // Vérifier si la tuile cliquée est libre ou non
                if (evt.getTerrain().get(tourY * 30 + tourX) != 114) {
                    System.out.println("Impossible de placer une tour sur cette tuile.");
                    return;
                }

                double tileX = tourX * tilePane.getTileWidth();
                double tileY = tourY * tilePane.getTileHeight();

                Tour t;

                // Créer la tour à l'emplacement du clic
                if (typeTourSelectionne.equals("Arthémis")) {
                    t = new Artémis((int) tileX, (int) tileY, evt);
                    this.evt.ajouterTour(t);
                } else {
                    System.out.println("les autres tours");
                    // Créez d'autres types de tours en fonction de la sélection
                }

                ajoutTourEnCours = false;
            }
        });
        */



    public void afficherCaractéristiquesArthémis() {

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


    }










}









