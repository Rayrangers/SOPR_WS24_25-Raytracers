package rayrangers.raytracer.world;

import java.util.List;
import java.util.UUID;
import rayrangers.raytracer.math.Vector3D;

/**
 * Represents an entity (object/mesh) described by a Wavefront OBJ file.
 */
public class Entity {

    // TODO: Sphere / Interface Hittable?

    /**
     * Randomly generated UUID of the entity.
     * Used in the JSON file for the specification of the scene view.
     */
    private UUID uuid;

    /**
     * Entity name.
     * OBJ file:
     * o <string entity-name>
     */
    private String name;

    /**
     * List containing all faces the entity is composed of.
     */
    private List<Face> faces;

    /**
     * List containing all unique vertices of the entity.
     */
    private List<Vertex3D> vertices;

    // TODO: Extend by Rotation and Scaling?
    /**
     * Position of the entity in the world coordinate system.
     */
    private Vertex3D worldPosition;

    /**
     * Class constructor specifying the name, the faces and the world position.
     * 
     * @param name     entity name, null if {@code name == null}
     * @param faces    face list
     * @param vertices vertices
     */
    public Entity(String name, List<Face> faces, List<Vertex3D> vertices) {
        uuid = UUID.randomUUID();
        this.name = name;
        this.faces = faces;
        this.vertices = vertices;
    }

    /**
     * Returns a list of all faces the entity is composed of.
     * 
     * @return face list
     */
    public List<Face> getFaces() {
        return faces;
    }

    /**
     * Returns a list of all unique vertices of the entity.
     * 
     * @return vertex list
     */
    public List<Vertex3D> getVertices() {
        return vertices;
    }

    /**
     * Returns the entity name.
     * 
     * @return entity name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the UUID of the entity.
     * 
     * @return UUID
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Returns the position of the entity in the world coordinate system.
     * 
     * @return world position
     */
    public Vertex3D getWorldPosition() {
        return worldPosition;
    }

    /**
     * Sets the entity name.
     * 
     * @param name entity name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Places the entity in the world coordinate system.
     * 
     * @param worldPosition position in the world coordinate system
     * @param rotation      rotation by a vector
     * @param sx1           scaling factor in x1-direction
     * @param sx2           scaling factor in x2-direction
     * @param sx3           scaling factor in x3-direction
     */
    public Entity placeInWorld(Vertex3D worldPosition, Vector3D rotation, double sx1, double sx2, double sx3) {
        // TODO: Transformation from object into world coordinates 
        return null;
    }
}