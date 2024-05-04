package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;

public interface IWeapon {
    void setIsAttacking(boolean ready);

    /**
     * The attack logic
     * @param direction the given direction for spawned entities
     * @param startPosition the position that an entity will be spawned
     */
    void attack(double direction, Coordinate2D startPosition);

    /**
     * @return the damage increate in percentages.
     * For example: 10 = 10% increase
     */
    float getDamage();

    /**
     * @return the default knockback performed in speed.
     * For example: 1 = 1 speed in yaeger
     */
    float getKnockback();

    /**
     * Give the user visual feedback on where the weapon is pointing (sprite index for example)
     * @param direction the given direction
     */
    void setAttackDirection(double direction);

    /**
     * Increase the knockback on the weapon
     * @param percentage the amount
     */
    void increaseKnockback(int percentage);

    /**
     * Restore any multiplier values back to default
     */
    void resetKnockback();

    /**
     * Increase the damage on the weapon
     * @param percentage the amount
     */
    void increaseDamage(int percentage);

    /**
     * Restore any multiplier values back to default
     */
    void resetDamage();
}
