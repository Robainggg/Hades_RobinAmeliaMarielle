package fr.iut.montreuil.saesprint1.modele.Ennemis;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Terrain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnnemiTest {

    @Test
    void estArrivé() {
        Environnement environnement = new Environnement();
        Terrain terrain = new Terrain();
        Ennemi ennemiTest = new DiableJaune(environnement, 0, 2);
        ennemiTest.changeProchaineCase(); //Ici par rapport au terrain, sa prochaine case sera celle en dessous de lui
        assertFalse(ennemiTest.estArrivé()); //Il n'est pas encore sur la case cible donc "false" attendu
        ennemiTest.setCoordY(3*32);     //On le mets aux coordonnées de sa case cible
        assertTrue(ennemiTest.estArrivé());
    }

    @Test
    void definirDirection() {
        //Ici on va tester si l'ennemi est capable de trouver la direction dans laquelle il doit aller à chaque case.
        //Teste aussi indirectement la méthode prochaineCase.

        Environnement environnement = new Environnement();
        Terrain terrain = new Terrain();


        Ennemi ennemiTest = new DiableJaune(environnement, 0, 3);
        ennemiTest.changeProchaineCase();
        ennemiTest.definirDirection(); //Ici l'ennemi sera obligé d'aller à droite
        assertEquals("d", ennemiTest.getDirection());


        ennemiTest = new DiableJaune(environnement,1,19);
        ennemiTest.changeProchaineCase();
        ennemiTest.definirDirection();
        assertEquals("h", ennemiTest.getDirection());


        ennemiTest = new DiableJaune(environnement,11,2); //Ici mis exprès en dehors sur la droite du chemin pour qu'il aille à gauche
        ennemiTest.changeProchaineCase();                                       // car pas de cas de figure ou le diable va à gauche.
        ennemiTest.definirDirection();
        assertEquals("g", ennemiTest.getDirection());


        ennemiTest = new DiableJaune(environnement,9,4);
        ennemiTest.changeProchaineCase();
        ennemiTest.definirDirection();
        assertEquals("b", ennemiTest.getDirection());
    }

    @Test
    void estArriveAuBout() {
        Environnement environnement = new Environnement();
        Terrain terrain = new Terrain();

        Ennemi ennemi = new DiableJaune(environnement,5,7);
        assertFalse(ennemi.estArriveAuBout());


        ennemi = new DiableJaune(environnement, 29,13);
        assertTrue(ennemi.estArriveAuBout());
    }

}