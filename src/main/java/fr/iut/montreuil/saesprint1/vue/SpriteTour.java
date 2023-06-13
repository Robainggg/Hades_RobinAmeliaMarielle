package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;
import fr.iut.montreuil.saesprint1.modele.Tours.Poséidon;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javax.swing.plaf.basic.BasicPopupMenuSeparatorUI;

public class SpriteTour {

    final static Image artemis = new Image(SpriteTour.class.getResource("/images/tours/artemis.png").toExternalForm());
    final static Image poseidon = new Image(SpriteTour.class.getResource("/images/tours/poseidon.png").toExternalForm());



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

/*
            Circle c = new Circle(((TourAvecPortée) tour).getPortée());
            c.setOpacity(0.1);
            if(tour instanceof Artémis){
                c.setFill(Color.PINK);
            }
            else if(tour instanceof Poséidon){
                c.setFill(Color.CADETBLUE);
            }

            c.translateXProperty().bind(tour.centreTourX());
            c.translateYProperty().bind(tour.centreTourY());
            pane.getChildren().add(c);
            c.setId("rangeOf" + tour.getId());
*/

       // Image image = new Image(getClass().getResource("/images/tours/Tower-PNG-Image.png").toExternalForm());
        if(tour instanceof Artémis)
            image = artemis;
        else if(tour instanceof Poséidon)
            image = poseidon;
        else
            image = new Image(getClass().getResource("/images/tours/Tower-PNG-Image.png").toExternalForm());
        t.setImage(image);
        t.setFitWidth(32);
        t.setFitHeight(32);
        t.translateXProperty().bind(tour.getXProperty());
        t.translateYProperty().bind(tour.getYProperty());
        pane.getChildren().add(t);
        t.setId(tour.getId());
        if (!ameliore) {
            afficherAmélioration(t);
        }



    }

    private void afficherAmélioration(ImageView imageView) {
/*
        if (!ameliore) {
            Tooltip tooltipPoséidon = new Tooltip();

            tooltipPoséidon.setText(getTour().getId() + "\n" + "Amélioration : 10€");

            Button button = new Button("Améliorer");
            button.setOnAction(event -> {
                // Code pour gérer l'action du bouton "Améliorer"
                System.out.println("Bouton Améliorer cliqué !");
                Image image = new Image(getClass().getResource("/images/tours/tour_ameliore.jpg").toExternalForm());
                t.setImage(image);
                ameliore = true;

            });

            VBox tooltipContent = new VBox();
            tooltipContent.getChildren().addAll(button);
            tooltipPoséidon.setGraphic(tooltipContent);

            final boolean[] tooltipVisiblePoséidon = {false};
            imageView.setOnMousePressed(event -> {
                if (event.isPrimaryButtonDown()) {
                    if (!tooltipVisiblePoséidon[0] && !ameliore) {
                        Tooltip.install(imageView, tooltipPoséidon);
                        tooltipPoséidon.show(imageView, event.getScreenX(), event.getScreenY());
                        tooltipVisiblePoséidon[0] = true;

                        PauseTransition pause = new PauseTransition(Duration.seconds(2));
                        pause.setOnFinished(e -> {
                            tooltipPoséidon.hide();
                            Tooltip.uninstall(imageView, tooltipPoséidon);
                            tooltipVisiblePoséidon[0] = false;
                        });
                        pause.play();
                    }
                    event.consume();
                }
            });
        }*/

        Tooltip tooltip = new Tooltip();

        Button button = new Button("Améliorer");
        button.setOnAction(event -> {
            // Code pour gérer l'action du bouton "Améliorer"
            System.out.println("Bouton Améliorer cliqué !");
            Image image = new Image(getClass().getResource("/images/tours/tour_ameliore.jpg").toExternalForm());
            t.setImage(image);
            ameliore = true;
        });

        Button boutonSupprimer = new Button("Supprimer");
        boutonSupprimer.setOnAction(event -> {
            System.out.println("Bouton Supprimer cliqué !");
            pane.getChildren().remove(t);
            evt.supprimerUneTour(tour);
        });



        VBox tooltipContent = new VBox();
        tooltipContent.getChildren().addAll(button, boutonSupprimer);
        tooltip.setGraphic(tooltipContent);

        final boolean[] tooltipVisible = {false};
        imageView.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                if (!tooltipVisible[0] && !ameliore) {
                    String tourType = getTour().getClass().getSimpleName();
                    String tooltipText = "";

                    if (tourType.equals("Artémis")) {
                        tooltipText = "Caractéristiques de la tour Artémis :\nAttaque : 6\nPortée : 4";
                    } else if (tourType.equals("Poséidon")) {
                        tooltipText = "Caractéristiques de la tour Poséidon :\nAttaque : 8\nPortée : 5";
                    }
                    tooltip.setText(tooltipText);

                    Tooltip.install(imageView, tooltip);
                    tooltip.show(imageView, event.getScreenX(), event.getScreenY());
                    tooltipVisible[0] = true;

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

    public ImageView getT() {
        return t;
    }

    public Tour getTour() {
        return tour;
    }

}
