package rayrangers.raytracer.math;

/**
 * Represents a vector in 3D space.
 */
public class Vector3D {

    /**
     * Array containing the coordinates of the vector (x1/x2/x3-dimension).
     */
    private double[] coordinates;

    /**
     * Constructor to initialize the vector with 3 components.
     *
     * @param x1 coordinate of x1-dimension
     * @param x2 coordinate of x2-dimension
     * @param x3 coordinate of x3-dimension
     */
    public Vector3D(double x1, double x2, double x3) {
        this.coordinates = new double[]{x1, x2, x3};
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
    public void setCoordinates(double x1, double x2, double x3) {
        this.coordinates = new double[]{x1, x2, x3};
    }

    /**
     * Adds another vector to this vector.
     *
     * @param vec The other vector.
     * @return A new vector representing the sum.
     */
    public Vector3D add(Vector3D vec) {
        double x1 = this.coordinates[0] + vec.coordinates[0];
        double x2 = this.coordinates[1] + vec.coordinates[1];
        double x3 = this.coordinates[2] + vec.coordinates[2];
        return new Vector3D(x1, x2, x3);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param vec The other vector.
     * @return A new vector representing the difference.
     */
    public Vector3D sub(Vector3D vec) {
        double x1 = this.coordinates[0] - vec.coordinates[0];
        double x2 = this.coordinates[1] - vec.coordinates[1];
        double x3 = this.coordinates[2] - vec.coordinates[2];
        return new Vector3D(x1, x2, x3);
    }

    /**
     * Multiplies this vector by a scalar.
     *
     * @param scalar The scalar value.
     * @return A new vector scaled by the scalar.
     */
    public Vector3D mult(double scalar) {
        double x1 = this.coordinates[0] * scalar;
        double x2 = this.coordinates[1] * scalar;
        double x3 = this.coordinates[2] * scalar;
        return new Vector3D(x1, x2, x3);
    }

    /**
     * Computes the scalar (dot) product with another vector.
     *
     * @param vec The other vector.
     * @return The scalar product as a double.
     */
    public double scalar(Vector3D vec) {
        return this.coordinates[0] * vec.coordinates[0] +
                this.coordinates[1] * vec.coordinates[1] +
                this.coordinates[2] * vec.coordinates[2];
    }

    /**
     * Computes the scalar triple product with two other vectors.
     *
     * @param vec1 The first vector.
     * @param vec2 The second vector.
     * @return The scalar triple product (a · (b × c)) as a double.
     */
    public double scalarTriple(Vector3D vec1, Vector3D vec2) {
        // Compute b × c
        Vector3D crossProduct = vec1.cross(vec2);
        // Compute a · (b × c)
        return this.scalar(crossProduct);
    }

    /**
     * Computes the cross product with another vector.
     *
     * @param vec The other vector.
     * @return A new vector representing the cross product.
     */
    public Vector3D cross(Vector3D vec) {
        double x1 = this.coordinates[1] * vec.coordinates[2] - this.coordinates[2] * vec.coordinates[1];
        double x2 = this.coordinates[2] * vec.coordinates[0] - this.coordinates[0] * vec.coordinates[2];
        double x3 = this.coordinates[0] * vec.coordinates[1] - this.coordinates[1] * vec.coordinates[0];
        return new Vector3D(x1, x2, x3);
    }

    /**
     * Computes the length (magnitude) of this vector.
     *
     * @return The length as a double.
     */
    public double length() {
        return Math.sqrt(this.coordinates[0] * this.coordinates[0] +
                this.coordinates[1] * this.coordinates[1] +
                this.coordinates[2] * this.coordinates[2]);
    }

    /**
     * Normalizes this vector, returning a new vector with the same direction
     * but a length (magnitude) of 1.
     *
     * @return A new Vector3D instance representing the normalized vector.
     * @throws ArithmeticException If the vector has a length of 0, as normalization is undefined.
     */
    public Vector3D normalize() {
        double len = this.length();
        if (len == 0) {
            throw new ArithmeticException("Cannot normalize a zero vector");
        }
        return new Vector3D(this.coordinates[0] / len, this.coordinates[1] / len, this.coordinates[2] / len);
    }
}
