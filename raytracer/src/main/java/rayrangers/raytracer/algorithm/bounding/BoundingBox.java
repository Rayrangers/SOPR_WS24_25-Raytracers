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
    private final double x2min;
    private final double x2max;
    private final double x3min;
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

        // Get x1, x2 and x3 coordinates of ray direction d
        double x1d = ray.getDirection().getCoord(1);
        double x2d = ray.getDirection().getCoord(2);
        double x3d = ray.getDirection().getCoord(3);

        double tx1min = (x1min - x1e) / x1d;
        double tx1max = (x1max - x1e) / x1d;
        double tx2min = (x2min - x2e) / x2d;
        double tx2max = (x2max - x2e) / x2d;
        double tx3min = (x3min - x3e) / x3d;
        double tx3max = (x3max - x3e) / x3d;

        // Check if swapping is necessary due to a negative ray direction
        if (tx1min > tx1max) {
            double tmp = tx1min;
            tx1min = tx1max;
            tx1max = tmp;
        }

        if (tx2min > tx2max) {
            double tmp = tx2min;
            tx2min = tx2max;
            tx2max = tmp;
        }

        if (tx3min > tx3max) {
            double tmp = tx3min;
            tx3min = tx3max;
            tx3max = tmp;
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
