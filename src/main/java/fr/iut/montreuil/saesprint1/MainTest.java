package fr.iut.montreuil.saesprint1;

import fr.iut.montreuil.saesprint1.modele.Case;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.ParcoursBFS;
import fr.iut.montreuil.saesprint1.modele.Terrain;
import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;

public class MainTest {

    public static void main(String[] args) {
        
        Terrain terrain = new Terrain();
        terrain.afficheTableau();
        ParcoursBFS p = new ParcoursBFS(terrain);
        p.afficherParcours();
        p.getParcours();
        System.out.println(p.getParcours().size());
        System.out.println(p.obtenirProchaineCase(new Case(22,8)));
        System.out.println(p.getBFS()[2][1]);
        System.out.println();

        //Test de l'amélioration
        Environnement environnement = new Environnement();
        Artémis tour1 = new Artémis(10,20,environnement);
        System.out.println(tour1.getEspaceEntreAttaques());
        System.out.println(tour1.getPortée());
        tour1.améliorer();
        System.out.println(tour1.getEspaceEntreAttaques());
        tour1.attaque();
        System.out.println(tour1.getPortée());
    }
}
