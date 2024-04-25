package brawlhalla.weapons;

import brawlhalla.utility.Direction;
import com.github.hanyaeger.api.Coordinate2D;

public class Sword extends Melee {
    public Sword(Coordinate2D initialLocation) {
        super(initialLocation);
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
