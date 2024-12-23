package rayrangers.raytracer.algorithm.bounding;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;
import rayrangers.raytracer.world.Hittable;

/**
 * 
 */
public class BoundingVolumeHierarchy implements Hittable {

    /**
     * Root node of the tree.
     * Can either be an inner node of type BoundingVolume or a leaf node of type Face.
     */
    private Hittable root;

    /**
     * @see Hittable
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        // Start traversing the tree
        return root.hit(ray, t0, t1, record);
    }
}
