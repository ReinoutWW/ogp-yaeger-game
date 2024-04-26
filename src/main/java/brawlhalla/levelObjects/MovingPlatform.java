package brawlhalla.levelObjects;

import brawlhalla.timer.PlatformTimer;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public class MovingPlatform extends DynamicSpriteEntity implements Collider, TimerContainer {
    public MovingPlatform(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation);
        setMotion(0.5, 180d);
    }

    @Override
    public void setupTimers() {
        var platformTimer = new PlatformTimer(1750, this);
        addTimer(platformTimer);
    }

    public void switchDirection() {
        if (getDirection() == 0) {
            setDirection(180);
        }
        else {
            setDirection(0);
        }
    }


}
