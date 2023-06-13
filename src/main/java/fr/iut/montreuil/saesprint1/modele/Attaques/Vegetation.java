package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Tours.Tour;

public class Vegetation extends AttaqueTours{
    public Vegetation(Tour tour, int coordXArrivé, int coordYArrivé) {

        super(tour, coordXArrivé, coordYArrivé);
    }
    
    @Override
    public void agit(){}
}
