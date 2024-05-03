package brawlhalla.weapons;

import brawlhalla.timer.PickupTimer;
import brawlhalla.timer.WeaponCooldownTimer;
import brawlhalla.yaegerExtension.DirectionHelper;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
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
    private Timer pickupTimer;
    private boolean isReadyForPickup = false;

    protected Weapon(String resource, Coordinate2D initialLocation, Size size, long attackSpeedCooldown) {
        super(resource, initialLocation, size, 1, 2);
        this.attackSpeedCooldown = attackSpeedCooldown;
        pickupTimer = new PickupTimer(2000, this);
    }

    public void setIsHeldByCharacter(boolean isHeldByCharacter) {
        this.isHeldByCharacter = isHeldByCharacter;
    }

    public void setReadyForAttack(boolean ready) {
        this.readyForAttack = ready;
    }

    public boolean isReadyForPickup() {
        return isReadyForPickup;
    }

    public void setIsReadyForPickup(boolean isReadyForPickup) {
        this.isReadyForPickup = isReadyForPickup;
    }

    public abstract Weapon cloneWeapon();

    public void removeWeapon() {
        remove();
    }

    @Override
    public void setupTimers() {
        WeaponCooldownTimer WeaponCooldownTimer = new WeaponCooldownTimer(attackSpeedCooldown, this);
        addTimer(WeaponCooldownTimer);
        addTimer(pickupTimer);
    }

    @Override
    public void setAttackDirection(double direction) {
        int index = DirectionHelper.isRight(direction) ? 0 : 1;
        setCurrentFrameIndex(index);
    }

    public void setPickupDelayBlock() {
        isReadyForPickup = false;
        pickupTimer.reset();
    }
}
