package brawlhalla.powerup;

import brawlhalla.player.IPowerUpPlayer;
import brawlhalla.player.IPowerUpPlayerDamageBoost;
import com.github.hanyaeger.api.Coordinate2D;

public class DamageBoostPowerUp extends PowerUp {

    public DamageBoostPowerUp(Coordinate2D initialLocation) {
        super("sprites/powerups/gem_red.png", initialLocation);
    }

    @Override
    public void consume(IPowerUpPlayer player) {
        super.consume(player);
        player.increaseKnockbackBoost(20);
    }

    @Override
    public void end() {
        // Reset player to old values and destroy
        player.resetKnockbackBoost();
        super.end();
    }
}
