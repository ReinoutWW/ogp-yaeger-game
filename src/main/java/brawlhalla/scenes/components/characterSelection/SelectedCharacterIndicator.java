package brawlhalla.scenes.components.characterSelection;

import brawlhalla.player.characters.Character;
import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.paint.Color;

public class SelectedCharacterIndicator extends CharacterIndicator {
    public SelectedCharacterIndicator(Coordinate2D initialLocation, Color rectColor, Character selectedCharacter, int selectionID) {
        super(initialLocation, rectColor, selectedCharacter, selectionID);
    }
}
