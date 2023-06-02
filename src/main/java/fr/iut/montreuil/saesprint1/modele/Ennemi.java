package fr.iut.montreuil.saesprint1.modele;

import fr.iut.montreuil.saesprint1.vue.SpriteEnnemi;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ennemi {
    private static int compteur = 0;
    private String idEnnemi;
    private int pv;
    private int vitesse;
    private IntegerProperty coordX;
    private IntegerProperty coordY;
    private Environnement environnement;
    private StringProperty direction;
    private Case prochaineCase;

    public Ennemi(Environnement environnement){

        this.idEnnemi = "E" + compteur;
        
        coordY = new SimpleIntegerProperty();
        coordX = new SimpleIntegerProperty();
        direction = new SimpleStringProperty();
        coordX.setValue(0*32);
        coordY.setValue(2*32);
        this.environnement = new Environnement();
        prochaineCase = new Case(0,2);
        vitesse = 1;
        this.definirDirection();
        incrementeCompteur();
    }

    private static void incrementeCompteur(){
        compteur++;
    }

    public int getCoordX() {
        return coordX.getValue();
    }

    public IntegerProperty coordXProperty() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX.setValue(coordX);
    }

    public int getCoordY() {
        return coordY.getValue();
    }

    public IntegerProperty coordYProperty() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY.setValue(coordY);
    }

    public String getDirection() {
        return direction.getValue();
    }

    public boolean estArrivé(){
        return this.coordX.getValue()==this.prochaineCase.getI()*32 && this.coordY.getValue()==this.prochaineCase.getJ()*32;
    }

    public void changeProchaineCase(){
        prochaineCase=this.environnement.getBfs().obtenirProchaineCase(prochaineCase);
    }

    public Case getProchaineCase() {
        return prochaineCase;
    }

    public void definirDirection() {
        if(this.coordX.getValue() < prochaineCase.getI()*32)
            direction.setValue("d");
        else if (this.coordX.getValue() > prochaineCase.getI()*32)
            direction.setValue("g");
        else if (this.coordY.getValue()< prochaineCase.getJ()*32)
            direction.setValue("b");
        else
            direction.setValue("h");
    }

    public ParcoursBFS getBfs() {
        return this.environnement.getBfs();
    }

    public boolean estArriveAuBout(){
        return this.coordX.getValue() == 29*32 && this.coordY.getValue() == 13*32;
    }

    public void seDeplace() {
        if(this.estArrivé()) {
            this.changeProchaineCase();
            if(this.getProchaineCase() != null)
                this.definirDirection();
        }
        if(this.getDirection().equals("d")){
            if(this.getCoordX()+ vitesse > prochaineCase.getI()*32)
                this.setCoordX(prochaineCase.getI()*32);
            else
                this.setCoordX(this.getCoordX()+ vitesse);
        }
        else if (this.getDirection().equals("g")){
            if(this.getCoordX()- vitesse < prochaineCase.getI()*32)
                this.setCoordX(prochaineCase.getI()*32);
            else
                this.setCoordX(this.getCoordX()- vitesse);
        }
        else if (this.getDirection().equals("h")){
            if(this.getCoordY() - vitesse < prochaineCase.getJ()*32)
                this.setCoordY(prochaineCase.getJ()*32);
            else
                this.setCoordY(this.getCoordY()-vitesse);
        }
        else if (this.getDirection().equals("b")){
            if(this.getCoordY() + vitesse > prochaineCase.getJ()*32)
                this.setCoordY(prochaineCase.getJ()*32);
            else
                this.setCoordY(this.getCoordY()+ vitesse);
        }
    }

    public StringProperty directionProperty() {
        return direction;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }

    public void pertPv(int dégâts){
        this.pv -= dégâts;
    }
}


