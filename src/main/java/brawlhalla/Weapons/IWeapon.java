package brawlhalla.Weapons;

import com.github.hanyaeger.api.entities.Direction;

public interface IWeapon {
    int attackSpeedCooldown = 0;
    boolean isHeldByCharacter = false;

    public void attack(Direction direction);
}
