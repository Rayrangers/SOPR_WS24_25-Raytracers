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
     * Returns the point where the hit occurred.
     * 
     * @return
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
}
