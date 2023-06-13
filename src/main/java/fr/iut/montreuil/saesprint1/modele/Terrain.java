package fr.iut.montreuil.saesprint1.modele;

import java.util.Arrays;
import java.util.List;

public class Terrain {

    public static final int dimY = 20;
    public static final int dimX = 30;

    private int departX;
    private int departY;
    private int finX;
    private int finY;
    private ParcoursBFS bfs;

    private List<Integer> l;
    private int[][] tableauTerrain;

    public Terrain (){
        this.l = Arrays.asList(
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114,
                111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 114, 111, 111, 111, 111, 111,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111,
                114, 114, 114, 114, 114, 114, 114, 111, 111, 111, 111, 111, 111, 114, 111, 111, 111, 111, 111, 111, 111, 111, 111, 114, 114, 114, 114, 114, 114, 111,
                114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 114, 111,
                114, 114, 114, 114, 114, 114, 111, 111, 114, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 114, 111,
                114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 111, 111, 111, 114, 111, 111, 111, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 114, 111,
                114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 111, 114, 111, 111, 111, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 114, 114, 114, 114, 111,
                114, 114, 114, 114, 114, 114, 111, 111, 111, 111, 111, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 111, 111, 111, 111, 2,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 111, 114, 114, 114, 114,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111, 114, 114, 111, 114, 114, 114, 114,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 111, 111, 111, 111, 114, 114, 114, 114,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114,
                114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114, 114);

        finX = 23;
        finY = 14;
        this.tableauTerrain = terrainSousTableau();

        bfs = new ParcoursBFS(this);


    }


    //La Liste en Tableau int[][]
    public int[][] terrainSousTableau() {

        int[][] tab = new int[dimY][dimX];
        int index = 0;
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                tab[i][j] = l.get(index);
                index++;



            }
        }
        return tab;
    }



    public void afficheTableau() {
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                System.out.print(tableauTerrain[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Case[][] terrainCases(){
        Case[][] tabCases = new Case[dimY][dimX];
        return tabCases;
    }


    public boolean caseEstDansTerrain(Case c){
        return c.getI() >= 0 && c.getI() < dimX && c.getJ() >= 0 && c.getJ() < dimY;
    }

    public boolean coordEstDansTerrain(int x, int y){
        return x >= 0 && y >=0 && x<=dimX && y <= dimY;
    }


    //GETTERS
    public List<Integer> getL() {
        return l;
    }
    public int[][] getTableauTerrain() {
        return tableauTerrain;
    }


    public int getDepartX() {
        return departX;
    }

    public int getDepartY() {
        return departY;
    }

    public int getFinX() {
        return finX;
    }

    public int getFinY() {
        return finY;
    }

    public int get(int i){
      return l.get(i);
    }






}


