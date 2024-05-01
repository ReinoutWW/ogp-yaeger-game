package brawlhalla.weapons.projectiles;

import brawlhalla.player.Player;
import brawlhalla.weapons.ProjectileWeapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.scenes.SceneBorder;
import brawlhalla.yaegerExtension.ClassCollided;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class PistolBullet extends Projectile implements Collider, ClassCollided, SceneBorderCrossingWatcher {
    private final double BULLET_SPEED = 7;

    public PistolBullet(Coordinate2D initialLocation, ProjectileWeapon projectileWeapon, double bulletDirection) {
        super("sprites/weapons/PistolBullet.png", initialLocation, projectileWeapon, new Size(10, 10));
        setMotion(BULLET_SPEED, bulletDirection);
    }

    @Override
    public void onCollision(List<Collider> list) {

    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }

    @NonNull
    @Override
    public ProjectileWeapon getProjectileWeapon() {
        return projectileWeapon;
    }
}

