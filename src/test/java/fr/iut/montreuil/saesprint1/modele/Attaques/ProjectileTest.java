package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemis.DiableJaune;
import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Terrain;
import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectileTest {

    @Test
    void toucheUnEnnemi() {
        Environnement environnement = new Environnement();
        Terrain terrain = new Terrain();
        Ennemi ennemiTest = new DiableJaune(environnement, 2, 2);
        Artémis artemis = new Artémis(5, 0,environnement);
        Flèche fleche = new Flèche(artemis, 0,64);
        fleche.setX(80);
        fleche.setY(48);
        assertTrue(fleche.toucheUnEnnemi(ennemiTest));

        fleche.setY(47);
        assertFalse(fleche.toucheUnEnnemi(ennemiTest));

        fleche.setY(80);
        assertTrue(fleche.toucheUnEnnemi(ennemiTest));

        fleche.setY(81);
        assertFalse(fleche.toucheUnEnnemi(ennemiTest));

        fleche.setX(48);
        fleche.setY(80);
        assertTrue(fleche.toucheUnEnnemi(ennemiTest));

        fleche.setX(47);
        assertFalse(fleche.toucheUnEnnemi(ennemiTest));

        fleche.setX(80);
        assertTrue(fleche.toucheUnEnnemi(ennemiTest));

        fleche.setX(81);
        assertFalse(fleche.toucheUnEnnemi(ennemiTest));
    }
}