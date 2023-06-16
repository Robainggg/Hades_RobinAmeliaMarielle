package fr.iut.montreuil.saesprint1.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur {
    private IntegerProperty pv;
    public IntegerProperty argent;

    private DoubleProperty pourcentageVie;
    public Joueur(){
        this.pv = new SimpleIntegerProperty(5);
        this.argent = new SimpleIntegerProperty(60);
        this.pourcentageVie = new SimpleDoubleProperty(1.0);
    }

    public int getPv() {
        return pv.get();
    }

    public IntegerProperty pvProperty() {
        return pv;
    }

    public int getArgent() {
        return argent.get();
    }

    public void setArgent(int argent) {
        this.argent.set(argent);
    }

    public void gagneArgent (int argent) {this.argent.setValue(this.getArgent()+argent);}

    public IntegerProperty argentProperty() {
        return argent;
    }

    public final void paie(int argent){
        this.argent.setValue(this.getArgent()-argent);
    }

    public void perdPv(int pv) {
        this.pv.setValue(this.pv.getValue()-pv);
    }

    public boolean aPerdu(){
        if(this.pv.getValue() == 0){return true;}
        return false;
    }

    public void setPV(int pv){this.pv.setValue(pv);}

    public void resetJoueur(){
        this.setPV(20);
        this.setArgent(100);
    }

    public double getPourcentageVie() {
        return pourcentageVie.get();
    }

    public DoubleProperty pourcentageVieProperty() {
        return pourcentageVie;
    }

    public void setPourcentageVie(double pourcentageVie) {
        this.pourcentageVie.set(pourcentageVie);
    }

    public void perdPvD(int pvPerdus, int vieMax) {
        double nouveauPourcentage = (double) (vieMax - pvPerdus) / vieMax;
        this.pourcentageVie.set(nouveauPourcentage);
    }

}
