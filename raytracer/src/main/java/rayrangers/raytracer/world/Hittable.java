package rayrangers.raytracer.world;

import rayrangers.raytracer.algorithm.HitRecord;
import rayrangers.raytracer.algorithm.Ray;

/**
 * Interface for all objects that are hittable by a ray.
 * 
 * Inspired by:
 * @see <a href="https://raytracing.github.io/books/RayTracingInOneWeekend.html#toc6.3">RayTracingInOneWeekend</a>
 */
public interface Hittable {

    /**
     * Checks if a ray has a hit in the specified interval [t0;t1].
     * 
     * @param ray ray to check for a hit
     * @param t0 minimum ray parameter
     * @param t1 maximum ray parameter
     * @param record hit record
     * @return true if a hit occurred in the interval, otherwise false
     */
    boolean hit(Ray ray, double t0, double t1, HitRecord record);

}
