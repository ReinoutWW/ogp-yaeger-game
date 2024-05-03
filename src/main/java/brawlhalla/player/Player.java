package brawlhalla.player;

import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.scenes.components.Island;
import brawlhalla.scenes.components.MovingPlatform;
import brawlhalla.player.characters.Character;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import brawlhalla.timer.MovementTimer;
import brawlhalla.weapons.*;
import brawlhalla.weapons.projectiles.Projectile;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;
import brawlhalla.yaegerExtension.*;
import javafx.scene.paint.Color;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The main Player logic will be here. This class will provide all collisions, movement and features
 */
public class Player extends DynamicCompositeEntity implements IPlayer, Newtonian, ClassCollided, Collider, KeyListener, SceneBorderTouchingWatcher, TimerContainer {
    private final IMovementConfiguration playerMovementConfiguration;
    private final Coordinate2D WEAPON_POSITION = new Coordinate2D(0, 50);
    private double attackDirection = Direction.RIGHT.getValue();
    private int lives;
    private int damageTakenMultiplier;
    private Character character;
    private final String playerName;
    private PlayerTag playerTag;
    private PlayerScoreStatistics playerScoreStatistics = new PlayerScoreStatistics();
    private PlayerStatusIndicator playerStatusIndicator;
    private IEntitySpawnableScene islandScene;
    private SpriteEntity centreIsland;
    protected Optional<Weapon> weapon;
    protected boolean isGrounded;
    protected MovementTimer movementTimer;

    /**
     * This method will return if the controls or movement of the player is blocked.
     * For example, after a projectile hit, the movement will be temporarily be suspended.
     * @return boolean
     */
    public boolean areControlsBlocked() {
        return controlsBlocked;
    }

    /**
     * Sets the controllblock
     * @param controllBlock
     */
    public void setControlsBlocked(boolean controllBlock) {
        this.controlsBlocked = controllBlock;
    }

    protected boolean controlsBlocked = false;

    public Player(Coordinate2D initialLocation, String name, Character character, PlayerStatusIndicator playerStatusIndicator, IEntitySpawnableScene islandScene, SpriteEntity centreIsland, Color playerColor, IMovementConfiguration movementConfiguration) {
        super(initialLocation);
        this.playerStatusIndicator = playerStatusIndicator;
        this.islandScene = islandScene;
        this.centreIsland = centreIsland;
        this.playerName = name;
        this.character = character;
        this.weapon = Optional.of(character.createDefaultWeapon(WEAPON_POSITION, islandScene));
        this.weapon.ifPresent(weapon -> weapon.setIsHeldByCharacter(true));
        this.playerTag = new PlayerTag(
                new Coordinate2D(15, 0),
                name,
                playerColor
        );
        this.lives = 3;

        playerMovementConfiguration = movementConfiguration;
        movementTimer = new MovementTimer(1000, this);

        setGravityConstant(0.08);
        setFrictionConstant(0.04);

        playerStatusIndicator.updateStatus(this);
    }

    /**
     * The main method that will handle all key presses.
     * Mainly relies on the @PlayerMovementConfiguration for the active key options
     * @param pressedKeys
     */
    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if(areControlsBlocked()) {
            return;
        }

        if (pressedKeys.contains(playerMovementConfiguration.getUp()) && isGrounded) {
            setIsGrounded(false);
            setMotion(5, 180d);
        } else if (pressedKeys.contains(playerMovementConfiguration.getDown()) && !isGrounded) {
            setMotion(3, 0d);
        } else if (pressedKeys.contains(playerMovementConfiguration.getLeft())) {
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
        } else if (pressedKeys.contains(playerMovementConfiguration.getRight())) {
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
        if (pressedKeys.contains(playerMovementConfiguration.getAttack())) {
            System.out.println("attack! ");
            attack();
        }

        if(pressedKeys.contains(playerMovementConfiguration.getDrop())) {
            dropWeapon();
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

    public void setIsGrounded(boolean isGrounded) {
        this.isGrounded = isGrounded;
    }

    public Coordinate2D getWeaponPosition() {
        return WEAPON_POSITION;
    }

    public int getDamageTakenMiltiplier() {
        return damageTakenMultiplier;
    }

    public int getLives() {
        return lives;
    }

    /**
     * Will drop the weapon into the provided IEntitySpawnableScene.
     * Note: Will only drop the weapon if there's a weapon instance
     * If weapon is null, nothing will happen.
     */
    public void dropWeapon() {
        if(weapon.isPresent()) {
            // Current weapon
            Weapon selectedWeapon = weapon.get();

            // New instance of current weapon
            Weapon spawnableWeapon = selectedWeapon.cloneWeapon();

            // Remove current weapon
            selectedWeapon.removeWeapon();
            this.weapon = Optional.empty();

            // Spawn new
            spawnableWeapon.setAnchorLocation(
                    new Coordinate2D(
                            this.getAnchorLocation().getX(),
                            this.getAnchorLocation().getY() + WEAPON_POSITION.getY()
                    )
            );

            spawnableWeapon.setIsHeldByCharacter(false);
            spawnableWeapon.setPickupDelayBlock();
            islandScene.addEntityToSpawn(spawnableWeapon);
        }
    }

    @Override
    public void onCollision(List<Collider> list) {
        if(hitsClass(list, Island.class)) {
            handleIslandCollision();
        }

        // there can never be more than 1 moving platform in the collided. And if there are, just pick the first.
        // Set user movement as the same as the collided moving platform.
        if(hitsClass(list, MovingPlatform.class)) { // <-- instanceof in the background
            handleMovingPlatformCollision(list);
        }

        // Check if there's a Projectile.class in the list
        if(hitsClass(list, Projectile.class)) {
            handleProjectileCollision(list);
        }

        if(hitsClass(list, Weapon.class)) {
            handleWeaponCollision(list);
        }
    }

    /**
     * either handle the damage collision, or the pickup collision
     * @param list
     */
    private void handleWeaponCollision(List<Collider> list) {
        Weapon collidedWeapon = getFirstOfCollidedClasses(list, Weapon.class);

        if(this.weapon.isEmpty() && collidedWeapon.isReadyForPickup()) {

            // Clone collided weapon
            this.weapon = Optional.of(collidedWeapon.cloneWeapon());
            this.weapon.ifPresent(weapon -> weapon.setIsHeldByCharacter(true));
            this.weapon.ifPresent(weapon -> weapon.setAnchorLocation(WEAPON_POSITION));

            // Remove other scene weapon
            collidedWeapon.removeWeapon();

            // Use cloned weapon to set properties
            addEntity(this.weapon.get());
        }
    }

    private void handleIslandCollision() {
        // Island and platform hit
        double playerBottomY = this.getAnchorLocation().getY() + getHeight() - 10;

        this.isGrounded = true;
        if(!areControlsBlocked()) {
            setMotion(0, 0d);
        }

        if (this.getAnchorLocation().getX() < centreIsland.getAnchorLocation().getX() && playerBottomY > centreIsland.getAnchorLocation().getY() - centreIsland.getHeight()){
            this.setAnchorLocationX(centreIsland.getAnchorLocation().getX() - (centreIsland.getWidth() / 2) - this.getWidth() -1);
        }
        else if (this.getAnchorLocation().getX() > centreIsland.getAnchorLocation().getX() && playerBottomY > centreIsland.getAnchorLocation().getY() - centreIsland.getHeight()){
            this.setAnchorLocationX(centreIsland.getAnchorLocation().getX() + (centreIsland.getWidth() / 2) + 1);
        }
    }

    private void handleMovingPlatformCollision(List<Collider> list) {
        MovingPlatform movingPlatform = getFirstOfCollidedClasses(list, MovingPlatform.class);
        double playerBottomY = this.getAnchorLocation().getY() + getHeight() - 10;
        double platformTopY = movingPlatform.getAnchorLocation().getY();

        if (playerBottomY > platformTopY && playerBottomY < platformTopY + movingPlatform.getHeight()) {
            this.setAnchorLocationY(movingPlatform.getAnchorLocation().getY() - getHeight());
        }
        else if(playerBottomY < platformTopY) {
            this.isGrounded = true;
            if(!areControlsBlocked()) {
                moveWithMovingPlatform(movingPlatform);
            }
        }
    }

    private void handleProjectileCollision(List<Collider> list) {
        IProjectile collidedProjectile = getFirstOfCollidedClasses(list, Projectile.class);
        ProjectileWeapon weaponThatShotProjectile = collidedProjectile.getProjectileWeapon();

        if(weaponThatShotProjectile != this.weapon.orElse(null)) {
            this.movementTimer.reset();
            this.setControlsBlocked(true);
            addDamage(weaponThatShotProjectile.getDamage());
            doKnockback(weaponThatShotProjectile.getKnockback(), collidedProjectile.getDirection());

            playerStatusIndicator.updateStatus(this);
            collidedProjectile.remove();
            // Do something with the given projectile
        }
    }

    private void doKnockback(int knockback, double direction) {
        // To do: add knockback block timer

        float defaultAmount = 1; // Default 100%
        float knockbackMultiplier = defaultAmount + ((float) damageTakenMultiplier / 100); // default + multiplier
        float knockbackPerformed = knockback * knockbackMultiplier; // calculate force with multiplier
        setMotion(knockbackPerformed, direction);
    }

    private void addDamage(int damage) {
        damageTakenMultiplier += Math.max(damage, 0);
    }

    private void moveWithMovingPlatform(MovingPlatform movingPlatform) {
        setMotion(movingPlatform.getSpeed(), movingPlatform.getDirection());
    }

    protected void attack() {
        Coordinate2D playerLocation = getAnchorLocation();
        Coordinate2D weaponRelativePosition = getWeaponPosition();
        Coordinate2D weaponPosition = new Coordinate2D(
                playerLocation.getX() + weaponRelativePosition.getX(),
                (playerLocation.getY()) + weaponRelativePosition.getY()
        );

        weapon.ifPresent(value -> value.attack(getAttackDirection(), weaponPosition));
    }

    @Override
    public void respawn() {
        double centralIslandSpawnY = centreIsland.getAnchorLocation().getY() - centreIsland.getHeight() - this.getHeight();
        setAnchorLocation(new Coordinate2D(islandScene.getWidth() / 2, centralIslandSpawnY));
    }

    @Override
    protected void setupEntities() {
        addEntity(character);
        addEntity(playerTag);
        weapon.ifPresent(this::addEntity); // Method reference to create compact lambda expressions
    }

    @Override
    public void notifyBoundaryTouching(SceneBorder sceneBorder) {
        // Check which scene border has been touched
        // If below, lose lives

        if(sceneBorder == SceneBorder.BOTTOM) {
            decreateLives(1);
            this.damageTakenMultiplier = 0;
            respawn();
        }

        // Change position if user leaves the side of the screen
        if(sceneBorder == SceneBorder.LEFT || sceneBorder == SceneBorder.RIGHT) {
            double x = (sceneBorder == SceneBorder.LEFT ? islandScene.getWidth() - getWidth() : 0);
            double y = getAnchorLocation().getY();
            setAnchorLocation(new Coordinate2D(x, y));
        }
    }

    public double getAttackDirection() {
        return attackDirection;
    }

    public void setAttackDirection(double direction) {
        attackDirection = direction;
        weapon.ifPresent(weapon -> weapon.setAttackDirection(direction));
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

    @Override
    public void setupTimers() {
        addTimer(movementTimer);
    }
}
