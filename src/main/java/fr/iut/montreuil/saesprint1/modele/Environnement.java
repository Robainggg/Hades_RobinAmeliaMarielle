package fr.iut.montreuil.saesprint1.modele;

import fr.iut.montreuil.saesprint1.modele.Projectiles.Projectile;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private ObservableList<Ennemi> ennemis;
    private ObservableList<Tour> tours;
    private ObservableList<Projectile> projectiles;
    private Terrain terrain;
    private int temps;
    private ParcoursBFS bfs;
    private Joueur joueur;

    public Environnement() {
        this.ennemis = FXCollections.observableArrayList();
        this.tours = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.terrain = new Terrain();
        this.temps = 0;
        this.bfs = new ParcoursBFS(terrain);
        this.joueur = new Joueur();
    }

    public ObservableList<Ennemi> getEnnemis() {
        return ennemis;
    }

    public void ajouterTour(Tour tour){
        if(!(this.joueur.getArgent()-tour.getCout() < 0))
            this.tours.add(tour);
    }

    public void ajouterEnnemi(Ennemi ennemi){
        this.ennemis.add(ennemi);
    }

    public void ajouterProjectile(Projectile projectile){this.projectiles.add(projectile);}

    public void supprimerProjectile(Projectile projectile){
        this.projectiles.remove(projectile);
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

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }
    
    public Joueur getJoueur() {
        return joueur;
    }
}