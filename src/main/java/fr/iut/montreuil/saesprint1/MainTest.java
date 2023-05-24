package fr.iut.montreuil.saesprint1;

import fr.iut.montreuil.saesprint1.modele.Case;
import fr.iut.montreuil.saesprint1.modele.ParcoursBFS;
import fr.iut.montreuil.saesprint1.modele.Terrain;

public class MainTest {

    public static void main(String[] args) {

        Terrain terrain = new Terrain();
        terrain.afficheTableau();
        ParcoursBFS p = new ParcoursBFS(terrain);
        p.afficherParcours();
        p.getParcours();
        System.out.println(p.getParcours().size());
       // System.out.println(p.);
        System.out.println(p.obtenirProchaineCase(new Case(22,8)));
        System.out.println(p.getBFS()[2][1]);
    }
}
