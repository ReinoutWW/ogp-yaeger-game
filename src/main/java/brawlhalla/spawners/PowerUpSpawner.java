package brawlhalla.spawners;

import brawlhalla.powerup.DamageBoostPowerUp;
import brawlhalla.powerup.KnockBackBoostPowerUp;
import brawlhalla.powerup.PowerUp;
import brawlhalla.powerup.SpeedBoostPowerUp;
import brawlhalla.yaegerExtension.CoordinationHelper;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.hanyaeger.api.entities.YaegerEntity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class PowerUpSpawner extends EntitySpawner {
    private double width;
    private double height;

    public PowerUpSpawner(double width, double height) {
        super(3500);
        this.width = width;
        this.height = height;
    }

    @Override
    protected void spawnEntities() {
        int random = new Random().nextInt(0, 2);
        Coordinate2D randomCords = CoordinationHelper.getRandomLocation((int)width, (int)height, 20);
        PowerUp powerUp;

        switch (random) {
            case 0:
                powerUp = new DamageBoostPowerUp(randomCords);
                break;
            case 1:
                powerUp = new KnockBackBoostPowerUp(randomCords);
                break;
            case 2:
            default:
                powerUp = new SpeedBoostPowerUp(randomCords);
                break;
        }

        spawn(powerUp);
    }
}
