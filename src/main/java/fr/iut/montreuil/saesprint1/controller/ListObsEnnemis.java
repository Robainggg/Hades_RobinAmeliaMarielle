package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Ennemis.Ennemi;
import fr.iut.montreuil.saesprint1.vue.SpriteEnnemi;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ListObsEnnemis implements ListChangeListener<Ennemi> {

    private Pane pane;
    private List<ProgressBar> barresDeVie = new ArrayList<>();
    private List<SpriteEnnemi> spritesEnnemis = new ArrayList<>();


    public ListObsEnnemis(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends Ennemi> c) {
        while (c.next()) {
            //créer les sprite et leur barre de vie + ajout au pane.
            if (c.wasAdded()) {
                Ennemi ennemiAjouté = c.getAddedSubList().get(0);
                SpriteEnnemi sprite = new SpriteEnnemi(ennemiAjouté, pane);
                spritesEnnemis.add(sprite);
            }

            //retirer l'ennemi et sa barre dans le pane.
            if (c.wasRemoved()) {
                Ennemi ennemiRetiré = c.getRemoved().get(0);
                for (int i = 0; i < spritesEnnemis.size(); i++) {
                    SpriteEnnemi spriteEnnemi = spritesEnnemis.get(i);
                    if (spriteEnnemi.getEnnemi().equals(ennemiRetiré)) {
                        pane.getChildren().remove(spriteEnnemi.getImage());
                        pane.getChildren().remove(spriteEnnemi.getBarreDeVie());
                        spritesEnnemis.remove(spriteEnnemi);
                        break; // Sortir de la boucle une fois que l'ennemi et le sprite sont supprimés
                    }
                }
            }


        }
    }
}