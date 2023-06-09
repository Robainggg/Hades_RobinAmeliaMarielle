package fr.iut.montreuil.saesprint1.modele;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Joueur;
import fr.iut.montreuil.saesprint1.modele.VagueEnnemie;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Partie {

    private Environnement environnement;

    private IntegerProperty niveau;

    private VagueEnnemie vagueActuelle;

    public Partie(Environnement environnement){
        this.niveau = new SimpleIntegerProperty(0);
        this.environnement = environnement;
        vagueActuelle = null;
    }

    public void lanceVague(){
        this.vagueActuelle = new VagueEnnemie(this,environnement);
        niveau.setValue(this.getNiveau()+1);
    }

    public IntegerProperty niveauProperty(){return this.niveau;}
    public int getNiveau() {
        return niveau.getValue();
    }

    public VagueEnnemie getVagueActuelle() {
        return vagueActuelle;
    }

    public void stoppeVagueActuelle(){
        this.vagueActuelle = null;
    }

    public void resetPartie(){
        this.niveau.setValue(0);
        this.vagueActuelle = null;
    }

}
