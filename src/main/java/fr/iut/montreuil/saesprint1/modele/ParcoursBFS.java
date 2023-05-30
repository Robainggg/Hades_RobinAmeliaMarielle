package fr.iut.montreuil.saesprint1.modele;

import java.util.ArrayList;
import java.util.LinkedList;

public class ParcoursBFS {

    private Terrain terrain;
    private int[][] BFS;
    private LinkedList<Case> parcours;


    public ParcoursBFS(Terrain t) {
        this.terrain = t;
        parcours = new LinkedList<>();
        this.BFS = new int[Terrain.dimY][Terrain.dimX];
        for (int i = 0; i < BFS.length; i++) {
            for (int j = 0; j < BFS[i].length; j++) {
                BFS[i][j] = -1; //code par défaut pour les tours
                if (terrain.getTableauTerrain()[i][j] == 2) {
                    BFS[i][j] = 0;

                }
            }
        }

        remplirGrilleBFS();

    }

    public void remplirGrilleBFS() {
        ArrayList<Case> aFaire = new ArrayList<>();
        for (int i = 0; i < BFS.length; i++) {
            for (int j = 0; j < BFS[i].length; j++) {
                if (terrain.getTableauTerrain()[i][j] == 2) {
                    Case c = new Case(j, i);
                    aFaire.add(c);
                    BFS[i][j] = 0;
                }
            }
        }

        while (!aFaire.isEmpty()) {
            Case predecesseur = aFaire.remove(0);
            int cI = predecesseur.getI();
            int cJ = predecesseur.getJ();

            int[][] voisins = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            for (int[] direction : voisins) {
                int nextI = cI + direction[0];
                int nextJ = cJ + direction[1];

                if (terrain.caseEstDansTerrain(new Case(nextI, nextJ))
                        && terrain.getTableauTerrain()[nextJ][nextI] != 0
                        && BFS[nextJ][nextI] == -1 && terrain.getTableauTerrain()[nextJ][nextI] != 114 ) {

                    if (BFS[nextJ][nextI] > BFS[cJ][cI] + 1 || BFS[nextJ][nextI] == -1) {
                        aFaire.add(new Case(nextI, nextJ));
                        BFS[nextJ][nextI] = BFS[cJ][cI] + 1;
                        parcours.addFirst(new Case(cJ+1,cI+1));
                    }
                }
            }
        }
    }

//    public ArrayList<Case> creerParcours(Case source){
//        int cX,cY;
//        cX = source.getI();
//        cY=source.getJ();
//        ArrayList<Case> parcours = new ArrayList<>();
//        Case caseRegardée = new Case(cX,cY);
//        Case caseMin = null;
//        int min = 10000;
//
//

//        while(cX != terrain.getFinX() || cY != terrain.getFinY()) {
//            int[][] voisins = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//
//            for (int[] direction : voisins) {
//                int nextI = cX + direction[0];
//                int nextJ = cY + direction[1];
//
//                if (terrain.caseEstDansTerrain(new Case(nextI, nextJ))) {
//                    if(BFS[nextI][nextJ] < min && BFS[nextI][nextJ]!=-1){
//                        min = BFS[nextI][nextJ];
//                        caseMin = new Case(nextI,nextJ);
//                    }
//                }
//
//            }
//            parcours.add(caseMin);
//        }
//        return parcours;
//    }

//    public Case prochaineCase(Case c){
//        int cI = c.getI();
//        int cJ = c.getJ();
//        Case caseMin;
//
//        int[][] voisins = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//        ArrayList<Case> listeVoisins = new ArrayList<>();
//
//        for (int[] direction : voisins) {
//
//            Case caseRegardée = new Case(cI + direction[0], cJ + direction[1]);
//
//
//            if (terrain.caseEstDansTerrain(caseRegardée)) {
//                listeVoisins.add(caseRegardée);
//            }
//        }
//
//        caseMin = listeVoisins.get(0);
//
//        for(int i = 1; i<listeVoisins.size();i++){
//            if(BFS[caseMin.getJ()][caseMin.getI()] > BFS[listeVoisins.get(i).getJ()][listeVoisins.get(i).getI()]){
//                caseMin = listeVoisins.get(i);
//            }
//        }
//        return caseMin;
//    }


    public void afficherParcours(){
        for (int i = 0 ; i < BFS.length ; i++){
            for (int j = 0 ; j < BFS[0].length ; j++){
                if (BFS[i][j] >= 0 && BFS[i][j] < 10){
                    System.out.print(" ");
                }
                System.out.print(BFS[i][j] + " ");
            }
            System.out.println();
        }
    }

    public LinkedList<Case> getParcours() {
        return parcours;
    }

    public Case obtenirProchaineCase(Case caseActuelle) {
        Case caseRegardée;
        int cI = caseActuelle.getI();
        int cJ = caseActuelle.getJ();

        int[][] voisins = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int valeurCaseMin = 99999;
        Case caseMin = null;


        for (int[] direction : voisins) {

            caseRegardée = new Case(cI + direction[0], cJ + direction[1]);


            if (terrain.caseEstDansTerrain(caseRegardée)) {
                if (BFS[caseRegardée.getJ()][caseRegardée.getI()] <= valeurCaseMin && BFS[caseRegardée.getJ()][caseRegardée.getI()] !=-1) {
                    caseMin = new Case(caseRegardée.getI(), caseRegardée.getJ());
                    valeurCaseMin = BFS[caseMin.getJ()][caseMin.getI()];
                }
            }
        }
        return caseMin;
    }


    public int[][] getBFS() {
        return BFS;
    }
}