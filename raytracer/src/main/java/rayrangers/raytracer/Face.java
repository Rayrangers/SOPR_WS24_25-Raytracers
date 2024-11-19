package rayrangers.raytracer;

/**
 * Represents a face described by a Wavefront OBJ file.
 * OBJ file:
 * f <integer v> ...
 * f <integer v>/<integer vt> ...
 * f <integer v>/<integer vt>/<integer vn> ...
 */
public abstract class Face {

    /**
     * Group name the face belongs to.
     * OBJ file:
     * g <string group-name>
     */
    private String group;

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
     * Class constructor specifying the group, material and smoothing group of the face.
     * @param group     group, "unknown" if {@code group == null}
     * @param material  material
     * @param smoothing smoothing group, Integer.MIN_VALUE if not specified
     */
    public Face(String group, Material material, int smoothing) {
        this.group = group;
        this.material = material;
        this.smoothing = smoothing;
    }

    /**
     * Returns the group name of the face.
     * @return  group name
     */
    public String getGroup() {
        return group;
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

    /**
     * Sets the group name of the face.
     * @param group group name
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Sets the material of the face.
     * @param material  material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Sets the smoothing group of the face.
     * @param smoothing smoothing group
     */
    public void setSmoothing(int smoothing) {
        this.smoothing = smoothing;
    }
}