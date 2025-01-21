package rayrangers.raytracer.world;

import rayrangers.raytracer.math.Vertex3D;

/**
 * Represents a face described by a Wavefront OBJ file.
 * OBJ file:
 * f v ...
 * f v/vt ...
 * f v//vn
 * f v/vt/vn ...
 */
public abstract class Face implements Hittable {

    /**
     * Material of the face.
     */
    protected Material material;

    /**
     * Smoothing group the face belongs to.
     * OBJ file:
     * s smoothing-group
     */
    protected String smoothingGroup;

    /**
     * Constructs a face with the given material and smoothing group.
     * 
     * @param material material
     * @param smoothingGroup smoothing group, parser will pass Integer.MIN_VALUE if not specified
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
     * Checks if another face is in the same smoothing group.
     * 
     * @param otherFace face to compare
     * @return boolean result
     */
    public boolean isInSameSmoothinggroup(Face otherFace) {
        return smoothingGroup.equals(otherFace.getSmoothingGroup());
    }

    /**
     * Returns all vertices of the face in an array.
     * 
     * @return vertices
     */
    public abstract Vertex3D[] getAllVert();

    /**
     * Calculates the center point of a face.
     * 
     * @return center point
     */
    public abstract Vertex3D getCenter();
}