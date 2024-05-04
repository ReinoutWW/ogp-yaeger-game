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
    public SelectedCharacterRectangle selectedCharacterRectangle;

    public CharacterIndicator(Coordinate2D initialLocation, Color rectColor, Character selectedCharacter, int selectionID) {
        super(initialLocation);
        this.rectColor = rectColor;
        this.selectedCoordinate = initialLocation;
        this.selectionID = selectionID;
        this.selectedCharacter = selectedCharacter;
    }


    @Override
    protected void setupEntities() {
        selectedCharacterRectangle = new SelectedCharacterRectangle(new Coordinate2D(50,50), new Size(70), rectColor);

        selectedCharacterRectangle.setAnchorPoint(AnchorPoint.CENTER_CENTER);


        addEntity(selectedCharacterRectangle);
        addEntity(selectedCharacter);
    }

    public void setSelectedCharacter(Character selectedCharacter) {
        this.selectedCharacter = selectedCharacter;
    }

    public int getSelectionID() {
        return selectionID;
    }

    public void setRectColor(Color rectColor) {
        this.rectColor = rectColor;
    }
}
