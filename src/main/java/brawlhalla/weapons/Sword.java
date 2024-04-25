package brawlhalla.weapons;

import brawlhalla.utility.Direction;

public class Sword extends Melee {
    public Sword() {
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
