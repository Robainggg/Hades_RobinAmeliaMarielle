package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Joueur;

public class Partie {

    private Joueur joueur;

    private Environnement environnement;

    private int niveau;

    private VagueEnnemie vagueActuelle;

    public Partie(Joueur joueur, Environnement environnement){
        this.niveau = 0;
        this.joueur = joueur;
        this.environnement = environnement;
        vagueActuelle = null;
        //vagueActuelle = new VagueEnnemie(environnement,joueur);
    }

    public void lanceVague(){
        this.vagueActuelle = new VagueEnnemie(this,joueur, environnement);
        niveau++;
    }


    public int getNiveau() {
        return niveau;
    }

    public VagueEnnemie getVagueActuelle() {
        return vagueActuelle;
    }

    public void stoppeVagueActuelle(){
        this.vagueActuelle = null;
    }

    public void resetPartie(){
        this.niveau = 0;
        this.vagueActuelle = null;
    }

}
