package brawlhalla.weapons;

public abstract class Melee extends Weapon {
    protected int verticalDamage = 10, horizontalDamage = 10;

    public Melee(boolean isHeldByCharacter, int attackSpeedCooldown, int verticalDamage, int horizontalDamage) {
        super(isHeldByCharacter, attackSpeedCooldown);
        this.verticalDamage = verticalDamage;
        this.horizontalDamage = horizontalDamage;
    }
}
