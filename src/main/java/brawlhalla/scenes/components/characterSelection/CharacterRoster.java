package brawlhalla.scenes.components.characterSelection;

import brawlhalla.player.characters.CactiCharacter;
import brawlhalla.player.characters.Character;
import brawlhalla.player.characters.FoxCharacter;
import brawlhalla.player.characters.ManCharacter;
import brawlhalla.player.characters.YellowCharacter;
import brawlhalla.timer.SelectionTimer;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.CompositeEntity;
import com.github.hanyaeger.api.entities.DynamicCompositeEntity;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Set;

public class CharacterRoster extends DynamicCompositeEntity implements KeyListener, TimerContainer {
    private ArrayList<CharacterIndicator> selectedCharacters;
    private CharacterIndicator foxCharacterRect;
    private CharacterIndicator cactiCharacterRect;
    private CharacterIndicator manCharacterRect;
    private CharacterIndicator yellowCharacterRect;
    private int selectionPlayer2;
    private int selectionPlayer1;
    private Character selectedCharacterPlayer1;
    private Character selectedCharacterPlayer2;


    public boolean getKeysBlocked() {
        return keysBlocked;
    }

    private boolean keysBlocked;
    private SelectionTimer selectionTimer;

    public CharacterRoster(Coordinate2D initialLocation) {
        super(initialLocation);
        selectionPlayer1 = 1;
        selectionPlayer2 = 4;
        selectionTimer = new SelectionTimer(250, this);
    }

    @Override
    protected void setupEntities() {
        selectedCharacters = new ArrayList<>();

        foxCharacterRect = new CharacterIndicator(new Coordinate2D(getWidth() - 50, getHeight() - 50), Color.RED, new FoxCharacter(),1);
        selectedCharacters.add(foxCharacterRect);

        cactiCharacterRect = new CharacterIndicator(new Coordinate2D(getWidth() + 50, getHeight() - 50), Color.GREEN, new CactiCharacter(),2);
        selectedCharacters.add(cactiCharacterRect);

        manCharacterRect = new CharacterIndicator(new Coordinate2D(getWidth() - 50, getHeight() + 50), Color.GREEN, new ManCharacter(),3);
        selectedCharacters.add(manCharacterRect);

        yellowCharacterRect = new CharacterIndicator(new Coordinate2D(getWidth() + 50, getHeight() + 50), Color.BLUE, new YellowCharacter(),4);
        selectedCharacters.add(yellowCharacterRect);


        addEntity(foxCharacterRect);
        addEntity(cactiCharacterRect);
        addEntity(manCharacterRect);
        addEntity(yellowCharacterRect);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> set) {
        if(keysBlocked == false) {
            if (set.contains(KeyCode.LEFT)) {
                selectionPlayer1--;
                if (selectionPlayer1 <= 0) {
                    selectionPlayer1 = 4;
                }
            } else if (set.contains(KeyCode.RIGHT)) {
                selectionPlayer1++;
                if (selectionPlayer1 > 4) {
                    selectionPlayer1 = 1;
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
                    selectionPlayer2 = 1;
                }
            }
            keysBlocked = true;
            selectionTimer.reset();
            CheckSelection();
        }
    }


    public void CheckSelection() {
        for (CharacterIndicator selectedCharacter : selectedCharacters) {
            if (selectionPlayer1 == selectedCharacter.getSelectionID() && selectionPlayer2 == selectedCharacter.getSelectionID()) {
                selectedCharacter.selectedCharacterRectangle.setFill(Color.PURPLE);
            } else if (selectionPlayer1 == selectedCharacter.getSelectionID()) {
                selectedCharacter.selectedCharacterRectangle.setFill(Color.RED);
            } else if (selectionPlayer2 == selectedCharacter.getSelectionID()) {
                selectedCharacter.selectedCharacterRectangle.setFill(Color.BLUE);
            } else {
                selectedCharacter.selectedCharacterRectangle.setFill(Color.GREEN);
            }
        }

    }

    public void setKeysBlocked(boolean keysBlocked) {
        this.keysBlocked = keysBlocked;
    }

    @Override
    public void setupTimers() {
        addTimer(selectionTimer);
    }

    public void createPlayerCharacters() {
       switch ( selectionPlayer1 ) {
           case 1:
               selectedCharacterPlayer1 = new FoxCharacter();
               break;
           case 2:
               selectedCharacterPlayer1 = new CactiCharacter();
               break;
           case 3:
               selectedCharacterPlayer1 = new ManCharacter();
               break;
           case 4:
               selectedCharacterPlayer1 = new YellowCharacter();
               break;
       }
        switch ( selectionPlayer2 ) {
            case 1:
                selectedCharacterPlayer2 = new FoxCharacter();
                break;
            case 2:
                selectedCharacterPlayer2 = new CactiCharacter();
                break;
            case 3:
                selectedCharacterPlayer2 = new ManCharacter();
                break;
            case 4:
                selectedCharacterPlayer2 = new YellowCharacter();
                break;
        }
    }

    public Character getSelectedCharacterPlayer1() {
        return selectedCharacterPlayer1;
    }

    public Character getSelectedCharacterPlayer2() {
        return selectedCharacterPlayer2;
    }
}



