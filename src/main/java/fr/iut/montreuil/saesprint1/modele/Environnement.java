package fr.iut.montreuil.saesprint1.modele;

import java.util.ArrayList;

public class Environnement {

    private ArrayList<Ennemi> ennemis;
    private ArrayList<Tour> tours;
    private Terrain terrain;

    public Environnement(Terrain terrain) {
        this.ennemis = new ArrayList<>();
        this.tours = new ArrayList<>();
        this.terrain = terrain;
    }

    public ArrayList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public void ajouterTour(Tour tour){
        this.tours.add(tour);
    }

    public void ajouterEnnemi(Ennemi ennemi){
        this.ennemis.add(ennemi);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public boolean isTourPresent(int x, int y) {
        for (Tour tour : tours) {
            if (tour.getX() == x && tour.getY() == y) {
                return true; // Une tour est déjà présente à cet emplacement
            }
        }
        return false; // Aucune tour n'est présente à cet emplacement
    }

}