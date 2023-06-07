package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.vue.SpriteEnnemi;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
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
                System.out.println("a été ajouté");
                Ennemi ennemiAjouté = c.getAddedSubList().get(0);
                SpriteEnnemi sprite = new SpriteEnnemi(ennemiAjouté, pane);

            }
            if(c.wasRemoved()) {
                Ennemi ennemiRetiré = c.getRemoved().get(0);
                for(int i = 0; i < pane.getChildren().size(); i++)
                    if(pane.getChildren().get(i).getId().equals(ennemiRetiré.getIdEnnemi()))
                        pane.getChildren().remove(i);
            }
        }

    }
}
