package brawlhalla.player.characters;

import brawlhalla.weapons.Dagger;
import brawlhalla.weapons.IWeapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;

public class CactiCharacter extends Character {
    public CactiCharacter(Coordinate2D initialLocation) {
        super("/sprites/characters/CactiRun.png", initialLocation, new Size(110, 10), 1, 11);
        setAutoCycle(10);
        setMotion(1, 270d);
    }

    @Override
    protected IWeapon getDefaultWeapon(Coordinate2D initialLocation) {
        // Create weapon
        return new Dagger(initialLocation);
    }
}
