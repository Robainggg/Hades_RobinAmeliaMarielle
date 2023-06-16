package fr.iut.montreuil.saesprint1.modele.Ennemis;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import javafx.beans.property.SimpleIntegerProperty;

public class DiableRouge extends Ennemi{

    public DiableRouge(Environnement environnement, int coordXDepart, int coordYDepart) {
        super(environnement, coordXDepart, coordYDepart, 2, new SimpleIntegerProperty(100));
    }


    @Override
    public boolean estArriveAuBout() {

        if(this.getCoordX()  == 29*32 && this.getCoordY() == 13*32) {
            this.estSorti = true;
            this.setToursIvres(0);
            this.setToursEffetTonneau(0);
            return true;
        }
        return false;
    }

    @Override
    public double getPointsDeVieMax() {
        return 100;
    }
}
