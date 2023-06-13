package fr.iut.montreuil.saesprint1.controller;

import fr.iut.montreuil.saesprint1.modele.Environnement;
import fr.iut.montreuil.saesprint1.modele.Tours.Tour;
import fr.iut.montreuil.saesprint1.vue.SpriteTour;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsTours implements ListChangeListener<Tour> {

    private Pane pane;
    private Environnement evt;

    public ListObsTours(Pane pane,Environnement evt) {
        this.pane = pane;
        this.evt = evt;
    }

    @Override
    public void onChanged(Change<? extends Tour> c) {
        while(c.next()){
            if(c.wasAdded()){
               Tour tour = c.getAddedSubList().get(0);
               SpriteTour spriteTour = new SpriteTour(tour,pane,evt);
            }
        }
    }
}
