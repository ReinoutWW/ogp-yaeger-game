package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Direction;

public class Sword extends Melee {
    public Sword(Coordinate2D initialLocation) {
        super("sprites/weapons/melee_sword.png", initialLocation, new Size(35, 60), 250);
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
        return new Sword(this.getAnchorLocation());
    }
}
