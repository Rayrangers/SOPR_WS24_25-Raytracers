package rayrangers.raytracer.algorithm;

import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.world.Hittable;
import rayrangers.raytracer.world.Material;

/**
 * 
 */
public class HitRecord {

    /**
     *
     */
    private Hittable hitObject;

    /**
     * 
     */
    private Vertex3D hitPoint;

    /**
     * 
     */
    private double t;

    /**
     * 
     */
    // TODO: Color instead of Material? (because of Lightsource)
    private Material material;

    /**
     * 
     */
    private Vector3D normalVector;

    /**
     * 
     * @return
     */
    public Vertex3D getHitPoint() {
        return hitPoint;
    }

    /**
     * 
     * @param hitPoint
     */
    public void setHitPoint(Vertex3D hitPoint) {
        this.hitPoint = hitPoint;
    }

    /**
     * 
     * @return
     */
    public double getT() {
        return t;
    }

    /**
     * 
     * @param t
     */
    public void setT(double t) {
        this.t = t;
    }

    /**
     * 
     * @return
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * 
     * @param material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * 
     * @return
     */
    public Vector3D getNormalVector() {
        return normalVector;
    }

    /**
     * 
     * @param normalVector
     */
    public void setNormalVector(Vector3D normalVector) {
        this.normalVector = normalVector;
    }

    /**
     * 
     */
    public Hittable getHitObject() {
        return hitObject;
    }

    /**
     * 
     */
    public void setHitObject(Hittable hitObject) {
        this.hitObject = hitObject;
    }
}
