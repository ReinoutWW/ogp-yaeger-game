package brawlhalla;

import brawlhalla.scenes.IslandScene;
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
        addScene(0, new IslandScene());
    }
}
