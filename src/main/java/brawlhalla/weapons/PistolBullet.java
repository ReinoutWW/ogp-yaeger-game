package brawlhalla.weapons;

public class PistolBullet implements IProjectile {
    int bulletDamage = 10;

    public PistolBullet() {
    }

    @Override
    public void setDamage(int damage) {
        bulletDamage = damage;
    }
}
