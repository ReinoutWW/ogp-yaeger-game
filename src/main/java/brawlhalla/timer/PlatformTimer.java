package brawlhalla.timer;

import brawlhalla.scenes.components.MovingPlatform;
import com.github.hanyaeger.api.Timer;

/**
 * Used to set a timer for the moving platform
 */
public class PlatformTimer extends Timer {
    private MovingPlatform movingPlatform;

    public PlatformTimer(long intervalInMs, MovingPlatform movingPlatform) {
        super(intervalInMs);
        this.movingPlatform = movingPlatform;
    }

    /**
     * calls the method to invert the direction of the platforms on the set interval.
     * @param l
     */
    @Override
    public void onAnimationUpdate(long l)
    {
        movingPlatform.switchDirection();
    }
}
