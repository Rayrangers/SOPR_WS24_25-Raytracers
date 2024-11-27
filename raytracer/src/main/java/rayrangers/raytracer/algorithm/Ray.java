package rayrangers.raytracer.algorithm;

import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.math.Vertex3D;

/**
 * Represents a ray with an origin
 */
public class Ray {

    /**
     * Origin of the ray as a vertex.
     */
    private Vertex3D origin;

    /**
     * Direction of the ray as a vector.
     */
    private Vector3D direction;

    /**
     * Creates a new ray.
     * 
     * @param origin    Origin of ray
     * @param direction Direction of ray
     */
    public Ray(Vertex3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

    /**
     * Returns the origin of the ray.
     * 
     * @return origin of the ray
     */
    public Vertex3D getOrigin() {
        return origin;
    }

    /**
     * Returns the direction of the ray
     * 
     * @return Vector representing direction of ray
     */
    public Vector3D getDirection() {
        return direction;
    }

    /**
     * Calculates the vertex for a given ray parameter t
     * using the equation p(t) = origin + t * direction
     * 
     * @param t Ray parameter t
     * @return vertex
     */
    public Vertex3D pointAt(double t) {
        return new Vertex3D(origin.getlocationVector().add(direction.mult(t)));
    }
}
