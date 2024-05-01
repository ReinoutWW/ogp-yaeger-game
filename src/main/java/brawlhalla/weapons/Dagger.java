package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Direction;

public class Dagger extends Melee {
    public Dagger(Coordinate2D initialLocation, Size size) {
        super("sprites/weapons/Dagger.png", initialLocation, size, 50);
        attackSpeedCooldown = 5;
        verticalDamage = 10;
        horizontalDamage = 15;
    }

    @Override
    public void attack(double direction, Coordinate2D initialLocation) {
        // Do swing with weapon
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getKnockback() {
        return 0;
    }

    @Override
    public IWeapon pickup() {
        return this;
    }
}
