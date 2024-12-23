package rayrangers.raytracer.world;

import java.util.List;
import java.util.UUID;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;
import rayrangers.raytracer.algorithm.bounding.BoundingBox;
import rayrangers.raytracer.math.TrafoMatrix;
import rayrangers.raytracer.math.Vertex3D;

/**
 * Represents an entity (object/mesh) described by a Wavefront OBJ file.
 */
public class Entity implements Hittable, Transformable {

    // TODO: Maybe class TemplateEntity <|---- Entity -------> Hittable, Transformable
    // TODO: Sphere as Entity?
    // TODO: Take a look at how UUIDs are handled (e.g. for template entities, clones etc.)

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

    // TODO: Shift to BVH tree structure
    private BoundingBox testBoundingBox;
    
    /**
     * Class constructor with a given UUID.
     * 
     * @param uuid     UUID of the entity
     * @param name     entity name, null if {@code name == null}
     * @param faces    face list
     * @param vertices vertices
     */
    public Entity(UUID uuid, String name, List<Face> faces, List<Vertex3D> vertices) {
        this.uuid = uuid;
        this.name = name;
        this.faces = faces;
        this.vertices = vertices;
    }

    /**
     * Class constructor without a given UUID.
     * Generates a random UUID for the entity.
     * 
     * @param name     entity name, null if {@code name == null}
     * @param faces    face list
     * @param vertices vertices
     */
    public Entity(String name, List<Face> faces, List<Vertex3D> vertices) {
        this(UUID.randomUUID(), name, faces, vertices);
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
     * @see Hittable
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        boolean hit = false;
        // TODO: Deal with entities without transformations
        if (testBoundingBox != null && !testBoundingBox.hit(ray, t0, t1, record)) {
            return false;
        }
        for (Face face : faces) {
            // Check if the ray hits the face and if t lies within interval [t0,t1]
            if (face.hit(ray, t0, t1, record) && record.getT() <= t1 && record.getT() >= t0) {
                hit = true;
                t1 = record.getT(); // Update t1 to decrease interval [t0,t1]
            }
        }
        return hit;
    }

    /**
     * @see Transformable
     */
    @Override
    public void transform(TrafoMatrix tm) {
        // Set initial minimum and maximum values for the axis aligned bounding box
        double x1min = Double.MAX_VALUE;
        double x1max = Double.MIN_VALUE;
        double x2min = Double.MAX_VALUE;
        double x2max = Double.MIN_VALUE;
        double x3min = Double.MAX_VALUE;
        double x3max = Double.MIN_VALUE;

        for (Vertex3D vertex : vertices) {
            // Transform the vertex first
            vertex.transform(tm);
            
            // Calculate new axis-aligned bounding box
            double vertX1 = vertex.getCoord(1);
            double vertX2 = vertex.getCoord(2);
            double vertX3 = vertex.getCoord(3);

            // TODO: Move to BoundingBox
            if (vertX1 < x1min) {
                x1min = vertX1;
            }
            if (vertX1 > x1max) {
                x1max = vertX1;
            }
            if (vertX2 < x2min) {
                x2min = vertX2;
            }
            if (vertX2 > x2max) {
                x2max = vertX2;
            }
            if (vertX3 < x3min) {
                x3min = vertX1;
            }
            if (vertX3 > x3max) {
                x3max = vertX3;
            }
        }
        testBoundingBox = new BoundingBox(x1min, x1max, x2min, x2max, x3min, x3max);
    }
}