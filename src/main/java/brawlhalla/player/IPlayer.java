package brawlhalla.player;

import com.github.hanyaeger.api.entities.Newtonian;
import com.github.hanyaeger.api.userinput.KeyListener;

public interface IPlayer extends IPowerUpPlayer, Newtonian, KeyListener {
    void respawn();
    void setIsGrounded(boolean isGrounded);
    String getName();
    int getDamageTakenMiltiplier();
    int getLives();
    void dropWeapon();
}
