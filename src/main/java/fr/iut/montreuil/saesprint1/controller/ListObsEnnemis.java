package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.vue.SpriteEnnemi;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsEnnemis implements ListChangeListener<Ennemi> {

    private Pane pane;

    public ListObsEnnemis(Pane pane){
        this.pane = pane;
    }
    @Override
    public void onChanged(Change<? extends Ennemi> c) {
        while(c.next()) {
            if (c.wasAdded()) {
                Ennemi ennemiAjouté = c.getAddedSubList().get(0);
                SpriteEnnemi sprite = new SpriteEnnemi(ennemiAjouté, pane);
            }
        }

    }
}
