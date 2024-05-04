package brawlhalla.timer;

import brawlhalla.scenes.components.characterSelection.CharacterRoster;
import com.github.hanyaeger.api.Timer;

public class SelectionTimer extends Timer {
    private CharacterRoster characterroster;

    public SelectionTimer(long intervalInMs, CharacterRoster characterRoster) {
        super(intervalInMs);
        this.characterroster = characterRoster;
    }

    @Override
    public void onAnimationUpdate(long l) {
        characterroster.setKeysBlocked(false);
    }
}
