package brawlhalla.weapons.projectiles;

import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.YaegerEntity;

/**
 * A shotgun weapon with the defined attack logic and knockback and damage
 * Extends ProjectileWeapon
 */
public class Shotgun extends ProjectileWeapon {

    public Shotgun(Coordinate2D initialLocation, IEntitySpawnableScene scene){
        super("sprites/weapons/weapon_shotgun.png", initialLocation, scene, 100);

    }

    /**
     * The attack logic
     * @param direction the given direction for spawned entities
     * @param startPosition the position that an entity will be spawned
     */
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
        float multiplier = (float)knockbackMultiplier / 100;
        return 10 * multiplier;
    }

    /**
     * @return the default knockback performed in speed.
     * For example: 1 = 1 speed in yaeger
     */
    @Override
    public float getKnockback() {
        float multiplier = (float)knockbackMultiplier / 100;
        return 1 * multiplier;
    }

    /**
     * Method is used to clone the instance of a weapon instead of passing a reference
     * @return this weapon
     */
    @Override
    public Weapon cloneWeapon() {
        return new Shotgun(this.getAnchorLocation(), this.sceneToSpawnProjectiles);
    }
}
