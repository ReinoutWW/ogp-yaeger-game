package brawlhalla.powerup;

import brawlhalla.player.IPowerUpPlayer;
import com.github.hanyaeger.api.Coordinate2D;

public class KnockBackBoostPowerUp extends PowerUp {

    public KnockBackBoostPowerUp(Coordinate2D initialLocation) {
        super("sprites/powerups/gem_green.png", initialLocation);
    }

    /**
     * when a player collides with a PowerUp it gets consumed and activated.
     * temporarily increases the knockback modifier on the player.
     * @param player
     */
    @Override
    public void consume(IPowerUpPlayer player) {
        super.consume(player);
        player.increaseKnockbackBoost(20);
    }

    /**
     * after the powerUp time is over the boost gets reverted
     * and the powerUp gets removed.
     */
    @Override
    public void end() {
        // Reset player to old values and destroy
        player.resetWeaponDamage();
        super.end();
    }
}
