package brawlhalla.player;

import javafx.scene.input.KeyCode;

/**
 * The MovementConfiguration will
 */
public class PlayerMovementConfiguration implements IMovementConfiguration {
    private KeyCode up;
    private KeyCode down;
    private KeyCode left;
    private KeyCode right;
    private KeyCode attack;
    private KeyCode drop;

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
