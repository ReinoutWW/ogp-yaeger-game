package brawlhalla.scenes.components;

import brawlhalla.player.PlayerScoreStatistics;
import brawlhalla.scenes.components.playerStatusIndicator.ScorePanelRectangle;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import javafx.scene.paint.Color;


public class ScorePanel extends DynamicCompositeEntity {
    private final PlayerScoreStatistics playerScoreStatistics;
    private final PlayerScoreStatistics enemyPlayerScoreStatistics;
    private final Color backgroundColor;

    public ScorePanel(Coordinate2D initialLocation, PlayerScoreStatistics playerScoreStatistics, PlayerScoreStatistics enemyPlayerScoreStatistics, Color backgroundColor) {
        super(initialLocation);
        this.playerScoreStatistics = playerScoreStatistics;
        this.enemyPlayerScoreStatistics = enemyPlayerScoreStatistics;
        this.backgroundColor = backgroundColor;
    }

    @Override
    protected void setupEntities() {
        var scorePanelRectangle = new ScorePanelRectangle(new Coordinate2D(0, 0), backgroundColor);
        var damageDealt = new ScoreLabel(new Coordinate2D(5, 10), "Damage Dealt: " + enemyPlayerScoreStatistics.getDamageReceived(), 15);
        var damageReceived = new ScoreLabel(new Coordinate2D(5, 35), "Damage Received: " + playerScoreStatistics.getDamageReceived(), 15);
        var hitsDealt = new ScoreLabel(new Coordinate2D(5, 60), "Hits Dealt: " + enemyPlayerScoreStatistics.getHitsReceived(),15);
        var hitsReceived = new ScoreLabel(new Coordinate2D(5, 85), "Hits Received: " + playerScoreStatistics.getHitsReceived(), 15);

        addEntity(scorePanelRectangle);
        addEntity(damageDealt);
        addEntity(damageReceived);
        addEntity(hitsDealt);
        addEntity(hitsReceived);
    }
}
