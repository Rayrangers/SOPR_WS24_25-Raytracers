package rayrangers.raytracer.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Vector4D class.
 */
public class Vector4DTest {

    // Test for constructor and getter
    @Test
    public void testConstructorAndGetter() {
        Vector4D vector = new Vector4D(1, 2, 3, 4);
        double[] expected = {1, 2, 3, 4};
        assertArrayEquals(expected, vector.getCoordinates());
    }

    // Test for setter
    @Test
    public void testSetter() {
        Vector4D vector = new Vector4D(1, 2, 3, 4);
        vector.setCoordinates(4, 3, 2, 1);
        double[] expected = {4, 3, 2, 1};
        assertArrayEquals(expected, vector.getCoordinates());
    }

    // Test for vector addition
    @Test
    public void testAdd() {
        Vector4D vector1 = new Vector4D(1, 2, 3, 4);
        Vector4D vector2 = new Vector4D(4, 3, 2, 1);
        Vector4D result = vector1.add(vector2);

        double[] expected = {5, 5, 5, 5};
        assertArrayEquals(expected, result.getCoordinates());
    }

    // Test for vector subtraction
    @Test
    public void testSub() {
        Vector4D vector1 = new Vector4D(5, 5, 5, 5);
        Vector4D vector2 = new Vector4D(1, 1, 1, 1);
        Vector4D result = vector1.sub(vector2);

        double[] expected = {4, 4, 4, 4};
        assertArrayEquals(expected, result.getCoordinates());
    }

    // Test for scalar multiplication
    @Test
    public void testMul() {
        Vector4D vector = new Vector4D(1, 2, 3, 4);
        Vector4D result = vector.mul(2);

        double[] expected = {2, 4, 6, 8};
        assertArrayEquals(expected, result.getCoordinates());
    }

    // Test for scalar (dot) product
    @Test
    public void testScalar() {
        Vector4D vector1 = new Vector4D(1, 2, 3, 4);
        Vector4D vector2 = new Vector4D(4, 3, 2, 1);
        double result = vector1.scalar(vector2);

        assertEquals(20, result, 0.0001);  // 1*4 + 2*3 + 3*2 + 4*1 = 20
    }

    // Test for vector length
    @Test
    public void testLength() {
        Vector4D vector = new Vector4D(1, 2, 3, 4);
        double result = vector.length();

        assertEquals(5.477, result, 0.0001);  // sqrt(1^2 + 2^2 + 3^2 + 4^2)
    }

    // Test for vector normalization
    @Test
    public void testNormalize() {
        Vector4D vector = new Vector4D(1, 2, 3, 4);
        Vector4D result = vector.normalize();

        double[] expected = {1 / 5.477, 2 / 5.477, 3 / 5.477, 4 / 5.477};
        assertArrayEquals(expected, result.getCoordinates(), 0.0001);
    }

    // Test for normalization of zero vector (should throw ArithmeticException)
    @Test
    public void testNormalizeZeroVector() {
        Vector4D vector = new Vector4D(0, 0, 0, 0);
        assertThrows(ArithmeticException.class, vector::normalize);
    }
}
