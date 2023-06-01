package fr.iut.montreuil.saesprint1.modele;

public abstract class TourAvecPortée extends Tour {

    private int portée;

    public TourAvecPortée(String nomTour, int cout, int x, int y, Environnement env, int portée, int nbAttaques) {
        super(nomTour, cout, x, y, env, nbAttaques);
        this.portée = portée;
    }

    //Vérifie que l'ennemi est dans la zone
    public Ennemi ennemiZone(Ennemi e){
        if(e.getCoordX() >= this.centreTourX().getValue()-(getPortée()*32+16) && e.getCoordX() <= this.centreTourX().getValue()+(getPortée()*32+16)
                && e.getCoordY() >= this.centreTourY().getValue()-(getPortée()*32+16) && e.getCoordY() <= this.centreTourY().getValue()+(getPortée()*32+16))
                return e;
        return null;
    }



    public int getPortée() {
        return portée;
    }
}
