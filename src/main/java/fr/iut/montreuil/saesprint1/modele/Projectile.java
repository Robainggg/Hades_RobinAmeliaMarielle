package fr.iut.montreuil.saesprint1.modele;

import fr.iut.montreuil.saesprint1.vue.SpriteProjectile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import static java.lang.Math.ceil;
import static java.lang.Math.round;

public class Projectile {

    public static int idProjectile = 0 ;
    private IntegerProperty x;
    private IntegerProperty y;

    private IntegerProperty coordXEnnemi;
    private IntegerProperty coordYEnnemi;
    //y = ax+b
    private int vitesse = 1;

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
        this.degats = degats;
        this.tour = tour;
        this.x = new SimpleIntegerProperty(tour.centreTourX().get());
        this.y = new SimpleIntegerProperty(tour.centreTourY().get());
        this.coordXEnnemi = new SimpleIntegerProperty(coordXEnnemi);
        this.coordYEnnemi = new SimpleIntegerProperty(coordYEnnemi);
        this.indicateurDirectionX = indicateurDirectionX();

        //Calcul du vecteur de déplacement
        this.deltaX = this.coordXEnnemi.get() - this.x.get();
        this.deltaY = this.coordYEnnemi.get() - this.y.get();
        this.magnitude = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        this.normalizeDeltaX = deltaX/magnitude;
        this.normalizeDeltaY = deltaY/magnitude;
        
        //Calcul du coefficient directeur et ordonnée
//        this.a = (this.coordYEnnemi.get() - this.y.get()) / (this.coordXEnnemi.get() - this.x.get());
//        this.b = this.y.get() - (a * this.x.get());
        this.idProjectile = idProjectile;
        idProjectile++;

        this.tour.getEnv().ajouterProjectile(this);
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

        this.setX((int)(this.x.get()+ normalizeDeltaX*vitesse));
        this.setY((int)(this.y.get()+ normalizeDeltaY*vitesse));
        
//        this.setX(this.getX()+this.indicateurDirectionX*vitesse);
//
//        // y = ax+b
//        this.setY((int)(this.getA()*this.getX()+this.getB()));

    }
    
    //Setters & Getters
    public final int getX() {
        return x.get();
    }

    public final IntegerProperty xProperty() {
        return x;
    }

    public final int getY() {
        return y.get();
    }

    public final IntegerProperty yProperty() {
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
    
    public void setX(int x) {
        this.x.set(x);
    }
    public void setY(int y) {
        this.y.set(y);
    }

    public String getId(){
        return "Projectile"+this.idProjectile;
    }

    public TourAvecPortée getTour() {
        return tour;
    }
}
