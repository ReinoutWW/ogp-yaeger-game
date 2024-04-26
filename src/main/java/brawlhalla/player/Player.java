package brawlhalla.player;

import brawlhalla.levelObjects.Island;
import brawlhalla.levelObjects.MovingPlatform;
import brawlhalla.player.characters.CactiCharacter;
import brawlhalla.player.characters.Character;
import brawlhalla.scenes.IslandScene;
import brawlhalla.weapons.IProjectile;
import brawlhalla.weapons.IWeapon;
import brawlhalla.weapons.projectiles.Projectile;
import brawlhalla.yaegerExtension.ClassCollided;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.checkerframework.checker.units.qual.C;

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

    public Player(Coordinate2D initialLocation, String name, Character character) {
        super(initialLocation);
        this.playerName = name;
        this.character = character;
        this.playerTag = new PlayerTag(
                new Coordinate2D(15, 0),
                name
        );

        setGravityConstant(0.1);
        setFrictionConstant(0.04);
    }

    public void setIsGrounded(boolean isGrounded) {
        this.isGrounded = isGrounded;
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys){

        if(pressedKeys.contains(KeyCode.LEFT)){
            setMotion(3,270d);
        } else if(pressedKeys.contains(KeyCode.RIGHT)){
            setMotion(3,90d);
        } else if(pressedKeys.contains(KeyCode.UP) && isGrounded){
            setIsGrounded(false);
            setMotion(4,180d);
        } else if(pressedKeys.contains(KeyCode.DOWN) && !isGrounded){
            setMotion(3,0d);
        }
    }

    @Override
    public void onCollision(List<Collider> list) {
        if(hitsClass(list, Island.class) || hitsClass(list, MovingPlatform.class)) {
            // Island and platform hit
            this.isGrounded = true;
            setMotion(0, 0d);
        }

        // there can never be more than 1 moving platform in the collided. And if there are, just pick the first.
        // Set user movement as the same as the collided moving platform.
        if(hitsClass(list, MovingPlatform.class)) { // <-- instanceof in the background
            MovingPlatform movingPlatform = getFirstOfCollidedClasses(list, MovingPlatform.class);
            moveWithMovingPlatform(movingPlatform);
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
    public void respawn(Coordinate2D location) {
        setAnchorLocation(location);
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
    }
}
