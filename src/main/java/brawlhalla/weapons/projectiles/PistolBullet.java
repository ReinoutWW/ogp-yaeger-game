package brawlhalla.weapons.projectiles;

import brawlhalla.weapons.ProjectileWeapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;

import java.util.List;

public class PistolBullet extends Projectile implements Collider, Collided {


    public PistolBullet(Coordinate2D initialLocation, ProjectileWeapon projectileWeapon) {
        super("sprites/weapons/PistolBullet.png", initialLocation, projectileWeapon);
    }

    @Override
    public void onCollision(List<Collider> list) {
        this.setVisible(false); //
    }
}

