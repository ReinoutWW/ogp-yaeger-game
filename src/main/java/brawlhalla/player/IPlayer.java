package brawlhalla.player;

import com.github.hanyaeger.api.entities.Newtonian;
import com.github.hanyaeger.api.userinput.KeyListener;

public interface IPlayer extends IPowerUpPlayer, Newtonian, KeyListener {
    void respawn();
    String getName();
    int getDamageTakenMultiplier();
    int getLives();
    void dropWeapon();
}
