package brawlhalla.player;

import brawlhalla.scenes.components.Island;
import brawlhalla.scenes.components.MovingPlatform;
import brawlhalla.player.characters.Character;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import brawlhalla.weapons.IWeapon;
import brawlhalla.weapons.Weapon;
import brawlhalla.weapons.projectiles.Projectile;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.scenes.YaegerScene;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;
import brawlhalla.yaegerExtension.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Set;

public abstract class Player extends DynamicCompositeEntity implements IPlayer, Newtonian, ClassCollided, Collider, KeyListener, SceneBorderTouchingWatcher {
    private int lives;
    private int damageTakenMiltiplier;
    private Character character;
    private final String playerName;
    private PlayerTag playerTag;
    private PlayerScoreStatistics playerScoreStatistics = new PlayerScoreStatistics();
    private PlayerStatusIndicator playerStatusIndicator;
    private YaegerScene scene;
    private SpriteEntity centreIsland;
    protected Weapon weapon;
    protected boolean isGrounded;

    public Player(Coordinate2D initialLocation, String name, Character character, PlayerStatusIndicator playerStatusIndicator, YaegerScene scene, SpriteEntity centreIsland, Color playerColor) {
        super(initialLocation);
        this.playerStatusIndicator = playerStatusIndicator;
        this.scene = scene;
        this.centreIsland = centreIsland;
        this.playerName = name;
        this.character = character;
        this.weapon = character.createDefaultWeapon(new Coordinate2D(25, 10));
        this.playerTag = new PlayerTag(
                new Coordinate2D(15, 0),
                name,
                playerColor
        );
        this.lives = 3;

        setGravityConstant(0.08);
        setFrictionConstant(0.04);

        playerStatusIndicator.updateStatus(this);
    }

    public void setIsGrounded(boolean isGrounded) {
        this.isGrounded = isGrounded;
    }

    public int getDamageTakenMiltiplier() {
        return damageTakenMiltiplier;
    }

    public int getLives() {
        return lives;
    }

    @Override
    public abstract void onPressedKeysChange(Set<KeyCode> pressedKeys);

    @Override
    public void onCollision(List<Collider> list) {
        if(hitsClass(list, Island.class)) {
            // Island and platform hit
            this.isGrounded = true;
            setMotion(0, 0d);
        }

        // there can never be more than 1 moving platform in the collided. And if there are, just pick the first.
        // Set user movement as the same as the collided moving platform.
        if(hitsClass(list, MovingPlatform.class)) { // <-- instanceof in the background
            MovingPlatform movingPlatform = getFirstOfCollidedClasses(list, MovingPlatform.class);

            double playerBottomY = this.getAnchorLocation().getY() + getHeight() - 10;
            double playformTopY = movingPlatform.getAnchorLocation().getY();

            if(playerBottomY < playformTopY) {
                this.isGrounded = true;
                moveWithMovingPlatform(movingPlatform);
            }
        }

        // Check if there's a Projectile.class in the list
        if(hitsClass(list, Projectile.class)) {
            Projectile collidedProjectile = getFirstOfCollidedClasses(list, Projectile.class);

            // Do something with the given projectile
        }
    }

    private void moveWithMovingPlatform(MovingPlatform movingPlatform) {
        setMotion(movingPlatform.getSpeed(), movingPlatform.getDirection());
    }

    @Override
    public void respawn() {
        double centralIslandSpawnY = centreIsland.getAnchorLocation().getY() - centreIsland.getHeight() - this.getHeight();
        setAnchorLocation(new Coordinate2D(scene.getWidth() / 2, centralIslandSpawnY));
    }

    @Override
    protected void setupEntities() {
        addEntity(character);
        addEntity(playerTag);
        addEntity(weapon);
    }

    @Override
    public void notifyBoundaryTouching(SceneBorder sceneBorder) {
        // Check which scene border has been touched
        // If below, lose lives

        if(sceneBorder == SceneBorder.BOTTOM) {
            decreateLives(1);
            respawn();
        }

        // Change position if user leaves the side of the screen
        if(sceneBorder == SceneBorder.LEFT || sceneBorder == SceneBorder.RIGHT) {
            double x = (sceneBorder == SceneBorder.LEFT ? scene.getWidth() - getWidth() : 0);
            double y = getAnchorLocation().getY();
            setAnchorLocation(new Coordinate2D(x, y));
        }
    }

    @Override
    public String getName() {
        return playerName;
    }

    /**
     * Will decreate the players' lives if lives > 0. (Lives can never be negative)
     * If lives <= 0, nothing will happen.
     * @param amount should be positive
     */
    public void decreateLives(int amount) {
        if(lives - amount < 0) {
            lives = 0; // In case it would end up below 0
        } else {
            lives -= (lives > 0) ? Math.max(amount, 0) : 0; // Decrease only positive amounts, and fallback to 0.
        }

        playerStatusIndicator.updateStatus(this);
        if(lives < 1) {
            // Do something here, for example restart
        }
    }
}
