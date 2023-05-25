package fr.iut.montreuil.saesprint1.modele;

public abstract class TourAvecPortée extends Tour {

    private int portée;

    public TourAvecPortée(String nomTour, int cout, int x, int y, Environnement env, int portée) {
        super(nomTour, cout, x, y, env);
        this.portée = portée;
    }

    @Override
    public abstract void attaque();

    public int getPortée() {
        return portée;
    }
}
