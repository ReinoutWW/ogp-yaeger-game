package brawlhalla.levelObjects;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class Island extends SpriteEntity implements Collider {
    public Island(Coordinate2D initialLocation) {
        super("sprites/hd-rock-png-transparent-background-7.png", initialLocation, new Size(250, 100));
    }
}
