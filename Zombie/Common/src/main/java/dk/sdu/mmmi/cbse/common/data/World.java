package dk.sdu.mmmi.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class World {

    private WorldMap worldMap;

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();

    public String addEntity(Entity entity) {
        entityMap.put(entity.getId(), entity);
        return entity.getId();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getId());
    }
    
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public List<Entity> getBoss() {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            if (e.getType().equals("boss")) {
                r.add(e);
            }
        }
        return r;
    }


    public List<Entity> getEnemies() {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            if (e.getType().equals("enemy")) {
                r.add(e);
            }
        }
        return r;
    }


    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }
}