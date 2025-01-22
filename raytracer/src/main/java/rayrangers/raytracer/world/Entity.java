package rayrangers.raytracer.world;

import java.util.List;
import java.util.UUID;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;
import rayrangers.raytracer.algorithm.bounding.BoundingVolumeHierarchy;
import rayrangers.raytracer.math.TrafoMatrix;
import rayrangers.raytracer.math.Vertex3D;

/**
 * Represents an entity (object/mesh) described by a Wavefront OBJ file.
 */
public class Entity implements Hittable, Transformable {

    // TODO: Maybe class TemplateEntity <|---- Entity -------> Hittable,Transformable
    // TODO: Take a look at how UUIDs are handled (e.g. for template entities, clones etc.)

    /**
     * Randomly generated UUID of the entity.
     * Used in the JSON file for the specification of the scene.
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
     * BVH tree associated with the entity.
     */
    private BoundingVolumeHierarchy bvhTree;

    /**
     * Constructs an entity with the specified name, faces and vertices.
     * The entity is identified with the given UUID.
     * 
     * @param uuid UUID of the entity
     * @param name entity name, null if {@code name == null}
     * @param faces list of faces
     * @param vertices list of vertices
     */
    public Entity(UUID uuid, String name, List<Face> faces, List<Vertex3D> vertices) {
        this.uuid = uuid;
        this.name = name;
        this.faces = faces;
        this.vertices = vertices;
    }

    /**
     * Constructs an entity with the specified name, faces and vertices,
     * but without a given UUID. Generates a random UUID for the entity instead.
     * 
     * @param name entity name, null if {@code name == null}
     * @param faces list of faces
     * @param vertices list of vertices
     */
    public Entity(String name, List<Face> faces, List<Vertex3D> vertices) {
        this(UUID.randomUUID(), name, faces, vertices);
    }

    /**
     * Returns a list of all faces the entity is composed of.
     * 
     * @return list of faces
     */
    public List<Face> getFaces() {
        return faces;
    }

    /**
     * Returns a list of all unique vertices of the entity.
     * 
     * @return list if vertices
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
     * @see Hittable
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        boolean hit = false;
        // TODO: Deal with entities without transformations

        // Check if the ray hits anything in the BVH tree
        // and if t lies within interval [t0,t1]
        if (bvhTree != null && bvhTree.hit(ray, t0, t1, record) && record.getT() <= t1 && record.getT() >= t0) {
            hit = true;
        }
        return hit;
    }

    /**
     * @see Transformable
     */
    @Override
    public void transform(TrafoMatrix tm) {
        // Transform all vertices of the entity
        for (Vertex3D vertex : vertices) {
            vertex.transform(tm);
        }
        bvhTree = new BoundingVolumeHierarchy(faces);
    }
}