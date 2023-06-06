package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;

public class Bouteille extends Projectile {
    private Ennemi ennemi;
    public Bouteille(Tour tour, Ennemi ennemi) {
        super(tour, ennemi.getCoordX(), ennemi.getCoordY(),0);
        this.ennemi = ennemi;
    }
    @Override
    public void agit(){
        if(this.ennemi.getToursIvres() == 0){
            this.getTour().getEnv().supprimerProjectile(this);
        }
    }

    public Ennemi getEnnemi() {
        return ennemi;
    }
}
