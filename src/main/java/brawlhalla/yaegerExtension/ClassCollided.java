package brawlhalla.yaegerExtension;

import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.YaegerEntity;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ClassCollided extends Collided {

    /*
     *
     * */
    default boolean hitsClass(List<Collider> collidingObjects, Class<? extends YaegerEntity> classType) {
        return collidingObjects.stream().anyMatch(classType::isInstance);
    }

    default List<Collider> getCollidedClasses(List<Collider> collidingObjects, Class<? extends YaegerEntity> classType) {
        return collidingObjects.stream()
                .filter(classType::isInstance)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the first collider from a list that matches the specified type and casts it to that type.
     * This method utilizes type inference to determine the generic type T based on the provided classType.
     *
     * @param <T> the type of the class you want to be returned, extending YaegerEntity
     * @param collidingObjects a list of Collider objects, typically provided by han.yaeger onCollision method
     * @param classType the class literal of the type T to which the found collider will be cast
     * @return the first matching collider cast to type T, or null if no such collider is found
     */
    @Nullable
    default <T extends YaegerEntity> T getFirstOfCollidedClasses(List<Collider> collidingObjects, Class<T> classType) {
        Optional<Collider> collidedItem = collidingObjects.stream()
                .filter(classType::isInstance)
                .findFirst();

        return collidedItem.map(classType::cast).orElse(null);
    }


    default boolean hitsClasses(List<Collider> collidingObjects, List<Class<? extends YaegerEntity>> classTypes) {
        for(Class<? extends YaegerEntity> classType : classTypes) {
            if(hitsClass(collidingObjects, classType)) {
                return true;
            }
        }
        return false;
    }
}
