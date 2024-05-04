package brawlhalla.weapons.projectiles;

/**
 * Define required projectile methods
 */
public interface IProjectile {
    ProjectileWeapon getProjectileWeapon();
    double getDirection();
    void remove();
}
