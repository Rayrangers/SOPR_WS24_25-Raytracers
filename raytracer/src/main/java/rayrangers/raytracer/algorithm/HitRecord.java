package rayrangers.raytracer.algorithm;

import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.world.Hittable;
import rayrangers.raytracer.world.Material;

/**
 * Stores information about the intersection of a ray with a hittable object.
 */
public class HitRecord {

    /**
     * Reference to the hit object.
     */
    private Hittable hitObject;

    /**
     * Point where the hit occurred.
     */
    private Vertex3D hitPoint;

    /**
     * Parameter for ray function.
     */
    private double t;

    /**
     * Reference to material of the hittable object.
     */
    private Material material;

    /**
     * Normal vector of hitObject (optional)
     */
    private Vector3D normalVector;

    /**
     * Direction vector of ray for this hit record.
     */
    private Vector3D viewRayDirection;

    /**
     * All vertices of the hittable object.
     */
    private Vertex3D[] verticesAll;

    /**
     * Returns the point where the hit occurred.
     * 
     * @return Vertex3D of hit point
     */
    public Vertex3D getHitPoint() {
        return hitPoint;
    }

    /**
     * Sets the hit point of the record.
     * 
     * @param hitPoint Vertex3D representing the hit point
     */
    public void setHitPoint(Vertex3D hitPoint) {
        this.hitPoint = hitPoint;
    }

      /**
     * Returns all vertices of the hittable object.
     * 
     * @return Array of vertices
     */
    public Vertex3D[] getAllVert() {
        return verticesAll;
    }

    /**
     * Sets all vertices of the hittable object.
     * 
     * @param verticesAll Array of vertices
     */
    public void setAllVert(Vertex3D[] verticesAll) {
        this.verticesAll = verticesAll;
    }

    /**
     * Returns the ray parameter t.
     * 
     * @return Ray parameter t of record
     */
    public double getT() {
        return t;
    }

    /**
     * Sets the ray parameter t.
     * 
     * @param t Ray parameter
     */
    public void setT(double t) {
        this.t = t;
    }

    /**
     * Returns the material of the record.
     * 
     * @return Material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material of the record.
     * 
     * @param material Material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Returns the normal vector associated with the hittable object (optional)
     * 
     * @return Normal vector (optional)
     */
    public Vector3D getNormalVector() {
        return normalVector;
    }

    /**
     * Sets the normal vector associated with the hittable object (optional)
     * 
     * @param normalVector Vertex3D representing the normal vector
     */
    public void setNormalVector(Vector3D normalVector) {
        this.normalVector = normalVector;
    }

    /**
     * Returns the direction of the view ray associated with this record.
     * 
     * @return View ray direction
     */
    public Vector3D getViewRayDirection() {
        return viewRayDirection;
    }

    /**
     * Sets the direction of the view ray associated with this record.
     * 
     * @param viewRayDirection View ray direction
     */
    public void setViewRayDirection(Vector3D viewRayDirection) {
        this.viewRayDirection = viewRayDirection;
    }

    /**
     * Returns the hittable object stored in record.
     * 
     * @return Hittable object
     */
    public Hittable getHitObject() {
        return hitObject;
    }

    /**
     * /**
     * Sets the hittable object stored in record.
     * 
     * @param hitObject Hittable object
     */
    public void setHitObject(Hittable hitObject) {
        this.hitObject = hitObject;
    }

    /**
     * Sets the fields of this record to the fields of another record.
     * Has to be used as Java does not support pass by reference.
     * 
     * @see <a href="https://stackoverflow.com/a/30520738">Stackoverflow answer</a>
     * 
     * @param other HitRecord to copy fields from
     */
    public void set(HitRecord other) {
        this.t = other.t;
        this.hitPoint = other.hitPoint;
        this.normalVector = other.normalVector;
        this.material = other.material;
        this.viewRayDirection = other.viewRayDirection;
        this.verticesAll = other.verticesAll;
        this.hitObject = other.hitObject;
    }
}
