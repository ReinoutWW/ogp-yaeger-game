package brawlhalla.player;

import brawlhalla.player.characters.Character;
import brawlhalla.scenes.IProjectileSpawnableScene;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import brawlhalla.timer.MovementTimer;
import brawlhalla.yaegerExtension.DirectionHelper;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.Set;

public class ControlledPlayer extends Player implements TimerContainer {
    private boolean curveStep;
    private final PlayerMovementConfiguration playerMovementConfiguration;
    MovementTimer movementTimer = new MovementTimer(1, this);

    public ControlledPlayer(Coordinate2D initialLocation, String name, Character character, PlayerStatusIndicator playerStatusIndicator, IProjectileSpawnableScene scene, SpriteEntity centreIsland, Color playerColor, PlayerMovementConfiguration movementConfiguration) {
        super(initialLocation, name, character, playerStatusIndicator, scene, centreIsland, playerColor);
        playerMovementConfiguration = movementConfiguration;
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (pressedKeys.contains(playerMovementConfiguration.getUp()) && isGrounded) {
            setIsGrounded(false);
            setMotion(5, 180d);
        } else if (pressedKeys.contains(playerMovementConfiguration.getDown()) && !isGrounded) {
            setMotion(3, 0d);
        } else if (pressedKeys.contains(playerMovementConfiguration.getLeft())) {
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
        } else if (pressedKeys.contains(playerMovementConfiguration.getRight())) {
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
        if (pressedKeys.contains(playerMovementConfiguration.getAttack())) {
            System.out.println("attack! ");
            attack();
        }

        double currentDirection = getDirection();

        // Check if the attack is between
        saveAttackDirection(currentDirection);
    }

    private void saveAttackDirection(double currentDirection) {
        if (DirectionHelper.isBetweenCoordinates(Direction.UP_LEFT.getValue(), Direction.DOWN_LEFT.getValue(), currentDirection) ||
            DirectionHelper.isBetweenCoordinates(Direction.DOWN_RIGHT.getValue(), Direction.UP_RIGHT.getValue(), currentDirection)) {
            setAttackDirection(getDirection());
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