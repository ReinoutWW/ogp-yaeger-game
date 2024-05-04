package brawlhalla.powerup;

import brawlhalla.player.IPowerUpPlayer;
import brawlhalla.player.IPowerUpPlayerDamageBoost;
import com.github.hanyaeger.api.Coordinate2D;

public class DamageBoostPowerUp extends PowerUp {

    public DamageBoostPowerUp(Coordinate2D initialLocation) {
        super("sprites/powerups/gem_red.png", initialLocation);
    }

    /**
     * when a player collides with a PowerUp it gets consumed and activated.
     * temporarily increases the damage modifier on the player.
     * @param player
     */
    @Override
    public void consume(IPowerUpPlayer player) {
        super.consume(player);
        player.increaseWeaponDamage(50);
    }

    /**
     * after the powerUp time is over the boost gets reverted
     * and the powerUp gets removed.
     */
    @Override
    public void end() {
        // Reset player to old values and destroy
        player.resetKnockbackBoost();
        super.end();
    }
}
