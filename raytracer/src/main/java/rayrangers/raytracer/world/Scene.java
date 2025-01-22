package rayrangers.raytracer.world;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;

/**
 * Represents the scene to be rendered.
 */
public class Scene implements Hittable {

    /**
     * Background color of the scene.
     */
    private Color backgroundColor;

    /**
     * Map of entities contained in the scene.
     */

    private Map<UUID, Entity> entities = new HashMap<>();;

    /**
     * Map of cameras to capture the scene.
     */
    private Map<UUID, Camera> cameras = new HashMap<>();;

    /**
     * Map of light sources that illuminate the scene.
     */
    private Map<UUID, LightSource> lightSources = new HashMap<>();;

    /**
     * Constructs a scene with the specified background color.
     * 
     @param backgroundColor background color
     */
    public Scene(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Adds a new entity to the map of entities.
     * 
     * @param entity entity to add
     */
    public void addEntity(Entity entity) {
        entities.put(entity.getUuid(), entity);
    }

    /**
     * Returns the entity from the map with the given UUID.
     * 
     * @param uuid UUID to search for
     * @return associated entity 
     */
    public Entity getEntityByUuid(UUID uuid) {
        return entities.get(uuid);
    }

    /**
     * Returns the background color of the scene.
     * 
     * @return background color
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Returns a map of all entites in the scene.
     * 
     * @return map of entities
     */
    public Map<UUID, Entity> getEntities() {
        return entities;
    }

    /**
     * Returns a map of all cameras in the scene.
     * 
     * @return map of cameras
     */
    public Map<UUID, Camera> getCameras() {
        return cameras;
    }

    /**
     * Returns a map of all light sources in the scene.
     * 
     * @return map of light sources
     */
    public Map<UUID, LightSource> getLightSources() {
        return lightSources;
    }

    /**
     * Adds a camera with a unique UUID if it has not been added yet.
     * 
     * @param camera camera to add
     * @return true if camera has not been added yet, otherwise false
     */
    public boolean addCamera(Camera camera) {
        return cameras.putIfAbsent(camera.getUuid(), camera) == null;
    }

    /**
     * Adds a light source with a unique UUID if it has not been added yet.
     * 
     * @param lightSource light source to add
     * @return true if light source has not been added yet, otherwise false.
     */
    public boolean addLightSource(LightSource lightSource) {
        return lightSources.putIfAbsent(lightSource.getUuid(), lightSource) == null;
    }

    /**
     * @see Hittable
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        boolean hit = false;
        // Iterate over all entities
        for (Entity entity : entities.values()) {
            // Check if the ray hits the entity and if t lies within interval [t0,t1]
            if (entity.hit(ray, t0, t1, record) && record.getT() <= t1 && record.getT() >= t0) {
                hit = true;
                t1 = record.getT(); // Update t1 to decrease interval [t0,t1]
            }
        }
        return hit;
    }
}
