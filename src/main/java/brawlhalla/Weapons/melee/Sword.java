package brawlhalla.weapons.melee;

import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class Sword extends Melee {
    public Sword(Coordinate2D initialLocation) {
        super("sprites/weapons/melee_sword.png", initialLocation, new Size(35, 60), 250);
    }

    @Override
    public float getDamage() {
        return 30;
    }

    @Override
    public float getKnockback() {
        return 3;
    }

    @Override
    public Weapon cloneWeapon() {
        return new Sword(this.getAnchorLocation());
    }
}
