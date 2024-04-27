package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class ProjectileWeapon extends DynamicSpriteEntity implements IWeapon{
    protected ProjectileWeapon(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation);
    }

    @Override
    public abstract void attack(double direction) ;
}
