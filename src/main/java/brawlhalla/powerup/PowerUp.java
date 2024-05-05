package brawlhalla.powerup;

import brawlhalla.player.IPowerUpPlayer;
import brawlhalla.player.Player;
import brawlhalla.timer.PowerUpTimer;
import brawlhalla.yaegerExtension.ClassCollided;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

import java.util.List;

public abstract class PowerUp extends DynamicSpriteEntity implements TimerContainer, ClassCollided {
    private final PowerUpTimer powerUpTimer;
    protected IPowerUpPlayer player;
    private int cyclesIdle = 0;
    private final int MAX_CYCLES_IDLE = 5;

    protected PowerUp(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation, new Size(30, 40), 1, 7);
        setAutoCycle(100);
        powerUpTimer = new PowerUpTimer(this);
    }

    /**
     * when a player collides with a PowerUp it gets consumed and activated.
     * starts the powerUp duration timer
     * @param player
     */
    public void consume(IPowerUpPlayer player) {
        this.player = player;
        startPowerUpTimer();
        System.out.println("start powerup");
    }

    /**
     * removes the powerUp
     */
    public void end() {
        remove();
        System.out.println("reset powerup");
    }

    @Override
    public void setupTimers() {
        addTimer(powerUpTimer);
    }

    public void startPowerUpTimer() {
        powerUpTimer.reset();
    }

    /**
     * handles the collision of the powerUp
     * if it collides with a player it is consumed for the colliding player and it becomes invisible
     * @param list
     */
    @Override
    public void onCollision(List<Collider> list) {
        if(player != null) {
            return;
        }

        if(hitsClass(list, Player.class)) {
            Player player = getFirstOfCollidedClasses(list, Player.class);

            if(player != null) {
                consume(player);
                setVisible(false);
            }
        }
    }

    /**
     * End will be called 3 seconds after consume is called.
     * The Power Up will last for an x amount of cycles (times triggered, but not picked up)
     * When the max is exceeded, the PowerUp will despawn.
     */
    public void reset() {
        if(player != null) {
            end();
        } else if(cyclesIdle < MAX_CYCLES_IDLE) {
            cyclesIdle += 1;
        } else {
            remove();
        }
    }
}
