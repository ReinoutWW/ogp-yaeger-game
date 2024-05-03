package brawlhalla.player;

import javafx.scene.input.KeyCode;

public interface IMovementConfiguration {
    KeyCode getUp();
    KeyCode getDown();
    KeyCode getLeft();
    KeyCode getRight();
    KeyCode getAttack();
    KeyCode getDrop();
}
