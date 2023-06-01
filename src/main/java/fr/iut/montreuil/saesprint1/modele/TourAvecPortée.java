package fr.iut.montreuil.saesprint1.modele;

public abstract class TourAvecPortée extends Tour {

    private int portée;

    public TourAvecPortée(String nomTour, int cout, int x, int y, Environnement env, int portée, int nbAttaques) {
        super(nomTour, cout, x, y, env, nbAttaques);
        this.portée = portée;
    }


    public int getPortée() {
        return portée;
    }
}
