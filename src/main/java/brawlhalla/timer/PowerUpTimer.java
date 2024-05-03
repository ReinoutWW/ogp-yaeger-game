package brawlhalla.timer;

import brawlhalla.powerup.PowerUp;
import com.github.hanyaeger.api.Timer;

public class PowerUpTimer extends Timer {
    private PowerUp powerUp;

    public PowerUpTimer(PowerUp powerUp) {
        super(3000); // Always 3s
        this.powerUp = powerUp;
    }

    @Override
    public void onAnimationUpdate(long l)
    {
        powerUp.reset();
    }
}
