package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Tours.TourAvecPortée;


//On aura 8 points de coordonnées par Vague qui avanceront autour de la tour sans sortir de la portée
public class PetiteVague extends Projectile{

    private TourAvecPortée tourAvecPortée;
    private static int degatsVague = 3;
    private static int vitesse = 1;

    public PetiteVague(TourAvecPortée tour, int coordX, int coordY) {
        super(tour, coordX, coordY,vitesse,degatsVague);
        this.tourAvecPortée = (TourAvecPortée) this.getTour();

    }
    
    
}
