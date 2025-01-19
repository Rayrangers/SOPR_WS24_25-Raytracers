package rayrangers.raytracer.algorithm.bounding;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;
import rayrangers.raytracer.world.Hittable;
import rayrangers.raytracer.world.Face;
import rayrangers.raytracer.math.Vertex3D;

/**
 * Represents an axis-aligned bounding box in 3D space.
 * Used to calculate the intersection of a ray with a bounding box 
 * instead of a single face to enhance runtime during rendering.
 */
public class BoundingBox implements Hittable {

    /**
     * Minimum value of bounding box along x1-axis.
     */
    private final double x1min;

    /**
     * Maximum value of bounding box along x1-axis.
     */
    private final double x1max;

    /**
     * Minimum value of bounding box along x2-axis.
     */
    private final double x2min;

    /**
     * Maximum value of bounding box along x2-axis.
     */
    private final double x2max;

    /**
     * Minimum value of bounding box along x3-axis.
     */
    private final double x3min;

    /**
     * Maximum value of bounding box along x3-axis.
     */
    private final double x3max;

    /**
     * Constructs a bounding box with the given minimum and maximum values along all axes. 
     * Only for internal use to combine two bounding boxes.
     * 
     * @param x1min minimum value along x1-axis
     * @param x1max maximum value along x1-axis
     * @param x2min minimum value along x2-axis
     * @param x2max maximum value along x2-axis
     * @param x3min minimum value along x3-axis
     * @param x3max maximum value along x3-axis
     */
    public BoundingBox(double x1min, double x1max, double x2min, double x2max, double x3min, double x3max) {
        this.x1min = x1min;
        this.x1max = x1max;
        this.x2min = x2min;
        this.x2max = x2max;
        this.x3min = x3min;
        this.x3max = x3max;
    }

    /**
     * Constructs a bounding box that encompasses the given face.
     * 
     * @param face face to create the bounding box around
     */
    public BoundingBox(Face face) {
        // Initialise all variables with minimum and maximum double values,
        // representing +/- infinity
        double x1min = Double.MAX_VALUE;
        double x1max = Double.MIN_VALUE;
        double x2min = Double.MAX_VALUE;
        double x2max = Double.MIN_VALUE;
        double x3min = Double.MAX_VALUE;
        double x3max = Double.MIN_VALUE;

        // Compare all vertices of the face
        for (Vertex3D vertex : face.getAllVert()) {

            // Calculate axis-aligned bounding box
            double vertX1 = vertex.getCoord(1);
            double vertX2 = vertex.getCoord(2);
            double vertX3 = vertex.getCoord(3);

            // Compare the vertices with the current minimum and maximum values
            // and update the values if necessary 
            if (vertX1 < x1min) {
                x1min = vertX1;
            }
            if (vertX1 > x1max) {
                x1max = vertX1;
            }
            if (vertX2 < x2min) {
                x2min = vertX2;
            }
            if (vertX2 > x2max) {
                x2max = vertX2;
            }
            if (vertX3 < x3min) {
                x3min = vertX3;
            }
            if (vertX3 > x3max) {
                x3max = vertX3;
            }
        }
        this.x1min = x1min;
        this.x1max = x1max;
        this.x2min = x2min;
        this.x2max = x2max;
        this.x3min = x3min;
        this.x3max = x3max;
    }

    /**
     * @see Hittable
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        // Get x1, x2 and x3 coordinates of ray origin e
        double x1e = ray.getOrigin().getCoord(1);
        double x2e = ray.getOrigin().getCoord(2);
        double x3e = ray.getOrigin().getCoord(3);

        // Get x1, x2 and x3 coordinates of ray direction d and calculate reciprocal
        // Divisions by 0 are handled by IEEE floating point conventions (yields +/- infinity)
        double reciprocalX1d = 1 / ray.getDirection().getCoord(1); // Reciprocal: (1/x1)
        double reciprocalX2d = 1 / ray.getDirection().getCoord(2); // Reciprocal: (1/x2)
        double reciprocalX3d = 1 / ray.getDirection().getCoord(3); // Reciprocal: (1/x3)

        double tx1min;
        double tx1max;
        double tx2min;
        double tx2max;
        double tx3min;
        double tx3max;

        if (reciprocalX1d >= 0) {
            tx1min = (x1min - x1e) * reciprocalX1d;
            tx1max = (x1max - x1e) * reciprocalX1d;
        } else {
            tx1max = (x1min - x1e) * reciprocalX1d;
            tx1min = (x1max - x1e) * reciprocalX1d;
        }

        if (reciprocalX2d >= 0) {
            tx2min = (x2min - x2e) * reciprocalX2d;
            tx2max = (x2max - x2e) * reciprocalX2d;
        } else {
            tx2max = (x2min - x2e) * reciprocalX2d;
            tx2min = (x2max - x2e) * reciprocalX2d;
        }

        if (reciprocalX3d >= 0) {
            tx3min = (x3min - x3e) * reciprocalX3d;
            tx3max = (x3max - x3e) * reciprocalX3d;
        } else {
            tx3max = (x3min - x3e) * reciprocalX3d;
            tx3min = (x3max - x3e) * reciprocalX3d;
        }

        // Check all permutations
        if (tx1min > tx2max || tx2min > tx1max
                || tx1min > tx3max || tx3min > tx1max
                || tx3min > tx2max || tx2min > tx3max) {
            return false;
        }
        return true;
    }

    /**
     * Combines two bounding boxes into one 
     * by taking the minimum and maximum values from both.
     * 
     * @param box1 first box to combine
     * @param box2 second box to combine
     * @return combined bounding box
     */
    public static BoundingBox combine(BoundingBox box1, BoundingBox box2) {
        double newX1Min = Math.min(box1.x1min, box2.x1min);
        double newX1Max = Math.max(box1.x1max, box2.x1max);
        double newX2Min = Math.min(box1.x2min, box2.x2min);
        double newX2Max = Math.max(box1.x2max, box2.x2max);
        double newX3Min = Math.min(box1.x3min, box2.x3min);
        double newX3Max = Math.max(box1.x3max, box2.x3max);

        return new BoundingBox(newX1Min, newX1Max, newX2Min, newX2Max, newX3Min, newX3Max);
    }
}
