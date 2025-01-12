package rayrangers.raytracer.algorithm.bounding;

import java.util.List;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;
import rayrangers.raytracer.world.Face;
import rayrangers.raytracer.world.Hittable;

/**
 * 
 */
public class BoundingVolumeHierarchy implements Hittable {

    /**
     * Root node of the tree.
     */
    private BoundingVolume root;

    /**
     * 
     */
    public BoundingVolumeHierarchy(List<Face> faces) {
        root = new BoundingVolume(faces, 0);
    }

    /**
     * @see Hittable
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        // Start traversing the tree
        return root.hit(ray, t0, t1, record);
    }
}
