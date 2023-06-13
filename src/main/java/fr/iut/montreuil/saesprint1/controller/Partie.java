package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Joueur;

public class Partie {

    private Joueur joueur;

    private Environnement environnement;

    private int niveau;

    public Partie(Joueur joueur, Environnement environnement){
        this.niveau = 0;
        this.joueur = joueur;
        this.environnement = environnement;


    }
}
