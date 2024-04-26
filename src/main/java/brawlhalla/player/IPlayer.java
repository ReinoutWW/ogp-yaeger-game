package brawlhalla.player;

import com.github.hanyaeger.api.Coordinate2D;

public interface IPlayer  {
    void respawn(Coordinate2D location);
    void setIsGrounded(boolean isGrounded);
}
