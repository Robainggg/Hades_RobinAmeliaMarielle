package fr.iut.montreuil.saesprint1.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur {
    private IntegerProperty pv;
    public IntegerProperty argent;
    
    public Joueur(){
        this.pv = new SimpleIntegerProperty(20);
        this.argent = new SimpleIntegerProperty(100);
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

}
