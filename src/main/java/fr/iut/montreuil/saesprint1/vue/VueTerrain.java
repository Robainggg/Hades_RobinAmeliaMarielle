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

    private static Image tileset =  new Image(VueTerrain.class.getResource("/images/terrain.png").toExternalForm());

    final static Image tileBlanche = new Image((VueTerrain.class.getResource("/images/tileChemin.png").toExternalForm()));
    final static Image tileNoire = new Image((VueTerrain.class.getResource("/images/tilePasChemin.png").toExternalForm()));

    public VueTerrain(TilePane tilePane, Terrain terrain){
        ImageView chemin = null;
        ImageView pasChemin = null;

        for (int i=0; i<terrain.getL().size(); i++){
            try {
                tilePane.getChildren().add(chargerImage(terrain.getL().get(i)-1));
            } catch (FileNotFoundException e) {
            }
//            if(terrain.getL().get(i) == 114) {
//                pasChemin = new ImageView(tileNoire);
//                tilePane.getChildren().add(pasChemin);
//            }
//            else{
//                chemin = new ImageView(tileBlanche);
//                tilePane.getChildren().add(chemin);
//            }



        }
    }
    
    public ImageView chargerImage(int indice) throws FileNotFoundException {
        int indiceUtilisé = indice;
        if(indiceUtilisé == 1)
            indiceUtilisé = 367;

        ImageView imageview = new ImageView(tileset);
        int tileWidth=32;
        int tileHeight=32;
        int indiceTile=indiceUtilisé;
        int tileParLigne= (int)(tileset.getWidth()/tileWidth);
        int indiceLigne= indiceTile/tileParLigne;
        int indiceColonne = indiceTile %tileParLigne;
        Rectangle2D viewport = new Rectangle2D(indiceColonne*tileWidth, indiceLigne*tileHeight, tileWidth, tileHeight);
        imageview.setViewport(viewport);
        return imageview;
    }


}
