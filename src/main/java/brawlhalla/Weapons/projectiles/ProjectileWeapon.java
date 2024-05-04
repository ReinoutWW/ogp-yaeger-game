package brawlhalla.weapons.projectiles;

import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

/**
 * Base class for projectile weapons
 */
public abstract class ProjectileWeapon extends Weapon {
    protected IEntitySpawnableScene sceneToSpawnProjectiles;

    protected ProjectileWeapon(String resource, Coordinate2D initialLocation, IEntitySpawnableScene scene, long attackSpeedCooldown) {
        super(resource, initialLocation, new Size(60, 30), attackSpeedCooldown);
        this.sceneToSpawnProjectiles = scene;
    }

    /**
     * The attack logic
     * @param direction the given direction for spawned entities
     * @param startPosition the position that an entity will be spawned
     */
    @Override
    public abstract void attack(double direction, Coordinate2D startPosition);
}
