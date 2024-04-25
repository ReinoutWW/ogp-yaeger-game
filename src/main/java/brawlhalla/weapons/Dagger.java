package brawlhalla.weapons;

import brawlhalla.utility.Direction;

public class Dagger extends Melee {
    public Dagger(boolean isHeldByCharacter, int attackSpeedCooldown) {
        super(isHeldByCharacter, attackSpeedCooldown, 10, 10);
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
