package brawlhalla.scenes;

import brawlhalla.Brawhalla;
import brawlhalla.player.PlayerScoreStatistics;

import brawlhalla.scenes.components.ScorePanel;
import brawlhalla.scenes.components.WinnerText;
import brawlhalla.scenes.components.buttons.AgainButton;
import brawlhalla.scenes.components.buttons.CloseButton;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.StaticScene;
import javafx.scene.paint.Color;

public class EndScene extends StaticScene {
    private Brawhalla brawhalla;

    public EndScene(Brawhalla brawhalla) {
        this.brawhalla = brawhalla;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/EndScene.jpeg");
    }

    @Override
    public void setupEntities() {
        var playerStatsTest1 = new PlayerScoreStatistics();
        var playerStatsTest2 = new PlayerScoreStatistics();
        var againButton = new AgainButton(new Coordinate2D(getWidth()/2, getHeight()/2), brawhalla);
        var closeButton = new CloseButton(new Coordinate2D(getWidth()/2, getHeight()/2 + 90), brawhalla);
        var winnerText = new WinnerText(new Coordinate2D(getWidth()/2, 100));
        var scorePanel1 = new ScorePanel(new Coordinate2D(0, getHeight() - 200), playerStatsTest1, Color.BLUE);
        var scorePanel2 = new ScorePanel(new Coordinate2D(getWidth() - 200, getHeight() - 200), playerStatsTest2, Color.RED);

        winnerText.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        againButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        closeButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);

        addEntity(winnerText);
        addEntity(againButton);
        addEntity(closeButton);
        addEntity(scorePanel1);
        addEntity(scorePanel2);
    }
}
