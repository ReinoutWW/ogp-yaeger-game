package brawlhalla.timer;

import brawlhalla.levelObjects.MovingPlatform;
import com.github.hanyaeger.api.Timer;

public class PlatformTimer extends Timer {
    private MovingPlatform movingPlatform;

    public PlatformTimer(long intervalInMs, MovingPlatform movingPlatform) {
        super(intervalInMs);
        this.movingPlatform = movingPlatform;
    }

    @Override
    public void onAnimationUpdate(long l) {
        movingPlatform.switchDirection();
    }



}
