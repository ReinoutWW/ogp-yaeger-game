package brawlhalla.weapons;

import brawlhalla.utility.Direction;
import com.github.hanyaeger.api.Coordinate2D;

public class Pistol extends ProjectileWeapon {
    public Pistol(Coordinate2D initialLocation) {
        super(initialLocation);
    }

    @Override
    public void attack(Direction direction) {
        // Determine which projectile it should shoot here
        // Create
        // Shoot
    }

    @Override
    public IWeapon pickup() {
        return this;
    }
}
