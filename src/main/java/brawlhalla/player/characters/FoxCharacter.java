package brawlhalla.player.characters;

import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.weapons.projectiles.Shotgun;
import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import org.checkerframework.checker.nullness.qual.NonNull;

public class FoxCharacter extends Character {
    public FoxCharacter() {
        super("sprites/characters/Fox.png", new Coordinate2D(0, 0), new Size(50, 100), 1, 1);
        //setAutoCycle(1);
    }

    @NonNull
    @Override
    public Weapon createDefaultWeapon(Coordinate2D initialLocation, IEntitySpawnableScene scene) {
        return new Shotgun(initialLocation, scene);
    }

    // Extra logic for only the FoxCharacter
}
