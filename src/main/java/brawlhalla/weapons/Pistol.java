package brawlhalla.Weapons;

import brawlhalla.weapons.projectiles.PistolBullet;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Direction;

public class Pistol extends ProjectileWeapon {

    public Pistol(Coordinate2D initialLocation){
        super("resource here", initialLocation);
    }


    @Override
    public void attack(double direction) {
        new PistolBullet(new Coordinate2D(), this); // insert player position as start position for Bullet
    }
}
