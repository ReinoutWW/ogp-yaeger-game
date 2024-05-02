package brawlhalla.scenes.components.characterSelection;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import javafx.scene.paint.Color;

public class SelectedCharacterRectangle extends RectangleEntity {
    protected SelectedCharacterRectangle(Coordinate2D initialLocation, Size size, Color color) {
        super(initialLocation);
        setWidth(size.width());
        setHeight(size.height());
        setFill(color);
    }
}
