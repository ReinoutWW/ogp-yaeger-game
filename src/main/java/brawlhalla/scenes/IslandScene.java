package brawlhalla.scenes;

import brawlhalla.player.ControlledPlayerArrows;
import brawlhalla.player.ControlledPlayerWSDA;
import brawlhalla.scenes.components.Island;
import brawlhalla.scenes.components.MovingPlatform;
import brawlhalla.player.Player;
import brawlhalla.player.characters.CactiCharacter;
import brawlhalla.scenes.components.playerStatusIndicator.IndicatorCircle;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;
import javafx.scene.paint.Color;

public class IslandScene extends DynamicScene {
    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/LevelBackground.png");
    }

    @Override
    public void setupEntities() {
        var island = new Island(new Coordinate2D(getWidth() / 2 , getHeight() - 50) );
        var platform1 = new MovingPlatform(new Coordinate2D(getWidth() / 6 , 350) );
        var platform2 = new MovingPlatform(new Coordinate2D(getWidth() - (getWidth() / 3) , 350) );
        var playerStatusIndicator1 = new PlayerStatusIndicator(new Coordinate2D(getWidth() - 100, 10), Color.RED);
        var playerStatusIndicator2 = new PlayerStatusIndicator(new Coordinate2D(50, 10), Color.BLUE);

        var player1 = new ControlledPlayerWSDA(
                new Coordinate2D(getWidth() / 2, getHeight() /2),
                "RG1",
                new CactiCharacter(),
                playerStatusIndicator1,
                this,
                island,
                Color.RED
        );

        var player2 = new ControlledPlayerArrows(
                new Coordinate2D(getWidth() / 2 + 30, getHeight() /2),
                "RG2",
                new CactiCharacter(),
                playerStatusIndicator2,
                this,
                island,
                Color.BLUE
        );


        island.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        addEntity(island);
        addEntity(platform1);
        addEntity(platform2);
        addEntity(player1);
        addEntity(player2);
        addEntity(playerStatusIndicator1);
        addEntity(playerStatusIndicator2);
    }

}
