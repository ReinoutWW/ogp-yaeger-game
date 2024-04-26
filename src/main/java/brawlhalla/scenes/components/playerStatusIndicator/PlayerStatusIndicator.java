package brawlhalla.scenes.components.playerStatusIndicator;

import brawlhalla.player.Player;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;

public class PlayerStatusIndicator extends CompositeEntity {
    private final Player player;

    public PlayerStatusIndicator(Player player, Coordinate2D initialLocation) {
        super(initialLocation);
        this.player = player;
    }

    @Override
    protected void setupEntities() {

    }
}
