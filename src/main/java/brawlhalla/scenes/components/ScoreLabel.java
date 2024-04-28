package brawlhalla.scenes.components;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScoreLabel extends DynamicTextEntity {
    protected ScoreLabel(Coordinate2D initialLocation, String text, int fontSize) {
        super(initialLocation, text);
        setFill(Color.WHITE);
        setFont(Font.font("Roboto", FontWeight.BOLD, fontSize));
    }
}
