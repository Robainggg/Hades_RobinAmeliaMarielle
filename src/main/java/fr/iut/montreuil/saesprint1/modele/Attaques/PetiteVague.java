package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.TourAvecPortée;


//On aura 8 points de coordonnées par Vague qui avanceront autour de la tour sans sortir de la portée
public class PetiteVague extends Projectile{

    private TourAvecPortée tourAvecPortée;
    private int degats = 2;
    private static int vitesse = 1;

    public PetiteVague(TourAvecPortée tour, int coordX, int coordY) {
        super(tour, coordX, coordY,vitesse);
        this.tourAvecPortée = (TourAvecPortée) this.getTour();

    }

    @Override
    public void agit(){
        super.avance();

        if(!this.tourAvecPortée.estDansLaZone(this.getX(),this.getY())){
            this.tourAvecPortée.getEnv().supprimerProjectile(this);
        }

        for (int i = this.tourAvecPortée.getEnv().getEnnemis().size()-1; i >= 0; i--){
            Ennemi ennemi = this.tourAvecPortée.getEnv().getEnnemis().get(i);
            if(this.tourAvecPortée.ennemiZone(ennemi)!=null){
                if(ennemi.getCoordX() <= this.getX()+16 && ennemi.getCoordX()+32 >= this.getX()+16 &&
                        ennemi.getCoordY() <= this.getY()+16 && ennemi.getCoordY()+32 >= this.getY()+16) {
                    ennemi.pertPv(this.degats);
                    System.out.println(ennemi.getIdEnnemi() + " perd des PV");
                }
            }
        }
    }
    
}
