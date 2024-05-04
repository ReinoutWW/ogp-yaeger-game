package brawlhalla.timer;

import brawlhalla.player.Player;
import com.github.hanyaeger.api.Timer;

/**
 * Used to set a block on the movement, and unblock after the interval
 */
public class MovementTimer extends Timer {
    private Player player;

    public MovementTimer(long intervalInMs, Player player) {
        super(intervalInMs);
        this.player = player;
    }

    /**
     * Releases the block on playerMovement after being hit with an attack based on the set timer interval.
     * @param l
     */
    @Override
    public void onAnimationUpdate(long l) {
        player.setControlsBlocked(false);
    }
}
