package brawlhalla.scenes.components.playerStatusIndicator;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import javafx.scene.paint.Color;


public class ScorePanelRectangle extends RectangleEntity {
    public ScorePanelRectangle(Coordinate2D initialLocation, Color color) {
        super(initialLocation);
        setFill(color);
        setHeight(200);
        setWidth(200);
    }
}
