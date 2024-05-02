package brawlhalla;

import brawlhalla.scenes.EndScene;
import brawlhalla.scenes.IslandScene;
import brawlhalla.scenes.StartScene;
import com.github.hanyaeger.api.YaegerGame;

public class Brawhalla extends YaegerGame {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {
    }

    @Override
    public void setupScenes() {
        addScene(0, new StartScene(this));
        addScene(1, new IslandScene(this));
        addScene(2, new EndScene(this));
    }
}
