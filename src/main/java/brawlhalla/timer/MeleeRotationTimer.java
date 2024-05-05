package brawlhalla.timer;

import brawlhalla.weapons.melee.Melee;
import com.github.hanyaeger.api.Timer;

/**
 * This will give the user the visual feedback of an attacking melee weapon (Rotating)
 */
public class MeleeRotationTimer extends Timer {
    private final Melee weapon;

    public MeleeRotationTimer(long intervalInMs, Melee weapon) {
        super(intervalInMs);
        this.weapon = weapon;
    }

    /**
     * handles the meleeWeapon rotation based on a set interval for visual purposes
     * @param l
     */
    @Override
    public void onAnimationUpdate(long l) {
        if(weapon.isDoingDamage()) {
            double rotation = weapon.getRotation();
            weapon.setRotate(rotation + 20);
            System.out.println("Rotating: " + rotation);
        }
    }
}
