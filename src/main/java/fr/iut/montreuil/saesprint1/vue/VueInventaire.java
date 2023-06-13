package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;
import fr.iut.montreuil.saesprint1.modele.Tours.Poséidon;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class VueInventaire {

    private ImageView imageTourArthemis;
    private ImageView imageTourPoséidon;
    private RadioButton boutonArthemis;
    private RadioButton boutonPoséidon;

    private ToggleGroup groupeRadio;
    private Button boutonAjouterTour;
    private Pane panePrincipal;
    private TilePane tilePane;

    private Environnement evt;

    private ImageView imageView;

    private VBox vboutique;

    private Pane boutique_pane;

    private ImageView boutique_bg;


    private String selectedType;
    private boolean boutonRadioSelectionne = false;


    public VueInventaire(ImageView imageTourArt, ImageView imageTourPos, RadioButton boutonArt, RadioButton boutonPos, ToggleGroup groupeRadio, Button boutonAjtTour, Pane pane, TilePane tp, VBox vboutique, ImageView boutique_bg, Environnement evt) {
        this.imageTourArthemis = imageTourArt;
        this.imageTourPoséidon = imageTourPos;
        this.boutonArthemis = boutonArt;
        this.boutonPoséidon = boutonPos;
        this.groupeRadio = groupeRadio;
        this.boutonAjouterTour = boutonAjtTour;
        this.panePrincipal = pane;
        this.tilePane = tp;
        this.boutique_bg = boutique_bg;
        this.evt = evt;
        chargerImage();
        this.placerDesTours();
        this.afficherCaractéristiquesArthémis();
        this.afficherCaractéristiquesPoséidon();


    }

    public void chargerImage() {
        Image tourArthemis = new Image(getClass().getResourceAsStream("/images/Tower-PNG-Image.png"));
        imageTourArthemis.setImage(tourArthemis);
        Image tourPoséidon = new Image(getClass().getResourceAsStream("/images/Tower-PNG-Image.png"));
        imageTourPoséidon.setImage(tourPoséidon);
        Image background = new Image(getClass().getResourceAsStream("/images/boutique.png"));
        boutique_bg.setImage(background);
    }


    public void placerDesTours() {
        placerUneTour();
    }
    private void placerUneTour() {
        boutonArthemis.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedType = "Arthémis";
                boutonPoséidon.setSelected(false);
                boutonRadioSelectionne = true;

            }
            afficherTour();
        });

        boutonPoséidon.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedType = "Poséidon";
                boutonArthemis.setSelected(false);
                boutonRadioSelectionne = true;
           }
            afficherTour();

        });


    }

        private void afficherTour(){
        if (boutonRadioSelectionne) {
            boutonAjouterTour.setOnAction(event -> {
                afficherTour2();
            });
        }
    }

        private void afficherTour2() {
            if (boutonArthemis.isSelected() || boutonPoséidon.isSelected()) {
                panePrincipal.setOnMouseClicked(event -> {
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

                            if (selectedType.equals("Arthémis")) {
                                t = new Artémis((int) tileX, (int) tileY, evt);
                                this.evt.ajouterTour(t);


                            } else if (selectedType.equals("Poséidon")) {
                                t = new Poséidon((int) tileX, (int) tileY, evt);
                                this.evt.ajouterTour(t);


                            }
                        }
                    }

                    boutonArthemis.setSelected(false);
                    boutonPoséidon.setSelected(false);
                    panePrincipal.setOnMouseClicked(null);

                    boutonRadioSelectionne = false;
                });

            }
        }



    public void afficherCaractéristiquesArthémis () {

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
    public void afficherCaractéristiquesPoséidon() {
        Tooltip tooltipPoséidon = new Tooltip();
        tooltipPoséidon.setText("Caractéristiques de la tour Poséidon :\nAttaque : 8\nPortée : 5");
        final boolean[] tooltipVisiblePoséidon = {false};

        imageTourPoséidon.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (!tooltipVisiblePoséidon[0]) {
                    Tooltip.install(imageTourPoséidon, tooltipPoséidon);
                    tooltipPoséidon.show(imageTourPoséidon, event.getScreenX(), event.getScreenY());
                    tooltipVisiblePoséidon[0] = true;

                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> {
                        tooltipPoséidon.hide();
                        Tooltip.uninstall(imageTourPoséidon, tooltipPoséidon);
                        tooltipVisiblePoséidon[0] = false;
                    });
                    pause.play();
                }
                event.consume();
            }
        });
    }

    public boolean tourExiste ( int tourX, int tourY){
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
