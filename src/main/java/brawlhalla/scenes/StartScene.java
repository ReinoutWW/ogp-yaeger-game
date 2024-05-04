package brawlhalla.scenes;

import brawlhalla.Brawhalla;
import brawlhalla.scenes.components.buttons.StartButton;
import brawlhalla.scenes.components.characterSelection.CharacterRoster;
import brawlhalla.scenes.components.characterSelection.SelectedCharacterIndicator;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;
import javafx.scene.paint.Color;

public class StartScene extends DynamicScene {
    private Brawhalla brawhalla;

    public StartScene(Brawhalla brawhalla) {
        this.brawhalla = brawhalla;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/CharSelectBackground.jpg");
    }

    @Override
    public void setupEntities() {
        var startButton = new StartButton(new Coordinate2D(getWidth()/2, getHeight()-50), brawhalla);
        var player1SelectIndicator = new SelectedCharacterIndicator(new Coordinate2D(0, 0), Color.RED, null);
        var player2SelectIndicator = new SelectedCharacterIndicator(new Coordinate2D(getWidth()-50, 0), Color.BLUE, null);
        var characterRosster = new CharacterRoster(new Coordinate2D(getWidth()/2, getHeight()/2));

       // characterRosster.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        player2SelectIndicator.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        player1SelectIndicator.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        startButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);

        addEntity(characterRosster);
        addEntity(startButton);
        addEntity(player1SelectIndicator);
        addEntity(player2SelectIndicator);
    }
}
