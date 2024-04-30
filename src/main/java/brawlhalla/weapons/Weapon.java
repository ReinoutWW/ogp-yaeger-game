package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Weapon extends DynamicSpriteEntity implements IWeapon, Collider {
    protected int attackSpeedCooldown;
    protected boolean isHeldByCharacter;

    protected Weapon(String resource, Coordinate2D initialLocation, Size size) {
        super(resource, initialLocation, size);
    }

    public void setIsHeldByCharacter(boolean isHeldByCharacter) {
        this.isHeldByCharacter = isHeldByCharacter;
    }
}
