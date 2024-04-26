package brawlhalla.scenes.components.playerStatusIndicator;

import brawlhalla.player.Player;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import javafx.scene.paint.Color;

public class PlayerStatusIndicator extends DynamicCompositeEntity {
    private final Player player;
    public PlayerStatusIndicator(Player player, Coordinate2D initialLocation) {
        super(initialLocation);
        this.player = player;
    }

    @Override
    protected void setupEntities() {
        var playerNameCircle = new IndicatorCircle(new Coordinate2D(0, 0), Color.RED, 25);
        var playerNameText = new IndicatorLabel(new Coordinate2D(7.5, 7.5), player.getName(), 20);
        var playerDamagetakenMulitplierCircle = new IndicatorCircle(new Coordinate2D(-10, 35), Color.RED, 15);
        var playerDamagetakenMulitplierText = new IndicatorLabel(new Coordinate2D(0, 40), String.valueOf(player.getDamageTakenMiltiplier()), 12);

        var playerLivesCircle = new IndicatorCircle(new Coordinate2D(25, 35), Color.RED, 15);
        var playerLivesText = new IndicatorLabel(new Coordinate2D(33, 40), String.valueOf(player.getLives()), 12);

        addEntity(playerNameCircle);
        addEntity(playerNameText);
        addEntity(playerDamagetakenMulitplierCircle);
        addEntity(playerDamagetakenMulitplierText);
        addEntity(playerLivesCircle);
        addEntity(playerLivesText);
    }
}
