package brawlhalla.weapons;

public interface IWeapon {
    int attackSpeedCooldown = 0;
    boolean isHeldByCharacter = false;

    public void attack(double direction);
}
