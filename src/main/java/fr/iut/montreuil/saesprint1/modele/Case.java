package fr.iut.montreuil.saesprint1.modele;


public class Case {
    private int i;
    private int j;


    public Case(int i, int j){
        this.i=i;
        this.j=j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String toString(){
        return this.i + " , " +this.j;
    }

}
