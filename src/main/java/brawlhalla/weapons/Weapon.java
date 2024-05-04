package brawlhalla.weapons;

import brawlhalla.timer.PickupTimer;
import brawlhalla.timer.WeaponCooldownTimer;
import brawlhalla.yaegerExtension.DirectionHelper;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

/**
 * The main Weapon class.
 * Sprites: Every sprite should have an image providing 1 row and 2 columns to provide visual feedback to the user on where the weapon is pointing.
 *  Main logic will be provided by the weapon class and is open for extension.
 */
public abstract class Weapon extends DynamicSpriteEntity implements IWeapon, IPowerUpWeaponKnockBack, IPowerUpWeaponDamage, Collider, TimerContainer {
    protected long attackSpeedCooldown;
    protected boolean isHeldByCharacter;
    protected boolean isAttacking = false;
    private final Timer pickupTimer;
    private final Timer weaponCooldownTimer;
    private boolean isPickupBlocked = true;
    protected int damageMultiplier = 100;
    protected int knockbackMultiplier = 100;

    protected Weapon(String resource, Coordinate2D initialLocation, Size size, long attackSpeedCooldown) {
        super(resource, initialLocation, size, 1, 2);
        this.attackSpeedCooldown = attackSpeedCooldown;
        pickupTimer = new PickupTimer(2000, this);
        weaponCooldownTimer = new WeaponCooldownTimer(attackSpeedCooldown, this);
    }

    public final void setIsHeldByCharacter(boolean isHeldByCharacter) {
        this.isHeldByCharacter = isHeldByCharacter;
    }

    public final void setIsAttacking(boolean ready) {
        this.isAttacking = ready;
    }

    public final boolean isReadyForPickup() {
        return !isPickupBlocked && !isHeldByCharacter;
    }

    public final void setIsPickupBlocked(boolean blocked) {
        this.isPickupBlocked = blocked;
    }

    public abstract Weapon cloneWeapon();

    public final void removeWeapon() {
        remove();
    }

    @Override
    public void setupTimers() {
        addTimer(weaponCooldownTimer);
        addTimer(pickupTimer);
    }

    @Override
    public final void setAttackDirection(double direction) {
        int index = DirectionHelper.isRight(direction) ? 0 : 1;
        setCurrentFrameIndex(index);
    }

    public final void startPickupBlockDelay() {
        isPickupBlocked = true;
        pickupTimer.reset();
    }

    public final void increaseKnockback(int percentage) {
        knockbackMultiplier += Math.max(percentage, 0);
    }

    public final void resetKnockback() {
        knockbackMultiplier = 100;
    }

    public final void increaseDamage(int percentage) {
        damageMultiplier += Math.max(percentage, 0);
    }

    public final void resetDamage() {
        damageMultiplier = 100;
    }
}
