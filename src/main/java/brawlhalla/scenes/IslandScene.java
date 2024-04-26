package brawlhalla.scenes;

import brawlhalla.levelObjects.Island;
import brawlhalla.levelObjects.MovingPlatform;
import brawlhalla.player.characters.CactiCharacter;
import brawlhalla.player.characters.Character;
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
        var island = new Island("sprites/hd-rock-png-transparent-background-7.png", new Coordinate2D(getWidth() / 2 , getHeight()) );
        var platform1 = new MovingPlatform("sprites/Floating Platform.png", new Coordinate2D(getWidth() / 6 , 350) );
        var platform2 = new MovingPlatform("sprites/Floating Platform.png", new Coordinate2D(getWidth() - (getWidth() / 3) , 350) );

        Character cactiCharacter = new CactiCharacter(new Coordinate2D(getWidth() / 2, getHeight() / 2));

        island.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        addEntity(island);
        addEntity(platform1);
        addEntity(platform2);
        addEntity(cactiCharacter);
    }

}
