package fr.iut.montreuil.saesprint1.modele.Projectiles;

import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static java.lang.Math.round;

public abstract class Projectile {
    private static int compteur = 0 ;
    private int idProjectile;
    private DoubleProperty x;
    private DoubleProperty y;
    private IntegerProperty coordXEnnemi;
    private IntegerProperty coordYEnnemi;
    //y = ax+b
    private int vitesse = 5;
    private double deltaX;
    private double deltaY;
    private double magnitude;
    private double normalizeDeltaX;
    private double normalizeDeltaY;
    private Tour tour;

    public Projectile(Tour tour, int coordXEnnemi, int coordYEnnemi) {
        this.idProjectile = compteur;
        compteur++;

        this.tour = tour;
        this.x = new SimpleDoubleProperty((double)tour.centreTourX().getValue());
        this.y = new SimpleDoubleProperty((double)tour.centreTourY().getValue());
        this.coordXEnnemi = new SimpleIntegerProperty(coordXEnnemi);
        this.coordYEnnemi = new SimpleIntegerProperty(coordYEnnemi);

        //Calcul du vecteur de déplacement
        this.deltaX = this.coordXEnnemi.get() - this.x.get();
        this.deltaY = this.coordYEnnemi.get() - this.y.get();
        this.magnitude = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        this.normalizeDeltaX = deltaX/magnitude;
        this.normalizeDeltaY = deltaY/magnitude;
    }

    public abstract void agit();

    public void avance(){

        this.setX((this.x.get()+ normalizeDeltaX*vitesse));
        this.setY((this.y.get()+ normalizeDeltaY*vitesse));
    }
    
    //Setters & Getters
    public final double getX() {
        return x.get();
    }

    public final DoubleProperty xProperty() {
        return x;
    }

    public final double getY() {
        return y.get();
    }

    public final DoubleProperty yProperty() {
        return y;
    }
    
    public void setX(double x) {
        this.x.set(x);
    }
    public void setY(double y) {
        this.y.set(y);
    }

    public String getId(){
        return "Projectile"+this.idProjectile;
    }
    public Tour getTour() {
        return tour;
    }
    
}
