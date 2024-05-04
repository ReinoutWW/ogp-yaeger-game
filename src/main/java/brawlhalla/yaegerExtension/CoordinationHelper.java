package brawlhalla.yaegerExtension;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.YaegerEntity;

import java.util.Random;

/**
 * Make life easier, use helper classes.
 * Help generate coordinates
 */
public class CoordinationHelper {
    public static Coordinate2D getRandomLocation(int width, int height, int paddingOffset) {
        Random random = new Random();
        int x = random.nextInt(paddingOffset, width - paddingOffset);
        int y = random.nextInt(paddingOffset, height - paddingOffset);
        return new Coordinate2D(x, y);
    }
}
