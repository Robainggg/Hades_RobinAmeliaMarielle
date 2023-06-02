package fr.iut.montreuil.saesprint1.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Joueur {
    private IntegerProperty pv;
    private IntegerProperty argent;

    public Joueur(){
        this.pv = new SimpleIntegerProperty(20);
        this.argent = new SimpleIntegerProperty(100);
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

    public IntegerProperty argentProperty() {
        return argent;
    }
}