package brawlhalla.scenes.components.playerStatusIndicator;

import brawlhalla.player.IPlayer;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import javafx.scene.paint.Color;

public class PlayerStatusIndicator extends DynamicCompositeEntity {
    IndicatorLabel playerNameText, playerDamageTakenMulitplierText, playerLivesText;
    Color backgroundColor;

    public PlayerStatusIndicator(Coordinate2D initialLocation, Color backgroundColor) {
        super(initialLocation);
        this.backgroundColor = backgroundColor;
        playerNameText = new IndicatorLabel(new Coordinate2D(7.5, 7.5), "...",20);
        playerDamageTakenMulitplierText = new IndicatorLabel(new Coordinate2D(-5, 40), "...", 12);
        playerLivesText = new IndicatorLabel(new Coordinate2D(33, 40), "...", 12);
    }

    public void updateStatus(IPlayer player) {
        this.playerNameText.setText(player.getName());
        this.playerLivesText.setText(String.valueOf(player.getLives()));
        this.playerDamageTakenMulitplierText.setText(String.valueOf(player.getDamageTakenMiltiplier()));
    }

    @Override
    protected void setupEntities() {
        var playerNameCircle = new IndicatorCircle(new Coordinate2D(0, 0), backgroundColor, 25);
        var playerDamageTakenMulitplierCircle = new IndicatorCircle(new Coordinate2D(-10, 35), backgroundColor, 15);
        var playerLivesCircle = new IndicatorCircle(new Coordinate2D(25, 35), backgroundColor, 15);

        addEntity(playerNameCircle);
        addEntity(playerNameText);
        addEntity(playerDamageTakenMulitplierCircle);
        addEntity(playerDamageTakenMulitplierText);
        addEntity(playerLivesCircle);
        addEntity(playerLivesText);
    }
}
