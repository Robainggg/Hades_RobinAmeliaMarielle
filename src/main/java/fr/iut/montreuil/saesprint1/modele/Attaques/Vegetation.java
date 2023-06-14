package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Tours.Déméter;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Vegetation {
    private Déméter tour;
    private IntegerProperty coordXArrivé;
    private IntegerProperty coordYArrivé;
    private static int compteur = 0;
    private String idVege;
    public Vegetation(Déméter tour, int coordXArrivé, int coordYArrivé) {
        this.tour = tour;
        this.coordXArrivé = new SimpleIntegerProperty(coordXArrivé);
        this.coordYArrivé = new SimpleIntegerProperty(coordYArrivé);
        this.idVege = "Vegetation"+idVege;
        compteur++;
    }
    public String getId() {
        return idVege;
    }

    public Déméter getTour() {
        return tour;
    }
    public final IntegerProperty coordXprop() {
        return coordXArrivé;
    }

    public final IntegerProperty coordYprop() {
        return coordYArrivé;
    }
}
