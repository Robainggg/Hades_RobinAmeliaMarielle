package fr.iut.montreuil.saesprint1.modele;

import fr.iut.montreuil.saesprint1.vue.SpriteProjectile;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static java.lang.Math.ceil;
import static java.lang.Math.round;

public class Projectile {

    private static int compteur = 0 ;
    private String idProjectile;

    private DoubleProperty x;
    private DoubleProperty y;

    private IntegerProperty coordXEnnemi;
    private IntegerProperty coordYEnnemi;
    //y = ax+b
    private int vitesse = 2;

    private double deltaX;
    private double deltaY;

    private double magnitude;

    private double normalizeDeltaX;
    private double normalizeDeltaY;

    private TourAvecPortée tour;

    private int degats;
    private int a;
    private int b;

    private int indicateurDirectionX;



    public Projectile( TourAvecPortée tour, int degats, int coordXEnnemi, int coordYEnnemi) {
        this.idProjectile = "P" + compteur;
        compteur++;
        this.degats = degats;
        this.tour = tour;
        this.x = new SimpleDoubleProperty((double)tour.centreTourX().getValue());
        this.y = new SimpleDoubleProperty((double)tour.centreTourY().getValue());
        this.coordXEnnemi = new SimpleIntegerProperty(coordXEnnemi);
        this.coordYEnnemi = new SimpleIntegerProperty(coordYEnnemi);
        this.indicateurDirectionX = indicateurDirectionX();

        //Calcul du vecteur de déplacement
        this.deltaX = this.coordXEnnemi.get() - this.x.get();
        this.deltaY = this.coordYEnnemi.get() - this.y.get();
        this.magnitude = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        this.normalizeDeltaX = deltaX/magnitude;
        this.normalizeDeltaY = deltaY/magnitude;

    }

    public int indicateurDirectionX (){

        //Savoir dans quelle direction X doit évoluer
        int indicateurDirectionX;

        //Si la tour doit tirer en avant ou en arrière
        if(this.getX()-coordXEnnemi.get() > 0){return -1;}
        else if(this.getX()-coordXEnnemi.get() < 0){return 1;}
        else{
            System.out.println("Cas particulier");
            return 0;
        }
    }
    
    public void avance(){

        this.setX((this.x.get()+ normalizeDeltaX*vitesse));
        this.setY((this.y.get()+ normalizeDeltaY*vitesse));
        
//        this.setX(this.getX()+this.indicateurDirectionX*vitesse);
//
//        // y = ax+b
//        this.setY((int)(this.getA()*this.getX()+this.getB()));

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

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public int getDegats() {
        return degats;
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

    public TourAvecPortée getTour() {
        return tour;
    }
}
