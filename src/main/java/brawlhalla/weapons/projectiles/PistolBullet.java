package brawlhalla.weapons.projectiles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A pistolbullet that can be used by for example projectile weapons
 */
public class PistolBullet extends Projectile implements Collider {
    private final double BULLET_SPEED = 7;

    public PistolBullet(Coordinate2D initialLocation, ProjectileWeapon projectileWeapon, double bulletDirection) {
        super("sprites/weapons/PistolBullet.png", initialLocation, projectileWeapon, new Size(10, 10));
        setMotion(BULLET_SPEED, bulletDirection);
    }

    /**
     * Return the weapon that shot the projectile
     * @return the weapon
     */
    @NonNull
    @Override
    public ProjectileWeapon getProjectileWeapon() {
        return projectileWeapon;
    }
}

