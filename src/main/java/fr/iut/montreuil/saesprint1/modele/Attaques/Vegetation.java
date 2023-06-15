package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Tours.Déméter;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Vegetation {
    private Tour tour;
    private IntegerProperty coordXArrivé;
    private IntegerProperty coordYArrivé;
    private static int compteur = 0;
    private int idVege;
    public Vegetation(Tour tour, int coordXArrivé, int coordYArrivé) {
        this.tour = tour;
        this.coordXArrivé = new SimpleIntegerProperty(coordXArrivé);
        this.coordYArrivé = new SimpleIntegerProperty(coordYArrivé);
        this.idVege = compteur;
        compteur++;
    }
    public String getId() {
        return "Vegetation"+ this.idVege;
    }

    public Tour getTour() {
        return tour;
    }
    public final IntegerProperty coordXprop() {
        return coordXArrivé;
    }

    public final IntegerProperty coordYprop() {
        return coordYArrivé;
    }
}
