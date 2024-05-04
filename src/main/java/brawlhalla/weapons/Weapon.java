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

    /**
     * Indicates if the weapon is held by a player
     * For example: If a player drop the weapon, it will be set to false
     * @param isHeldByCharacter
     */
    public final void setIsHeldByCharacter(boolean isHeldByCharacter) {
        this.isHeldByCharacter = isHeldByCharacter;
    }

    /**
     * Indicates if the weapon is busy attacking
     * Usage: For example a check if damage should be done on collisions
     * @param ready if ready
     */
    public final void setIsAttacking(boolean ready) {
        this.isAttacking = ready;
    }

    /**
     * Indicates if the weapon can be picked up on for example a collision
     * @return is ready boolean
     */
    public final boolean isReadyForPickup() {
        return !isPickupBlocked && !isHeldByCharacter;
    }

    /**
     * Sets the pickup block value
     * @param blocked if blocked
     */
    public final void setIsPickupBlocked(boolean blocked) {
        this.isPickupBlocked = blocked;
    }

    /**
     * Method is used to clone the instance of a weapon instead of passing a reference
     * @return this weapon
     */
    public abstract Weapon cloneWeapon();

    /**
     * Destroy the instance of this weapon
     */
    public final void removeWeapon() {
        remove();
    }

    /**
     * Setup timers for weapon cooldown and pickup
     */
    @Override
    public void setupTimers() {
        addTimer(weaponCooldownTimer);
        addTimer(pickupTimer);
    }

    /**
     * Used to set an attack direction on the weapon
     * @param direction the given direction
     */
    @Override
    public final void setAttackDirection(double direction) {
        int index = DirectionHelper.isRight(direction) ? 0 : 1;
        setCurrentFrameIndex(index);
    }

    /**
     * Start the pickup block timer to prevent the weapon to be picked up instantly after dropping for example
     */
    public final void startPickupBlockDelay() {
        isPickupBlocked = true;
        pickupTimer.reset();
    }

    /**
     * Increase the knockback of the weapon
     * @param percentage increase
     */
    public final void increaseKnockback(int percentage) {
        knockbackMultiplier += Math.max(percentage, 0);
    }

    /**
     * Restore any multiplier values back to default
     */
    public final void resetKnockback() {
        knockbackMultiplier = 100;
    }

    /**
     * Increase the damage of the weapon
     * @param percentage increase
     */
    public final void increaseDamage(int percentage) {
        damageMultiplier += Math.max(percentage, 0);
    }

    /**
     * Restore any multiplier values back to default
     */
    public final void resetDamage() {
        damageMultiplier = 100;
    }
}
