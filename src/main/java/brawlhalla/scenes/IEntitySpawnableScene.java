package brawlhalla.scenes;

import brawlhalla.Scenes;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.scenes.YaegerScene;

public interface IEntitySpawnableScene extends YaegerScene {
    void addEntityToSpawn(YaegerEntity entity);
    void setActiveScene(Scenes scene);
}
