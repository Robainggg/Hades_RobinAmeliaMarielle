package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Environnement;
//import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;
//import fr.iut.montreuil.saesprint1.modele.Tours.Poséidon;
//import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import fr.iut.montreuil.saesprint1.modele.Tours.*;
import javafx.animation.PauseTransition;
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

        t.setImage(image);
        t.setFitWidth(32);
        t.setFitHeight(32);
        t.translateXProperty().bind(tour.getXProperty());
        t.translateYProperty().bind(tour.getYProperty());
        pane.getChildren().add(t);
        t.setId(tour.getId());
        InitialisationOptionsAméliorationSuppression(t);
    }

    private void InitialisationOptionsAméliorationSuppression(ImageView imageView) {

        Tooltip tooltip = new Tooltip();

        Button button = new Button("Améliorer");
        button.setOnAction(event -> {

            // Code pour gérer l'action du bouton "Améliorer"
            System.out.println("Bouton Améliorer cliqué !");
            if (tour instanceof Artémis) {
                ((Artémis) tour).améliorer();
            } else if (tour instanceof Poséidon) {
                ((Poséidon) tour).améliorer();
            } else if (tour instanceof Dionysos) {
                ((Dionysos) tour).améliorer();
            } else if (tour instanceof Déméter) {
                ((Déméter) tour).améliorer();
            }

            //Afficher la tour améliorée si l'amélioration s'est bien effectuée
            if (this.tour.isAmélioré()) {
                ameliore = true;
            }
        });

        Button boutonSupprimer = new Button("Supprimer");
        boutonSupprimer.setOnAction(event -> {
            System.out.println("Bouton Supprimer cliqué !");
//            pane.getChildren().remove(t);
            evt.supprimerUneTour(tour);
        });



        VBox tooltipContent = new VBox();
        tooltipContent.getChildren().add(button);
        tooltipContent.getChildren().add(boutonSupprimer);
        tooltip.setGraphic(tooltipContent);

        final boolean[] tooltipVisible = {false};

        imageView.setOnMousePressed(event -> {

            if (event.isPrimaryButtonDown()) {
                if (!tooltipVisible[0]) {
                    String tourType = getTour().getClass().getSimpleName();
                    String tooltipText = tour.getId() + "\nType : ";
                    if (tourType.equals("Artémis")) {
                        tooltipText += "Arthémis";
                    } else if (tourType.equals("Poséidon")) {
                        tooltipText += "Poséidon";
                    } else if (tourType.equals("Démeter")) {
                        tooltipText += "Démeter";
                    } else {
                        tooltipText += "Dionysos";
                    }
                    tooltip.setText(tooltipText);

                    if (ameliore) {
                        tooltipContent.getChildren().remove(button);
                    }

                    Tooltip.install(imageView, tooltip);
                    tooltip.show(imageView, event.getScreenX(), event.getScreenY());
                    tooltipVisible[0] = true;

                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
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


    public ImageView getT() {
        return t;
    }

    public Tour getTour() {
        return tour;
    }

}
