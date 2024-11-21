package rayrangers.raytracer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents an entity (object/mesh) described by a Wavefront OBJ file.
 */
public class Entity {

    /**
     * Ramdomly generated UUID of the entity.
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
     * Class constructor specifying the entity name and the faces.
     * @param name  entity name, "unknown" if {@code name == null}
     * @param faces face list
     */
    public Entity(String name, List<Face> faces) {
        uuid = UUID.randomUUID();
        this.name = (name == null) ? "unknown" : name;
        this.faces = new ArrayList<>(faces);
    }

    /**
     * Adds a face to the entity.
     * @param face  face to be added to the end of the face list
     */
    public void addFace(Face face) {
        faces.add(face);
    }

    /**
     * Returns a list of all faces the entity is composed of.
     * @return  face list
     */
    public List<Face> getAllFaces() {
        return faces;
    }

    /**
     * Returns the entity name.
     * @return  entity name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the UUID of the entity.
     * @return  UUID
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Sets all faces of the entity.
     * @param faces face list
     */
    public void setAllFaces(List<Face> faces) {
        this.faces = new ArrayList<>(faces);
    }

    /**
     * Sets the entity name.
     * @param name  entity name
     */
    public void setName(String name) {
        this.name = name;
    }
}