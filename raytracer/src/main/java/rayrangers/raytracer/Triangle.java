package rayrangers.raytracer;

/**
 * Represents a triangle as a special face.
 */
public class Triangle extends Face {

    /**
     * Array containing all vertices of the triangle.
     */
    private Vertex3D[] vertices;

    /**
     * Class constructor specifying the group name, material and the vertices of the triangle.
     * @param group     group name
     * @param material  material
     * @param v1        vertex 1     
     * @param v2        vertex 2
     * @param v3        vertex 3
     * @see Face
     */
    public Triangle(String group, Material material, Vertex3D v1, Vertex3D v2, Vertex3D v3) {
        this(group, material, Integer.MIN_VALUE, v1, v2, v3);
    }

    /**
     * Class constructor specifying the group name, material, smoothing group and the vertices of the triangle.
     * @param group     group name
     * @param material  material
     * @param smoothing smoothing group
     * @param v1        vertex 1
     * @param v2        vertex 2
     * @param v3        vertex 3
     * @see Face
     */
    public Triangle(String group, Material material, int smoothing, Vertex3D v1, Vertex3D v2, Vertex3D v3) {
        super(group == null ? "unknown" : group, material, smoothing);
        vertices = new Vertex3D[] {v1, v2, v3};
    }
    
    /**
     * Returns all vertices of the triangle in an array.
     * @return  vertices
     */
    public Vertex3D[] getAllVert() {
        return vertices;
    }

    /**
     * Sets all vertices of the triangle.
     * @param v1    vertex 1
     * @param v2    vertex 2
     * @param v3    vertex 3
     */ 
    public void setAllVert(Vertex3D v1, Vertex3D v2, Vertex3D v3) {
        vertices = new Vertex3D[] {v1, v2, v3};
    }
}