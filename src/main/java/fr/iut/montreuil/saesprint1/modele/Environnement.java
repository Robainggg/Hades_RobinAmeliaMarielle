package fr.iut.montreuil.saesprint1.modele;

import fr.iut.montreuil.saesprint1.controller.VagueEnnemie;
import fr.iut.montreuil.saesprint1.modele.Attaques.AttaqueTours;
import fr.iut.montreuil.saesprint1.modele.Attaques.Projectile;
import fr.iut.montreuil.saesprint1.modele.Tours.Déméter;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private ObservableList<Ennemi> ennemis;
    private ObservableList<Tour> tours;
    private ObservableList<AttaqueTours> attaques;
    private Terrain terrain;
    private int temps;
    private ParcoursBFS bfs;
    private Joueur joueur;
    private int niveau;
    private VagueEnnemie vagueActuelle;

    public Environnement() {
        this.ennemis = FXCollections.observableArrayList();
        this.tours = FXCollections.observableArrayList();
        this.attaques = FXCollections.observableArrayList();
        this.terrain = new Terrain();
        this.temps = 0;
        this.bfs = new ParcoursBFS(terrain);
        this.joueur = new Joueur();
        this.niveau = 0;
        this.vagueActuelle = new VagueEnnemie(this,joueur);
    }

    public ObservableList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public void ajouterTour(Tour tour){
        
        if(this.joueur.getArgent()-tour.getCout() > 0){
            if(tour instanceof Déméter){
                ((Déméter) tour).creerVégétation();
            }
            this.tours.add(tour);
            this.joueur.paie(tour.getCout());
            System.out.println("le joueur paie une tour");
        }
        
    }

    public void ajouterEnnemi(Ennemi ennemi){
        this.ennemis.add(ennemi);
    }

    public void ajouterAttaqueTours(AttaqueTours attaqueTours){this.attaques.add(attaqueTours);}

    public void supprimerAttaqueTours(AttaqueTours attaqueTours){
        this.attaques.remove(attaqueTours);
    }

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
    
    public Joueur getJoueur() {
        return joueur;
    }

    public int getNiveau() {
        return niveau;
    }

    public void nouvelleVague(){

        if(this.vagueActuelle.isVagueEstFinie()) {
            niveau++;
            this.vagueActuelle = new VagueEnnemie(this, joueur);
        }
    }

    public VagueEnnemie getVagueActuelle() {
        return vagueActuelle;
    }
}