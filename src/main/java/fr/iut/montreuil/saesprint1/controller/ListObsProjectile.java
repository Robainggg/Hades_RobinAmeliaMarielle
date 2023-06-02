package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Projectile;
import fr.iut.montreuil.saesprint1.vue.SpriteProjectile;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsProjectile implements ListChangeListener<Projectile> {
    private Pane pane;

    public ListObsProjectile(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends Projectile> c) {
        while(c.next()){
            if(c.wasAdded()){
                Projectile projectile = c.getAddedSubList().get(0);
                SpriteProjectile sprite = new SpriteProjectile(projectile, pane);
            }
            if(c.wasRemoved()){

            }
        }
        
    }
}
