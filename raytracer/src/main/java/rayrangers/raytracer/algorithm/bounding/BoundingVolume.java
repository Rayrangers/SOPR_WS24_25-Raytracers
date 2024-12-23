package rayrangers.raytracer.algorithm.bounding;

import java.util.List;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;
import rayrangers.raytracer.world.Face;
import rayrangers.raytracer.world.Hittable;

/**
 * 
 */
public class BoundingVolume implements Hittable {

    /**
     * Left child of a volume node.
     * Can either be an inner node of type BoundingVolume or a primitive Face (leaf
     * node representation).
     * If there is none, its value is null.
     */
    private Hittable left;

    /**
     * Right child of a volume node.
     * Can either be an inner node of type BoundingVolume or a primitive Face (leaf
     * node representation).
     * If there is none, its value is null.
     */
    private Hittable right;

    /**
     * 
     */
    private BoundingBox boundingBox;

    /**
     * 
     */
    private List<Face> faces;

    public void create(int axis) {
        // TODO
    }

    /**
     * @see Hittable
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        if (boundingBox.hit(ray, t0, t1, record)) {
            HitRecord lrec = new HitRecord();
            HitRecord rrec = new HitRecord();
            boolean leftHit = (left != null) && left.hit(ray, t0, t1, lrec);
            boolean rightHit = (right != null) && right.hit(ray, t0, t1, rrec);

            if (leftHit && rightHit) {
                if (lrec.getT() < rrec.getT()) {
                    record = lrec;
                } else {
                    record = rrec;
                }
                return true;
            } else if (leftHit) {
                record = lrec;
                return true;
            } else if (rightHit) {
                record = rrec;
                return true;
            }
        }
        return false;
    }

}
