package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Attaques.AttaqueTours;
import fr.iut.montreuil.saesprint1.modele.Attaques.Bouteille;
import fr.iut.montreuil.saesprint1.modele.Attaques.Tonneau;
import fr.iut.montreuil.saesprint1.modele.Attaques.Vegetation;
import fr.iut.montreuil.saesprint1.vue.SpriteAttaqueTours;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsAttaquesTours implements ListChangeListener<AttaqueTours> {
    private Pane pane;

    public ListObsAttaquesTours(Pane pane) {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends AttaqueTours> c) {
        while (c.next()) {
            if (c.wasAdded()) {
                AttaqueTours attaqueTours = c.getAddedSubList().get(0);
                SpriteAttaqueTours spriteAttaqueTours = new SpriteAttaqueTours(attaqueTours, pane);
            }
            if (c.wasRemoved()) {
                AttaqueTours finAttaque = c.getRemoved().get(0);
                this.pane.getChildren().remove(this.pane.lookup("#" + finAttaque.getId()));

                //Enlève les états ivre et effetsTonneau à la disparition des bouteilles et des tonneaux
                if(finAttaque instanceof Bouteille){
                    ((Bouteille) finAttaque).getEnnemi().setToursIvres(0);
                }
                else if(finAttaque instanceof Tonneau){
                    ((Tonneau) finAttaque).getEnnemi().setToursEffetTonneau(0);
                }
            }
        }

    }
}
