package fr.iut.montreuil.saesprint1.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile {

    public static int idProjectile = 0 ;
    private IntegerProperty x;
    private IntegerProperty y;
    //y = ax+b
    private double a;
    private double b;

    private int vitesse = 1;

    private int degats;

    private int indicateurDirectionX;

    final private TourAvecPortée tour;

    public Projectile(int x, int y, double a, double b, int degats, TourAvecPortée tour, int indicateurDirectionX) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.a = a;
        this.b = b;
        this.degats = degats;
        this.tour = tour;
        this.indicateurDirectionX = indicateurDirectionX;
        this.tour.getEnv().ajouterProjectile(this);
        this.idProjectile = idProjectile;
        idProjectile++;
    }

    public void avance(){

        //Calcule d'abord X puisque Y peut en dépendre
        System.out.print("X: "+this.getX()+" vers ");
        this.setX(this.getX()+this.indicateurDirectionX*vitesse);
        System.out.println(this.getX());

        if(this.indicateurDirectionX != 0){
            // y = ax+b
            System.out.print("Y: "+this.getY()+" vers ");
            double y = this.getA()*this.getX()+this.getB();
            //On veut qu'elle se retrouve au milieu de la case ???
            int dirY = (int)(y+Tour.tailleCase/2);
            this.setY(dirY);
            System.out.println(this.getY());
        }
        else{
            System.out.println("cas particulier direction au dessus/en dessous de la tour");
            // y = y+b
            this.setY((int)(this.getY()+b+Tour.tailleCase/2));
        }

        this.disparait();

    }

    //Méthode disparait : sortie de portée de la Tour || toucheEnnemi
    public void disparait(){
        //Si en dehors de la zone de manhattan : doit disparaitre
        if ((Math.abs(this.getTour().centreTourX().get() - this.getX()) + Math.abs(this.getTour().centreTourY().get() - this.getY()) > this.getTour().getPortée()*Tour.tailleCase+(Tour.tailleCase/2))){
             this.getTour().getEnv().supprimerProjectile(this);
        }
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

    public TourAvecPortée getTour() {
        return tour;
    }

    public String getId(){
        return "Projectile"+this.idProjectile;
    }

}
