package brawlhalla.player;

import brawlhalla.weapons.IWeapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;

public class Player extends DynamicCompositeEntity implements IPlayer {
    private int lives;
    private int damageTakenMiltiplier;
    private IWeapon weapon;
    private Character character;
    private final String playerName;

    public Player(Coordinate2D initialLocation, String name, Character character) {
        super(initialLocation);
        this.playerName = name;
        this.character = character;
    }

    @Override
    public void spawn() {

    }

    @Override
    public void respawn() {

    }

    @Override
    protected void setupEntities() {

    }
}
