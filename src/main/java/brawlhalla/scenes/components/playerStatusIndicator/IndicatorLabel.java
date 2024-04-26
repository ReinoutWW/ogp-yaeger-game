package brawlhalla.scenes.components.playerStatusIndicator;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class IndicatorLabel extends DynamicTextEntity {
    protected IndicatorLabel(Coordinate2D initialLocation, String text, int fontSize) {
        super(initialLocation, text);
        setFill(Color.WHITE);
        setFont(Font.font("Roboto", FontWeight.BOLD, fontSize));
    }

    public void changeLabel(String text) {
        setText(text);
    }
}
