package brawlhalla.weapons;

import brawlhalla.utility.Direction;

public class Pistol extends ProjectileWeapon {
    public Pistol() {
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
