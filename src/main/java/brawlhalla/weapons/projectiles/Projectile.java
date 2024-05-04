package brawlhalla.weapons.projectiles;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Projectile extends DynamicSpriteEntity implements IProjectile {
    protected ProjectileWeapon projectileWeapon;

    protected Projectile(String resource, Coordinate2D initialLocation, ProjectileWeapon projectileWeapon, Size size) {
        super(resource, initialLocation, size);
        this.projectileWeapon = projectileWeapon;
    }

    @Override
    public double getDirection() {
        return super.getDirection();
    }
}
