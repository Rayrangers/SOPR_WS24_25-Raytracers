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
        this.coordinates = new double[] { x1, x2, x3 };
    }

    /**
     * Getter for the vertices array.
     */
    public double[] getCoordinates() {
        return coordinates;
    }

    /**
     * Returns the coordinate of the specified dimension.
     * 
     * @param dim dimension, integer value in [1,3]
     * @return coordinate
     * @throws IndexOutOfBoundsException if {@code dim < 1 || dim > 3}
     */
    public double getCoord(int dim) {
        if (dim < 1 || dim > 3)
            throw new IndexOutOfBoundsException("Specified dimension out of 3D space.");
        return coordinates[dim - 1];
    }

    /**
     * Setter for the vertices array.
     */
    public void setVertices(double x1, double x2, double x3) {
        this.coordinates = new double[] { x1, x2, x3 };
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
     * @return The scalar triple product as a double.
     */
    public double scalarTriple(Vector3D vec1, Vector3D vec2) {
        // Scalar triple product is the dot product of this vector and the cross product
        // of the other two
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

    /**
     * Translates (shifts) the vector by another vector.
     *
     * @param vec The vector to add (translation).
     * @return A new Vector3D instance representing the translated vector.
     */
    public Vector3D transl(Vector3D vec) {
        return this.add(vec);
    }

    /**
     * Rotates the vector around the X, Y, and Z axes.
     * Rotation is applied in the order X -> Y -> Z.
     *
     * @param angleX The angle of rotation around the X-axis, in radians.
     * @param angleY The angle of rotation around the Y-axis, in radians.
     * @param angleZ The angle of rotation around the Z-axis, in radians.
     * @return A new Vector3D instance representing the rotated vector.
     */
    public Vector3D rotate(double angleX, double angleY, double angleZ) {
        double x = this.coordinates[0];
        double y = this.coordinates[1];
        double z = this.coordinates[2];

        // Rotation around the X-axis
        double cosX = Math.cos(angleX);
        double sinX = Math.sin(angleX);
        double y1 = y * cosX - z * sinX;
        double z1 = y * sinX + z * cosX;

        // Rotation around the Y-axis
        double cosY = Math.cos(angleY);
        double sinY = Math.sin(angleY);
        double x2 = x * cosY + z1 * sinY;
        double z2 = -x * sinY + z1 * cosY;

        // Rotation around the Z-axis
        double cosZ = Math.cos(angleZ);
        double sinZ = Math.sin(angleZ);
        double x3 = x2 * cosZ - y1 * sinZ;
        double y3 = x2 * sinZ + y1 * cosZ;

        return new Vector3D(x3, y3, z2);
    }

    /**
     * Scales the vector by the given factors along the X, Y, and Z axes.
     *
     * @param scaleX Scaling factor along the X-axis.
     * @param scaleY Scaling factor along the Y-axis.
     * @param scaleZ Scaling factor along the Z-axis.
     * @return A new Vector3D instance representing the scaled vector.
     */
    public Vector3D scale(double scaleX, double scaleY, double scaleZ) {
        double x = this.coordinates[0] * scaleX;
        double y = this.coordinates[1] * scaleY;
        double z = this.coordinates[2] * scaleZ;
        return new Vector3D(x, y, z);
    }

    /**
     * Applies homogeneous translation using a 4x4 matrix.
     *
     * @param tx Translation along the X-axis.
     * @param ty Translation along the Y-axis.
     * @param tz Translation along the Z-axis.
     * @return A new Vector3D instance representing the translated vector.
     */
    public Vector3D homogenousTransl(double tx, double ty, double tz) {
        double[] result = new double[3];
        result[0] = this.coordinates[0] + tx;
        result[1] = this.coordinates[1] + ty;
        result[2] = this.coordinates[2] + tz;
        return new Vector3D(result[0], result[1], result[2]);
    }

    /**
     * Applies homogeneous scaling using a 4x4 matrix.
     *
     * @param sx Scaling factor along the X-axis.
     * @param sy Scaling factor along the Y-axis.
     * @param sz Scaling factor along the Z-axis.
     * @return A new Vector3D instance representing the scaled vector.
     */
    public Vector3D homogenousScale(double sx, double sy, double sz) {
        double[] result = new double[3];
        result[0] = this.coordinates[0] * sx;
        result[1] = this.coordinates[1] * sy;
        result[2] = this.coordinates[2] * sz;
        return new Vector3D(result[0], result[1], result[2]);
    }

    /**
     * Applies homogeneous rotation using 4x4 rotation matrices.
     * Rotation is applied in the order X -> Y -> Z.
     *
     * @param angleX Rotation angle around the X-axis (in radians).
     * @param angleY Rotation angle around the Y-axis (in radians).
     * @param angleZ Rotation angle around the Z-axis (in radians).
     * @return A new Vector3D instance representing the rotated vector.
     */
    public Vector3D homogenousRotate(double angleX, double angleY, double angleZ) {
        double x = this.coordinates[0];
        double y = this.coordinates[1];
        double z = this.coordinates[2];

        // Rotation around the X-axis
        double cosX = Math.cos(angleX);
        double sinX = Math.sin(angleX);
        double y1 = y * cosX - z * sinX;
        double z1 = y * sinX + z * cosX;

        // Rotation around the Y-axis
        double cosY = Math.cos(angleY);
        double sinY = Math.sin(angleY);
        double x2 = x * cosY + z1 * sinY;
        double z2 = -x * sinY + z1 * cosY;

        // Rotation around the Z-axis
        double cosZ = Math.cos(angleZ);
        double sinZ = Math.sin(angleZ);
        double x3 = x2 * cosZ - y1 * sinZ;
        double y3 = x2 * sinZ + y1 * cosZ;

        return new Vector3D(x3, y3, z2);
    }
}
