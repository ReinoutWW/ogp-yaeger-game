package brawlhalla.player;

import brawlhalla.Scenes;
import brawlhalla.scenes.IEntitySpawnableScene;
import brawlhalla.scenes.components.Island;
import brawlhalla.scenes.components.MovingPlatform;
import brawlhalla.player.characters.Character;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import brawlhalla.timer.MovementTimer;
import brawlhalla.weapons.*;
import brawlhalla.weapons.melee.Melee;
import brawlhalla.weapons.projectiles.IProjectile;
import brawlhalla.weapons.projectiles.Projectile;
import brawlhalla.weapons.projectiles.ProjectileWeapon;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import javafx.scene.input.KeyCode;
import brawlhalla.yaegerExtension.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The main Player logic will be here. This class will provide all collisions, movement and features
 */
public class Player extends DynamicCompositeEntity implements IPlayer, TimerContainer, SceneBorderTouchingWatcher, ClassCollided, Collider {
    private final IMovementConfiguration playerMovementConfiguration;
    private final Coordinate2D WEAPON_POSITION = new Coordinate2D(0, 50);
    private final Character character;
    private final String playerName;
    private final PlayerTag playerTag;
    private final PlayerScoreStatistics playerScoreStatistics = new PlayerScoreStatistics();
    private final PlayerStatusIndicator playerStatusIndicator;
    private final IEntitySpawnableScene islandScene;
    private final SpriteEntity centreIsland;
    private double attackDirection = Direction.RIGHT.getValue();
    private int lives;
    private int damageTakenMultiplier;
    private int speedBoostMultiplier = 100;

    protected Optional<Weapon> weapon;
    protected boolean isGrounded;
    protected final MovementTimer movementTimer;
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
     * Sets direction and speed for the player based on the key press and the current movement status of the player
     * @param pressedKeys
     */
    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if(areControlsBlocked()) {
            return;
        }

        double speedBoostMultiplier = (double)this.speedBoostMultiplier / 100;
        double movementSpeed = 3 * speedBoostMultiplier;

        if (pressedKeys.contains(playerMovementConfiguration.getUp()) && isGrounded) {
            isGrounded = false;
            setMotion(movementSpeed + 2, 180d);
        } else if (pressedKeys.contains(playerMovementConfiguration.getDown()) && !isGrounded) {
            setMotion(movementSpeed, 0d);
        } else if (pressedKeys.contains(playerMovementConfiguration.getLeft())) {
            if (isGrounded) {
                setMotion(movementSpeed, 270d);
            } else if (!isGrounded && getDirection() == 315d) {
                setMotion(movementSpeed, 315d);
            } else if (!isGrounded) {
                if (getDirection() == 0) {
                    setMotion(movementSpeed, 355d);
                } else if (getDirection() < 315d && getDirection() >= 180d) {
                    setMotion(movementSpeed, getDirection() + 5);
                } else if (getDirection() > 315d || getDirection() < 180d) {
                    setMotion(movementSpeed, getDirection() - 5);
                }
            }
        } else if (pressedKeys.contains(playerMovementConfiguration.getRight())) {
            if (getDirection() == 360d) {
                setDirection(0d);
            }
            if (isGrounded) {
                setMotion(movementSpeed, 90d);
            } else if (!isGrounded && getDirection() == 45d) {
                setMotion(movementSpeed, 45d);
            } else if (!isGrounded) {
                if (getDirection() < 45d || getDirection() > 180d) {
                    setMotion(movementSpeed, getDirection() + 5);
                } else if (getDirection() > 45d && getDirection() <= 180d) {
                    setMotion(movementSpeed, getDirection() - 5);
                }
            }
        }

        // Do attack
        if (pressedKeys.contains(playerMovementConfiguration.getAttack())) {
            attack();
        }

        if(pressedKeys.contains(playerMovementConfiguration.getDrop())) {
            //dropWeapon(); TO DO: Fix weapon spawn. Can't do addEntity on runtime..
        }

        double currentDirection = getDirection();

        // Check if the attack is between
        saveAttackDirection(currentDirection);
    }

    /**
     * saves the last pressed direction key for later us in directional attacking
     * @param currentDirection
     */
    private void saveAttackDirection(double currentDirection) {
        if (DirectionHelper.isBetweenCoordinates(Direction.UP_LEFT.getValue(), Direction.DOWN_LEFT.getValue(), currentDirection) ||
                DirectionHelper.isBetweenCoordinates(Direction.DOWN_RIGHT.getValue(), Direction.UP_RIGHT.getValue(), currentDirection)) {
            setAttackDirection(getDirection());
        }
    }

    
    public int getDamageTakenMultiplier() {
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
            spawnableWeapon.startPickupBlockDelay();
            islandScene.addEntityToSpawn(spawnableWeapon);
        }
    }

    /**
     * Checks which object the player collides with and calls the corresponding method
     * @param list
     */
    @Override
    public void onCollision(List<Collider> list) {
        if(hitsClass(list, Island.class)) {
            handleIslandCollision();
        }

        // there can never be more than 1 moving platform in the collided. And if there are, just pick the first.
        // Set user movement as the same as the collided moving platform.
        if(hitsClass(list, MovingPlatform.class) && !areControlsBlocked()) { // <-- instanceof in the background
            handleMovingPlatformCollision(list);
        }

        // Check if there's a Projectile.class in the list
        if(hitsClass(list, Projectile.class)) {
            handleProjectileCollision(list);
        }

        if(hitsClass(list, Weapon.class)) {
            handleWeaponCollision(list);
        }

        if(hitsClass(list, Melee.class)) {
            handleMeleeCollision(list);
        }
    }

    /**
     * handles the player being hit with a melee attack.
     * sets the damage and knockback to the hit player corresponding the used attack
     * sets controlsBlocked to true to block the player from moving temporary after being hit
     * resets the movementTimer to insure the blocked time is always the same
     * updates the playerScoreStatistics
     * @param list
     */
    private void handleMeleeCollision(List<Collider> list) {
        Melee collidedMelee = getFirstOfCollidedClasses(list, Melee.class);

        if(collidedMelee.isDoingDamage() && this.weapon.orElse(null) != collidedMelee) {
            this.movementTimer.reset();
            this.setControlsBlocked(true);
            addDamage(collidedMelee.getDamage());
            doKnockback(collidedMelee.getKnockback(), attackDirection);

            playerScoreStatistics.incrementHitsReceived(1);
            playerStatusIndicator.updateStatus(this);
            // Do something with the given projectile
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

    /**
     * sets the players speed and position to the right values when colliding with the island
     * sets the isGrounded to true to indicate the player is standing on solid ground for movement
     */
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

    /**
     * handles the moving platform collision by setting speed and location
     * calls moveWithMovingPlatform so player movement will be adjusted to the platform
     * sets the isGrounded to true to indicate the player is standing on solid ground for movement
     * @param list
     */
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

    /**
     * handles the player being hit with a projectile attack.
     * sets the damage and knockback to the hit player corresponding the used attack
     * sets controlsBlocked to true to block the player from moving temporary after being hit
     * resets the movementTimer to insure the blocked time is always the same
     * updates the playerScoreStatistics
     * deletes the used projectile after being hit
     * @param list
     */
    private void handleProjectileCollision(List<Collider> list) {
        IProjectile collidedProjectile = getFirstOfCollidedClasses(list, Projectile.class);
        ProjectileWeapon weaponThatShotProjectile = collidedProjectile.getProjectileWeapon();

        if(weaponThatShotProjectile != this.weapon.orElse(null)) {
            this.movementTimer.reset();
            this.setControlsBlocked(true);
            addDamage(weaponThatShotProjectile.getDamage());
            doKnockback(weaponThatShotProjectile.getKnockback(), collidedProjectile.getDirection());

            playerScoreStatistics.incrementHitsReceived(1);
            playerStatusIndicator.updateStatus(this);
            collidedProjectile.remove();
            // Do something with the given projectile
        }
    }

    /**
     * handles the movement of the player being influenced by an attack knockback
     * sets playerScoreStatistics based on knockback
     * @param knockback
     * @param direction
     */
    private void doKnockback(float knockback, double direction) {
        // To do: add knockback block timer

        float defaultAmount = 1; // Default 100%
        float knockbackMultiplier = defaultAmount + ((float) damageTakenMultiplier / 100); // default + multiplier
        float knockbackPerformed = knockback * knockbackMultiplier; // calculate force with multiplier
        setMotion(knockbackPerformed, direction);
    }

    /**
     * increases the damageTakenMultiplier and playerScoreStatistics on the player based on the damage value of the attack
     * @param damage
     */
    private void addDamage(float damage) {
        damageTakenMultiplier += (int) Math.max(damage, 0);
        playerScoreStatistics.incrementDamageReceived((int)damage);
    }

    /**
     * sets the player movement to the value used by the movingPlatform
     * @param movingPlatform
     */
    private void moveWithMovingPlatform(MovingPlatform movingPlatform) {
        setMotion(movingPlatform.getSpeed(), movingPlatform.getDirection());
    }

    /**
     * calls the attack method on the currently used player weapon
     */
    protected void attack() {
        Coordinate2D playerLocation = getAnchorLocation();
        Coordinate2D weaponRelativePosition = WEAPON_POSITION;
        Coordinate2D weaponPosition = new Coordinate2D(
                playerLocation.getX() + weaponRelativePosition.getX(),
                (playerLocation.getY()) + weaponRelativePosition.getY()
        );

        weapon.ifPresent(value -> value.attack(getAttackDirection(), weaponPosition));
    }

    /**
     * handles the replacing of the physical player character after the player dies
     * updates the playerScoreIndicator to reflect fresh life
     */
    @Override
    public void respawn() {
        double centralIslandSpawnY = centreIsland.getAnchorLocation().getY() - centreIsland.getHeight() - this.getHeight() - 10;
        setAnchorLocation(new Coordinate2D(islandScene.getWidth() / 2, centralIslandSpawnY));
        setSpeed(0);
        playerStatusIndicator.updateStatus(this);
        setControlsBlocked(false);
    }


    @Override
    protected void setupEntities() {
        addEntity(character);
        addEntity(playerTag);
        weapon.ifPresent(this::addEntity); // Method reference to create compact lambda expressions
    }

    /**
     * resets player position in case of exiting screen on the sides
     * "kills" the player if the player exits the screen on the bottom
     * @param sceneBorder
     */
    @Override
    public void notifyBoundaryTouching(SceneBorder sceneBorder) {
        // Check which scene border has been touched
        // If below, lose lives

        if(sceneBorder == SceneBorder.BOTTOM) {
            decreaseLives(1);
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
    public void decreaseLives(int amount) {
        if(lives - amount < 0) {
            lives = 0; // In case it would end up below 0
        } else {
            lives -= (lives > 0) ? Math.max(amount, 0) : 0; // Decrease only positive amounts, and fallback to 0.
        }

        playerStatusIndicator.updateStatus(this);
        if(lives < 1) {
            islandScene.setActiveScene(Scenes.END);
        }
    }

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

    @Override
    public void setupTimers() {
        addTimer(movementTimer);
    }

    @Override
    public void increaseWeaponDamage(int percentage) {
        weapon.ifPresent(weapon1 -> weapon1.increaseDamage(percentage));
    }

    @Override
    public void resetWeaponDamage() {
        weapon.ifPresent(Weapon::resetDamage);
    }

    @Override
    public void increaseKnockbackBoost(int percentage) {
        weapon.ifPresent(weapon1 -> weapon1.increaseKnockback(percentage));
    }

    @Override
    public void resetKnockbackBoost() {
        weapon.ifPresent(Weapon::resetKnockback);
    }

    @Override
    public void increaseSpeedBoost(int percentage) {
        speedBoostMultiplier += Math.max(percentage, 0);
    }

    @Override
    public void resetSpeedBoost() {
        speedBoostMultiplier = 100;
    }

    public PlayerScoreStatistics getPlayerScoreStatistics() {
        return playerScoreStatistics;
    }

}
