package brawlhalla.timer;

import brawlhalla.weapons.IWeapon;
import brawlhalla.weapons.Melee;
import com.github.hanyaeger.api.Timer;

/**
 * This will give the user the visual feedback of an attacking melee weapon (Rotating)
 */
public class MeleeRotationTimer extends Timer {
    private Melee weapon;

    public MeleeRotationTimer(long intervalInMs, Melee weapon) {
        super(intervalInMs);
        this.weapon = weapon;
    }

    @Override
    public void onAnimationUpdate(long l) {
        if(weapon.isDoingDamage()) {
            double rotation = weapon.getRotation();
            weapon.setRotate(rotation + 20);
            System.out.println("Rotating: " + rotation);
        }
    }
}
