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
     *
     * @param x1 coordinate of x1-dimension
     * @param x2 coordinate of x2-dimension
     * @param x3 coordinate of x3-dimension
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
     *
     * @param vec The other vector.
     * @return A new vector representing the sum.
     */
    public Vector3D add(Vector3D vec) {
        double x1 = this.vertices[0] + vec.vertices[0];
        double x2 = this.vertices[1] + vec.vertices[1];
        double x3 = this.vertices[2] + vec.vertices[2];
        return new Vector3D(x1, x2, x3);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param vec The other vector.
     * @return A new vector representing the difference.
     */
    public Vector3D sub(Vector3D vec) {
        double x1 = this.vertices[0] - vec.vertices[0];
        double x2 = this.vertices[1] - vec.vertices[1];
        double x3 = this.vertices[2] - vec.vertices[2];
        return new Vector3D(x1, x2, x3);
    }

    /**
     * Multiplies this vector by a scalar.
     *
     * @param scalar The scalar value.
     * @return A new vector scaled by the scalar.
     */
    public Vector3D mult(double scalar) {
        double x1 = this.vertices[0] * scalar;
        double x2 = this.vertices[1] * scalar;
        double x3 = this.vertices[2] * scalar;
        return new Vector3D(x1, x2, x3);
    }

    /**
     * Computes the scalar (dot) product with another vector.
     *
     * @param vec The other vector.
     * @return The scalar product as a double.
     */
    public double scalar(Vector3D vec) {
        return this.vertices[0] * vec.vertices[0] +
                this.vertices[1] * vec.vertices[1] +
                this.vertices[2] * vec.vertices[2];
    }

    /**
     * Computes the scalar triple product with two other vectors.
     *
     * @param vec1 The first vector.
     * @param vec2 The second vector.
     * @return The scalar triple product as a double.
     */
    private double scalarTriple(Vector3D vec1, Vector3D vec2) {
        // Scalar triple product is the dot product of this vector and the cross product of the other two
        Vector3D crossProduct = this.cross(vec1).cross(vec2);
        return this.scalar(crossProduct);
    }

    /**
     * Computes the cross product with another vector.
     *
     * @param vec The other vector.
     * @return A new vector representing the cross product.
     */
    public Vector3D cross(Vector3D vec) {
        double x1 = this.vertices[1] * vec.vertices[2] - this.vertices[2] * vec.vertices[1];
        double x2 = this.vertices[2] * vec.vertices[0] - this.vertices[0] * vec.vertices[2];
        double x3 = this.vertices[0] * vec.vertices[1] - this.vertices[1] * vec.vertices[0];
        return new Vector3D(x1, x2, x3);
    }

    /**
     * Computes the length (magnitude) of this vector.
     *
     * @return The length as a double.
     */
    public double length() {
        return Math.sqrt(this.vertices[0] * this.vertices[0] +
                this.vertices[1] * this.vertices[1] +
                this.vertices[2] * this.vertices[2]);
    }
}
