package brawlhalla.player;

import javafx.scene.input.KeyCode;

/**
 * The MovementConfiguration will be used to ensure better readability in the handling of movement
 */
public class PlayerMovementConfiguration implements IMovementConfiguration {
    private final KeyCode up;
    private final KeyCode down;
    private final KeyCode left;
    private final KeyCode right;
    private final KeyCode attack;
    private final KeyCode drop;

    public PlayerMovementConfiguration(KeyCode up,
        KeyCode down,
        KeyCode left,
        KeyCode right,
        KeyCode attack,
        KeyCode drop) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.attack = attack;
        this.drop = drop;
    }

    public KeyCode getUp() {
        return up;
    }

    public KeyCode getDown() {
        return down;
    }

    public KeyCode getLeft() {
        return left;
    }

    public KeyCode getRight() {
        return right;
    }

    public KeyCode getAttack() {
        return attack;
    }

    public KeyCode getDrop() {
        return drop;
    }
}
