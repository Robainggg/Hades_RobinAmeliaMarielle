package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Artémis;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Tour;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;                   
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

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

        boutonAjouterTour.setOnAction(event -> {
            ajoutTourEnCours = true;

        });

        panePrincipal.setOnMouseClicked(event -> {
            if (ajoutTourEnCours) {
                double mouseX = event.getX();
                double mouseY = event.getY();

                // Convertir les coordonnées du clic de souris en position sur le TilePane
                int tourX = (int) (mouseX / tilePane.getTileWidth());
                int tourY = (int) (mouseY / tilePane.getTileHeight());

                if (!tourExiste(tourX, tourY)) {
                    // Calculer les coordonnées réelles du coin supérieur gauche de la tuile
                    double tileX = tourX * tilePane.getTileWidth();
                    double tileY = tourY * tilePane.getTileHeight();

                    // Créer la tour à l'emplacement du clic
                    if (evt.getTerrain().get(tourY * 30 + tourX) == 114) {
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
            }
        });
    }

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


    public boolean tourExiste(int tourX, int tourY) {
        for (Tour tour : evt.getTours()) {
            int x = tour.getX() / Tour.tailleCase;
            int y = tour.getY() / Tour.tailleCase;
            if (x == tourX && y == tourY) {
                return true;
            }
        }
        return false;
    }




}









