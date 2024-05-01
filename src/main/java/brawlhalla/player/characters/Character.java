package brawlhalla.player.characters;

import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import org.checkerframework.checker.nullness.qual.NonNull;

public abstract class Character extends DynamicSpriteEntity {
    public Character(String resource, Coordinate2D initialLocation, Size size, int rows, int columns) {
        super(resource, initialLocation, size, rows, columns);
    }

    @NonNull
    public abstract Weapon createDefaultWeapon(Coordinate2D initialLocation, IEntitySpawnableScene scene);
}
