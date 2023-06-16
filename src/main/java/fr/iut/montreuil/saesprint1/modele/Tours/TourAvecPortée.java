package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;

public abstract class TourAvecPortée extends Tour {

    private int portée;

    public TourAvecPortée(String nomTour, int cout, int x, int y, Environnement env, int portée, int espaceEntreAttaques) {
        super(nomTour, cout, x, y, env, espaceEntreAttaques);
        this.portée = portée*32;
    }
    //Vérifie que l'élément est dans la portée
    public boolean estDansLaZone(double x, double y){
        if(x >= this.centreTourX().getValue()-(getPortée()+16) && x <= this.centreTourX().getValue()+(getPortée()+16)
                && y >= this.centreTourY().getValue()-(getPortée()+16) && y <= this.centreTourY().getValue()+(getPortée()+16)) {
            return true;
        }
        return false;
    }

    //Vérifie que l'ennemi est bien dans la portée
    public Ennemi ennemiZone(Ennemi e){
        if(estDansLaZone(e.getCoordX(),e.getCoordY()))
            return e;
        return null;
    }
    
    public int getPortée() {
        return portée;
    }

    public void améliorer(int coutAmélioration, int espaceEntreAttaquesAmélioré, int portéeAméliorée){
        super.améliorer(coutAmélioration,espaceEntreAttaquesAmélioré);
        if(this.isAmélioré()){ this.setPortée(portéeAméliorée*32);}
    }

    public void setPortée(int portée) {
        this.portée = portée;
    }
}
