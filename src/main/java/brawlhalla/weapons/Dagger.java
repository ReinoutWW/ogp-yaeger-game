package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Direction;

public class Dagger extends Melee {
    public Dagger(Coordinate2D initialLocation, Size size) {
        super("sprites/weapons/Dagger.png", initialLocation, size);
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
