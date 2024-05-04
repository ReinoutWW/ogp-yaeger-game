package brawlhalla.weapons.projectiles;

public interface IProjectile {
    ProjectileWeapon getProjectileWeapon();
    double getDirection();
    void remove();
}
