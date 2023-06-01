package fr.iut.montreuil.saesprint1.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Tour {

    public final static int tailleCase = 32;

    private String nomTour;
    private int cout;
    private Environnement env;
    private IntegerProperty x;
    private IntegerProperty y;

    //Attention : pour le moment contre-intuitif. Il faut augmenter le nombre d'attaque pour qu'il y en ait moins
    private int nbAttaques;
    public static int idTour = 0 ;

    public Tour(String nomTour, int cout, int x, int y, Environnement env, int nbAttaques) {
        this.nomTour = nomTour;
        this.cout = cout;
        this.x = new SimpleIntegerProperty(x*32);
        this.y = new SimpleIntegerProperty(y*32);
        this.env = env;
        this.nbAttaques = nbAttaques;
        this.idTour = idTour;
        idTour++;
        this.env.ajouterTour(this);
    }

    public abstract void attaque();

    public int getCout() {
        return cout;
    }

    public final int getY() {
        return this.y.get();
    }

    public final int getX() {
        return this.x.get();
    }

    public final IntegerProperty getXProperty(){
        return this.x;
    }

    public final IntegerProperty centreTourX(){
        IntegerProperty a = new SimpleIntegerProperty(this.x.get()+(tailleCase/2));
        return a;
    }

    public final IntegerProperty centreTourY(){
        IntegerProperty a = new SimpleIntegerProperty(this.y.get()+(tailleCase/2));
        return a;
    }

    public final IntegerProperty getYProperty(){
        return this.y;
    }

    public Environnement getEnv() {
        return env;
    }

    public String getId(){
        return "Tour"+this.idTour;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "nomTour='" + nomTour + '\'' +
                ", cout=" + cout +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public int getNbAttaques() {
        return nbAttaques;
    }
}