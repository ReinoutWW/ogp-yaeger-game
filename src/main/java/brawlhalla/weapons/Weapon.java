package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public abstract class Weapon extends SpriteEntity implements IWeapon {
    protected int attackSpeedCooldown;
    protected boolean isHeldByCharacter;

    protected Weapon(Coordinate2D initialLocation) {
        super("", initialLocation);
    }

    public void setIsHeldByCharacter(boolean isHeldByCharacter) {
        this.isHeldByCharacter = isHeldByCharacter;
    }
}
