package brawlhalla.weapons.projectiles;

import brawlhalla.weapons.ProjectileWeapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Projectile extends DynamicSpriteEntity {
    private ProjectileWeapon projectileWeapon;


    protected Projectile(String resource, Coordinate2D initialLocation, ProjectileWeapon projectileWeapon) {
        super(resource, initialLocation);
        this.projectileWeapon = projectileWeapon;
    }
}
