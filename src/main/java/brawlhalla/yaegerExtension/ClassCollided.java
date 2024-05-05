package brawlhalla.yaegerExtension;

import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.YaegerEntity;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Provide an easier way to check which class has collided
 * Provides:
 * default methods to make life easier
 */
public interface ClassCollided extends Collided {

    /*
     * Return true if the list with colliding objects contains a class of the given type
     * @param collidingObjects list of colliding objects
     * @param classType the given type to check instanceof
     * */
    default boolean hitsClass(List<Collider> collidingObjects, Class<? extends YaegerEntity> classType) {
        return collidingObjects.stream().anyMatch(classType::isInstance);
    }

    /**
     * Returns a list of colliding objects that are an instance of the given class
     * @param collidingObjects list of colliding objects
     * @param classType the given type to check instanceof
     * @return the list of the given classType
     */
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
        // Functional programming
        Optional<Collider> collidedItem = collidingObjects.stream()
                .filter(classType::isInstance)
                .findFirst();

        return collidedItem.map(classType::cast).orElse(null);

        // Or imperative programming:
        // Collider collidedItem = null;
        //for (Collider object : collidingObjects) {
        //    if (classType.isInstance(object)) {
        //        collidedItem = classType.cast(object);
        //        break;
        //    }
        //}
        //return collidedItem;
    }


    /**
     * Will validate if there are any of the type classes in the colliding objects
     * This method utilizes type inference to determine the generic type T based on the provided classType.
     *
     * @param collidingObjects a list of Collider objects, typically provided by han.yaeger onCollision method
     * @param classTypes the class literal of the type T to which the found collider will be cast
     * @return if at least one class matches the type T, or false if no such collide is found
     */
    default boolean hitsClasses(List<Collider> collidingObjects, List<Class<? extends YaegerEntity>> classTypes) {
        for(Class<? extends YaegerEntity> classType : classTypes) {
            if(hitsClass(collidingObjects, classType)) {
                return true;
            }
        }
        return false;
    }
}
