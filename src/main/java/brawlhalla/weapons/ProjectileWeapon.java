package brawlhalla.weapons;

public abstract class ProjectileWeapon extends Weapon {
    public ProjectileWeapon(boolean isHeldByCharacter, int attackSpeedCooldown) {
        super(isHeldByCharacter, attackSpeedCooldown);
    }
}
