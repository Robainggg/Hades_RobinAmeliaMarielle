package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.controller.HelloController;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Joueur;
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
//import static fr.iut.montreuil.saesprint1.modele.Tours.Dionysos.coutDionysos;
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

    private ImageView boutique_bg;
    private String selectedType;
    private boolean boutonRadioSelectionne = false;

    final static Image artemis = new Image(VueInventaire.class.getResourceAsStream("/images/tours/artemisHD.png"));
    final static Image demeter = new Image(VueInventaire.class.getResourceAsStream("/images/tours/demeterHD.png"));
    final static Image dionysos = new Image(VueInventaire.class.getResourceAsStream("/images/tours/dionysosHD.png"));
    final static Image poseidon = new Image(VueInventaire.class.getResourceAsStream("/images/tours/poseidonHD.png"));

    public VueInventaire(ImageView imageTourArt, ImageView imageTourPos, ImageView imageTourDem, ImageView imageTourDio, RadioButton boutonArt, RadioButton boutonPos, RadioButton boutonDem, RadioButton boutonDio, ToggleGroup groupeRadio, Button boutonAjtTour, ImageView p1, ImageView p2, Label ai, Label ni, Pane pane, TilePane tp, VBox vboutique, ImageView boutique_bg, Environnement evt) {
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
        this.argentItem = ai;
        this.nomItem = ni;
        this.panePrincipal = pane;
        this.tilePane = tp;
        this.boutique_bg = boutique_bg;
        this.evt = evt;
        chargerImage();
        this.placerDesTours();

    }

    public void chargerImage() {
        Image tourArthemis = artemis;
        imageTourArthemis.setImage(tourArthemis);
        this.afficherCaractéristiquesTours(imageTourArthemis);

        Image tourPoséidon = poseidon;
        imageTourPoséidon.setImage(tourPoséidon);
        this.afficherCaractéristiquesTours(imageTourPoséidon);

        Image tourDémeter = demeter;
        imageTourDéméter.setImage(tourDémeter);
        this.afficherCaractéristiquesTours(imageTourDéméter);

        Image tourDionysos = dionysos;
        imageTourDionysos.setImage(tourDionysos);
        this.afficherCaractéristiquesTours(imageTourDionysos);

        Image background = new Image(getClass().getResourceAsStream("/images/boutique.png"));
        Image pieces = new Image(getClass().getResourceAsStream("/images/lespieces.png"));
        pieces1.setImage(pieces);
        pieces2.setImage(pieces);
        boutique_bg.setImage(background);

    }


    public void placerDesTours() {
        selectionTour();
    }

    private void selectionTour() {
        boutonArthemis.setToggleGroup(groupeRadio);
        boutonArthemis.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedType = "Arthémis";
                //boutonPoséidon.setSelected(false);
                boutonRadioSelectionne = true;
                mettreAJourLabel();
                poserTour();
            }

        });

        boutonPoséidon.setToggleGroup(groupeRadio);
        boutonPoséidon.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedType = "Poséidon";
                //boutonArthemis.setSelected(false);
                boutonRadioSelectionne = true;
                mettreAJourLabel();
                poserTour();
            }
        });
        boutonDemeter.setToggleGroup(groupeRadio);
        boutonDemeter.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedType = "Déméter";
                //boutonPoséidon.setSelected(false);
                boutonRadioSelectionne = true;
                mettreAJourLabel();
                poserTour();
            }

        });

        boutonDionysos.setToggleGroup(groupeRadio);
        boutonDionysos.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                selectedType = "Dionysos";
                //boutonPoséidon.setSelected(false);
                boutonRadioSelectionne = true;
                mettreAJourLabel();
                poserTour();
            }

        });


    }

    private void poserTour() {
        if (boutonRadioSelectionne) {
            boutonAjouterTour.setOnAction(event -> {
                CréationTour();
            });
        }
    }

    private void CréationTour() {
        if (boutonArthemis.isSelected() || boutonPoséidon.isSelected() || boutonDemeter.isSelected() || boutonDionysos.isSelected()) {
            panePrincipal.setOnMouseClicked(event -> {
                double mouseX = event.getX();
                double mouseY = event.getY();


                // Convertir les coordonnées du clic de souris en position sur le TilePane
                int tourX = (int) (mouseX / tilePane.getTileWidth());
                int tourY = (int) (mouseY / tilePane.getTileHeight());

                if (!evt.tourExiste(tourX, tourY)) {
                    // Calculer les coordonnées réelles du coin supérieur gauche de la tuile
                    double tileX = tourX * tilePane.getTileWidth();
                    double tileY = tourY * tilePane.getTileHeight();

                    // Créer la tour à l'emplacement du clic
                    if (evt.getTerrain().get(tourY * 30 + tourX) == 371) {
                        Tour t;


                        if (selectedType.equals("Arthémis")) {
                            t = new Artémis((int) tileX, (int) tileY, evt);
                            this.evt.ajouterTour(t);


                        } else if (selectedType.equals("Poséidon")) {
                            t = new Poséidon((int) tileX, (int) tileY, evt);
                            this.evt.ajouterTour(t);
                        } else if (selectedType.equals("Déméter")) {
                            t = new Déméter((int) tileX, (int) tileY, evt);
                            this.evt.ajouterTour(t);
                        } else if (selectedType.equals("Dionysos")) {
                            Dionysos d = new Dionysos((int) tileX, (int) tileY, evt);
                            this.evt.ajouterTour(d);
                        }
                    }
                }


                nomItem.setText("___________________________");
                argentItem.setText("___");
                panePrincipal.setOnMouseClicked(null);
                boutonRadioSelectionne = false;
                groupeRadio.selectToggle(null);
            });

        }
    }

    public void afficherCaractéristiquesTours(ImageView im) {

        Tooltip tool = new Tooltip();

        if (im.equals(imageTourArthemis))
            tool.setText("Caractéristiques de la tour Arthémis :\nAttaque : 10 \nPortée : 4");
        else if (im.equals(imageTourPoséidon))
            tool.setText("Caractéristiques de la tour Poséidon :\nAttaque : 8\nPortée : 5");
        else if (im.equals(imageTourDéméter))
            tool.setText("Caractéristiques de la tour Démeter :\nRalenstissement \nPortée : 4");
        else if (im.equals(imageTourDionysos))
            tool.setText("Caractéristiques de la tour Dionysos : \nrend ivre 1 ennemi");

        final boolean[] tooltip = {false};
        im.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (!tooltip[0]) {
                    Tooltip.install(imageTourPoséidon, tool);
                    tool.show(imageTourPoséidon, event.getScreenX(), event.getScreenY());
                    tooltip[0] = true;

                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> {
                        tool.hide();
                        Tooltip.uninstall(imageTourPoséidon, tool);
                        tooltip[0] = false;
                    });
                    pause.play();
                }
                event.consume();
            }
        });


    }


    private void mettreAJourLabel() {

        RadioButton selectedButton = (RadioButton) groupeRadio.getSelectedToggle();
        if (selectedButton != null) {
            String tourName = selectedButton.getText();
            nomItem.setText(getTypeItem(tourName));
            argentItem.setText("" + getPrixTour(tourName));
        }
    }

    private String getTypeItem(String tourName) {
        if (tourName.equals("Arthémis")) {
            return "TOUR ARTÉMIS";
        } else if (tourName.equals("Poséidon")) {
            return "TOUR POSÉIDON";
        } else if (tourName.equals("Déméter"))
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
        } else if (tourName.equals("Déméter")) {
            return coutDéméter;
        } else if (tourName.equals("Dionysos")) {
            return 20;
        }
        return 0;
    }


}
