package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;

public class Bouteille extends AttaqueTours{
    private Ennemi ennemi;
    public Bouteille(Tour tour, Ennemi ennemi) {
        super(tour, ennemi.getCoordX(), ennemi.getCoordY());
        this.ennemi = ennemi;
    }

    //Se supprime si l'ennemi n'est plus affecté par la bouteille
    @Override
    public void attaque(){
        
        if(this.ennemi.estMort() || this.ennemi.getToursIvres() == 0){
            this.getTour().getEnv().supprimerAttaqueTours(this);
        }
    }

    public Ennemi getEnnemi() {
        return ennemi;
    }
}
