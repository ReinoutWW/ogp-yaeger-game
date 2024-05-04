package brawlhalla.weapons;

/**
 * The required methods to support PowerUp knockback
 */
public interface IPowerUpWeaponKnockBack {
    void increaseKnockback(int percentage);
    void resetKnockback();
}
