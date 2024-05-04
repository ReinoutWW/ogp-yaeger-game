package brawlhalla.scenes.components.buttons;

import brawlhalla.Brawhalla;
import brawlhalla.Scenes;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AgainButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {
    private Brawhalla brawhalla;

    public AgainButton(Coordinate2D initialLocation, Brawhalla brawhalla) {
        super(initialLocation);
        this.brawhalla = brawhalla;
        setText("Play Again");
        setFill(Color.RED);
        setFont(Font.font("Roboto", FontWeight.BOLD, 30));
    }

    /**
     * resets game to start
     * @param mouseButton
     * @param coordinate2D
     */
    @Override
    public void onMouseButtonPressed(MouseButton mouseButton, Coordinate2D coordinate2D) {
        brawhalla.setActiveScene(Scenes.StART.index);
    }

    /**
     * visual mouse indicator
     */
    @Override
    public void onMouseEntered() {
        setFill(Color.GREEN);
        setCursor(Cursor.HAND);
    }

    /**
     * visual mouse indicator
     */
    @Override
    public void onMouseExited(){
        setFill(Color.RED);
        setCursor(Cursor.DEFAULT);
    }
}
