package brawlhalla.player.characters;

import brawlhalla.weapons.Dagger;
import brawlhalla.weapons.Weapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CactiCharacter extends Character {
    public CactiCharacter() {
        super("sprites/characters/CactiRun.png", new Coordinate2D(0, 0), new Size(50, 100), 1, 11);
        //setAutoCycle(1);
    }

    @NonNull
    @Override
    public Weapon createDefaultWeapon(Coordinate2D initialLocation) {
        // Create weapon
        return new Dagger(initialLocation, new Size(30, 60));
    }

    // Extra logic for only the CactiCharacter
}
