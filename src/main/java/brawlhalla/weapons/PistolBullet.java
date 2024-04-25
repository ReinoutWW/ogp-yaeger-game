package brawlhalla.weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class PistolBullet extends SpriteEntity implements IProjectile {
    int bulletDamage = 10;

    public PistolBullet(Coordinate2D initialLocation) {
        super("", initialLocation);
    }

    @Override
    public void setDamage(int damage) {
        bulletDamage = damage;
    }
}
