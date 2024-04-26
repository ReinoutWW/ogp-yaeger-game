package brawlhalla.player.characters;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Character extends DynamicSpriteEntity {
    public Character(String resource, Coordinate2D initialLocation, Size size, int rows, int columns) {
        super(resource, initialLocation, size, rows, columns);
    }

    public abstract YaegerEntity createDefaultWeapon(Coordinate2D initialLocation);
}
