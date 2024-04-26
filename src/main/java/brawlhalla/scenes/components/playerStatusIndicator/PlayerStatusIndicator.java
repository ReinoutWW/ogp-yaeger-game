package brawlhalla.scenes.components.playerStatusIndicator;

import brawlhalla.player.Player;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import javafx.scene.paint.Color;

public class PlayerStatusIndicator extends DynamicCompositeEntity {
    IndicatorLabel playerNameText, playerDamagetakenMulitplierText, playerLivesText;

    public PlayerStatusIndicator(Coordinate2D initialLocation) {
        super(initialLocation);
        playerNameText = new IndicatorLabel(new Coordinate2D(7.5, 7.5), "...", 20);
        playerDamagetakenMulitplierText = new IndicatorLabel(new Coordinate2D(0, 40), "...", 12);
        playerLivesText = new IndicatorLabel(new Coordinate2D(33, 40), "...", 12);
    }

    public void updateStatus(Player player) {
        this.playerNameText.setText(player.getName());
        this.playerLivesText.setText(String.valueOf(player.getLives()));
        this.playerDamagetakenMulitplierText.setText(String.valueOf(player.getDamageTakenMiltiplier()));
    }

    @Override
    protected void setupEntities() {
        var playerNameCircle = new IndicatorCircle(new Coordinate2D(0, 0), Color.RED, 25);
        var playerDamagetakenMulitplierCircle = new IndicatorCircle(new Coordinate2D(-10, 35), Color.RED, 15);
        var playerLivesCircle = new IndicatorCircle(new Coordinate2D(25, 35), Color.RED, 15);

        addEntity(playerNameCircle);
        addEntity(playerNameText);
        addEntity(playerDamagetakenMulitplierCircle);
        addEntity(playerDamagetakenMulitplierText);
        addEntity(playerLivesCircle);
        addEntity(playerLivesText);
    }
}
