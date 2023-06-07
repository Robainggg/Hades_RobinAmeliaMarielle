package fr.iut.montreuil.saesprint1.modele.Attaques;

import fr.iut.montreuil.saesprint1.modele.Ennemi;
import fr.iut.montreuil.saesprint1.modele.Tours.TourAvecPortée;

public class Flèche extends Projectile {

    private TourAvecPortée tourAvecPortée;
    private static int degats = 1;
    private static int vitesse = 5;
    
    public Flèche(TourAvecPortée tour,int coordXEnnemi, int coordYEnnemi) {
        super(tour, coordXEnnemi, coordYEnnemi,vitesse);
        this.tourAvecPortée = (TourAvecPortée) this.getTour();
    }

    @Override
    public void agit() {

        super.avance();
        
        //S'il sort de la portée de sa tour
        if(!this.tourAvecPortée.estDansLaZone(this.getX(),this.getY())){
            this.tourAvecPortée.getEnv().supprimerProjectile(this);
        }

        //S'il touche un ennemi
        for (int i = this.tourAvecPortée.getEnv().getEnnemis().size()-1; i > 0; i--){
            Ennemi ennemi = this.tourAvecPortée.getEnv().getEnnemis().get(i);
            if(this.tourAvecPortée.ennemiZone(ennemi)!=null){
                if(ennemi.getCoordX() <= this.getX()+16 && ennemi.getCoordX()+32 >= this.getX()+16 &&
                        ennemi.getCoordY() <= this.getY()+16 && ennemi.getCoordY()+32 >= this.getY()+16) {
                    this.getTour().getEnv().supprimerProjectile(this);
                    ennemi.pertPv(this.degats);
                    System.out.println(ennemi.getIdEnnemi() + " perd des PV");
                }
            }
        }
    }
}
