package rayrangers.raytracer.world;

import rayrangers.raytracer.math.TrafoMatrix;

/**
 * Interface for all transformable objects.
 * Transformable embraces translatable, rotatable and scalable.
 */
public interface Transformable {

    /**
     * Transforms the object according to the given cumulated transformation matrix.
     * 
     * @param tm cumulated transformation matrix
     */
    void transform(TrafoMatrix tm);
}
