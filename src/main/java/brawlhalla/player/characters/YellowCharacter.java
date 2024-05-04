package brawlhalla.player.characters;

import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.weapons.melee.Sword;
import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import org.checkerframework.checker.nullness.qual.NonNull;

public class YellowCharacter extends Character {
    public YellowCharacter() {
        super("sprites/characters/Yellow.png", new Coordinate2D(0, 0), new Size(50, 100), 1, 1);
        setAutoCycle(1);
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
        return new Sword(initialLocation);
    }

    // Extra logic for only the CactiCharacter
}