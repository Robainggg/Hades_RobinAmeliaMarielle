package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;

public class Tonneau extends AttaqueTours{
    private Ennemi ennemi;
    public Tonneau(Tour tour, Ennemi ennemi) {
        super(tour, ennemi.getCoordX(), ennemi.getCoordY());
        this.ennemi = ennemi;
    }

    //Enlève le tonneau si l'ennemi est mort ou n'est plus sous effet
    @Override
    public void attaque() {
        if(this.ennemi.estMort() || this.ennemi.getToursEffetTonneau() == 0){
            this.getTour().getEnv().supprimerAttaqueTours(this);
        }
    }

    public Ennemi getEnnemi() {
        return ennemi;
    }
}


