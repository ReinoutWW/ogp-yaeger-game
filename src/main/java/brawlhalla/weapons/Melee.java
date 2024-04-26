package brawlhalla.weapons;

import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.Coordinate2D;

public abstract class Melee extends Weapon {
    protected int verticalDamage = 10, horizontalDamage = 10;

    public Melee(Coordinate2D initialLocation) {
        super(initialLocation);
    }

    public abstract void attack(Direction direction);

    public abstract brawlhalla.weapons.IWeapon pickup();
}
