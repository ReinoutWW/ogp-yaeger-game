package brawlhalla.weapons;

public abstract class Weapon implements IWeapon {
    protected int attackSpeedCooldown;
    protected boolean isHeldByCharacter;

    public void setIsHeldByCharacter(boolean isHeldByCharacter) {
        this.isHeldByCharacter = isHeldByCharacter;
    }
}
