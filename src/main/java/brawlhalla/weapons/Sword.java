package brawlhalla.weapons;

import brawlhalla.utility.Direction;

public class Sword extends Melee {
    public Sword(boolean isHeldByCharacter, int attackSpeedCooldown) {
        super(isHeldByCharacter, attackSpeedCooldown, 10, 10);
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
