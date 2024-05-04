package brawlhalla.weapons.melee;

import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class Dagger extends Melee {
    public Dagger(Coordinate2D initialLocation) {
        super("sprites/weapons/melee_dagger.png", initialLocation, new Size(35, 60), 200);
    }

    @Override
    public float getDamage() {
        return 40;
    }

    @Override
    public float getKnockback() {
        return 2;
    }

    @Override
    public Weapon cloneWeapon() {
        return new Dagger(this.getAnchorLocation());
    }
}
