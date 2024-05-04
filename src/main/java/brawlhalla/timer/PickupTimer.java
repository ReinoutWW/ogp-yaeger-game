package brawlhalla.timer;

import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Timer;

/**
 * A timer to unblock the pickup value after the interval
 */
public class PickupTimer extends Timer {
    private Weapon weapon;

    public PickupTimer(long intervalInMs, Weapon weapon) {
        super(intervalInMs);
        this.weapon = weapon;
    }

    @Override
    public void onAnimationUpdate(long l) {
        weapon.setIsPickupBlocked(false);
    }
}