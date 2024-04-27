package brawlhalla.player;

import brawlhalla.player.characters.Character;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.YaegerScene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.Set;

public class ControlledPlayerWSDA extends Player {
    public ControlledPlayerWSDA(Coordinate2D initialLocation, String name, Character character, PlayerStatusIndicator playerStatusIndicator, YaegerScene scene, SpriteEntity centreIsland, Color playerColor) {
        super(initialLocation, name, character, playerStatusIndicator, scene, centreIsland, playerColor);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys){
        if(pressedKeys.contains(KeyCode.A)){
            setMotion(3,270d);
        } else if(pressedKeys.contains(KeyCode.D)){
            setMotion(3,90d);
        } else if(pressedKeys.contains(KeyCode.W) && isGrounded){
            setIsGrounded(false);
            setMotion(5,180d);
        } else if(pressedKeys.contains(KeyCode.S) && !isGrounded){
            setMotion(3,0d);
        }

        // Do attack
        if(pressedKeys.contains(KeyCode.Q)) {
            weapon.attack(getDirection());
        }
    }

    @Override
    public void CurveMotion() {

    }

}
