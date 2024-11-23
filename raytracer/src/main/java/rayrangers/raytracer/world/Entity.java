package rayrangers.raytracer.world;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import rayrangers.raytracer.math.Vector3D;

/**
 * Represents an entity (object/mesh) described by a Wavefront OBJ file.
 */
public class Entity {

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
     * Position of the entity in the world coordinate system.
     */
    private Vertex3D worldPosition;

    /**
     * Class constructor specifying the faces.
     * 
     * @param faces face list
     */
    public Entity(List<Face> faces) {
        this(null, faces);
    }

    /**
     * Class constructor specifying the name and the faces.
     * 
     * @param name  entity name, null if {@code name == null}
     * @param faces face list
     */
    public Entity(String name, List<Face> faces) {
        this(name, faces, null);
    }

    /**
     * Class constructor specifying the name, the faces and the world position.
     * 
     * @param name          entity name, null if {@code name == null}
     * @param faces         face list
     * @param worldPosition position in the world coordinate system
     */
    public Entity(String name, List<Face> faces, Vertex3D worldPosition) {
        uuid = UUID.randomUUID();
        this.name = name;
        this.faces = new ArrayList<>(faces);
        this.worldPosition = worldPosition;
    }

    /**
     * Returns a list of all faces the entity is composed of.
     * 
     * @return face list
     */
    public List<Face> getAllFaces() {
        return faces;
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
        // TODO
        return null;
    }
}