package brawlhalla;

import brawlhalla.scenes.EndScene;
import brawlhalla.scenes.IslandScene;
import brawlhalla.scenes.StartScene;
import com.github.hanyaeger.api.YaegerGame;
import javafx.scene.Scene;

public class Brawhalla extends YaegerGame {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {
    }

    @Override
    public void setupScenes() {
        addScene(Scenes.StART.index, new StartScene(this));
        addScene(Scenes.ISLAND.index, new IslandScene(this));
        addScene(Scenes.END.index, new EndScene(this));
    }
}
