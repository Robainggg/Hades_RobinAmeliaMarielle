package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Tour;
import fr.iut.montreuil.saesprint1.modele.TourAvecPortée;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SpriteTour {

    private Tour tour;
    private Pane pane;
    private Rectangle t;

    public SpriteTour(Tour tour, Pane pane) {
        this.tour = tour;
        this.pane = pane;
        this.t = new Rectangle(32,32);

        if(tour instanceof TourAvecPortée){

            Circle c = new Circle(((TourAvecPortée) tour).getPortée()*32);
            c.setOpacity(0.1);
            c.setFill(Color.PINK);
            c.translateXProperty().bind(tour.centreTourX());
            c.translateYProperty().bind(tour.centreTourY());
            pane.getChildren().add(c);
            c.setId("rangeOf"+tour.getId());

            t.setFill(Color.PINK);
            t.translateXProperty().bind(tour.getXProperty());
            t.translateYProperty().bind(tour.getYProperty());
            pane.getChildren().add(t);
            t.setId(tour.getId());

        }

        else{
            t.setFill(Color.ORANGE);
            t.translateXProperty().bind(tour.getXProperty());
            t.translateYProperty().bind(tour.getYProperty());
            pane.getChildren().add(t);
            t.setId(tour.getId());
        }
    }
}
