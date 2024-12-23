package rayrangers.raytracer.algorithm.bounding;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;
import rayrangers.raytracer.world.Hittable;

/**
 * 
 */
public class BoundingBox implements Hittable {

    /**
     * 
     */
    private final double x1min;

    /**
     * 
     */
    private final double x1max;

    /**
     * 
     */
    private final double x2min;

    /**
     * 
     */
    private final double x2max;

    /**
     * 
     */
    private final double x3min;

    /**
     * 
     */
    private final double x3max;

    /**
     * 
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
     * 
     */
    @Override
    public boolean hit(Ray ray, double t0, double t1, HitRecord record) {
        // Get x1, x2 and x3 coordinates of ray origin e
        double x1e = ray.getOrigin().getCoord(1);
        double x2e = ray.getOrigin().getCoord(2);
        double x3e = ray.getOrigin().getCoord(3);

        // Get x1, x2 and x3 coordinates of ray direction d and calculate reciprocal
        // Divisions by 0 are handled by IEEE floating point conventions (yields +/- Infinity)
        double reciprocalX1d = 1 / ray.getDirection().getCoord(1);
        double reciprocalX2d = 1 / ray.getDirection().getCoord(2);
        double reciprocalX3d = 1 / ray.getDirection().getCoord(3);

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

}
