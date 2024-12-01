package rayrangers.raytracer.world;

/**
 * Represents a face described by a Wavefront OBJ file.
 * OBJ file:
 * f <integer v> ...
 * f <integer v>/<integer vt> ...
 * f <integer v>/<integer vt>/<integer vn> ...
 */
public abstract class Face implements Hittable {

    /**
     * Material of the face.
     */
    private Material material;

    /**
     * Smoothing group the face belongs to.
     * OBJ file:
     * s <integer smoothing-group>
     */
    private String smoothingGroup;

    /**
     * Class constructor specifying the material and smoothing group of the face.
     * 
     * @param material  material
     * @param smoothing smoothing group, parser will pass Integer.MIN_VALUE if not
     *                  specified
     */
    public Face(Material material, String smoothingGroup) {
        this.material = material;
        this.smoothingGroup = smoothingGroup;
    }

    /**
     * Returns the material of the face.
     * 
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Returns the smoothing group of the face.
     * 
     * @return smoothing group
     */
    public String getSmoothingGroup() {
        return smoothingGroup;
    }

    /**
     * Checks if another Face is in the same smoothing group.
     * 
     * @param otherFace Face to compare
     * @return result
     */
    public boolean isInSameSmoothinggroup(Face otherFace) {
        return smoothingGroup.equals(otherFace.getSmoothingGroup());
    }
}