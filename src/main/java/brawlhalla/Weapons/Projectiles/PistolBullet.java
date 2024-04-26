package brawlhalla.Weapons.Projectiles;

import brawlhalla.Weapons.ProjectileWeapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicCircleEntity;

import java.util.List;

public class PistolBullet extends Projectile implements Collider, Collided {


    public PistolBullet(String resource, Coordinate2D initialLocation, ProjectileWeapon projectileWeapon) {
        super(resource, initialLocation, projectileWeapon);

    }

    @Override
    public void onCollision(List<Collider> list) {
        this.setVisible(false); //
    }
}

