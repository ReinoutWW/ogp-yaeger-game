package brawlhalla.player;

import brawlhalla.player.characters.Character;
import brawlhalla.scenes.IProjectileSpawnableScene;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.YaegerScene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.Set;

public class ControlledPlayerWSDA extends Player {
    public ControlledPlayerWSDA(Coordinate2D initialLocation, String name, Character character, PlayerStatusIndicator playerStatusIndicator, IProjectileSpawnableScene scene, SpriteEntity centreIsland, Color playerColor) {
        super(initialLocation, name, character, playerStatusIndicator, scene, centreIsland, playerColor);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys){
        if (pressedKeys.contains(KeyCode.W) && isGrounded) {
            setIsGrounded(false);
            setMotion(5, 180d);
        } else if (pressedKeys.contains(KeyCode.S) && !isGrounded) {
            setMotion(3, 0d);
        } else if (pressedKeys.contains(KeyCode.A)) {

            if (isGrounded) {
                setMotion(3, 270d);
            } else if (!isGrounded && getDirection() == 315d) {
                setMotion(3, 315d);
            } else if (!isGrounded) {
                if (getDirection() == 0) {
                    setMotion(3, 355);
                } else if (getDirection() < 315d && getDirection() >= 180d) {
                    setMotion(3, getDirection() + 5);
                } else if (getDirection() > 315d || getDirection() < 180d) {
                    setMotion(3, getDirection() - 5);
                }
            }
        } else if (pressedKeys.contains(KeyCode.D)) {

            if (getDirection() == 360d) {
                setDirection(0d);
            }
            if (isGrounded) {
                setMotion(3, 90d);
            } else if (!isGrounded && getDirection() == 45d) {
                setMotion(3, 45d);
            } else if (!isGrounded) {
                if (getDirection() < 45d || getDirection() > 180d) {
                    setMotion(3, getDirection() + 5);
                } else if (getDirection() > 45d && getDirection() <= 180d) {
                    setMotion(3, getDirection() - 5);
                }
            }
        }

        // Do attack
        if (pressedKeys.contains(KeyCode.Q)) {
            System.out.println("attack! ");

            attack();
        }
    }

    @Override
    public void CurveMotion() {

    }

}
