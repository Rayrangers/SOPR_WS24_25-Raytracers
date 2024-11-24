package rayrangers.raytracer.world;

import java.util.List;

/**
 * Represents a triangle as a special face.
 */
public class Triangle extends Face {

    /**
     * Array containing all vertices of the triangle.
     */
    private Vertex3D[] vertices;

    /**
     * Class constructor specifying the material, smoothing group and the vertices
     * of the triangle.
     * 
     * @param material  material
     * @param smoothing smoothing group
     * @param v1        vertex 1
     * @param v2        vertex 2
     * @param v3        vertex 3
     * @see Face
     */
    public Triangle(Material material, int smoothing, Vertex3D v1, Vertex3D v2, Vertex3D v3) {
        super(material, smoothing);
        vertices = new Vertex3D[] { v1, v2, v3 };
    }

    /**
     * Class constructor called by ObjParser.
     * 
     * @param material  material
     * @param smoothing smoothing group
     * @param vertices  vertex list
     */
    public Triangle(Material material, int smoothing, List<Vertex3D> vertices) {
        super(material, smoothing);
        this.vertices = new Vertex3D[] { vertices.get(0), vertices.get(1), vertices.get(2) };
    }

    /**
     * Returns all vertices of the triangle in an array.
     * 
     * @return vertices
     */
    public Vertex3D[] getAllVert() {
        return vertices;
    }
}