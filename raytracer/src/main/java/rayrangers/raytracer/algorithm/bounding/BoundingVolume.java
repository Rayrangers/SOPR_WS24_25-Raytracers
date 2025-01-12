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
     * Left child of a BoundingVolume node.
     * Can either be an inner node of type BoundingVolume or a primitive Face (leaf
     * node representation).
     * If there is none, its value is null.
     */
    private Hittable left;

    /**
     * Right child of a BoundingVolume node.
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

    public BoundingVolume(List<Face> faces, int axis) {
        this.faces = faces;
        // Create left and right BoundingVolumes
        create(axis);
    }

    /**
     * 
     */
    public void create(int axis) {
        if (faces.size() == 1) { // BoundingVolume only contains one face
            left = faces.get(0); // Final leaf with Face object
            right = null;
            boundingBox = new BoundingBox(faces.get(0));
        } else {

            // Sort faces by the center of the chosen axis
            faces.sort((face1, face2) -> {
                double center1 = face1.getCenter().getCoord(axis + 1); // getCoord expects a number between 1 and 3
                double center2 = face2.getCenter().getCoord(axis + 1); // getCoord expects a number between 1 and 3
                return Double.compare(center1, center2);
            });

            // Split faces sorted along the chosen axis in the middle
            int mid = faces.size() / 2;
            List<Face> leftFaces = faces.subList(0, mid);
            List<Face> rightFaces = faces.subList(mid, faces.size());

            left = new BoundingVolume(leftFaces, (axis + 1) % 3); // Create left BoundingVolume
            right = new BoundingVolume(rightFaces, (axis + 1) % 3); // Create rigth BoundingVolume

            // Create BoundingBox for the entire BoundingVolume
            boundingBox = BoundingBox.combine(((BoundingVolume) left).boundingBox,
                    ((BoundingVolume) right).boundingBox);
        }
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
                    record.set(lrec);
                } else {
                    record.set(rrec);
                }
                return true;
            } else if (leftHit) {
                record.set(lrec);
                return true;
            } else if (rightHit) {
                record.set(rrec);
                return true;
            }
        }
        return false;
    }

    // public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
    // // Check if the ray intersects the bounding box of the current BoundingVolume
    // if (!boundingBox.hit(ray, t0, t1, record)) {
    // return false;
    // }

    // // Initialize variables to keep track of whether we hit anything
    // boolean hitLeft = false;
    // boolean hitRight = false;

    // // Check for intersection with the left child
    // if (left != null) {
    // hitLeft = left.hit(ray, t0, t1, record);
    // double t1 = record.getT();
    // }

    // // Check for intersection with the right child
    // if (right != null) {
    // hitRight = right.hit(ray, t0, t1, record);
    // double t2 = record.getT();
    // }

    // // Return true if we hit anything
    // return hitLeft || hitRight;
    // }

}
