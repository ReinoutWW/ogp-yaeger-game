package brawlhalla.levelObjects;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class Island extends SpriteEntity implements Collider {
    public Island(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation);
    }
}
