package brawlhalla.scenes.components;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WinnerText extends TextEntity {


    public WinnerText(Coordinate2D initialLocation, String winner) {
        super(initialLocation);
        setText(winner + " is the winner!!");
        setFill(Color.GREEN);
        setFont(Font.font("Roboto", FontWeight.BOLD, 30));
    }
}
