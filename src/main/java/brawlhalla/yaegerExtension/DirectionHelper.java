package brawlhalla.yaegerExtension;

import com.github.hanyaeger.api.entities.Direction;

public class DirectionHelper {
    public static boolean isBetweenCoordinates(double min, double max, double value) {
        return (value >= min && value <= max);
    }

    public static boolean isLeft(double value) {
        return DirectionHelper.isBetweenCoordinates(Direction.UP_LEFT.getValue(), Direction.DOWN_LEFT.getValue(), value);
    }

    public static boolean isRight(double value) {
        return DirectionHelper.isBetweenCoordinates(Direction.DOWN_RIGHT.getValue(), Direction.UP_RIGHT.getValue(), value);
    }
}
