package brawlhalla.player;

import brawlhalla.scenes.components.Island;
import brawlhalla.scenes.components.MovingPlatform;
import brawlhalla.player.characters.Character;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import brawlhalla.weapons.IWeapon;
import brawlhalla.weapons.projectiles.Projectile;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.scenes.YaegerScene;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;
import brawlhalla.yaegerExtension.*;

import java.util.List;
import java.util.Set;

public class Player extends DynamicCompositeEntity implements IPlayer, Newtonian, ClassCollided, Collider, KeyListener, SceneBorderTouchingWatcher {
    private int lives;
    private int damageTakenMiltiplier;
    private IWeapon weapon;
    private Character character;
    private final String playerName;
    private PlayerTag playerTag;
    private boolean isGrounded;
    private PlayerScoreStatistics playerScoreStatistics = new PlayerScoreStatistics();
    private PlayerStatusIndicator playerStatusIndicator;
    private YaegerScene scene;
    private SpriteEntity centreIsland;

    public Player(Coordinate2D initialLocation, String name, Character character, PlayerStatusIndicator playerStatusIndicator, YaegerScene scene, SpriteEntity centreIsland) {
        super(initialLocation);
        this.playerStatusIndicator = playerStatusIndicator;
        this.scene = scene;
        this.centreIsland = centreIsland;
        this.playerName = name;
        this.character = character;
        this.playerTag = new PlayerTag(
                new Coordinate2D(15, 0),
                name
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
    public void onPressedKeysChange(Set<KeyCode> pressedKeys){
        if(pressedKeys.contains(KeyCode.LEFT)){
            setMotion(3,270d);
        } else if(pressedKeys.contains(KeyCode.RIGHT)){
            setMotion(3,90d);
        } else if(pressedKeys.contains(KeyCode.UP) && isGrounded){
            setIsGrounded(false);
            setMotion(5,180d);
        } else if(pressedKeys.contains(KeyCode.DOWN) && !isGrounded){
            setMotion(3,0d);
        }
    }

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
    }

    @Override
    public void notifyBoundaryTouching(SceneBorder sceneBorder) {
        // Check which scene border has been touched
        // If below, lose lives

        if(sceneBorder == SceneBorder.BOTTOM) {
            decreateLives(1);
            respawn();
        }
    }

    @Override
    public String getName() {
        return playerName;
    }

    public void decreateLives(int amount) {
        lives -= amount;
        playerStatusIndicator.updateStatus(this);

        if(lives < 1) {
            // Do something here, for example restart
        }
    }
}
