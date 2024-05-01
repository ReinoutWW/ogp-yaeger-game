package brawlhalla.scenes.components.playerStatusIndicator;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.CircleEntity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class IndicatorCircle extends CircleEntity {
    public IndicatorCircle(Coordinate2D initialLocation, Color color, double radius) {
        super(initialLocation);
        setFill(color);
        setRadius(radius);
    }
}
