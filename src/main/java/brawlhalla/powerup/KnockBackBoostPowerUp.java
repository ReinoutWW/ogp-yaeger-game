package brawlhalla.powerup;

import brawlhalla.player.IPowerUpPlayer;
import com.github.hanyaeger.api.Coordinate2D;

public class KnockBackBoostPowerUp extends PowerUp {

    public KnockBackBoostPowerUp(Coordinate2D initialLocation) {
        super("sprites/powerups/gem_green.png", initialLocation);
    }

    @Override
    public void consume(IPowerUpPlayer player) {
        super.consume(player);
        player.increaseWeaponDamage(50);
    }

    @Override
    public void end() {
        // Reset player to old values and destroy
        player.resetWeaponDamage();
        super.end();
    }
}
