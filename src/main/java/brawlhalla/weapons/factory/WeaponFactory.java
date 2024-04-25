package brawlhalla.weapons.factory;

import brawlhalla.weapons.Dagger;
import brawlhalla.weapons.Pistol;
import brawlhalla.weapons.Sword;

public class WeaponFactory {
    public static Dagger createDagger() {
        return new Dagger(false, 10);
    }

    public static Sword createSword() {
        return new Sword(false, 10);
    }

    public static Pistol createPistol() {
        return new Pistol(false, 10);
    }
}
