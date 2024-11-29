package rayrangers.raytracer.world;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a scene to be rendered.
 */
public class Scene {

    /**
     * Background color of the scene.
     */
    private Color backgroundColor;

    /**
     * Collection of entities
     */

    private Map<UUID, Entity> entities;

    /**
     * Collection of cameras to capture the scene.
     */
    private Map<UUID, Camera> cameras;

    public Scene(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        entities = new HashMap<>();
        cameras = new HashMap<>();
    }

    /**
     * Adds a new entity to the entities hash map.
     * 
     * @param entity Entity to add
     */
    public void addEntity(Entity entity) {
        entities.put(entity.getUuid(), entity);
    }

    /**
     * Returns an entity from the hash map for a given UUID
     * 
     * @param uuid UUID to search for
     * @return
     */
    public Entity getEntityByUuid(UUID uuid) {
        return entities.get(uuid);
    }

    /**
     * Returns the background color of the scene.
     * 
     * @return Background color
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Returns the hash map of all entites in the scene.
     * 
     * @return Entities hash map
     */
    public Map<UUID, Entity> getEntities() {
        return entities;
    }

    /**
     * Returns the hash map of all cameras in the scene.
     * 
     * @return Cameras hash map
     */
    public Map<UUID, Camera> getCameras() {
        return cameras;
    }
}
