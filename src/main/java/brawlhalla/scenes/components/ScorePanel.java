package brawlhalla.scenes.components;

import brawlhalla.player.PlayerScoreStatistics;
import brawlhalla.scenes.components.playerStatusIndicator.ScorePanelRectangle;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import javafx.scene.paint.Color;


public class ScorePanel extends DynamicCompositeEntity {
   PlayerScoreStatistics playerScoreStatistics;
   Color backgroundColor;

    public ScorePanel(Coordinate2D initialLocation, PlayerScoreStatistics playerScoreStatistics, Color backgroundColor) {
        super(initialLocation);
        this.playerScoreStatistics = playerScoreStatistics;
        this.backgroundColor = backgroundColor;
    }

    @Override
    protected void setupEntities() {
        var scorePanelRectangle = new ScorePanelRectangle(new Coordinate2D(0, 0), backgroundColor);
        var damageDealt = new ScoreLabel(new Coordinate2D(5, 10), "Damage Dealt: " + playerScoreStatistics.getDamageDealt(), 20);
        var damageReceived = new ScoreLabel(new Coordinate2D(5, 35), "Damage Received: " + playerScoreStatistics.getDamageReceived(), 20);
        var hitsDealt = new ScoreLabel(new Coordinate2D(5, 60), "Hits Dealt: " + playerScoreStatistics.getHitsDealt(),20);
        var hitsReceived = new ScoreLabel(new Coordinate2D(5, 85), "Hits Received: " + playerScoreStatistics.getHitsReceived(), 20);

        addEntity(scorePanelRectangle);
        addEntity(damageDealt);
        addEntity(damageReceived);
        addEntity(hitsDealt);
        addEntity(hitsReceived);
    }
}
