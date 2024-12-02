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
     * Default constructor initializes the matrix as an identity matrix.
     */
    public Matrix3x3() {
        this.elements = new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
    }

    /**
     * Returns the identity matrix (3x3).
     *
     * @return A new instance of Matrix3x3 representing the identity matrix.
     */
    public static Matrix3x3 getIdentity() {
        return new Matrix3x3();
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

    // --- Rotation Matrices ---
    /**
     * Rotation around the x3-axis (z-axis).
     * Matrix:
     * [ cos(φ) -sin(φ)  0 ]
     * [ sin(φ)  cos(φ)  0 ]
     * [   0       0     1 ]
     *
     * @param phi The rotation angle in radians.
     * @return A new Matrix3x3 instance representing the rotation.
     */
    public static Matrix3x3 rotationZ(double phi) {
        double[][] mat = {
                {Math.cos(phi), -Math.sin(phi), 0},
                {Math.sin(phi), Math.cos(phi), 0},
                {0, 0, 1}
        };
        return new Matrix3x3(mat);
    }

    /**
     * Rotation around the x1-axis (x-axis).
     * Matrix:
     * [ 1      0       0    ]
     * [ 0   cos(φ) -sin(φ)  ]
     * [ 0   sin(φ)  cos(φ)  ]
     *
     * @param phi The rotation angle in radians.
     * @return A new Matrix3x3 instance representing the rotation.
     */
    public static Matrix3x3 rotationX(double phi) {
        double[][] mat = {
                {1, 0, 0},
                {0, Math.cos(phi), -Math.sin(phi)},
                {0, Math.sin(phi), Math.cos(phi)}
        };
        return new Matrix3x3(mat);
    }

    /**
     * Rotation around the x2-axis (y-axis).
     * Matrix:
     * [  cos(φ)   0  sin(φ)  ]
     * [    0      1    0     ]
     * [ -sin(φ)   0  cos(φ)  ]
     *
     * @param phi The rotation angle in radians.
     * @return A new Matrix3x3 instance representing the rotation.
     */
    public static Matrix3x3 rotationY(double phi) {
        double[][] mat = {
                {Math.cos(phi), 0, Math.sin(phi)},
                {0, 1, 0},
                {-Math.sin(phi), 0, Math.cos(phi)}
        };
        return new Matrix3x3(mat);
    }

    public double[][] getElements() {
        return this.elements;
    }

//    REDUNDANT?
//    // --- Cartesian Transformations ---
//    /**
//     * Cartesian translation matrix.
//     * For vector addition:
//     *
//     * @param tx Translation along the x1-axis.
//     * @param ty Translation along the x2-axis.
//     * @param tz Translation along the x3-axis.
//     * @return A 4x4 matrix for translation.
//     *//*
//    public static double[][] cartesianTranslation(double tx, double ty, double tz) {
//        // Returning null instead of an actual matrix
//        return null;
//    }
//
//    /**
//     * Cartesian scaling matrix.
//     * [ sx  0   0   0 ]
//     * [ 0   sy  0   0 ]
//     * [ 0   0   sz  0 ]
//     *
//     * @param sx Scaling factor along the x1-axis.
//     * @param sy Scaling factor along the x2-axis.
//     * @param sz Scaling factor along the x3-axis.
//     * @return A 3x3 matrix for scaling.
//     *//*
//    public static double[][] cartesianScaling(double sx, double sy, double sz) {
//        return new double[][]{
//                {sx, 0, 0, 0},
//                {0, sy, 0, 0},
//                {0, 0, sz, 0},
//        };
//    }
//
//    // --- Homogeneous Transformations ---
//
//    /**
//     * Homogenous translation matrix.
//     * For vector addition:
//     * [ 1  0  0  tx ]
//     * [ 0  1  0  ty ]
//     * [ 0  0  1  tz ]
//     * [ 0  0  0   1 ]
//     *
//     * @param tx Translation along the x1-axis.
//     * @param ty Translation along the x2-axis.
//     * @param tz Translation along the x3-axis.
//     * @return A 4x4 matrix for translation.
//     */
//    public static double[][] homogenousTranslation(double tx, double ty, double tz) {
//        return new double[][]{
//                {1, 0, 0, tx},
//                {0, 1, 0, ty},
//                {0, 0, 1, tz},
//                {0, 0, 0, 1}
//        };
//    }
//    /**
//     * Homogeneous scaling matrix.
//     * Similar to the cartesian scaling matrix but used in a 4x4 context.
//     * [ sx  0   0   0 ]
//     * [ 0   sy  0   0 ]
//     * [ 0   0   sz  0 ]
//     * [ 0   0   0   1 ]
//     *
//     * @param sx Scaling factor along the x1-axis.
//     * @param sy Scaling factor along the x2-axis.
//     * @param sz Scaling factor along the x3-axis.
//     * @return A 4x4 matrix for scaling.
//     */
//    public static double[][] homogeneousScaling(double sx, double sy, double sz) {
//        return new double[][]{
//                {sx, 0, 0, 0},
//                {0, sy, 0, 0},
//                {0, 0, sz, 0},
//                {0, 0, 0, 1}
//        };
//    }
//
//    /**
//     * Homogeneous rotation matrix for x3-axis (z-axis).
//     * ***Use this as standard rotation method.***
//     * Matrix:
//     * [ cos(φ) -sin(φ)  0   0 ]
//     * [ sin(φ)  cos(φ)  0   0 ]
//     * [   0       0     1   0 ]
//     * [   0       0     0   1 ]
//     *
//     * @param phi Rotation angle in radians.
//     * @return A 4x4 matrix for rotation around the z-axis.
//     */
//    public static double[][] homogeneousRotationZ(double phi) {
//        return new double[][]{
//                {Math.cos(phi), -Math.sin(phi), 0, 0},
//                {Math.sin(phi), Math.cos(phi), 0, 0},
//                {0, 0, 1, 0},
//                {0, 0, 0, 1}
//        };
//    }
//
//    /**
//     * Homogeneous rotation matrix for x1-axis (x-axis).
//     *
//     * @param phi The rotation angle in radians.
//     * @return A 4x4 matrix for homogeneous rotation around the x-axis.
//     */
//    public static double[][] homogeneousRotationX(double phi) {
//        return new double[][]{
//                {1, 0, 0, 0},
//                {0, Math.cos(phi), -Math.sin(phi), 0},
//                {0, Math.sin(phi), Math.cos(phi), 0},
//                {0, 0, 0, 1}
//        };
//    }
//
//    /**
//     * Homogeneous rotation matrix for x2-axis (y-axis).
//     *
//     * @param phi The rotation angle in radians.
//     * @return A 4x4 matrix for homogeneous rotation around the y-axis.
//     */
//    public static double[][] homogeneousRotationY(double phi) {
//        return new double[][]{
//                {Math.cos(phi), 0, Math.sin(phi), 0},
//                {0, 1, 0, 0},
//                {-Math.sin(phi), 0, Math.cos(phi), 0},
//                {0, 0, 0, 1}
//        };
//    }
//
//    /**
//     * Prints the matrix for debugging purposes.
//     */
//    public void print() {
//        for (double[] row : elements) {
//            for (double value : row) {
//                System.out.printf("%.2f ", value);
//            }
//            System.out.println();
//        }
//    }

}
