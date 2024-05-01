package brawlhalla.weapons;

import brawlhalla.timer.WeaponCooldownTimer;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Weapon extends DynamicSpriteEntity implements IWeapon, Collider, TimerContainer {
    protected long attackSpeedCooldown;
    protected boolean isHeldByCharacter;
    protected boolean readyForAttack = true;

    protected Weapon(String resource, Coordinate2D initialLocation, Size size, long attackSpeedCooldown) {
        super(resource, initialLocation, size);
        this.attackSpeedCooldown = attackSpeedCooldown;
    }

    public void setIsHeldByCharacter(boolean isHeldByCharacter) {
        this.isHeldByCharacter = isHeldByCharacter;
    }

    public void setReadyForAttack(boolean ready) {
        this.readyForAttack = ready;
    }

    @Override
    public void setupTimers() {
        WeaponCooldownTimer WeaponCooldownTimer = new WeaponCooldownTimer(attackSpeedCooldown, this);
        addTimer(WeaponCooldownTimer);
    }
}
