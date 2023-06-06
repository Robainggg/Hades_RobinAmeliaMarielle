package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;

public abstract class TourAvecPortée extends Tour {

    private int portée;

    public TourAvecPortée(String nomTour, int cout, int x, int y, Environnement env, int portée, int espaceEntreAttaques) {
        super(nomTour, cout, x, y, env, espaceEntreAttaques);
        this.portée = portée*32;
    }

    public boolean estDansLaZone(double x, double y){
        if(x >= this.centreTourX().getValue()-(getPortée()+16) && x <= this.centreTourX().getValue()+(getPortée()+16)
                && y >= this.centreTourY().getValue()-(getPortée()+16) && y <= this.centreTourY().getValue()+(getPortée()+16)) {
            //System.out.println("Vérifie si est dans la zone");
            return true;
        }
        //System.out.println("nest plus dans la zone");
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
