package fr.iut.montreuil.saesprint1.modele;

import fr.iut.montreuil.saesprint1.modele.Attaques.AttaqueTours;
import fr.iut.montreuil.saesprint1.modele.Attaques.Vegetation;
import fr.iut.montreuil.saesprint1.modele.Tours.Déméter;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private ObservableList<Ennemi> ennemis;
    private ObservableList<Tour> tours;
    private ObservableList<AttaqueTours> attaques;
    private ObservableList<Vegetation> vegetations;
    private Terrain terrain;
    private int temps;
    private ParcoursBFS bfs;
    private Joueur joueur;
    private boolean peutPlacerUneTour;

    private StringProperty argentSuffisant;
    private Partie partie;

    public Environnement() {
        this.ennemis = FXCollections.observableArrayList();
        this.tours = FXCollections.observableArrayList();
        this.attaques = FXCollections.observableArrayList();
        this.vegetations = FXCollections.observableArrayList();
        this.terrain = new Terrain();
        this.temps = 0;
        this.bfs = new ParcoursBFS(terrain);
        this.joueur = new Joueur();
        this.argentSuffisant = new SimpleStringProperty();
        peutPlacerUneTour = false;
    }

    public ObservableList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public void ajouterTour(Tour tour){
        if (this.joueur.getArgent() - tour.getCout() >= 0) {
            if (tour instanceof Déméter) {
                ((Déméter) tour).creerVégétation();
            }
            this.tours.add(tour);
            this.joueur.paie(tour.getCout());
            this.argentSuffisant.setValue("");
        }
        else{
            this.argentSuffisant.setValue("Pas assez d'argent pour acheter la tour");
        }

    }

    public void supprimerUneTour(Tour t){
        this.getTours().remove(t);
        this.joueur.gagneArgent(t.getCout()/2);
    }
    public void ajouterEnnemi(Ennemi ennemi){
        this.ennemis.add(ennemi);
    }

    public void ajouterAttaqueTours(AttaqueTours attaqueTours){this.attaques.add(attaqueTours);}

    public void supprimerAttaqueTours(AttaqueTours attaqueTours){
        this.attaques.remove(attaqueTours);
    }

    public void ajouterVegetation(Vegetation vegetation){this.vegetations.add(vegetation);}

    public void supprimerVegetation(Vegetation vegetation){
        this.vegetations.remove(vegetation);
    }

    public ObservableList<Vegetation> getVegetation(){return this.vegetations;}

    public Terrain getTerrain() {
        return terrain;
    }

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public void augmenteTemps(){
        this.temps++;
    }
    public int getTemps() {
        return temps;
    }

    public ParcoursBFS getBfs() {
        return bfs;
    }

    public ObservableList<AttaqueTours> getAttaques() {
        return attaques;
    }

    public boolean tourExiste ( int tourX, int tourY){
        for (Tour tour : this.getTours()) {
            int x = tour.getX() / Tour.tailleCase;
            int y = tour.getY() / Tour.tailleCase;
            if (x == tourX && y == tourY) {
                return true;
            }
        }
        return false;
    }
    
    public Joueur getJoueur() {
        return joueur;
    }


//    public void nouvelleVague(){
//
//        if(this.vagueActuelle.isVagueEstFinie()) {
//            niveau++;
//            this.vagueActuelle = new VagueEnnemie(this, joueur);
//        }
//    }

    //public VagueEnnemie getVagueActuelle() {
       // return vagueActuelle;
   // }

    public void changeEtatPlaçage(){
        this.peutPlacerUneTour = !peutPlacerUneTour;
    }

    public boolean isPeutPlacerUneTour() {
        return peutPlacerUneTour;
    }

    public void nettoieEnvironnement(){
        for(int i = ennemis.size()-1; i >= 0; i--)
            this.ennemis.remove(i);
        for(int i = attaques.size()-1; i >= 0; i--)
            this.attaques.remove(i);
        for(int i = vegetations.size()-1; i >= 0; i--)
            this.vegetations.remove(i);
        for(int i = tours.size()-1; i >= 0; i--)
            this.tours.remove(i);
    }
    public StringProperty argentSuffisantProperty() {
        return argentSuffisant;
    }
}