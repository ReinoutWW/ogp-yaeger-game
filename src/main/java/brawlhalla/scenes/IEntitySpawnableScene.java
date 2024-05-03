package brawlhalla.scenes;

import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.scenes.YaegerScene;

public interface IEntitySpawnableScene extends YaegerScene {
    void addEntityToSpawn(YaegerEntity entity);
}
