package brawlhalla.weapons.projectiles;

import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.YaegerEntity;

public class Pistol extends ProjectileWeapon {

    public Pistol(Coordinate2D initialLocation, IEntitySpawnableScene scene){
        super("sprites/weapons/weapon_pistol.png", initialLocation, scene, 100);
    }

    @Override
    public void attack(double direction, Coordinate2D startPosition) {
        if(!isAttacking) {
            YaegerEntity pistolBullet = new PistolBullet(startPosition, this, direction); // insert player position as start position for Bullet
            sceneToSpawnProjectiles.addEntityToSpawn(pistolBullet);
            setIsAttacking(true);
        }
    }

    /**
     * @return the damage increate in percentages.
     * For example: 10 = 10% increase
     */
    @Override
    public float getDamage() {
        return 10;
    }

    /**
     * @return the default knockback performed in speed.
     * For example: 1 = 1 speed in yaeger
     */
    @Override
    public float getKnockback() {
        return 1;
    }

    @Override
    public Weapon cloneWeapon() {
        return new Pistol(this.getAnchorLocation(), this.sceneToSpawnProjectiles);
    }
}