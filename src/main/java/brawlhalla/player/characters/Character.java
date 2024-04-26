package brawlhalla.player.characters;

import brawlhalla.weapons.IWeapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public abstract class Character extends DynamicSpriteEntity {
    IWeapon defaultWeapon;

    public Character(String resource, Coordinate2D initialLocation, Size size, int rows, int columns) {
        super(resource, initialLocation, size, rows, columns);
    }

    public abstract IWeapon createDefaultWeapon(Coordinate2D initialLocation);
}
