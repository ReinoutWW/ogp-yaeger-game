package brawlhalla.player.characters;

import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.weapons.projectiles.Pistol;
import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import org.checkerframework.checker.nullness.qual.NonNull;

public class ManCharacter extends Character {
    public ManCharacter() {
        super("sprites/characters/Office.png", new Coordinate2D(0, 0), new Size(50, 100), 1, 1);
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
        return new Pistol(initialLocation, scene);
    }

    // Extra logic for only the CactiCharacter
}