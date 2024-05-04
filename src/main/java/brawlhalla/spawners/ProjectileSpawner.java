package brawlhalla.spawners;

import com.github.hanyaeger.api.entities.*;

import java.util.Iterator;
import java.util.LinkedList;

public class ProjectileSpawner extends EntitySpawner {
    private LinkedList<YaegerEntity> entitiesToSpawn = new LinkedList<>();

    public ProjectileSpawner() {
        super(100);
    }

    public void addEntityToSpawn(YaegerEntity entity) {
        this.entitiesToSpawn.add(entity);
    }

    /**
     * loops through the list of to spawn projectiles and spawns them
     */
    @Override
    protected void spawnEntities() {
        for (Iterator<YaegerEntity> it = entitiesToSpawn.iterator(); it.hasNext(); ) {
            YaegerEntity entity = it.next();
            spawn(entity);
            it.remove();
        }
    }
}