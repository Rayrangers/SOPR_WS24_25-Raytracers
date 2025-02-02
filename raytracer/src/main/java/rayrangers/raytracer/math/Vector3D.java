package rayrangers.raytracer.math;

import rayrangers.raytracer.world.Transformable;

/**
 * Represents a vector in 3D space.
 */
public class Vector3D implements Transformable {

    /**
     * Array containing the coordinates of the vector (x1/x2/x3-dimension).
     */
    private double[] coordinates;

    /**
     * Constructs a vector with the three specified coordinates.
     *
     * @param x1 coordinate of x1-dimension
     * @param x2 coordinate of x2-dimension
     * @param x3 coordinate of x3-dimension
     */
    public Vector3D(double x1, double x2, double x3) {
        this.coordinates = new double[] { x1, x2, x3 };
    }

    /**
     * Returns the coordinates of the vector.
     * 
     * @return array of coordinates
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
     * Sets the coordinates of the vector.
     * 
     * @param x1 coordinate of x1-dimension
     * @param x2 coordinate of x2-dimension
     * @param x3 coordinate of x3-dimension
     */
    public void setCoordinates(double x1, double x2, double x3) {
        this.coordinates = new double[]{x1, x2, x3};
    }

    /**
     * Adds another vector to this vector.
     *
     * @param vec other vector
     * @return new vector representing the sum
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
     * @param vec other vector
     * @return new vector representing the difference
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
     * @param scalar scalar value
     * @return new vector scaled by the scalar
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
     * @param vec other vector
     * @return scalar product as a double
     */
    public double scalar(Vector3D vec) {
        return this.coordinates[0] * vec.coordinates[0] +
                this.coordinates[1] * vec.coordinates[1] +
                this.coordinates[2] * vec.coordinates[2];
    }

    /**
     * Computes the scalar triple product with two other vectors:
     * (a · (b × c))
     *
     * @param vec1 first other vector
     * @param vec2 second other vector
     * @return scalar triple product as a double
     */
    public double scalarTriple(Vector3D vec1, Vector3D vec2) {
        // Scalar triple product is the dot product of this vector 
        // and the cross product of the other two
        // Compute a · (b × c)
        Vector3D crossProduct = vec1.cross(vec2);
        return this.scalar(crossProduct);
    }

    /**
     * Computes the cross product with another vector.
     *
     * @param vec other vector
     * @return new vector representing the cross product
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
     * @return length as a double
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
     * @return new vector representing the normalized vector
     * @throws ArithmeticException if the vector has a length of 0, as normalization is undefined
     */
    public Vector3D normalize() {
        double len = this.length();
        if (len == 0) {
            throw new ArithmeticException("Cannot normalize a zero vector");
        }
        return new Vector3D(this.coordinates[0] / len, this.coordinates[1] / len, this.coordinates[2] / len);
    }

    /**
     * Scales the vector by the given scaling factors (sx, sy, sz).
     *
     * @param sx scaling factor for the x-coordinate
     * @param sy scaling factor for the y-coordinate
     * @param sz scaling factor for the z-coordinate
     */
    public void scale(double sx, double sy, double sz) {
        // Scaling matrix (4x4)
        // [  sx  0   0   0 ]
        // [  0   sy  0   0 ]
        // [  0   0   sz  0 ]
        // [  0   0   0   1 ]

        // Extend the vector to homogeneous coordinates (x1, x2, x3, 1)
        double[] v = this.coordinates;
        double[] scaleMatrix = new double[]{
                sx * v[0], sy * v[1], sz * v[2], 1
        };

        // Assign the scaled coordinates back to the vector
        this.coordinates = new double[]{scaleMatrix[0], scaleMatrix[1], scaleMatrix[2]};
    }

    /**
     * Rotates the vector around the x-axis by the given angle.
     *
     * @param angle rotation angle around x-axis in degrees
     */
    public void rotateX(double angle) {
        // Rotation matrix around the x-axis (4x4)
        // [ 1    0           0          0 ]
        // [ 0    cos(θ)     -sin(θ)     0 ]
        // [ 0    sin(θ)     cos(θ)      0 ]
        // [ 0    0           0          1 ]

        double angleRad = Math.toRadians(angle); // Convert angle to radians
        double cosAngle = Math.cos(angleRad);
        double sinAngle = Math.sin(angleRad);

        // Apply the rotation to the coordinates (homogeneous coordinates)
        double x1 = this.coordinates[0];
        double y1 = this.coordinates[1];
        double z1 = this.coordinates[2];

        this.coordinates[0] = x1;
        this.coordinates[1] = cosAngle * y1 - sinAngle * z1;
        this.coordinates[2] = sinAngle * y1 + cosAngle * z1;
    }

    /**
     * Rotates the vector around the y-axis by the given angle.
     *
     * @param angle rotation angle around y-axis in degrees
     */
    public void rotateY(double angle) {
        // Rotation matrix around the y-axis (4x4)
        // [ cos(θ)   0    sin(θ)   0 ]
        // [ 0        1    0        0 ]
        // [ -sin(θ)  0    cos(θ)   0 ]
        // [ 0        0    0        1 ]

        double angleRad = Math.toRadians(angle); // Convert angle to radians
        double cosAngle = Math.cos(angleRad);
        double sinAngle = Math.sin(angleRad);

        // Apply the rotation to the coordinates (homogeneous coordinates)
        double x1 = this.coordinates[0];
        double y1 = this.coordinates[1];
        double z1 = this.coordinates[2];

        this.coordinates[0] = cosAngle * x1 + sinAngle * z1;
        this.coordinates[1] = y1;
        this.coordinates[2] = -sinAngle * x1 + cosAngle * z1;
    }

    /**
     * Rotates the vector around the z-axis by the given angle.
     *
     * @param angle rotation angle around z-axis in degrees
     */
    public void rotateZ(double angle) {
        // Rotation matrix around the z-axis (4x4)
        // [ cos(θ)  -sin(θ)   0   0 ]
        // [ sin(θ)   cos(θ)   0   0 ]
        // [  0        0       1   0 ]
        // [  0        0       0   1 ]

        double angleRad = Math.toRadians(angle); // Convert angle to radians
        double cosAngle = Math.cos(angleRad);
        double sinAngle = Math.sin(angleRad);

        // Apply the rotation to the coordinates (homogeneous coordinates)
        double x1 = this.coordinates[0];
        double y1 = this.coordinates[1];

        this.coordinates[0] = cosAngle * x1 - sinAngle * y1;
        this.coordinates[1] = sinAngle * x1 + cosAngle * y1;
    }

    /**
     * Translates the vector by the given translation factors (tx, ty, tz).
     *
     * @param tx translation factor for the x-coordinate
     * @param ty translation factor for the y-coordinate
     * @param tz translation factor for the z-coordinate
     */
    public void translate(double tx, double ty, double tz) {
        // Translation matrix (4x4)
        // [ 1  0  0  tx ]
        // [ 0  1  0  ty ]
        // [ 0  0  1  tz ]
        // [ 0  0  0   1 ]

        // Apply the translation to the coordinates (homogeneous coordinates)
        double x1 = this.coordinates[0];
        double y1 = this.coordinates[1];
        double z1 = this.coordinates[2];

        this.coordinates[0] = x1 + tx;
        this.coordinates[1] = y1 + ty;
        this.coordinates[2] = z1 + tz;
    }

    /**
     * @see Transformable
     */
    @Override
    public void transform(TrafoMatrix tm) {
        double[] result = new double[3];
        for (int i = 0; i < 3; i++) {
            result[i] = tm.getElement(i,0) * coordinates[0] 
                    + tm.getElement(i,1) * coordinates[1]
                    + tm.getElement(i,2) * coordinates[2]
                    + tm.getElement(i,3);
        }     
        coordinates = result;
    }   
}
