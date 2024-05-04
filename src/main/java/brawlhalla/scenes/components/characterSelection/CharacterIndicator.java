package brawlhalla.scenes.components.characterSelection;

import brawlhalla.player.characters.Character;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import javafx.scene.paint.Color;

public class CharacterIndicator extends CompositeEntity {
    private Color rectColor;
    private Character selectedCharacter;
    private Coordinate2D selectedCoordinate;
    private int selectionID;

    public CharacterIndicator(Coordinate2D initialLocation, Color rectColor, Character selectedCharacter, int selectionID) {
        super(initialLocation);
        this.rectColor = rectColor;
        this.selectedCoordinate = initialLocation;
        this.selectedCharacter = selectedCharacter;
        this.selectionID = selectionID;
    }


    @Override
    protected void setupEntities() {
        var selectedRectangle = new SelectedCharacterRectangle(new Coordinate2D(50,50), new Size(70), rectColor);

        selectedRectangle.setAnchorPoint(AnchorPoint.CENTER_CENTER);


    addEntity(selectedRectangle);
        if (selectedCharacter != null) {
            selectedCharacter.setAnchorPoint(AnchorPoint.TOP_LEFT);
            addEntity(selectedCharacter);
        }
    }

    public void setSelectedCharacter(Character selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }
}
