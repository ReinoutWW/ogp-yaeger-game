package brawlhalla.scenes.components;

import brawlhalla.timer.PlatformTimer;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public class MovingPlatform extends DynamicSpriteEntity implements Collider, TimerContainer {
    private float platformMovingSpeed = 0.5f;

    public MovingPlatform(Coordinate2D initialLocation) {
        super("sprites/Floating Platform.png", initialLocation);
        setMotion(platformMovingSpeed, Direction.UP);
    }

    @Override
    public void setupTimers() {
        var platformTimer = new PlatformTimer(1750, this);
        addTimer(platformTimer);
    }

    /**
     * switches the movement direction of the platform controlled by PlatformTimer.
     */
    public void switchDirection() {
        if (getDirection() == 0) {
            setDirection(Direction.UP);
        }
        else {
            setDirection(Direction.DOWN);
        }
    }

    // For power ups, the game can speed up
    public void changeMovingSpeed(float speed) {
        setSpeed(speed);
    }

    public void resetMovingSpeed() {
        setSpeed(platformMovingSpeed);
    }
}
