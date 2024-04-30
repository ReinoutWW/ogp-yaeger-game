package brawlhalla.weapons;

import brawlhalla.scenes.IProjectileSpawnableScene;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public abstract class ProjectileWeapon extends Weapon implements IWeapon {
    protected IProjectileSpawnableScene sceneToSpawnProjectiles;

    protected ProjectileWeapon(String resource, Coordinate2D initialLocation, IProjectileSpawnableScene scene) {
        super(resource, initialLocation, new Size(30, 60));
        this.sceneToSpawnProjectiles = scene;
    }

    @Override
    public abstract void attack(double direction, Coordinate2D startPosition);
}
