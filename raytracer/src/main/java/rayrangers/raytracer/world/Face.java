package rayrangers.raytracer.world;

/**
 * Represents a face described by a Wavefront OBJ file.
 * OBJ file:
 * f <integer v> ...
 * f <integer v>/<integer vt> ...
 * f <integer v>/<integer vt>/<integer vn> ...
 */
public abstract class Face {

    /**
     * Material of the face.
     */
    private Material material;

    /**
     * Smoothing group the face belongs to.
     * OBJ file:
     * s <integer smoothing-group>
     */
    private int smoothing;

    /**
     * Class constructor specifying the material and smoothing group of the face.
     * @param material  material
     * @param smoothing smoothing group, Integer.MIN_VALUE if not specified
     */
    public Face(Material material, int smoothing) {
        this.material = material;
        this.smoothing = smoothing;
    }

    /**
     * Returns the material of the face.
     * @return  material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Returns the smoothing group of the face.
     * @return  smoothing group
     */
    public int getSmoothing() {
        return smoothing;
    }
}