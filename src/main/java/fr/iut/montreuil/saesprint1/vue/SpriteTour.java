package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Environnement;
//import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;
//import fr.iut.montreuil.saesprint1.modele.Tours.Poséidon;
//import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import fr.iut.montreuil.saesprint1.modele.Tours.*;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import javax.swing.plaf.basic.BasicPopupMenuSeparatorUI;

public class SpriteTour {

    //les images des tours
    final static Image artemis = new Image(SpriteTour.class.getResource("/images/tours/artemis.png").toExternalForm());
    final static Image poseidon = new Image(SpriteTour.class.getResource("/images/tours/poseidon.png").toExternalForm());
    final static Image demeter = new Image(SpriteTour.class.getResource("/images/tours/demeter.png").toExternalForm());
    final static Image dionysos = new Image(SpriteTour.class.getResource("/images/tours/dionysos.png").toExternalForm());
    
    private Tour tour;
    private Pane pane;
    private ImageView t;

    private Environnement evt;

    boolean ameliore = false;

    public SpriteTour(Tour tour, Pane pane, Environnement evt) {
        this.tour = tour;
        this.pane = pane;
        this.evt = evt;
        this.t = new ImageView();
        Image image;

        if (tour instanceof Artémis)
            image = artemis;
        else if(tour instanceof Poséidon)
            image = poseidon;
        else if (tour instanceof Déméter)
            image = demeter;
        else if(tour instanceof Dionysos)
            image = dionysos;
        else
            image = new Image(getClass().getResource("/images/tours/Tower-PNG-Image.png").toExternalForm());

        //création des sprites des tours quand on les pose à l'endroit cliqué
        t.setImage(image);
        t.setFitWidth(32);
        t.setFitHeight(32);
        t.translateXProperty().bind(tour.getXProperty());
        t.translateYProperty().bind(tour.getYProperty());
        this.pane.getChildren().add(t);
        t.setId(tour.getId());
        InitialisationOptionsAméliorationSuppression(t);
    }

    //bulle pour afficher les options d'amélioration et de suppression de la tour une fois posée
    private void InitialisationOptionsAméliorationSuppression(ImageView imageView) {

        //création de la bulle
        Tooltip tooltip = new Tooltip();

        //Button AMÉLIORER qui une fois cliquée appelle la méthode améliorer qui améliore les tours en terme d'attaque et/ou la portée
        Button button = new Button("Améliorer");
        button.setOnAction(event -> {
            // Code pour gérer l'action du bouton "Améliorer"
            if (tour instanceof Déméter) {
                ((Déméter) tour).améliorer();
            }
            else{tour.améliorer();}

            //augmentation de la taille quand amélioration
            if (this.tour.isAmélioré()) {
                ameliore = true;
                t.setFitWidth(32*1.3);
                t.setFitHeight(32*1.3);
            }

        });

        //Boutton supprimer qui Supprime la tour côté environnement
        Button boutonSupprimer = new Button("Supprimer");
        boutonSupprimer.setOnAction(event -> {
            evt.supprimerUneTour(tour);
        });

        //les boutons sont mis dans une vbox
        VBox tooltipContent = new VBox();
        tooltipContent.getChildren().add(button);
        tooltipContent.getChildren().add(boutonSupprimer);
        //la vbox est mise dans le tooltip
        tooltip.setGraphic(tooltipContent);

        final boolean[] tooltipVisible = {false};

        //affichage des options de suppression et d'amélioration lorsqu'on clique sur les sprite des tours sur le pane
        imageView.setOnMousePressed(event -> {

            if (event.isPrimaryButtonDown()) {
                if (!tooltipVisible[0]) {
                    //affichage de l'id de la tour et du type de tour
                    String tourType = getTour().getClass().getSimpleName();
                    String tooltipText = "Prix : "+ getTour().getCoutAmélioration()+" or" + "\nType : ";
                    if (tourType.equals("Artémis")) {
                        tooltipText += "Arthémis";
                    } else if (tourType.equals("Poséidon")) {
                        tooltipText += "Poséidon";
                    } else if (tourType.equals("Dionysos")) {
                        tooltipText += "Dionysos";
                    } else {
                        tooltipText += "Déméter";
                    }
                    tooltip.setText(tooltipText);

                    //affichage du bouton AMELIORER uniquement si la tour n'est pas améliorée
                    if (ameliore) {
                        tooltipContent.getChildren().remove(button);
                    }
                    //affichage de la bulle
                    Tooltip.install(imageView, tooltip);
                    tooltip.show(imageView, event.getScreenX(), event.getScreenY());
                    tooltipVisible[0] = true;

                    //Disparition de la bulle au bout de 1s
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> {
                        tooltip.hide();
                        Tooltip.uninstall(imageView, tooltip);
                        tooltipVisible[0] = false;
                    });
                    pause.play();
                }
                event.consume();
            }
        });
    }

    public Tour getTour() {
        return tour;
    }

}
