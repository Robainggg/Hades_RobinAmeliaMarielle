package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;

public abstract class TourAvecPortée extends Tour {

    private int portée;

    private int portéeAméliorée;

    public TourAvecPortée(String nomTour, int cout, int x, int y, Environnement env, int portée, int espaceEntreAttaques, int coutAmélioration, int nouvelEspaceAtt, int nouvellePortée) {
        super(nomTour, cout, x, y, env, espaceEntreAttaques,coutAmélioration,nouvelEspaceAtt);
        this.portée = portée*32;
        this.portéeAméliorée = nouvellePortée*32;
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


    public void améliorer(){
        super.améliorer();
        if(this.isAmélioré()){ this.setPortée(this.portéeAméliorée);}
    }

    public void setPortée(int portée) {
        this.portée = portée;
    }
}
