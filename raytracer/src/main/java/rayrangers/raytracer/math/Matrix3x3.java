package rayrangers.raytracer.math;

/**
 * Represents a 3x3 transformation matrix.
 */
public class Matrix3x3 {

    /**
     * Array containing the values of the matrix (3x3).
     */
    private double[][] elements;

    /**
     * Constructor to initialize a 3x3 matrix.
     *
     * @param elements 2D array representing the matrix values.
     */
    public Matrix3x3(double[][] elements) {
        if (elements.length != 3 || elements[0].length != 3) {
            throw new IllegalArgumentException("Matrix must be 3x3.");
        }
        this.elements = elements;
    }

    /**
     * Adds another matrix to this matrix.
     *
     * @param m The matrix to add.
     * @return A new Matrix3x3 instance representing the result.
     */
    public Matrix3x3 add(Matrix3x3 m) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.elements[i][j] + m.elements[i][j];
            }
        }
        return new Matrix3x3(result);
    }

    /**
     * Subtracts another matrix from this matrix.
     *
     * @param m The matrix to subtract.
     * @return A new Matrix3x3 instance representing the result.
     */
    public Matrix3x3 sub(Matrix3x3 m) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.elements[i][j] - m.elements[i][j];
            }
        }
        return new Matrix3x3(result);
    }

    /**
     * Computes the trace of the matrix, which is the sum of its diagonal elements.
     *
     * @return The trace of the matrix.
     */
    public double trace() {
        return elements[0][0] + elements[1][1] + elements[2][2];
    }

    /**
     * Computes the inverse of the matrix.
     *
     * @return The inverse of the matrix.
     * @throws ArithmeticException If the matrix is singular and cannot be inverted.
     */
    public Matrix3x3 inverse() {
        double det = this.det();
        if (det == 0) {
            throw new ArithmeticException("Matrix is singular and cannot be inverted.");
        }
        double[][] result = new double[3][3];
        result[0][0] = (elements[1][1] * elements[2][2] - elements[1][2] * elements[2][1]) / det;
        result[0][1] = (elements[0][2] * elements[2][1] - elements[0][1] * elements[2][2]) / det;
        result[0][2] = (elements[0][1] * elements[1][2] - elements[0][2] * elements[1][1]) / det;
        result[1][0] = (elements[1][2] * elements[2][0] - elements[1][0] * elements[2][2]) / det;
        result[1][1] = (elements[0][0] * elements[2][2] - elements[0][2] * elements[2][0]) / det;
        result[1][2] = (elements[0][2] * elements[1][0] - elements[0][0] * elements[1][2]) / det;
        result[2][0] = (elements[1][0] * elements[2][1] - elements[1][1] * elements[2][0]) / det;
        result[2][1] = (elements[0][1] * elements[2][0] - elements[0][0] * elements[2][1]) / det;
        result[2][2] = (elements[0][0] * elements[1][1] - elements[0][1] * elements[1][0]) / det;
        return new Matrix3x3(result);
    }

    /**
     * Multiplies this matrix by another matrix.
     *
     * @param m The matrix to multiply with.
     * @return A new Matrix3x3 instance representing the result.
     */
    public Matrix3x3 matrMult(Matrix3x3 m) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.elements[i][0] * m.elements[0][j] + this.elements[i][1] * m.elements[1][j] + this.elements[i][2] * m.elements[2][j];
            }
        }
        return new Matrix3x3(result);
    }

    /**
     * Multiplies the matrix by a scalar.
     *
     * @param scalar The scalar value.
     * @return A new Matrix3x3 instance representing the scaled matrix.
     */
    public Matrix3x3 scalarMult(double scalar) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.elements[i][j] * scalar;
            }
        }
        return new Matrix3x3(result);
    }

    /**
     * Computes the transpose of the matrix.
     *
     * @return A new Matrix3x3 instance representing the transposed matrix.
     */
    public Matrix3x3 transpose() {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = this.elements[j][i];
            }
        }
        return new Matrix3x3(result);
    }

    /**
     * Computes the rank of the matrix.
     *
     * @return The rank of the matrix.
     */
    public double rank() {
        // In this case, assume the matrix is 3x3. Rank determination can be simplified for small matrices.
        return (this.det() == 0) ? 2 : 3;
    }

    /**
     * Computes the determinant of the matrix.
     *
     * @return The determinant of the matrix.
     */
    public double det() {
        return elements[0][0] * (elements[1][1] * elements[2][2] - elements[1][2] * elements[2][1]) -
                elements[0][1] * (elements[1][0] * elements[2][2] - elements[1][2] * elements[2][0]) +
                elements[0][2] * (elements[1][0] * elements[2][1] - elements[1][1] * elements[2][0]);
    }

    /**
     * Returns the identity matrix (3x3).
     *
     * @return The identity matrix.
     */
    public static Matrix3x3 getIdentity() {
        return new Matrix3x3(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });
    }

    /**
     * Prints the matrix for debugging purposes.
     */
    public void print() {
        for (int i = 0; i < 3; i++) {
            System.out.println(elements[i][0] + " " + elements[i][1] + " " + elements[i][2]);
        }
    }
}
