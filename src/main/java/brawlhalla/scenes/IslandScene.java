package brawlhalla.scenes;

import brawlhalla.LevelObjects.Island;
import brawlhalla.LevelObjects.MovingPlatform;
import brawlhalla.Timer.PlatformTimer;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.StaticScene;

import java.util.List;

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
        island.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        addEntity(island);
        addEntity(platform1);
        addEntity(platform2);
    }

}
