package brawlhalla.player;

import brawlhalla.scenes.IProjectileSpawnableScene;
import brawlhalla.scenes.components.Island;
import brawlhalla.scenes.components.MovingPlatform;
import brawlhalla.player.characters.Character;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import brawlhalla.weapons.*;
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
    private final Coordinate2D WEAPON_POSITION = new Coordinate2D(20, 40);

    private int lives;
    private int damageTakenMultiplier;
    private Character character;
    private final String playerName;
    private PlayerTag playerTag;
    private PlayerScoreStatistics playerScoreStatistics = new PlayerScoreStatistics();
    private PlayerStatusIndicator playerStatusIndicator;
    private IProjectileSpawnableScene islandScene;
    private SpriteEntity centreIsland;
    protected Weapon weapon;
    protected boolean isGrounded;

    public Player(Coordinate2D initialLocation, String name, Character character, PlayerStatusIndicator playerStatusIndicator, IProjectileSpawnableScene islandScene, SpriteEntity centreIsland, Color playerColor) {
        super(initialLocation);
        this.playerStatusIndicator = playerStatusIndicator;
        this.islandScene = islandScene;
        this.centreIsland = centreIsland;
        this.playerName = name;
        this.character = character;
        //this.weapon = character.createDefaultWeapon(new Coordinate2D(25, 10));
        this.weapon = new Pistol(WEAPON_POSITION, islandScene);
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

    public Coordinate2D getWeaponPosition() {
        return WEAPON_POSITION;
    }

    public int getDamageTakenMiltiplier() {
        return damageTakenMultiplier;
    }

    public int getLives() {
        return lives;
    }

    @Override
    public abstract void onPressedKeysChange(Set<KeyCode> pressedKeys);

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
    }

    private void handleIslandCollision() {
        // Island and platform hit
        double playerBottomY = this.getAnchorLocation().getY() + getHeight() - 10;

        this.isGrounded = true;
        setMotion(0, 0d);

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
            moveWithMovingPlatform(movingPlatform);
        }
    }

    private void handleProjectileCollision(List<Collider> list) {
        IProjectile collidedProjectile = getFirstOfCollidedClasses(list, Projectile.class);
        ProjectileWeapon weaponThatShotProjectile = collidedProjectile.getProjectileWeapon();

        if(weaponThatShotProjectile != this.weapon) {
            addDamage(weaponThatShotProjectile.getDamage());
            doKnockback(weaponThatShotProjectile.getKnockback());

            playerStatusIndicator.updateStatus(this);
            // Do something with the given projectile
        }
    }

    private void doKnockback(int knockback) {
        // TO DO: Knockback with the damage taken multiplier
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

        weapon.attack(getDirection(), weaponPosition);
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
            double x = (sceneBorder == SceneBorder.LEFT ? islandScene.getWidth() - getWidth() : 0);
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
    public abstract void CurveMotion();
}
