package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;
import fr.iut.montreuil.saesprint1.modele.Tours.Poséidon;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import fr.iut.montreuil.saesprint1.modele.Tours.TourAvecPortée;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpriteTour {

    private Tour tour;
    private Pane pane;
    private ImageView t;

    public SpriteTour(Tour tour, Pane pane) {
        this.tour = tour;
        this.pane = pane;
        this.t = new ImageView();
        
        Image image = new Image(getClass().getResource("/images/Tower-PNG-Image.png").toExternalForm());
        t.setImage(image);
        t.setFitWidth(32);
        t.setFitHeight(32);
        t.translateXProperty().bind(tour.getXProperty());
        t.translateYProperty().bind(tour.getYProperty());
        pane.getChildren().add(t);
        t.setId(tour.getId());

    }

}
