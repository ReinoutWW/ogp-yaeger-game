package brawlhalla;

import brawlhalla.scenes.EndScene;
import brawlhalla.scenes.IslandScene;
import brawlhalla.scenes.StartScene;
import com.github.hanyaeger.api.YaegerGame;

public class Brawhalla extends YaegerGame {
    private StartScene startScene;
    private EndScene endScene;
    private IslandScene islandScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {
        startScene = new StartScene(this);
        endScene = new EndScene(this);
        islandScene = new IslandScene(this);
    }

    @Override
    public void setupScenes() {
        addScene(Scenes.StART.index, startScene);
        addScene(Scenes.ISLAND.index, islandScene);
        addScene(Scenes.END.index, endScene);
    }

    public EndScene getEndScene() {
        return endScene;
    }

    public IslandScene getIslandScene() {
        return islandScene;
    }

    public StartScene getStartScene() {
        return startScene;
    }
}

