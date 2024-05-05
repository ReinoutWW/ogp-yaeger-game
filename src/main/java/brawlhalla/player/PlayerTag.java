package brawlhalla.player;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Used to display the PlayerTag on a moving player
 */
public class PlayerTag extends DynamicTextEntity {
    protected PlayerTag(Coordinate2D initialLocation, String playerName, Color playerColor) {
        super(initialLocation, playerName);
        setFill(playerColor);
        setFont(Font.font("Roboto", FontWeight.BOLD, 12));
    }
}
