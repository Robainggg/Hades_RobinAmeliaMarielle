package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import fr.iut.montreuil.saesprint1.modele.Tours.TourAvecPortée;
import java.awt.Point;
import java.util.ArrayList;


//On aura 8 points de coordonnées par Vague qui avanceront autour de la tour sans sortir de la portée
public class PetiteVague extends Projectile{

    private TourAvecPortée tourAvecPortée;
    private int degats = 1;
    private static int vitesse = 1;

    public PetiteVague(TourAvecPortée tour, int coordX, int coordY) {
        super(tour, coordX, coordY,vitesse);
        this.tourAvecPortée = (TourAvecPortée) this.getTour();

    }

    @Override
    public void agit() {



    }
    
}
