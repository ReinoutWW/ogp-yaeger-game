package brawlhalla.scenes;

import brawlhalla.Brawhalla;
import brawlhalla.player.Player;
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
    private final Brawhalla brawhalla;
    private String winner;

    public EndScene(Brawhalla brawhalla) {
        this.brawhalla = brawhalla;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/EndScene.jpeg");
        winner = checkWinner();
    }

    private String checkWinner() {
        if (brawhalla.getIslandScene().getPlayer1().getLives() > 0) {
            return brawhalla.getIslandScene().getPlayer1().getName();
        }
        else if (brawhalla.getIslandScene().getPlayer2().getLives() > 0) {
            return brawhalla.getIslandScene().getPlayer2().getName();
        }
        else {
            return "Nobody";
        }
    }


    @Override
    public void setupEntities() {
        var againButton = new AgainButton(new Coordinate2D(getWidth()/2, getHeight()/2), brawhalla);
        var closeButton = new CloseButton(new Coordinate2D(getWidth()/2, getHeight()/2 + 90), brawhalla);
        var winnerText = new WinnerText(new Coordinate2D(getWidth()/2, 100), winner);
        var scorePanel1 = new ScorePanel(new Coordinate2D(0, getHeight() - 200), brawhalla.getIslandScene().getPlayer1().getPlayerScoreStatistics(), Color.BLUE);
        var scorePanel2 = new ScorePanel(new Coordinate2D(getWidth() - 200, getHeight() - 200), brawhalla.getIslandScene().getPlayer2().getPlayerScoreStatistics(), Color.RED);

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
