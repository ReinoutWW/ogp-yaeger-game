package brawlhalla.player;

import com.github.hanyaeger.api.Coordinate2D;

public interface IPlayer  {
    void respawn();
    void setIsGrounded(boolean isGrounded);
    String getName();
    int getDamageTakenMiltiplier();
    int getLives();
}
