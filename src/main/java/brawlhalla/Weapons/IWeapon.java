package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.YaegerEntity;

public interface IWeapon {
    int attackSpeedCooldown = 0;
    boolean isHeldByCharacter = false;

    void setReadyForAttack(boolean ready);

    void attack(double direction, Coordinate2D startPosition);

    /**
     * @return the damage increate in percentages.
     * For example: 10 = 10% increase
     */
    int getDamage();

    /**
     * @return the default knockback performed in speed.
     * For example: 1 = 1 speed in yaeger
     */
    int getKnockback();

    /**
     * Give the user visual feedback on where the weapon is pointing (sprite index for example)
     * @param direction the given direction
     */
    void setAttackDirection(double direction);
}
