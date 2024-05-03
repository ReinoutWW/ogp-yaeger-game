package brawlhalla.powerup;

import brawlhalla.player.IPowerUpPlayer;
import com.github.hanyaeger.api.Coordinate2D;

public class SpeedBoostPowerUp extends PowerUp {

    public SpeedBoostPowerUp(Coordinate2D initialLocation) {
        super("sprites/powerups/gem_yellow.png", initialLocation);
    }

    @Override
    public void consume(IPowerUpPlayer player) {
        super.consume(player);
        player.increaseSpeedBoost(70);
    }

    @Override
    public void end() {
        // Reset player to old values and destroy
        player.resetSpeedBoost();
        super.end();
    }
}
