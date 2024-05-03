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
public abstract class Weapon extends DynamicSpriteEntity implements IWeapon, Collider, TimerContainer {
    protected long attackSpeedCooldown;
    protected boolean isHeldByCharacter;
    protected boolean readyForAttack = true;
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

    public void setIsHeldByCharacter(boolean isHeldByCharacter) {
        this.isHeldByCharacter = isHeldByCharacter;
    }

    public void setReadyForAttack(boolean ready) {
        this.readyForAttack = ready;
    }

    public boolean isReadyForPickup() {
        return !isPickupBlocked && !isHeldByCharacter;
    }

    public void setIsPickupBlocked(boolean blocked) {
        this.isPickupBlocked = blocked;
    }

    public abstract Weapon cloneWeapon();

    public void removeWeapon() {
        remove();
    }

    @Override
    public void setupTimers() {
        addTimer(weaponCooldownTimer);
        addTimer(pickupTimer);
    }

    @Override
    public void setAttackDirection(double direction) {
        int index = DirectionHelper.isRight(direction) ? 0 : 1;
        setCurrentFrameIndex(index);
    }

    public void startPickupBlockDelay() {
        isPickupBlocked = true;
        pickupTimer.reset();
    }

    public void increaseKnockback(int percentage) {
        knockbackMultiplier += Math.max(percentage, 0);
    }

    public void resetKnockback() {
        knockbackMultiplier = 100;
    }

    public void increaseDamage(int percentage) {
        damageMultiplier += Math.max(percentage, 0);
    }

    public void resetDamage() {
        damageMultiplier = 100;
    }
}
