package brawlhalla.weapons;

public abstract class Weapon implements IWeapon {
    protected int attackSpeedCooldown;
    protected boolean isHeldByCharacter;

    public Weapon(boolean isHeldByCharacter, int attackSpeedCooldown) {
        this.isHeldByCharacter = isHeldByCharacter;
        this.attackSpeedCooldown = attackSpeedCooldown;
    }

    public void setIsHeldByCharacter(boolean isHeldByCharacter) {
        this.isHeldByCharacter = isHeldByCharacter;
    }
}
