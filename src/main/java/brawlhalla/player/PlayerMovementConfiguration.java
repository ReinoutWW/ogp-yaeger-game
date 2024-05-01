package brawlhalla.player;

import javafx.scene.input.KeyCode;

public class PlayerMovementConfiguration {
    private KeyCode up;
    private KeyCode down;
    private KeyCode left;
    private KeyCode right;
    private KeyCode attack;

    public PlayerMovementConfiguration(KeyCode up,
        KeyCode down,
        KeyCode left,
        KeyCode right,
        KeyCode attack) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.attack = attack;
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
}
