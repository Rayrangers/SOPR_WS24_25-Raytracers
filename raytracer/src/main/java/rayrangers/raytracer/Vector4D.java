package rayrangers.raytracer;

/**
 * Represents a vector in 4D space.
 */

public class Vector4D {

    /**
     * Array containing the coordinates of the vector (x1/x2/x3/x4-dimension).
     */
    private double[] vertices;

    /**
     * Constructor to initialize the vector with 4 components.
     */
    public Vector4D(double x1, double x2, double x3, double x4) {
        this.vertices = new double[]{x1, x2, x3, x4};
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
    public void setVertices(double x1, double x2, double x3, double x4) {
        this.vertices = new double[]{x1, x2, x3, x4};
    }

    /**
     * Adds another vector to this vector.
     * @param vec The other vector.
     * @return A new vector representing the sum.
     */
    public Vector4D add(Vector4D vec) {
        double x1 = this.vertices[0] + vec.vertices[0];
        double x2 = this.vertices[1] + vec.vertices[1];
        double x3 = this.vertices[2] + vec.vertices[2];
        double x4 = this.vertices[3] + vec.vertices[3];
        return new Vector4D(x1, x2, x3, x4);
    }

    /**
     * Subtracts another vector from this vector.
     * @param vec The other vector.
     * @return A new vector representing the difference.
     */
    public Vector4D sub(Vector4D vec) {
        double x1 = this.vertices[0] - vec.vertices[0];
        double x2 = this.vertices[1] - vec.vertices[1];
        double x3 = this.vertices[2] - vec.vertices[2];
        double x4 = this.vertices[3] - vec.vertices[3];
        return new Vector4D(x1, x2, x3, x4);

    }

    /**
     * Multiplies this vector by a scalar.
     * @param scalar The scalar value.
     * @return A new vector scaled by the scalar.
     */
    public Vector4D mul(double scalar) {
        double x1 = this.vertices[0] * scalar;
        double x2 = this.vertices[1] * scalar;
        double x3 = this.vertices[2] * scalar;
        double x4 = this.vertices[3] * scalar;
        return new Vector4D(x1, x2, x3, x4);
    }

    /**
     * Computes the scalar (dot) product with another vector.
     * @param vec The other vector.
     * @return The scalar product as a double.
     */
    public double scalar(Vector4D vec) {
        return this.vertices[0] * vec.vertices[0] +
                this.vertices[1] * vec.vertices[1] +
                this.vertices[2] * vec.vertices[2] +
                this.vertices[3] * vec.vertices[3];
    }

    /**
     * Computes the length (magnitude) of this vector.
     * @return The length as a double.
     */
    public double length() {
        return Math.sqrt(this.vertices[0] * this.vertices[0] +
                this.vertices[1] * this.vertices[1] +
                this.vertices[2] * this.vertices[2] +
                this.vertices[3] * this.vertices[3]);
    }
}
