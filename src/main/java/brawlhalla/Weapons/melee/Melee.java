package brawlhalla.weapons.melee;

import brawlhalla.timer.MeleeRotationTimer;
import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.Coordinate2D;

public abstract class Melee extends Weapon implements Collider {
    public Melee(String resource, Coordinate2D initialLocation, Size size, long attackSpeedCooldown) {
        super(resource, initialLocation, size, attackSpeedCooldown);
    }

    public final boolean isDoingDamage() {
        return isAttacking;
    }

    @Override
    public void attack(double direction, Coordinate2D initialLocation) {
        if(!isAttacking) {
            // Spin animation
            setIsAttacking(true);
        }
    }

    @Override
    public void setupTimers() {
        super.setupTimers();
        addTimer(new MeleeRotationTimer(5, this));
    }
}
