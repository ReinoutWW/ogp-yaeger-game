package brawlhalla.scenes;

import brawlhalla.player.PlayerScoreStatistics;

import brawlhalla.scenes.components.ScorePanel;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.StaticScene;
import javafx.scene.paint.Color;

public class EndScene extends StaticScene {
    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/EndScene.jpeg");
    }

    @Override
    public void setupEntities() {
        var playerStatsTest1 = new PlayerScoreStatistics();
        var playerStatsTest2 = new PlayerScoreStatistics();
        var scorePanel1 = new ScorePanel(new Coordinate2D(0, getHeight() - 200), playerStatsTest1, Color.BLUE);
        var scorePanel2 = new ScorePanel(new Coordinate2D(getWidth() - 200, getHeight() - 200), playerStatsTest2, Color.RED);

        addEntity(scorePanel1);
        addEntity(scorePanel2);
    }
}
