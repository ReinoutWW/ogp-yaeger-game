package brawlhalla.timer;

import brawlhalla.scenes.components.characterSelection.CharacterRoster;
import com.github.hanyaeger.api.Timer;

public class SelectionTimer extends Timer {
    private CharacterRoster characterroster;

    public SelectionTimer(long intervalInMs, CharacterRoster characterRoster) {
        super(intervalInMs);
        this.characterroster = characterRoster;
    }

    /**
     * interval handling for character selection key pressed.
     * delays character selection cycle.
     * @param l
     */
    @Override
    public void onAnimationUpdate(long l) {
        characterroster.setKeysBlocked(false);
    }
}
