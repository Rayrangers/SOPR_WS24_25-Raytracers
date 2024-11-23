package rayrangers.raytracer;

/**
 * Represents a vector in 4D space.
 */

public class Vector4D {

    /**
     * Array containing the coordinates of the vector (x1/x2/x3/x4-dimension).
     */
    private double[] coordinates;

    /**
     * Constructor to initialize the vector with 4 components.
     */
    public Vector4D(double x1, double x2, double x3, double x4) {
        this.coordinates = new double[]{x1, x2, x3, x4};
    }

    /**
     * Getter for the vertices array.
     */
    public double[] getCoordinates() {
        return coordinates;
    }

    /**
     * Setter for the vertices array.
     */
    public void setVertices(double x1, double x2, double x3, double x4) {
        this.coordinates = new double[]{x1, x2, x3, x4};
    }

    /**
     * Adds another vector to this vector.
     * @param vec The other vector.
     * @return A new vector representing the sum.
     */
    public Vector4D add(Vector4D vec) {
        double x1 = this.coordinates[0] + vec.coordinates[0];
        double x2 = this.coordinates[1] + vec.coordinates[1];
        double x3 = this.coordinates[2] + vec.coordinates[2];
        double x4 = this.coordinates[3] + vec.coordinates[3];
        return new Vector4D(x1, x2, x3, x4);
    }

    /**
     * Subtracts another vector from this vector.
     * @param vec The other vector.
     * @return A new vector representing the difference.
     */
    public Vector4D sub(Vector4D vec) {
        double x1 = this.coordinates[0] - vec.coordinates[0];
        double x2 = this.coordinates[1] - vec.coordinates[1];
        double x3 = this.coordinates[2] - vec.coordinates[2];
        double x4 = this.coordinates[3] - vec.coordinates[3];
        return new Vector4D(x1, x2, x3, x4);

    }

    /**
     * Multiplies this vector by a scalar.
     * @param scalar The scalar value.
     * @return A new vector scaled by the scalar.
     */
    public Vector4D mul(double scalar) {
        double x1 = this.coordinates[0] * scalar;
        double x2 = this.coordinates[1] * scalar;
        double x3 = this.coordinates[2] * scalar;
        double x4 = this.coordinates[3] * scalar;
        return new Vector4D(x1, x2, x3, x4);
    }

    /**
     * Computes the scalar (dot) product with another vector.
     * @param vec The other vector.
     * @return The scalar product as a double.
     */
    public double scalar(Vector4D vec) {
        return this.coordinates[0] * vec.coordinates[0] +
                this.coordinates[1] * vec.coordinates[1] +
                this.coordinates[2] * vec.coordinates[2] +
                this.coordinates[3] * vec.coordinates[3];
    }

    /**
     * Computes the length (magnitude) of this vector.
     * @return The length as a double.
     */
    public double length() {
        return Math.sqrt(this.coordinates[0] * this.coordinates[0] +
                this.coordinates[1] * this.coordinates[1] +
                this.coordinates[2] * this.coordinates[2] +
                this.coordinates[3] * this.coordinates[3]);
    }
}
