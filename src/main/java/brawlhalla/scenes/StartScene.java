package brawlhalla.scenes;

import brawlhalla.Brawhalla;
import brawlhalla.scenes.components.buttons.StartButton;
import brawlhalla.scenes.components.characterSelection.CharacterRoster;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;

public class StartScene extends DynamicScene {
    private Brawhalla brawhalla;
    private CharacterRoster characterRoster;

    public CharacterRoster getCharacterRoster() {
        return characterRoster;
    }


    public StartScene(Brawhalla brawhalla) {
        this.brawhalla = brawhalla;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/CharSelectBackground.jpg");
    }

    @Override
    public void setupEntities() {
        var startButton = new StartButton(new Coordinate2D(getWidth()/2, getHeight()-50), brawhalla, this);
        characterRoster = new CharacterRoster(new Coordinate2D(getWidth()/2, getHeight()/2));

        characterRoster.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        startButton.setAnchorPoint(AnchorPoint.CENTER_CENTER);

        addEntity(characterRoster);
        addEntity(startButton);
    }
}
