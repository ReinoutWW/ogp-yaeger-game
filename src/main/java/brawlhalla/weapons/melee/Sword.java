package brawlhalla.weapons.melee;

import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

/**
 * A sword weapon with the defined attack logic and knockback and damage
 * Extends Melee
 */
public class Sword extends Melee {
    public Sword(Coordinate2D initialLocation) {
        super("sprites/weapons/melee_sword.png", initialLocation, new Size(35, 60), 250);
    }

    /**
     * @return the damage increate in percentages.
     * For example: 10 = 10% increase
     */
    @Override
    public float getDamage() {
        float multiplier = (float)knockbackMultiplier / 100;
        return 10 * multiplier;
    }

    /**
     * @return the default knockback performed in speed.
     * For example: 1 = 1 speed in yaeger
     */
    @Override
    public float getKnockback() {
        float multiplier = (float)knockbackMultiplier / 100;
        return 1 * multiplier;
    }

    /**
     * Method is used to clone the instance of a weapon instead of passing a reference
     * @return this weapon
     */
    @Override
    public Weapon cloneWeapon() {
        return new Sword(this.getAnchorLocation());
    }
}
