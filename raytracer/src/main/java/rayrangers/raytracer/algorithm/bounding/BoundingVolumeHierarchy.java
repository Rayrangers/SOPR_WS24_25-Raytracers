package rayrangers.raytracer.algorithm.bounding;

import java.util.List;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;
import rayrangers.raytracer.world.Face;
import rayrangers.raytracer.world.Hittable;

/**
 * Represents a bounding volume hgierarchy (BVH tree)
 * that splits the faces of an entity into bounding volumes.
 */
public class BoundingVolumeHierarchy implements Hittable {

    /**
     * Root node of the tree.
     */
    private BoundingVolume root;

    /**
     * Constructs a bounding volume hierarchy (BVH tree) for the given faces.
     * 
     * @param faces faces the BVH tree is constructed for 
     */
    public BoundingVolumeHierarchy(List<Face> faces) {
        // Create the hierarchy recursivel, 
        // starting with the bounding volume for the root node
        root = new BoundingVolume(faces, 0);
    }

    /**
     * @see Hittable
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        // Start traversing the tree at the root node
        return root.hit(ray, t0, t1, record);
    }
}
