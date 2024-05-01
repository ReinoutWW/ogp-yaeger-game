package brawlhalla.weapons;

import brawlhalla.scenes.IEntitySpawnableScene;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public abstract class ProjectileWeapon extends Weapon{
    protected IEntitySpawnableScene sceneToSpawnProjectiles;

    protected ProjectileWeapon(String resource, Coordinate2D initialLocation, IEntitySpawnableScene scene, long attackSpeedCooldown) {
        super(resource, initialLocation, new Size(60, 30), attackSpeedCooldown);
        this.sceneToSpawnProjectiles = scene;
    }

    @Override
    public abstract void attack(double direction, Coordinate2D startPosition);
}
