package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Tours.*;
import javafx.animation.PauseTransition;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javax.swing.*;

import static fr.iut.montreuil.saesprint1.modele.Tours.Artémis.coutArtémis;
import static fr.iut.montreuil.saesprint1.modele.Tours.Dionysos.coutDionysos;
import static fr.iut.montreuil.saesprint1.modele.Tours.Déméter.coutDéméter;
import static fr.iut.montreuil.saesprint1.modele.Tours.Poséidon.coutPoséidon;

public class VueInventaire {

    private ImageView imageTourArthemis;
    private ImageView imageTourPoséidon;
    private ImageView imageTourDéméter;

    private ImageView imageTourDionysos;
    private RadioButton boutonArthemis;
    private RadioButton boutonPoséidon;
    private RadioButton boutonDemeter;
    private RadioButton boutonDionysos;
    private ToggleGroup groupeRadio;
    private Button boutonAjouterTour;
    private Pane panePrincipal;
    private TilePane tilePane;

    private ImageView pieces1;
    private ImageView pieces2;

    private Label argentItem;

    private Label nomItem;

    private Environnement evt;

    private ImageView imageView;

    private VBox vboutique;

    private Pane boutique_pane;

    private ImageView boutique_bg;


    private String selectedType;
    private boolean boutonRadioSelectionne = false;


    //this.vueInventaire = new VueInventaire(imageTourArthemis, imageTourPoséidon, imageTourDéméter, imageTourDionysos, boutonArthemis,  boutonPoséidon, boutonDéméter, boutonDionysos, groupeRadio, boutonAjouterTour, pieces, pieces2, argentItem, nomItem, panePrincipal, tilePane, vboutique, boutique_bg, evt);
    public VueInventaire(ImageView imageTourArt, ImageView imageTourPos, ImageView imageTourDem, ImageView imageTourDio, RadioButton boutonArt, RadioButton boutonPos, RadioButton boutonDem, RadioButton boutonDio, ToggleGroup groupeRadio, Button boutonAjtTour, ImageView p1, ImageView p2, Label ai, Label ni, Pane pane, TilePane tp, VBox vboutique,  ImageView boutique_bg, Environnement evt ) {
        this.imageTourArthemis = imageTourArt;
        this.imageTourPoséidon = imageTourPos;
        this.imageTourDéméter = imageTourDem;
        this.imageTourDionysos = imageTourDio;
        this.boutonArthemis = boutonArt;
        this.boutonPoséidon = boutonPos;
        this.boutonDemeter = boutonDem;
        this.boutonDionysos = boutonDio;
        this.groupeRadio = groupeRadio;
        this.boutonAjouterTour = boutonAjtTour;
        this.pieces1 = p1;
        this.pieces2 = p2;
        this.argentItem =ai;
        this.nomItem = ni;
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
        Image tourDémeter = new Image(getClass().getResourceAsStream("/images/Tower-PNG-Image.png"));
        imageTourDéméter.setImage(tourDémeter);
        Image tourDionysos = new Image(getClass().getResourceAsStream("/images/Tower-PNG-Image.png"));
        imageTourDionysos.setImage(tourDionysos);
        Image background = new Image(getClass().getResourceAsStream("/images/boutique.png"));
        Image pieces = new Image(getClass().getResourceAsStream("/images/png_coins.jpg"));
        pieces1.setImage(pieces);
        pieces2.setImage(pieces);
        boutique_bg.setImage(background);

    }


    public void placerDesTours() {
        placerUneTour();
    }
    private void placerUneTour() {
        boutonArthemis.setToggleGroup(groupeRadio);
        boutonArthemis.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedType = "Arthémis";
                //boutonPoséidon.setSelected(false);
                boutonRadioSelectionne = true;
                mettreAJourLabel();
                afficherTour();
            }

        });

        boutonPoséidon.setToggleGroup(groupeRadio);
        boutonPoséidon.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedType = "Poséidon";
                //boutonArthemis.setSelected(false);
                boutonRadioSelectionne = true;
                mettreAJourLabel();
                afficherTour();
           }



        });
        boutonDemeter.setToggleGroup(groupeRadio);
        boutonDemeter.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedType = "Déméter";
                //boutonPoséidon.setSelected(false);
                boutonRadioSelectionne = true;
                mettreAJourLabel();
                afficherTour();
            }

        });

        boutonDionysos.setToggleGroup(groupeRadio);
        boutonDionysos.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedType = "Dionysos";
                //boutonPoséidon.setSelected(false);
                boutonRadioSelectionne = true;
                mettreAJourLabel();
                afficherTour();
            }

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
            if (boutonArthemis.isSelected() || boutonPoséidon.isSelected() || boutonDemeter.isSelected()|| boutonDionysos.isSelected()) {
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

                            else if (selectedType.equals("Déméter")){
                                t = new Déméter((int) tileX, (int) tileY, evt);
                                this.evt.ajouterTour(t);
                            }

                            else if (selectedType.equals("Dionysos")){
                                Dionysos d = new Dionysos((int) tileX, (int) tileY, evt);
                                this.evt.ajouterTour(d);
                            }
                        }
                    }


                    nomItem.setText("___________________________");
                    argentItem.setText("___");
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

    private boolean tourExiste ( int tourX, int tourY){
        for (Tour tour : evt.getTours()) {
            int x = tour.getX() / Tour.tailleCase;
            int y = tour.getY() / Tour.tailleCase;
            if (x == tourX && y == tourY) {
                return true;
            }
        }
        return false;
    }

    private void mettreAJourLabel() {

        RadioButton selectedButton = (RadioButton) groupeRadio.getSelectedToggle();
        if (selectedButton != null) {
            String tourName = selectedButton.getText();
            nomItem.setText(getTypeItem(tourName));
            argentItem.setText(""+ getPrixTour(tourName));
        }
    }

    private String getTypeItem(String tourName) {
        if (tourName.equals("Arthémis")) {
            return "TOUR ARTÉMIS";
        } else if (tourName.equals("Poséidon")) {
            return "TOUR POSÉIDON";
        }
        else if (tourName.equals("Déméter"))
            return "TOUR DÉMÉTER";
        else if (tourName.equals("Dionysos"))
            return "TOUR DIONYSOS";
        return "___________________________";
    }


    private int getPrixTour(String tourName) {
        // Retourner le prix de la tour en fonction de son nom
        if (tourName.equals("Arthémis")) {
            return coutArtémis;
        } else if (tourName.equals("Poséidon")) {
            return coutPoséidon;
        } else if (tourName.equals("Déméter")){
            return coutDéméter;
        }else if (tourName.equals("Dionysos")){
            return coutDionysos;
        }
        return 0;
    }


}
