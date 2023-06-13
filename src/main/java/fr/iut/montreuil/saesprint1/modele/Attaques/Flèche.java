package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.Artémis;

public class Flèche extends Projectile {

    private Artémis tourArtémis;
    private static int degats = 5;
    private static int vitesse = 8;
    
    public Flèche(Artémis tour,int coordXEnnemi, int coordYEnnemi) {
        super(tour, coordXEnnemi, coordYEnnemi,vitesse);
        this.tourArtémis = tour;
    }

    @Override
    public void agit() {

        super.avance();
        
        //S'il sort de la portée de sa tour
        if(!this.tourArtémis.estDansLaZone(this.getX(),this.getY())){
            this.tourArtémis.getEnv().supprimerAttaqueTours(this);
        }

        //S'il touche un ennemi
        for (int i = this.tourArtémis.getEnv().getEnnemis().size()-1; i >= 0; i--){
            Ennemi ennemi = this.tourArtémis.getEnv().getEnnemis().get(i);
            if(this.tourArtémis.ennemiZone(ennemi)!=null){
                if(ennemi.getCoordX() <= this.getX()+16 && ennemi.getCoordX()+32 >= this.getX()+16 &&
                        ennemi.getCoordY() <= this.getY()+16 && ennemi.getCoordY()+32 >= this.getY()+16) {
                    this.getTour().getEnv().supprimerAttaqueTours(this);
                    ennemi.pertPv(this.degats);
                    System.out.println(ennemi.getIdEnnemi() + " perd des PV");
                    if(ennemi.isEstMort())
                        tourArtémis.setEnnemiAttaqué();
                }
                else
                    tourArtémis.setEnnemiAttaqué();
            }

        }
    }
}
