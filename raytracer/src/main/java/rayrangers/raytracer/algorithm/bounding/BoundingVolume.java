package rayrangers.raytracer.algorithm.bounding;

import java.util.List;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;
import rayrangers.raytracer.world.Face;
import rayrangers.raytracer.world.Hittable;

/**
 * Represents a node in a BVH tree.
 */
public class BoundingVolume implements Hittable {

    /**
     * Left child of a bounding volume node.
     * Can either be an inner node of type BoundingVolume or a primitive leaf node of type Face,
     * both of which implement the interface Hittable.
     * If there is none, its value is null.
     */
    private Hittable left;

    /**
     * Right child of a bounding volume node.
     * Can either be an inner node of type BoundingVolume or a primitive leaf node of type Face,
     * both of which implement the interface Hittable.
     * If there is none, its value is null.
     */
    private Hittable right;

    /**
     * Bounding box wrapped around the entire bounding volume.
     */
    private BoundingBox boundingBox;

    /**
     * List of faces contained inside the bounding volume.
     */
    private List<Face> faces;

    /**
     * Constructs a bounding volume with the given list of faces 
     * and the axis they are aligned to.
     * 
     * @param faces list of faces
     * @param axis axis 
     */
    public BoundingVolume(List<Face> faces, int axis) {
        this.faces = faces;
        // Create left and right bounding volumes
        create(axis);
    }

    /**
     * Creates left and right child nodes of the bounding volume recursively.
     * If only one face is left, the child node is set to the face itself, 
     * allowed due to the interface Hittable. 
     * Else, the faces are sorted along the chosen axis,
     * split in the middle and two new bounding boxes are created.
     * 
     * @param axis axis the faces are aligned to
     */
    public void create(int axis) {
        if (faces.size() == 1) { // Bounding volume only contains one face
            left = faces.get(0); // Final leaf with object of type Face
            right = null;
            boundingBox = new BoundingBox(faces.get(0));
        } else {

            // Sort faces by the center of the chosen axis
            faces.sort((face1, face2) -> {
                double center1 = face1.getCenter().getCoord(axis + 1); // Add 1 since getCoord(int dim) expects a number between 1 and 3
                double center2 = face2.getCenter().getCoord(axis + 1); // Add 1 since getCoord(int dim) expects a number between 1 and 3
                return Double.compare(center1, center2);
            });

            // Split faces sorted along the chosen axis in the middle
            int mid = faces.size() / 2;
            List<Face> leftFaces = faces.subList(0, mid);
            List<Face> rightFaces = faces.subList(mid, faces.size());

            left = new BoundingVolume(leftFaces, (axis + 1) % 3); // Create left bounding volume
            right = new BoundingVolume(rightFaces, (axis + 1) % 3); // Create right bounding volume

            // Create bounding box for the entire bounding volume
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

}
