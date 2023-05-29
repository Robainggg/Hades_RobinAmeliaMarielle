package fr.iut.montreuil.saesprint1.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile {

    private IntegerProperty x;
    private IntegerProperty y;
    private int dirX;
    private int dirY;
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
        this.dirX = 0;
        this.dirY = 0;
        this.a = a;
        this.b = b;
        this.degats = degats;
        this.tour = tour;
        this.indicateurDirectionX = indicateurDirectionX;
    }

    private void calculerY(){

        if(this.indicateurDirectionX != 0){
            // y = ax+b
            double y = this.getA()*this.getX()+this.getB();
            int dirY = (int)(y-(Tour.tailleCase/2));
            this.setDirX(dirY);
        }
        else{
            System.out.println("cas particulier direction au dessus/en dessous de la tour");
            // y = y+b
            this.setDirY((int)(this.getY()+b-Tour.tailleCase/2));
        }

    }

    private void avance(){
//        //Calculer les prochaines directions
//        calculerY();
//        this.setDirX(this.getX()+this.indicateurDirectionX*vitesse);
//
//        //Remplacer les directions par
//        this.setX(this.getDirX());
//        this.setY(this.getDirY());
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

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
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

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public void setX(int x) {
        this.x.set(x);
    }
    public void setY(int y) {
        this.y.set(y);
    }
}
