package fr.iut.montreuil.saesprint1;

import fr.iut.montreuil.saesprint1.modele.*;
import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;
import fr.iut.montreuil.saesprint1.modele.Tours.Dionysos;

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
        Ennemi ennemi = new Ennemi(environnement);

        Artémis tour1 = new Artémis(10,20,environnement);
        Dionysos dyo = new Dionysos(21,16,environnement);
//        System.out.println(tour1.getEspaceEntreAttaques());
//        System.out.println(tour1.getPortée());
        System.out.println(dyo.getEspaceEntreAttaques());
        dyo.attaque();
        dyo.améliorer();
        System.out.println(dyo.getEspaceEntreAttaques());
        dyo.attaque();

    }
}
