package brawlhalla.weapons;

import brawlhalla.utility.Direction;

public class Dagger extends Melee {
    public Dagger() {
        attackSpeedCooldown = 5;
        verticalDamage = 10;
        horizontalDamage = 15;
    }

    @Override
    public void attack(Direction direction) {
        // Do swing with weapon
    }

    @Override
    public IWeapon pickup() {
        return this;
    }
}
