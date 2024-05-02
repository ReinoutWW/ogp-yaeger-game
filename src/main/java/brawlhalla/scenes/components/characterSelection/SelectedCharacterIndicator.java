package brawlhalla.scenes.components.characterSelection;

import brawlhalla.player.characters.Character;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import javafx.scene.paint.Color;

public class SelectedCharacterIndicator extends CompositeEntity {
    private Color rectColor;
    private Character selectedCharacter;
    private Coordinate2D selectedCoordinate;

    public SelectedCharacterIndicator(Coordinate2D initialLocation, Color rectColor) {
        super(initialLocation);
        this.rectColor = rectColor;
        this.selectedCoordinate = initialLocation;
    }

    @Override
    protected void setupEntities() {
        var selectedRectangle = new SelectedCharacterRectangle(new Coordinate2D(50,50), new Size(50), rectColor);

        selectedRectangle.setAnchorPoint(AnchorPoint.CENTER_CENTER);

        addEntity(selectedRectangle);
        if (selectedCharacter != null) {
            addEntity(selectedCharacter);
        }
    }
}
