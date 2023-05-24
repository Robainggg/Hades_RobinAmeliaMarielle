package fr.iut.montreuil.saesprint1.vue;

import fr.iut.montreuil.saesprint1.modele.Terrain;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueTerrain {
    private TilePane tilePane;
    private Terrain terrain;

    public VueTerrain(TilePane tilePane, Terrain terrain){
        for (int i=0; i<terrain.getL().size(); i++){
            try {
                tilePane.getChildren().add(chargerImage(terrain.getL().get(i)-1));
            } catch (FileNotFoundException e) {
            }


        }
    }


    public ImageView chargerImage(int indice) throws FileNotFoundException {
        Image tileset = new Image(getClass().getResource("/images/terrain.png").toExternalForm());
        ImageView imageview = new ImageView(tileset);
        int tileWidth=32;
        int tileHeight=32;
        int indiceTile=indice;
        int tileParLigne= (int)(tileset.getWidth()/tileWidth);
        int indiceLigne= indiceTile/tileParLigne;
        int indiceColonne = indiceTile %tileParLigne;
        Rectangle2D viewport = new Rectangle2D(indiceColonne*tileWidth, indiceLigne*tileHeight, tileWidth, tileHeight);
        imageview.setViewport(viewport);
        return imageview;
    }


}
