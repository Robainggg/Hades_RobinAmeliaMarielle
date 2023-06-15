package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import fr.iut.montreuil.saesprint1.modele.Tours.TourAvecPortée;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static java.lang.Math.round;

public class Projectile extends AttaqueTours {
    private DoubleProperty x;
    private DoubleProperty y;
    private int vitesse;
    private double deltaX;
    private double deltaY;
    private double magnitude;
    private double normalizeDeltaX;
    private double normalizeDeltaY;

    public Projectile(TourAvecPortée tour, int coordXArrivé, int coordYArrivé, int vitesse) {

        super(tour,coordXArrivé,coordYArrivé);
        this.vitesse = vitesse;

        this.x = new SimpleDoubleProperty((double)tour.centreTourX().getValue());
        this.y = new SimpleDoubleProperty((double)tour.centreTourY().getValue());


        //Calcul du vecteur de déplacement
        this.deltaX = super.getCoordXArrivé() - this.getX();
        this.deltaY = super.getCoordYArrivé() - this.getY();
        this.magnitude = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        this.normalizeDeltaX = deltaX/magnitude;
        this.normalizeDeltaY = deltaY/magnitude;
    }

    public void avance(){
        this.setX((this.getX()+ normalizeDeltaX*vitesse));
        this.setY((this.getY()+ normalizeDeltaY*vitesse));
    }

    //Getters & Setters
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


    @Override
    public void attaque() {

        this.avance();

        TourAvecPortée tourAvecPortée = (TourAvecPortée) super.getTour();

        //S'il est sorti de la portée ou en dehors de la map
        if(!tourAvecPortée.estDansLaZone(this.getX(),this.getY())
                || this.getX() >= 960-32 || this.getX() <= 32 || this.getY() >= 640-32 || this.getY() <= 32){
            tourAvecPortée.getEnv().supprimerAttaqueTours(this);
        }
    }
}
