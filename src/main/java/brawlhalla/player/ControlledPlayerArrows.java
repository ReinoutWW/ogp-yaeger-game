package brawlhalla.player;

import brawlhalla.player.characters.Character;
import brawlhalla.scenes.IProjectileSpawnableScene;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import brawlhalla.timer.MovementTimer;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.YaegerScene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.Set;

public class ControlledPlayerArrows extends Player implements TimerContainer {
    private boolean curveStep;
    MovementTimer movementTimer = new MovementTimer(1, this);

    public ControlledPlayerArrows(Coordinate2D initialLocation, String name, Character character, PlayerStatusIndicator playerStatusIndicator, IProjectileSpawnableScene scene, SpriteEntity centreIsland, Color playerColor) {
        super(initialLocation, name, character, playerStatusIndicator, scene, centreIsland, playerColor);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (pressedKeys.contains(KeyCode.UP) && isGrounded) {
            setIsGrounded(false);
            setMotion(5, 180d);
        } else if (pressedKeys.contains(KeyCode.DOWN) && !isGrounded) {
            setMotion(3, 0d);
        } else if (pressedKeys.contains(KeyCode.LEFT)) {
            movementTimer.reset();

            if (isGrounded) {
                setMotion(3, 270d);
            } else if (!isGrounded && getDirection() == 315d) {
                setMotion(3, 315d);
            } else if (curveStep && !isGrounded) {
                if (getDirection() == 0) {
                    setMotion(3, 355);
                } else if (getDirection() < 315d && getDirection() >= 180d) {
                    setMotion(3, getDirection() + 5);
                } else if (getDirection() > 315d || getDirection() < 180d) {
                    setMotion(3, getDirection() - 5);
                }
            }
        } else if (pressedKeys.contains(KeyCode.RIGHT)) {
            movementTimer.reset();

            if (getDirection() == 360d) {
                setDirection(0d);
            }
            if (isGrounded) {
                setMotion(3, 90d);
            } else if (!isGrounded && getDirection() == 45d) {
                setMotion(3, 45d);
            } else if (curveStep && !isGrounded) {
                if (getDirection() < 45d || getDirection() > 180d) {
                    setMotion(3, getDirection() + 5);
                } else if (getDirection() > 45d && getDirection() <= 180d) {
                    setMotion(3, getDirection() - 5);
                }
            }
        }

        // Do attack
        if (pressedKeys.contains(KeyCode.CONTROL)) {
            System.out.println("attack! ");
            weapon.attack(getDirection(), getAnchorLocation());
        }
    }

    @Override
    public void setupTimers() {
        addTimer(movementTimer);
    }

    public void CurveMotion() {
        curveStep = true;
    }

}
