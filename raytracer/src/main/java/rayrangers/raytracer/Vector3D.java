package rayrangers.raytracer;

/**
 * Represents a vector in 3D space.
 */

public class Vector3D {

    /**
     * Array containing the coordinates of the vector (x1/x2/x3-dimension).
     */
    private double[] vertices;

    /**
     * Constructor to initialize the vector with 3 components.
     * @param x1    coordinate of x1-dimension
     * @param x2    coordinate of x2-dimension
     * @param x3    coordinate of x3-dimension
     */
    public Vector3D(double x1, double x2, double x3) {
        this.vertices = new double[]{x1, x2, x3};
    }

    /**
     * Getter for the vertices array.
     */
    public double[] getVertices() {
        return vertices;
    }

    /**
     * Setter for the vertices array.
     */
    public void setVertices(double x1, double x2, double x3) {
        this.vertices = new double[]{x1, x2, x3};
    }

    /**
     * Adds another vector to this vector.
     * @param vec The other vector.
     * @return A new vector representing the sum.
     */
    public Vector3D add(Vector3D vec) {
        return null;
    }

    /**
     * Subtracts another vector from this vector.
     * @param vec The other vector.
     * @return A new vector representing the difference.
     */
    public Vector3D sub(Vector3D vec) {
        return null;
    }

    /**
     * Multiplies this vector by a scalar.
     * @param scalar The scalar value.
     * @return A new vector scaled by the scalar.
     */
    public Vector3D mult(double scalar) {
        return null;
    }

    /**
     * Computes the scalar (dot) product with another vector.
     * @param vec The other vector.
     * @return The scalar product as a double.
     */
    public double scalar(Vector3D vec) {
        return 0;
    }

    /**
     * Computes the scalar triple product with two other vectors.
     * @param vec1 The first vector.
     * @param vec2 The second vector.
     * @return The scalar triple product as a double.
     */
    private double scalarTriple(Vector3D vec1, Vector3D vec2) {
        return 0;
    }

    /**
     * Computes the cross product with another vector.
     * @param vec The other vector.
     * @return A new vector representing the cross product.
     */
    public Vector3D cross(Vector3D vec) {
        return null;
    }

    /**
     * Computes the length (magnitude) of this vector.
     * @return The length as a double.
     */
    public double length() {
        return 0;
    }
}