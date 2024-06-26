package brawlhalla.scenes;

import brawlhalla.Brawhalla;
import brawlhalla.Scenes;
import brawlhalla.player.Player;
import brawlhalla.player.PlayerMovementConfiguration;
import brawlhalla.player.characters.FoxCharacter;
import brawlhalla.player.characters.ManCharacter;
import brawlhalla.powerup.DamageBoostPowerUp;
import brawlhalla.powerup.SpeedBoostPowerUp;
import brawlhalla.scenes.components.Island;
import brawlhalla.scenes.components.MovingPlatform;
import brawlhalla.player.characters.CactiCharacter;
import brawlhalla.scenes.components.playerStatusIndicator.PlayerStatusIndicator;
import brawlhalla.spawners.PowerUpSpawner;
import brawlhalla.spawners.ProjectileSpawner;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.scenes.DynamicScene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import com.github.hanyaeger.api.*;

public class IslandScene extends DynamicScene implements EntitySpawnerContainer, IEntitySpawnableScene {
    private ProjectileSpawner projectileSpawner;
    private final Brawhalla brawhalla;
    private Player player1;
    private Player player2;


    public IslandScene(Brawhalla brawhalla) {
        this.brawhalla = brawhalla;
    }

    public void setActiveScene(Scenes scene) {
        brawhalla.setActiveScene(scene.index);
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/LevelBackground.png");
    }

    /**
     * sets up the entities for the IslandScene
     * instantiates the playerCharacters using the character selection of the startScene
     */
    @Override
    public void setupEntities() {
        var island = new Island(new Coordinate2D(getWidth() / 2 , getHeight() - 50) );
        var platform1 = new MovingPlatform(new Coordinate2D(getWidth() / 6 , 350) );
        var platform2 = new MovingPlatform(new Coordinate2D(getWidth() - (getWidth() / 3) , 350) );
        var playerStatusIndicator1 = new PlayerStatusIndicator(new Coordinate2D(getWidth() - 100, 10), Color.RED);
        var playerStatusIndicator2 = new PlayerStatusIndicator(new Coordinate2D(50, 10), Color.BLUE);

        player1 = new Player(
                new Coordinate2D(getWidth() / 2, getHeight() /2),
                "RG1",
                brawhalla.getStartScene().getCharacterRoster().getSelectedCharacterPlayer1(),
                playerStatusIndicator1,
                this,
                island,
                Color.RED,
                new PlayerMovementConfiguration(
                        KeyCode.UP,
                        KeyCode.DOWN,
                        KeyCode.LEFT,
                        KeyCode.RIGHT,
                        KeyCode.CONTROL,
                        KeyCode.SHIFT
                )
        );

        player2 = new Player(
                new Coordinate2D(getWidth() / 2 + 30, getHeight() /2),
                "RG2",
                brawhalla.getStartScene().getCharacterRoster().getSelectedCharacterPlayer2(),
                playerStatusIndicator2,
                this,
                island,
                Color.BLUE,
                new PlayerMovementConfiguration(
                        KeyCode.W,
                        KeyCode.S,
                        KeyCode.A,
                        KeyCode.D,
                        KeyCode.Q,
                        KeyCode.E
                )
        );

        var damageBoost = new SpeedBoostPowerUp(new Coordinate2D(getWidth() / 2 - 100, getHeight() / 2));

        addEntity(damageBoost);

        island.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        addEntity(island);
        addEntity(platform1);
        addEntity(platform2);
        addEntity(player1);
        addEntity(player2);
        addEntity(playerStatusIndicator1);
        addEntity(playerStatusIndicator2);
    }

    /**
     * adds projectile to entity spawner to create them on the scene
     * @param projectile
     */
    public void addEntityToSpawn(YaegerEntity projectile) {
        this.projectileSpawner.addEntityToSpawn(projectile);
    }

    @Override
    public void setupEntitySpawners() {
        projectileSpawner = new ProjectileSpawner();
        addEntitySpawner(projectileSpawner);
        addEntitySpawner(new PowerUpSpawner(getWidth(), getHeight()));
    }

    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
}
