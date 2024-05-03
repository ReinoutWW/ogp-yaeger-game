package brawlhalla.weapons;

import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.weapons.projectiles.PistolBullet;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.YaegerEntity;

public class Shotgun extends ProjectileWeapon {

    public Shotgun(Coordinate2D initialLocation, IEntitySpawnableScene scene){
        super("sprites/weapons/weapon_shotgun.png", initialLocation, scene, 100);

    }

    @Override
    public void attack(double direction, Coordinate2D startPosition) {
        if(readyForAttack) {
            YaegerEntity pistolBullet = new PistolBullet(startPosition, this, direction); // insert player position as start position for Bullet
            sceneToSpawnProjectiles.addEntityToSpawn(pistolBullet);
            setReadyForAttack(false);
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

    @Override
    public Weapon cloneWeapon() {
        return new Shotgun(this.getAnchorLocation(), this.sceneToSpawnProjectiles);
    }
}