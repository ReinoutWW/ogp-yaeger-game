package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class Dagger extends Melee {
    public Dagger(Coordinate2D initialLocation) {
        super("sprites/weapons/melee_dagger.png", initialLocation, new Size(35, 60), 200);
    }

    @Override
    public float getDamage() {
        return 0;
    }

    @Override
    public float getKnockback() {
        return 0;
    }

    @Override
    public Weapon cloneWeapon() {
        return new Dagger(this.getAnchorLocation());
    }
}
