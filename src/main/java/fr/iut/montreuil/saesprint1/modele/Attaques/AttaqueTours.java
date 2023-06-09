package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
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

    public abstract void agit();

    //Setters & Getters
    
        public String getId(){
            return "AttaqueTours"+this.idAttaque;
        }
        public Tour getTour() {
            return tour;
        }
        public int getCoordXArrivé() {
            return coordXArrivé.get();
        }

        public IntegerProperty coordXprop() {
            return coordXArrivé;
        }

        public void setCoordXArrivé(int coordXArrivé) {
            this.coordXArrivé.set(coordXArrivé);
        }

        public int getCoordYArrivé() {
            return coordYArrivé.get();
        }

        public IntegerProperty coordYprop() {
            return coordYArrivé;
        }
        public void setCoordYArrivé(int coordYArrivé) {
            this.coordYArrivé.set(coordYArrivé);
        }

}
