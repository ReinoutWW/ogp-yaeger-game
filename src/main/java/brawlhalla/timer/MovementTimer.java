package brawlhalla.timer;

import brawlhalla.player.Player;
import com.github.hanyaeger.api.Timer;

public class MovementTimer extends Timer {
    private Player player;

    public MovementTimer(long intervalInMs, Player player) {
        super(intervalInMs);
        this.player = player;
    }

    @Override
    public void onAnimationUpdate(long l) {
        player.CurveMotion();
    }
}
