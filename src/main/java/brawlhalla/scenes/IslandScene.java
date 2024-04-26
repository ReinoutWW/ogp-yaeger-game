package brawlhalla.scenes;

import brawlhalla.scenes.components.Island;
import brawlhalla.scenes.components.MovingPlatform;
import brawlhalla.player.Player;
import brawlhalla.player.characters.CactiCharacter;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;

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

        var player1 = new Player(
                new Coordinate2D(getWidth() / 2, getHeight() /2),
                "RG",
                new CactiCharacter());

        island.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        addEntity(island);
        addEntity(platform1);
        addEntity(platform2);
        addEntity(player1);
    }

}
