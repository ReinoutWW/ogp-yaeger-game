package brawlhalla.scenes.components.buttons;

import brawlhalla.Brawhalla;
import brawlhalla.Scenes;
import brawlhalla.player.characters.Character;
import brawlhalla.scenes.StartScene;
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

public class StartButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {
    private Brawhalla brawhalla;
    private Character player1Character;
    private Character player2Character;
    private StartScene startScene;

    public StartButton(Coordinate2D initialLocation, Brawhalla brawhalla, StartScene scene) {
        super(initialLocation);
        this.brawhalla = brawhalla;
        this.startScene = scene;
        setText("Start Game");
        setFill(Color.RED);
        setFont(Font.font("Roboto", FontWeight.BOLD, 30));
    }


    @Override
    public void onMouseButtonPressed(MouseButton mouseButton, Coordinate2D coordinate2D) {
        startScene.getCharacterRoster().createPlayerCharacters();
        brawhalla.setActiveScene(Scenes.ISLAND.index);
    }

    @Override
    public void onMouseEntered() {
        setFill(Color.GREEN);
        setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseExited(){
        setFill(Color.RED);
        setCursor(Cursor.DEFAULT);
    }
}
