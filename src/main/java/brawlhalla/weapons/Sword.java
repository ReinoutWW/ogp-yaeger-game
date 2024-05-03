package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Direction;

public class Sword extends Melee {
    public Sword(Coordinate2D initialLocation, Size size) {
        super("sprites/weapons/melee_sword.png", initialLocation, size, 100);
        attackSpeedCooldown = 7;
        verticalDamage = 5;
        horizontalDamage = 20;
    }

    @Override
    public IWeapon pickup() {
        return this;
    }

    @Override
    public void attack(double direction, Coordinate2D startPosition) {
        // Logic for attacking here
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getKnockback() {
        return 0;
    }
}
