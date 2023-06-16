package fr.iut.montreuil.saesprint1.modele.Ennemis;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import javafx.beans.property.SimpleIntegerProperty;

public class DiableJaune extends Ennemi{

    public DiableJaune(Environnement environnement, int coordXDepart, int coordYDepart) {
        super(environnement, coordXDepart, coordYDepart,3, new SimpleIntegerProperty(80));

    }


    @Override
    public boolean estArriveAuBout() {

        if(this.getCoordX()  == 29*32 && this.getCoordY() == 13*32) {
            this.estSorti = true;
            this.setToursIvres(0);
            this.setToursEffetTonneau(0);
            this.getEnvironnement().getJoueur().setArgent(this.getEnvironnement().getJoueur().getArgent()- (int)(this.getEnvironnement().getJoueur().getArgent()*0.2));
            return true;
        }
        return false;
    }

    @Override
    public double getPointsDeVieMax() {
        return 80;
    }
}
