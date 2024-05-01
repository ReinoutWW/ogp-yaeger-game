package brawlhalla.scenes.components;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WinnerText extends TextEntity {
    private String winner;

    public WinnerText(Coordinate2D initialLocation) {
        super(initialLocation);
        setText(winner + " is the winner!!");
        setFill(Color.PURPLE);
        setFont(Font.font("Roboto", FontWeight.BOLD, 30));
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
