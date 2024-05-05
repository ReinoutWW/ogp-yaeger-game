package brawlhalla.weapons.projectiles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;

/**
 * Base class for a projectile
 */
public abstract class Projectile extends DynamicSpriteEntity implements IProjectile, SceneBorderCrossingWatcher {
    protected final ProjectileWeapon projectileWeapon;

    protected Projectile(String resource, Coordinate2D initialLocation, ProjectileWeapon projectileWeapon, Size size) {
        super(resource, initialLocation, size);
        this.projectileWeapon = projectileWeapon;
    }

    /**
     * Return the projectile direction
     * @return double direction
     */
    @Override
    public final double getDirection() {
        return super.getDirection();
    }

    /**
     * The projectile will destroy once it has crossed the border
     * @param sceneBorder
     */
    @Override
    public final void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }
}
