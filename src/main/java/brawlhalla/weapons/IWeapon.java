package brawlhalla.Weapons;

import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.YaegerEntity;

public interface IWeapon {
    int attackSpeedCooldown = 0;
    boolean isHeldByCharacter = false;

    public void attack(double direction);
}
