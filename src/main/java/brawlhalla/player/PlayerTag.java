package brawlhalla.player;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicTextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PlayerTag extends DynamicTextEntity {
    protected PlayerTag(Coordinate2D initialLocation, String playerName) {
        super(initialLocation, playerName);
        setFill(Color.RED);
        setFont(Font.font("Roboto", FontWeight.BOLD, 12));
    }

    /**
     * This will show the user feedback if the player takes damage
     * The name will show red for an x duration time
     * @param duration for the damage show duration
     */
    public void showDamage(int duration) {
        // Set timer to reset with duration
        setFill(Color.RED);
    }
}
