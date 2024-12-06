package rayrangers.raytracer.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Matrix3x3 class.
 */

public class Matrix3x3Test {

    // Test for the default constructor (Identity matrix)
    @Test
    public void testDefaultConstructor() {
        Matrix3x3 matrix = new Matrix3x3();
        double[][] expected = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j], matrix.getElements()[i][j], 0.0001);
            }
        }
    }

    // Test for the constructor with 2D array input
    @Test
    public void testConstructorWithElements() {
        double[][] elements = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3x3 matrix = new Matrix3x3(elements);
        assertArrayEquals(elements[0], matrix.getElements()[0]);
        assertArrayEquals(elements[1], matrix.getElements()[1]);
        assertArrayEquals(elements[2], matrix.getElements()[2]);
    }

    // Test for invalid matrix input (non-3x3 matrix)
    @Test
    public void testInvalidMatrixConstructor() {
        double[][] invalidElements = {
                {1, 2, 3},
                {4, 5, 6}
        };
        assertThrows(IllegalArgumentException.class, () -> new Matrix3x3(invalidElements));
    }

    // Test for matrix addition
    @Test
    public void testAdd() {
        double[][] elements1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        double[][] elements2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        Matrix3x3 matrix1 = new Matrix3x3(elements1);
        Matrix3x3 matrix2 = new Matrix3x3(elements2);
        Matrix3x3 result = matrix1.add(matrix2);

        double[][] expected = {
                {10, 10, 10},
                {10, 10, 10},
                {10, 10, 10}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j], result.getElements()[i][j], 0.0001);
            }
        }
    }

    // Test for matrix subtraction
    @Test
    public void testSub() {
        double[][] elements1 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        double[][] elements2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3x3 matrix1 = new Matrix3x3(elements1);
        Matrix3x3 matrix2 = new Matrix3x3(elements2);
        Matrix3x3 result = matrix1.sub(matrix2);

        double[][] expected = {
                {8, 6, 4},
                {2, 0, -2},
                {-4, -6, -8}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j], result.getElements()[i][j], 0.0001);
            }
        }
    }

    // Test for matrix determinant
    @Test
    public void testDet() {
        double[][] elements = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3x3 matrix = new Matrix3x3(elements);
        assertEquals(0, matrix.det(), 0.0001);  // The determinant of this matrix should be 0
    }

    // Test for matrix trace
    @Test
    public void testTrace() {
        double[][] elements = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3x3 matrix = new Matrix3x3(elements);
        assertEquals(15, matrix.trace(), 0.0001);  // Sum of diagonal elements: 1+5+9 = 15
    }

    // Test for matrix inverse (should throw exception for singular matrix)
    @Test
    public void testInverse() {
        double[][] elements = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3x3 matrix = new Matrix3x3(elements);
        assertThrows(ArithmeticException.class, matrix::inverse);  // This matrix is singular (determinant 0)
    }

    // Test for matrix multiplication
    @Test
    public void testMatrMult() {
        double[][] elements1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        double[][] elements2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
        Matrix3x3 matrix1 = new Matrix3x3(elements1);
        Matrix3x3 matrix2 = new Matrix3x3(elements2);
        Matrix3x3 result = matrix1.matrMult(matrix2);

        double[][] expected = {
                {30, 24, 18},
                {84, 69, 54},
                {138, 114, 90}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j], result.getElements()[i][j], 0.0001);
            }
        }
    }

    // Test for matrix scalar multiplication
    @Test
    public void testScalarMult() {
        double[][] elements = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3x3 matrix = new Matrix3x3(elements);
        Matrix3x3 result = matrix.scalarMult(2);

        double[][] expected = {
                {2, 4, 6},
                {8, 10, 12},
                {14, 16, 18}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j], result.getElements()[i][j], 0.0001);
            }
        }
    }

    // Test for matrix transpose
    @Test
    public void testTranspose() {
        double[][] elements = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Matrix3x3 matrix = new Matrix3x3(elements);
        Matrix3x3 result = matrix.transpose();

        double[][] expected = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j], result.getElements()[i][j], 0.0001);
            }
        }
    }

    // Test for rotation around Z-axis
    @Test
    public void testRotationZ() {
        double phi = Math.PI / 2; // 90 degrees
        Matrix3x3 rotationMatrix = Matrix3x3.rotationZ(phi);

        double[][] expected = {
                {0, -1, 0},
                {1, 0, 0},
                {0, 0, 1}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j], rotationMatrix.getElements()[i][j], 0.0001);
            }
        }
    }

    // Test for rotation around X-axis
    @Test
    public void testRotationX() {
        double phi = Math.PI / 2; // 90 degrees
        Matrix3x3 rotationMatrix = Matrix3x3.rotationX(phi);

        double[][] expected = {
                {1, 0, 0},
                {0, 0, -1},
                {0, 1, 0}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j], rotationMatrix.getElements()[i][j], 0.0001);
            }
        }
    }

    // Test for rotation around Y-axis
    @Test
    public void testRotationY() {
        double phi = Math.PI / 2; // 90 degrees
        Matrix3x3 rotationMatrix = Matrix3x3.rotationY(phi);

        double[][] expected = {
                {0, 0, 1},
                {0, 1, 0},
                {-1, 0, 0}
        };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j], rotationMatrix.getElements()[i][j], 0.0001);
            }
        }
    }
}
