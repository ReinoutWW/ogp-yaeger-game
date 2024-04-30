package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.YaegerEntity;

public interface IWeapon {
    int attackSpeedCooldown = 0;
    boolean isHeldByCharacter = false;

    void attack(double direction, Coordinate2D startPosition);
    int getDamage();
    int getKnockback();
}
