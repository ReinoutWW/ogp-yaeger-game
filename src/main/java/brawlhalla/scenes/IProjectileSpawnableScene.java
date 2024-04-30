package brawlhalla.scenes;

import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.scenes.YaegerScene;

public interface IProjectileSpawnableScene extends YaegerScene {
    void addProjectileToSpawn(YaegerEntity projectile);
}
