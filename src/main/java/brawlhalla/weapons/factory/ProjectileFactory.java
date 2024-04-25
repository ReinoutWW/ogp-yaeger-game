package brawlhalla.weapons.factory;

import brawlhalla.weapons.PistolBullet;

public class ProjectileFactory {
    public static PistolBullet createPistolBullet() {
        return new PistolBullet(10);
    }
}
