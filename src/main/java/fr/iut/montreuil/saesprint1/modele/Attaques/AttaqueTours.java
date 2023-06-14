package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Tours.Déméter;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class  AttaqueTours {

        private static int compteur = 0 ;
        private int idAttaque;
        private IntegerProperty coordXArrivé;
        private IntegerProperty coordYArrivé;
        private Tour tour;

    public AttaqueTours(Tour tour,int coordXArrivé, int coordYArrivé) {
        this.idAttaque = compteur;
        compteur++;
        this.coordXArrivé = new SimpleIntegerProperty(coordXArrivé);
        this.coordYArrivé = new SimpleIntegerProperty(coordYArrivé);
        this.tour = tour;
    }

    public abstract void attaque();

    public String getId() {
        return "AttaqueTours" + this.idAttaque;
    }

    public Tour getTour() {
        return tour;
    }

    public final int getCoordXArrivé() {
        return coordXArrivé.get();
    }

    public final IntegerProperty coordXprop() {
        return coordXArrivé;
    }

    public final int getCoordYArrivé() {
        return coordYArrivé.get();
    }

    public final IntegerProperty coordYprop() {
        return coordYArrivé;
    }

}
