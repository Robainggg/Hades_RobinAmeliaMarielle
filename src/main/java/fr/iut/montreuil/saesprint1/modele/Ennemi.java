package fr.iut.montreuil.saesprint1.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Ennemi {
    private int pv;
    private int vitesse;
    private IntegerProperty coordX;
    private IntegerProperty coordY;
    private Environnement environnement;
    private char direction;
    private ParcoursBFS bfs;
    private Case prochaineCase;

    public Ennemi(Terrain terrain){
        coordY = new SimpleIntegerProperty();
        coordX = new SimpleIntegerProperty();
        coordX.setValue(0*32);
        coordY.setValue(2*32);
        terrain = terrain;
        bfs = new ParcoursBFS(terrain);
        prochaineCase = new Case(0,2);
        vitesse = 1;

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

//    public void changeDirection(){
//        char directionProchaineCase;
//        if((direction == 'd' || direction == 'g') && (this.coordX.getValue()-16)%32 == 0){
//            directionProchaineCase = new Case((this.coordX.getValue()-16)/32,(this.coordY.getValue()-16)/32).position(bfs.prochaineCase(new Case((this.coordX.getValue()-16)/32,(this.coordY.getValue()-16)/32)));
//            this.direction = directionProchaineCase;
//            }
//        else if ((direction == 'b' || direction == 'h') && (this.coordY.getValue()-16)%32 == 0){
//            directionProchaineCase = new Case((this.coordX.getValue()-16)/32,(this.coordY.getValue()-16)/32).position(bfs.prochaineCase(new Case((this.coordX.getValue()-16)/32,(this.coordY.getValue()-16)/32)));
//            this.direction = directionProchaineCase;
//        }
   // }

    public char getDirection() {
        return direction;
    }

    public boolean estArrivé(){
        System.out.println("est en " + coordX.getValue() + " , " + coordY.getValue() +  " et a pour cible " + (prochaineCase.getI()*32) + " , " + (prochaineCase.getJ()*32));
        return this.coordX.getValue()==this.prochaineCase.getI()*32 && this.coordY.getValue()==this.prochaineCase.getJ()*32;
    }

    public void changeProchaineCase(){
        prochaineCase=bfs.obtenirProchaineCase(prochaineCase);
    }

    public Case getProchaineCase() {
        return prochaineCase;
    }

    public void definirDirection() {
        if(this.coordX.getValue() < prochaineCase.getI()*32)
            direction = 'd';
        else if (this.coordX.getValue() > prochaineCase.getI()*32)
            direction = 'g';
        else if (this.coordY.getValue()< prochaineCase.getJ()*32)
            direction = 'b';
        else
            direction = 'h';
    }

    public ParcoursBFS getBfs() {
        return bfs;
    }

    public boolean estArriveAuBout(){
        return this.coordX.getValue() == 29*32 && this.coordY.getValue() == 13*32;
    }

    public void seDeplace() {
        if(this.estArrivé()) {
            this.changeProchaineCase();
            System.out.println(this.getProchaineCase());
            if(this.getProchaineCase() != null)
                this.definirDirection();
            System.out.println(this.getDirection());
        }
        if(this.getDirection() == 'd'){
            if(this.getCoordX()+ vitesse > prochaineCase.getI()*32)
                this.setCoordX(prochaineCase.getI()*32);
            else
                this.setCoordX(this.getCoordX()+ vitesse);
        }
        else if (this.getDirection() == 'g'){
            if(this.getCoordX()- vitesse < prochaineCase.getI()*32)
                this.setCoordX(prochaineCase.getI()*32);
            else
                this.setCoordX(this.getCoordX()- vitesse);
        }
        else if (this.getDirection() == 'h'){
            if(this.getCoordY() - vitesse < prochaineCase.getJ()*32)
                this.setCoordY(prochaineCase.getJ()*32);
            else
                this.setCoordY(this.getCoordY()-vitesse);
        }
        else if (this.getDirection() == 'b'){
            if(this.getCoordY() + vitesse > prochaineCase.getJ()*32)
                this.setCoordY(prochaineCase.getJ()*32);
            else
                this.setCoordY(this.getCoordY()+ vitesse);
        }
    }
}


