package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;

public class Flèche extends Projectile {

    private Artémis tourArtémis;
    private static int degatsFlèche = 5;
    private static int vitesseFlèche = 8;
    
    public Flèche(Artémis tour,int coordXEnnemi, int coordYEnnemi) {
        super(tour, coordXEnnemi, coordYEnnemi, vitesseFlèche, degatsFlèche);
        this.tourArtémis = tour;
    }
    
}
