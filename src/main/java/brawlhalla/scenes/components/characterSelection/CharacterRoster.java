package brawlhalla.scenes.components.characterSelection;

import brawlhalla.player.characters.CactiCharacter;
import brawlhalla.player.characters.FoxCharacter;
import brawlhalla.player.characters.ManCharacter;
import brawlhalla.player.characters.YellowCharacter;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Set;

public class CharacterRoster extends CompositeEntity implements KeyListener {
    private ArrayList<CharacterIndicator> selectedCharacters;
    private CharacterIndicator foxCharacterRect;
    private CharacterIndicator cactiCharacterRect;
    private CharacterIndicator manCharacterRect;
    private CharacterIndicator yellowCharacterRect;
    private int selectionPlayer1;
    private int selectionPlayer2;

    public CharacterRoster(Coordinate2D initialLocation) {
        super(initialLocation);
        selectionPlayer1 = 1;
        selectionPlayer2 = 4;
    }

    @Override
    protected void setupEntities() {
        foxCharacterRect = new CharacterIndicator(new Coordinate2D(getWidth() - 50, getHeight() - 50), Color.RED, new FoxCharacter(),1);
        selectedCharacters.add(foxCharacterRect);

        cactiCharacterRect = new CharacterIndicator(new Coordinate2D(getWidth() + 50, getHeight() - 50), Color.GREEN, new CactiCharacter(),2);
        selectedCharacters.add(cactiCharacterRect);

        manCharacterRect = new CharacterIndicator(new Coordinate2D(getWidth() - 50, getHeight() + 50), Color.GREEN, new ManCharacter(),3);
        selectedCharacters.add(manCharacterRect);

        yellowCharacterRect = new CharacterIndicator(new Coordinate2D(getWidth() + 50, getHeight() + 50), Color.BLUE, new YellowCharacter(),4);
        selectedCharacters.add(yellowCharacterRect);

        foxCharacterRect.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        yellowCharacterRect.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        cactiCharacterRect.setAnchorPoint(AnchorPoint.CENTER_CENTER);
        manCharacterRect.setAnchorPoint(AnchorPoint.CENTER_CENTER);

        addEntity(foxCharacterRect);
        addEntity(cactiCharacterRect);
        addEntity(manCharacterRect);
        addEntity(yellowCharacterRect);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> set) {
        if (set.contains(KeyCode.LEFT)) {
            selectionPlayer1--;
            if (selectionPlayer1 <= 0) {
                selectionPlayer1 = 4;
            }
        } else if (set.contains(KeyCode.RIGHT)) {
            selectionPlayer1++;
            if (selectionPlayer1 > 4) {
                selectionPlayer1 = 0;
            }
        }
        if (set.contains(KeyCode.A)) {
            selectionPlayer2--;
            if (selectionPlayer2 <= 0) {
                selectionPlayer2 = 4;
            }
        } else if (set.contains(KeyCode.D)) {
            selectionPlayer2++;
            if (selectionPlayer2 > 4) {
                selectionPlayer2 = 0;
            }
        }

        CheckSelection();
    }

    public void CheckSelection() {

    }
}
