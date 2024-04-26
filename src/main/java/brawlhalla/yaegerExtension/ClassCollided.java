package brawlhalla.yaegerExtension;

import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.YaegerEntity;
import java.util.List;

public interface ClassCollided extends Collided {

    /*
     *
     * */
    default boolean hitsClass(List<Collider> collidingObjects, Class<? extends YaegerEntity> classType) {
        return collidingObjects.stream().anyMatch(classType::isInstance);
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
