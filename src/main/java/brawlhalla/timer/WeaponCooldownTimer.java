package brawlhalla.timer;

import brawlhalla.weapons.IWeapon;
import com.github.hanyaeger.api.Timer;

public class WeaponCooldownTimer extends Timer {
    private final IWeapon weapon;

    public WeaponCooldownTimer(long intervalInMs, IWeapon weapon) {
        super(intervalInMs);
        this.weapon = weapon;
    }

    /**
     * interval handling for weapon attacks to prevent attack spamming.
     * @param l
     */
    @Override
    public void onAnimationUpdate(long l) {
        weapon.setIsAttacking(false);
    }
}
