package fr.iut.montreuil.saesprint1.modele;

import fr.iut.montreuil.saesprint1.vue.VueInventaire;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ProgressBar;

public class Ennemi {

    //Constructeur
    private static int compteur = 0;
    private String idEnnemi;
    private IntegerProperty pv;
    private int vitesse;
    private int coordXDepart;
    private int coordYDepart;

    //Direction
    private IntegerProperty coordX;
    private IntegerProperty coordY;
    private Environnement environnement;
    private StringProperty direction;
    private Case prochaineCase;

    //Etat
    private boolean estMort;
    private int recompense;
    private boolean estSorti;
    private boolean estRalenti;
    private int toursIvres;
    private int toursEffetTonneau;



    public Ennemi(Environnement environnement, int coordXDepart, int coordYDepart){

        this.idEnnemi = "E" + compteur;
        incrementeCompteur();

        //Etat de l'ennemi
        this.estSorti = false;
        this.estMort = false;
        this.estRalenti = false;
        this.toursIvres = 0;
        this.pv = new SimpleIntegerProperty(100);

        coordY = new SimpleIntegerProperty(coordYDepart*32);
        coordX = new SimpleIntegerProperty(coordXDepart*32);
        direction = new SimpleStringProperty();
        this.environnement = environnement;
        prochaineCase = new Case(coordXDepart,coordYDepart);
        vitesse = 2;
        recompense = 10;
        this.definirDirection();
    }
    
    private static void incrementeCompteur(){
        compteur++;
    }

    public boolean estArrivé(){
        return this.coordX.getValue()==this.prochaineCase.getI()*32 && this.coordY.getValue()==this.prochaineCase.getJ()*32;
    }

    public void changeProchaineCase(){
        prochaineCase=this.environnement.getBfs().obtenirProchaineCase(prochaineCase);
    }

    public void definirDirection() {
        if(this.coordX.getValue() < prochaineCase.getI()*32)
            direction.setValue("d");
        else if (this.coordX.getValue() > prochaineCase.getI()*32)
            direction.setValue("g");
        else if (this.coordY.getValue()< prochaineCase.getJ()*32)
            direction.setValue("b");
        else
            direction.setValue("h");
    }

    public boolean estArriveAuBout(){

        if(this.coordX.getValue() == 29*32 && this.coordY.getValue() == 13*32) {
            this.estSorti = true;
            this.setToursIvres(0);
            this.setToursEffetTonneau(0);
            return true;
        }
        return false;
    }

    public void seDeplace() {

            if (this.estArriveAuBout()) {
                this.estSorti = true;
                this.meurt();
            }
            if (this.estArrivé()) {
                this.changeProchaineCase();
                if (this.getProchaineCase() != null)
                    this.definirDirection();
            }
            if (this.getDirection().equals("d")) {
                if (this.getCoordX() + vitesse > prochaineCase.getI() * 32)
                    this.setCoordX(prochaineCase.getI() * 32);
                else
                    this.setCoordX(this.getCoordX() + vitesse);
            } else if (this.getDirection().equals("g")) {
                if (this.getCoordX() - vitesse < prochaineCase.getI() * 32)
                    this.setCoordX(prochaineCase.getI() * 32);
                else
                    this.setCoordX(this.getCoordX() - vitesse);
            } else if (this.getDirection().equals("h")) {
                if (this.getCoordY() - vitesse < prochaineCase.getJ() * 32)
                    this.setCoordY(prochaineCase.getJ() * 32);
                else
                    this.setCoordY(this.getCoordY() - vitesse);
            } else if (this.getDirection().equals("b")) {
                if (this.getCoordY() + vitesse > prochaineCase.getJ() * 32)
                    this.setCoordY(prochaineCase.getJ() * 32);
                else
                    this.setCoordY(this.getCoordY() + vitesse);
            }
    }

    public void agit(){

        if(this.toursIvres == 0 && this.toursEffetTonneau == 0){
            this.seDeplace();
        }
        else if(this.toursIvres > 0){
            this.toursIvres--;
        }
        else{
            this.toursEffetTonneau--;
        }
        
    }
    public void meurt(){
        if(!this.estSorti)
            this.environnement.getJoueur().setArgent(this.environnement.getJoueur().getArgent()+this.recompense);
        else
            this.environnement.getJoueur().perdPv(1);
            this.environnement.getEnnemis().remove(this);
            this.setToursIvres(0);
            this.setToursEffetTonneau(0);
            this.estMort = true;

    }
     public void pertPv(int dégâts) {
        System.out.println(this.idEnnemi + " a " + getPv() + " points de vies, et perds " + dégâts + " points de vies ");
        setPv(getPv() - dégâts);
        if (getPv() <= 0) {
            this.meurt();
        }
    }
    public boolean estMort(){return this.estMort;}
    public boolean estRalenti(){return this.estRalenti;}

    public void seFaitRalentir(int ralentissement){
        this.vitesse -= ralentissement;
        this.estRalenti = true;
    }

    public void nestPlusRalenti(int ralentissement){
        this.vitesse += ralentissement;
        this.estRalenti = false;
    }


    //
    //Getters & Setters -> Etat de l'ennemi/Interaction avec le modèle
    public String getIdEnnemi() {
        return idEnnemi;
    }
    public Environnement getEnvironnement() {
        return environnement;
    }
    public int getToursIvres() {
        return toursIvres;
    }

    public void setToursIvres(int toursIvres) {
        this.toursIvres = toursIvres;
    }



    //
    // Getters & Setters ->  Mouvements de l'ennemi
    public StringProperty directionProperty() {
        return direction;
    }
    public ParcoursBFS getBfs() {
        return this.environnement.getBfs();
    }
    public Case getProchaineCase() {
        return prochaineCase;
    }
    public String getDirection() {
        return direction.getValue();
    }
    public int getCoordX() {
        return coordX.getValue();
    }

    public final IntegerProperty coordXProperty() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX.setValue(coordX);
    }

    public final int getCoordY() {
        return coordY.getValue();
    }

    public final IntegerProperty coordYProperty() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY.setValue(coordY);
    }

    public final IntegerProperty mainXEnnemi(){
        IntegerProperty a = new SimpleIntegerProperty(this.getCoordX()+16);
        return a;
    }
    public final IntegerProperty mainYEnnemi(){
        IntegerProperty a = new SimpleIntegerProperty(this.getCoordY()+16);
        return a;
    }

    public boolean isEstMort() {
        return estMort;
    }

    public int getPv() {
        return pv.get();
    }

    public IntegerProperty pvProperty() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv.set(pv);
    }


    public int getToursEffetTonneau() { return toursEffetTonneau;}

    public void setToursEffetTonneau(int toursEffetTonneau) {
        this.toursEffetTonneau = toursEffetTonneau;
    }

    public double getPointsDeVieMax() {
        return 100;
    }
}


