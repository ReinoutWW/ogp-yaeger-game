package brawlhalla.weapons;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.Coordinate2D;

public abstract class Melee extends Weapon {
    protected int verticalDamage = 10, horizontalDamage = 10;

    public Melee(String resource, Coordinate2D initialLocation, Size size, long attackSpeedCooldown) {
        super(resource, initialLocation, size, attackSpeedCooldown);
    }

    public abstract brawlhalla.weapons.IWeapon pickup();
}
