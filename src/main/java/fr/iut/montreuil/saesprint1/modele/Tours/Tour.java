package fr.iut.montreuil.saesprint1.modele.Tours;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public abstract class Tour {

    public final static int tailleCase = 32;

    private String nomTour;
    private int cout;
    private Environnement env;
    private IntegerProperty x;
    private IntegerProperty y;

    //De combien de tours sont espacés ses attaques
    private int espaceEntreAttaques;

    private int idTour;
    public static int compteur = 0 ;

    private int temps = 0;

    private boolean amélioré = false;
    

    public Tour(String nomTour, int cout, int x, int y, Environnement env, int espaceEntreAttaques) {
        this.nomTour = nomTour;
        this.cout = cout;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.env = env;
        this.espaceEntreAttaques = espaceEntreAttaques;
        this.idTour =compteur;
        compteur++;
    }

    public void agit(){
        if (!this.getEnv().getEnnemis().isEmpty()) {
            if(this.temps%this.getEspaceEntreAttaques()== 0) {
                this.attaque();
            }
            this.incrementeTemps();
        }

    }
    public abstract void attaque();

    public void améliorer(int coutAmélioration, int espaceEntreAttaquesAmélioré){
        if(!this.isAmélioré() && this.getEnv().getJoueur().getArgent() >= coutAmélioration){
            this.getEnv().getJoueur().setArgent(this.getEnv().getJoueur().getArgent()-coutAmélioration);
            this.setEspaceEntreAttaques(espaceEntreAttaquesAmélioré);
            this.setAmélioré(true);
            this.env.argentSuffisantProperty().setValue("");
        }
        else{
            this.env.argentSuffisantProperty().setValue("Pas assez d'argent pour l'amélioration");
        }
    }

    public boolean isAmélioré() { return this.amélioré;}

    public void incrementeTemps(){this.temps += 1;}
    //Getters & Setters
    public int getCout() {
        return cout;
    }
    public int getTemps() {
        return temps;
    }

    public final int getY() {
        return this.y.get();
    }

    public final int getX() {
        return this.x.get();
    }
    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public final IntegerProperty getXProperty(){
        return this.x;
    }

    public final IntegerProperty centreTourX(){
        IntegerProperty a = new SimpleIntegerProperty(this.x.get()+(tailleCase/2));
        return a;
    }

    public final IntegerProperty centreTourY(){
        IntegerProperty a = new SimpleIntegerProperty(this.y.get()+(tailleCase/2));
        return a;
    }

    public final IntegerProperty getYProperty(){
        return this.y;
    }

    public Environnement getEnv() {
        return env;
    }

    public String getId(){
        return "Tour"+this.idTour;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "nomTour='" + nomTour + '\'' +
                ", cout=" + cout +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public int getEspaceEntreAttaques() {
        return espaceEntreAttaques;
    }

    public int getPosX() {
        return getX();
    }

    public void setAmélioré(boolean amélioré) {
        this.amélioré = amélioré;
    }

    public void setEspaceEntreAttaques(int espaceEntreAttaques) {
        this.espaceEntreAttaques = espaceEntreAttaques;
    }
}