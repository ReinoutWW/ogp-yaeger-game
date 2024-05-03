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
    private PowerUpTimer powerUpTimer;
    protected IPowerUpPlayer player;

    protected PowerUp(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation, new Size(30, 40), 1, 7);
        setAutoCycle(100);
        powerUpTimer = new PowerUpTimer(this);
    }

    public void consume(IPowerUpPlayer player) {
        this.player = player;
        startPowerUpTimer();
        System.out.println("start powerup");
    }

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
     */
    public void reset() {
        if(player != null) {
            end();
        }
    }
}
