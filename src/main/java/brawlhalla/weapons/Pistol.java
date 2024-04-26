package brawlhalla.Weapons;

import brawlhalla.Weapons.Projectiles.PistolBullet;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Direction;

public class Pistol extends ProjectileWeapon{

    public Pistol(){

    }


    @Override
    public void attack(Direction direction) {

        new PistolBullet("insert bullet sprite", new Coordinate2D(), this); // insert player position as start position for Bullet

    }
}
