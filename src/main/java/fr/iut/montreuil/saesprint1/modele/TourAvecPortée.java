package fr.iut.montreuil.saesprint1.modele;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class TourAvecPortée extends Tour {

    private int portée;

    public TourAvecPortée(String nomTour, int cout, int x, int y, Environnement env, int portée, int nbAttaques) {
        super(nomTour, cout, x, y, env, nbAttaques);
        this.portée = portée;
    }

    public boolean estDansLaZone(double x, double y){
        if(x >= this.centreTourX().getValue()-(getPortée()*32+16) && x <= this.centreTourX().getValue()+(getPortée()*32+16)
                && y >= this.centreTourY().getValue()-(getPortée()*32+16) && y <= this.centreTourY().getValue()+(getPortée()*32+16))
            return true;
        return false;
    }

    //Vérifie que l'ennemi est bien dans la zone
    public Ennemi ennemiZone(Ennemi e){
        if(estDansLaZone(e.getCoordX(),e.getCoordY()))
            return e;
        return null;
    }
    
    public int getPortée() {
        return portée;
    }
}
