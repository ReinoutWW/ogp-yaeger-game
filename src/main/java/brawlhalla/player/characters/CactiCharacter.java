package brawlhalla.player.characters;

import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.weapons.melee.Dagger;
import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CactiCharacter extends Character {
    public CactiCharacter() {
        super("sprites/characters/Cacti.png", new Coordinate2D(0, 0), new Size(50, 100), 1, 1);
        //setAutoCycle(1);
    }

    /**
     * creates and returns the default weapon for this character
     * @param initialLocation
     * @param scene
     * @return
     */
    @NonNull
    @Override
    public Weapon createDefaultWeapon(Coordinate2D initialLocation, IEntitySpawnableScene scene) {
        // Create weapon
        return new Dagger(initialLocation);
    }

    // Extra logic for only the CactiCharacter
}
