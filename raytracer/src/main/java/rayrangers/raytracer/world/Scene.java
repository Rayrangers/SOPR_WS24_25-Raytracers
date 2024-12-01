package rayrangers.raytracer.world;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;

/**
 * Represents a scene to be rendered.
 */
public class Scene implements Hittable {

    /**
     * Background color of the scene.
     */
    private Color backgroundColor;

    /**
     * Collection of entities
     */

    private Map<UUID, Entity> entities = new HashMap<>();;

    /**
     * Collection of cameras to capture the scene.
     */
    private Map<UUID, Camera> cameras = new HashMap<>();;

    /**
     * 
     */
    private Map<UUID, LightSource> lightSources = new HashMap<>();;

    public Scene(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
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

    /**
     * Adds a camera with a unique identifier if it has not been added yet.
     * 
     * @param camera Camera to add
     * @return Returns true if camera has not been added yet, else false.
     */
    public boolean addCamera(Camera camera) {
        return cameras.putIfAbsent(camera.getUuid(), camera) == null;
    }

    /**
     * @see Hittable
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        boolean hit = false;
        // Iterate over entities
        for (Entity entity : entities.values()) {
            // Check if the ray hits the entity and if t lies within interval [t0,t1]
            if (entity.hit(ray, t0, t1, record) && record.getT() <= t1 && record.getT() >= t0) {
                hit = true;
                t1 = record.getT(); // Update t1 to decrease interval [t0,t1]
            }
        }
        // Iterate over lightsources
        for (LightSource lightSource : lightSources.values()) {
            // Check if the ray hits the lightsource and if t lies within interval [t0,t1]
            if (lightSource.hit(ray, t0, t1, record) && record.getT() <= t1 && record.getT() >= t0) {
                hit = true;
                t1 = record.getT(); // Update t1 to decrease interval [t0,t1]
            }
        }
        return hit;
    }
}
