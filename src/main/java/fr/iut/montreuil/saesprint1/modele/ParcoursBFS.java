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
                        && BFS[nextJ][nextI] == -1 && terrain.getTableauTerrain()[nextJ][nextI] != 371 ) {

                    if (BFS[nextJ][nextI] > BFS[cJ][cI] + 1 || BFS[nextJ][nextI] == -1) {
                        aFaire.add(new Case(nextI, nextJ));
                        BFS[nextJ][nextI] = BFS[cJ][cI] + 1;
                        parcours.addFirst(new Case(cJ+1,cI+1));
                    }
                }
            }
        }
    }

    public void afficherParcours(){
        for (int i = 0 ; i < BFS.length ; i++){
            for (int j = 0 ; j < BFS[0].length ; j++){
                if (BFS[i][j] >= 0 && BFS[i][j] < 10){
                }
            }
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
    
}