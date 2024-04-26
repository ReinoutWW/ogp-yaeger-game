package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Direction;

public class Dagger extends Melee {
    public Dagger(Coordinate2D initialLocation) {
        super(initialLocation);
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
