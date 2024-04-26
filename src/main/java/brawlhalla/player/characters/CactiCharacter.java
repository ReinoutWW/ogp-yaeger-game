package brawlhalla.player.characters;

import brawlhalla.weapons.Dagger;
import brawlhalla.weapons.IWeapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class CactiCharacter extends Character {
    public CactiCharacter() {
        super("sprites/characters/CactiRun.png", new Coordinate2D(0, 0), new Size(50, 100), 1, 11);
        //setAutoCycle(1);
    }

    @Override
    public IWeapon getDefaultWeapon(Coordinate2D initialLocation) {
        // Create weapon
        return new Dagger(initialLocation);
    }
}
