package fr.iut.montreuil.saesprint1.modele;

import fr.iut.montreuil.saesprint1.vue.SpriteEnnemi;
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
    private int ToursIvres;

    public Ennemi(Environnement environnement){

        this.idEnnemi = "E" + compteur;

        //Etat de l'ennemi
        this.estSorti = false;
        this.estMort = false;
        this.estRalenti = false;
        this.ToursIvres = 0;

        coordY = new SimpleIntegerProperty();
        coordX = new SimpleIntegerProperty();
        direction = new SimpleStringProperty();
        coordX.setValue(0*32);
        coordY.setValue(2*32);
        this.environnement = environnement;
        prochaineCase = new Case(0,2);
        vitesse = 2;
        recompense = 10;
        this.definirDirection();
        incrementeCompteur();
        pv = new SimpleIntegerProperty(100);
        ProgressBar barreDeVie = new ProgressBar();
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

        if(this.ToursIvres == 0){
            this.seDeplace();
        }
        else
            this.ToursIvres--;
        
    }
    public void meurt(){
        if(!this.estSorti)
            this.environnement.getJoueur().setArgent(this.environnement.getJoueur().getArgent()+this.recompense);
        this.environnement.getEnnemis().remove(this);
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
        return ToursIvres;
    }

    public void setToursIvres(int toursIvres) {
        ToursIvres = toursIvres;
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

    public IntegerProperty coordXProperty() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX.setValue(coordX);
    }

    public int getCoordY() {
        return coordY.getValue();
    }

    public IntegerProperty coordYProperty() {
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





}


