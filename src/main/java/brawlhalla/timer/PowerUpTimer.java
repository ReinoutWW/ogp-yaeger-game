package brawlhalla.timer;

import brawlhalla.powerup.PowerUp;
import com.github.hanyaeger.api.Timer;

/**
 * Used to time the powerup. Will remove the powerup after  the interval
 */
public class PowerUpTimer extends Timer {
    private final PowerUp powerUp;

    public PowerUpTimer(PowerUp powerUp) {
        super(3000); // Always 3s
        this.powerUp = powerUp;
    }

    /**
     * interval handling for powerUp removal.
     * @param l
     */
    @Override
    public void onAnimationUpdate(long l)
    {
        powerUp.reset();
    }
}
