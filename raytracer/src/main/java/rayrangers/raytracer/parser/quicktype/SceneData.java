package rayrangers.raytracer.parser.quicktype;

import com.fasterxml.jackson.annotation.JsonProperty;
import rayrangers.raytracer.world.Entity;

import java.util.List;

public class SceneData {
    // List of entities to be rendered (e.g., objects like cubes, spheres)
    @JsonProperty("renderentity")
    private List<Entity> renderEntity;

    // List of light sources in the scene
    @JsonProperty("lightentity")
    private List<Entity> lightEntity;

    // List of camera entities used for rendering the scene
    @JsonProperty("cameraentity")
    private List<Entity> cameraEntity;

    // List of resource objects, such as paths to assets like models or materials
    @JsonProperty("resources")
    private List<Resource> resources;

    // Metadata containing global scene settings, like background color or rendering mode
    @JsonProperty("metadata")
    private Metadata metadata;

    // Getter for render entities
    public List<Entity> getRenderEntity() { return renderEntity; }

    // Getter for light entities
    public List<Entity> getLightEntity() { return lightEntity; }

    // Getter for camera entities
    public List<Entity> getCameraEntity() { return cameraEntity; }

    // Getter for resources
    public List<Resource> getResources() { return resources; }
