package brawlhalla.weapons;

/**
 * The required methods to support PowerUp damage
 */
public interface IPowerUpWeaponDamage {
    void increaseDamage(int percentage);
    void resetDamage();
}
