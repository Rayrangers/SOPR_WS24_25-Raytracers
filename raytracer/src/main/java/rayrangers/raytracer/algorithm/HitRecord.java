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
     * Hit point where the hit occurred.
     */
    private Vertex3D hitPoint;

    /**
     * Parameter for the ray function.
     */
    private double t;

    /**
     * Reference to the material of the hittable object.
     */
    private Material material;

    /**
     * Normal vector of the hit object.
     */
    private Vector3D normalVector;

    /**
     * Direction vector of the ray that hits the hit object.
     */
    private Vector3D viewRayDirection;

    /**
     * All vertices of the hittable object.
     */
    private Vertex3D[] verticesAll;

    /**
     * Returns the point where the hit occurred.
     * 
     * @return vertex of the hit point
     */
    public Vertex3D getHitPoint() {
        return hitPoint;
    }

    /**
     * Sets the hit point of the hit record.
     * 
     * @param hitPoint vertex of the hit point
     */
    public void setHitPoint(Vertex3D hitPoint) {
        this.hitPoint = hitPoint;
    }

    /**
     * Returns all vertices of the hittable object.
     * 
     * @return array of vertices
     */
    public Vertex3D[] getAllVert() {
        return verticesAll;
    }

    /**
     * Sets all vertices of the hittable object.
     * 
     * @param verticesAll array of vertices
     */
    public void setAllVert(Vertex3D[] verticesAll) {
        this.verticesAll = verticesAll;
    }

    /**
     * Returns the ray parameter t.
     * 
     * @return ray parameter t of the hit record
     */
    public double getT() {
        return t;
    }

    /**
     * Sets the ray parameter t.
     * 
     * @param t ray parameter t
     */
    public void setT(double t) {
        this.t = t;
    }

    /**
     * Returns the material of the hit record.
     * 
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material of the hit record.
     * 
     * @param material material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Returns the normal vector associated with the hittable object.
     * 
     * @return normal vector
     */
    public Vector3D getNormalVector() {
        return normalVector;
    }

    /**
     * Sets the normal vector associated with the hittable object.
     * 
     * @param normalVector normal vector
     */
    public void setNormalVector(Vector3D normalVector) {
        this.normalVector = normalVector;
    }

    /**
     * Returns the direction of the view ray associated with the hit record.
     * 
     * @return view ray direction
     */
    public Vector3D getViewRayDirection() {
        return viewRayDirection;
    }

    /**
     * Sets the direction of the view ray associated with the hit record.
     * 
     * @param viewRayDirection view ray direction
     */
    public void setViewRayDirection(Vector3D viewRayDirection) {
        this.viewRayDirection = viewRayDirection;
    }

    /**
     * Returns the hittable object stored in the hit record.
     * 
     * @return hittable object
     */
    public Hittable getHitObject() {
        return hitObject;
    }

    /**
     * Sets the hittable object stored in the hit record.
     * 
     * @param hitObject hittable object
     */
    public void setHitObject(Hittable hitObject) {
        this.hitObject = hitObject;
    }

    /**
     * Sets the fields of the hit record to the fields of another hit record.
     * Has to be used as Java does not support pass-by-reference.
     * 
     * @see <a href="https://stackoverflow.com/a/30520738">Stackoverflow</a>
     * 
     * @param other hit record to copy fields from
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
