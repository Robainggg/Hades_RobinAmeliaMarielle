package fr.iut.montreuil.saesprint1.modele.Projectiles;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;

public class Bouteille extends Projectile {
    private Ennemi ennemi;
    public Bouteille(Tour tour, Ennemi ennemi) {
        super(tour, ennemi.getCoordX(), ennemi.getCoordY());
        this.ennemi = ennemi;
    }
    @Override
    public void agit(){
        if(this.ennemi.getToursIvres() == 0){
            this.getTour().getEnv().supprimerProjectile(this);
        }
        else
            System.out.println("bois bois bois");
    }

    public Ennemi getEnnemi() {
        return ennemi;
    }
}
