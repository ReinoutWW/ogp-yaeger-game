package brawlhalla.yaegerExtension;

import com.github.hanyaeger.api.entities.Direction;

public class DirectionHelper {
    public static boolean isBetweenCoordinates(double min, double max, double value) {
        return (value >= min && value <= max);
    }

    /**
     * Will calculate if the direction is between Direction.UP_LEFT and Direction.DOWN_LEFT
     * @param value the direction
     * @return if true
     */
    public static boolean isLeft(double value) {
        return DirectionHelper.isBetweenCoordinates(Direction.UP_LEFT.getValue(), Direction.DOWN_LEFT.getValue(), value);
    }

    /**
     * Will calculate if the direction is between Direction.DOWN_RIGHT and Direction.UP_RIGHT.getValue()
     * @param value the direction
     * @return if true
     */
    public static boolean isRight(double value) {
        return DirectionHelper.isBetweenCoordinates(Direction.DOWN_RIGHT.getValue(), Direction.UP_RIGHT.getValue(), value);
    }
}
