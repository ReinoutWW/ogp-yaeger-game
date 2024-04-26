package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Direction;

public class Sword extends Melee {
    public Sword(Coordinate2D initialLocation, Size size) {
        super("", initialLocation, size);
        attackSpeedCooldown = 7;
        verticalDamage = 5;
        horizontalDamage = 20;
    }

    @Override
    public void attack(Direction direction) {
        // Do attack animation here
    }

    @Override
    public IWeapon pickup() {
        return this;
    }
}
