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

   /* private boolean tuileEstVide(Pane pane, int x, int y) {
        for (Node node : pane.getChildren()) {
            Bounds bounds = node.getBoundsInParent();
            if (bounds.getMinX() <= x && bounds.getMaxX() >= x &&
                    bounds.getMinY() <= y && bounds.getMaxY() >= y) {
                return false;
            }
        }
        return true;
    }

    /*private boolean tuileEstVide(Pane pane, int x, int y) {
        ObservableList<Node> nodes = pane.getChildren();
        for (Node node : nodes) {
            Bounds bounds = node.getBoundsInParent();
            if (bounds.intersects(x, y, 1, 1)) {
                return false; // si l'emplacement est occupé, retourne false
            }
        }
        return true; // si l'emplacement est vide, retourne true
    }


    */


    public void placerUneTour() {
        panePrincipal.setOnMouseClicked(event -> {
            if (ajoutTourEnCours && boutonArthemis.isSelected()) {
                double mouseX = event.getX();
                double mouseY = event.getY();
                Pane toursPane = new Pane();
                panePrincipal.getChildren().add(toursPane);


                // Convertir les coordonnées du clic de souris en position sur le TilePane
                int tourX = (int) (mouseX / tilePane.getTileWidth());
                int tourY = (int) (mouseY / tilePane.getTileHeight());

                // Calculer les coordonnées réelles du coin supérieur gauche de la tuile
                double tileX = tourX * tilePane.getTileWidth();
                double tileY = tourY * tilePane.getTileHeight();

                // Vérifier si la case correspondante est valide et aucune tour n'est présente
                // Créer la tour à l'emplacement du clic
                if (evt.getTerrain().get(tourY * 30 + tourX) == 114 && !evt.isTourPresent(tourX, tourY)) {
                    Tour t;

                    if (typeTourSelectionne.equals("Arthémis")) {
                        t = new Artémis((int) tileX, (int) tileY, evt);
                        // Ajouter la tour au modèle
                        evt.ajouterTour(t);
                        // Créer l'élément graphique de la tour

                    } else {
                        System.out.println("les autres tours");
                        // Créez d'autres types de tours en fonction de la sélection
                    }
                }

                ajoutTourEnCours = false; // Réinitialiser l'état d'ajout de tour
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










}









