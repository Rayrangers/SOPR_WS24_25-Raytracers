package rayrangers.raytracer.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Vector3D class.
 */
public class Vector3DTest {

    /**
     * Tests the constructor and getter method.
     */
    @Test
    public void testConstructorAndGetCoordinates() {
        Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
        double[] coordinates = vector.getCoordinates();
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, coordinates, "Constructor or getCoordinates failed.");
    }

    /**
     * Tests the setVertices method.
     */
    @Test
    public void testSetCoordinates() {
        Vector3D vector = new Vector3D(0.0, 0.0, 0.0);
        vector.setCoordinates(4.0, 5.0, 6.0);
        assertArrayEquals(new double[]{4.0, 5.0, 6.0}, vector.getCoordinates(), "setVertices failed.");
    }

    /**
     * Tests the add method.
     */
    @Test
    public void testAdd() {
        Vector3D vector1 = new Vector3D(1.0, 2.0, 3.0);
        Vector3D vector2 = new Vector3D(4.0, 5.0, 6.0);
        Vector3D result = vector1.add(vector2);
        assertArrayEquals(new double[]{5.0, 7.0, 9.0}, result.getCoordinates(), "add failed.");
    }

    /**
     * Tests the sub method.
     */
    @Test
    public void testSub() {
        Vector3D vector1 = new Vector3D(5.0, 7.0, 9.0);
        Vector3D vector2 = new Vector3D(1.0, 2.0, 3.0);
        Vector3D result = vector1.sub(vector2);
        assertArrayEquals(new double[]{4.0, 5.0, 6.0}, result.getCoordinates(), "sub failed.");
    }

    /**
     * Tests the mult method.
     */
    @Test
    public void testMult() {
        Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
        Vector3D result = vector.mult(2.0);
        assertArrayEquals(new double[]{2.0, 4.0, 6.0}, result.getCoordinates(), "mult failed.");
    }

    /**
     * Tests the scalar method.
     */
    @Test
    public void testScalar() {
        Vector3D vector1 = new Vector3D(1.0, 2.0, 3.0);
        Vector3D vector2 = new Vector3D(4.0, 5.0, 6.0);
        double result = vector1.scalar(vector2);
        assertEquals(32.0, result, "scalar failed.");
    }

    /**
     * Tests the scalarTriple method.
     */
    @Test
    public void testScalarTriple() {
        // Case 1: Orthogonal unit vectors (forms a unit cube)
        Vector3D a = new Vector3D(1.0, 0.0, 0.0);
        Vector3D b = new Vector3D(0.0, 1.0, 0.0);
        Vector3D c = new Vector3D(0.0, 0.0, 1.0);
        double result = a.scalarTriple(b, c);
        assertEquals(1.0, result, 1e-9, "scalarTriple failed for orthogonal unit vectors.");

        // Case 2: Parallel vectors (scalar triple product is 0)
        b = new Vector3D(2.0, 0.0, 0.0); // Parallel to a
        result = a.scalarTriple(b, c);
        assertEquals(0.0, result, 1e-9, "scalarTriple failed for parallel vectors.");

        // Case 3: Opposite orientation (negative volume)
        b = new Vector3D(0.0, -1.0, 0.0);
        result = a.scalarTriple(b, c);
        assertEquals(-1.0, result, 1e-9, "scalarTriple failed for opposite orientation.");

        // Case 4: Zero vector (result should be 0)
        c = new Vector3D(0.0, 0.0, 0.0);
        result = a.scalarTriple(b, c);
        assertEquals(0.0, result, 1e-9, "scalarTriple failed for a zero vector.");

        // Case 5: Non-orthogonal vectors
        a = new Vector3D(1.0, 2.0, 3.0);
        b = new Vector3D(4.0, 5.0, 6.0);
        c = new Vector3D(7.0, 8.0, 9.0);
        result = a.scalarTriple(b, c);
        assertEquals(0.0, result, 1e-9, "scalarTriple failed for non-orthogonal vectors (degenerate case).");

        // Case 6: Arbitrary vectors with known result
        a = new Vector3D(2.0, 3.0, 4.0);
        b = new Vector3D(5.0, 6.0, 7.0);
        c = new Vector3D(8.0, 9.0, 10.0);
        result = a.scalarTriple(b, c);
        assertEquals(0.0, result, 1e-9, "scalarTriple failed for arbitrary vectors (expected value 0).");
    }

    /**
     * Tests the cross method.
     */
    @Test
    public void testCross() {
        Vector3D vector1 = new Vector3D(1.0, 2.0, 3.0);
        Vector3D vector2 = new Vector3D(4.0, 5.0, 6.0);
        Vector3D result = vector1.cross(vector2);
        assertArrayEquals(new double[]{-3.0, 6.0, -3.0}, result.getCoordinates(), "cross failed.");
    }

    /**
     * Tests the length method.
     */
    @Test
    public void testLength() {
        Vector3D vector = new Vector3D(3.0, 4.0, 0.0);
        double length = vector.length();
        assertEquals(5.0, length, "length failed.");
    }

    /**
     * Tests the normalize method.
     */
    @Test
    public void testNormalize() {
        Vector3D vector = new Vector3D(3.0, 4.0, 0.0);
        Vector3D normalized = vector.normalize();
        assertEquals(1.0, normalized.length(), 1e-9, "normalize failed.");
        assertArrayEquals(new double[]{0.6, 0.8, 0.0}, normalized.getCoordinates(), 1e-9, "normalize failed.");
    }

    /**
     * Tests normalization of a zero vector.
     */
    @Test
    public void testNormalizeZeroVector() {
        Vector3D zeroVector = new Vector3D(0.0, 0.0, 0.0);
        assertThrows(ArithmeticException.class, zeroVector::normalize, "normalize did not throw for zero vector.");
    }

    /**
     * Tests the transl method.
     */
    @Test
    public void testTransl() {
        Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
        Vector3D translation = new Vector3D(4.0, 5.0, 6.0);
        Vector3D result = vector.transl(translation);
        assertArrayEquals(new double[]{5.0, 7.0, 9.0}, result.getCoordinates(), "transl failed.");
    }

    /**
     * Tests the rotate method.
     */
    @Test
    public void testRotate() {
        Vector3D vector = new Vector3D(1.0, 0.0, 0.0);
        Vector3D rotated = vector.rotate(0, Math.PI / 2, 0); // 90 degrees around Y
        assertArrayEquals(new double[]{0.0, 0.0, -1.0}, rotated.getCoordinates(), 1e-9, "rotate failed.");
    }

    /**
     * Tests the scale method.
     */
    @Test
    public void testScale() {
        Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
        Vector3D scaled = vector.scale(2.0, 3.0, 4.0);
        assertArrayEquals(new double[]{2.0, 6.0, 12.0}, scaled.getCoordinates(), "scale failed.");
    }

    /**
     * Tests the homogenousTransl method.
     */
    @Test
    public void testHomogenousTransl() {
        Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
        Vector3D result = vector.homogenousTransl(4.0, 5.0, 6.0);

        assertArrayEquals(new double[]{5.0, 7.0, 9.0}, result.getCoordinates(), "homogenousTransl failed.");
    }

    /**
     * Tests the homogenousRotate method.
     */
    @Test
    public void testHomogenousRotate() {
        Vector3D vector = new Vector3D(1.0, 0.0, 0.0);
        Vector3D result = vector.homogenousRotate(0, Math.PI / 2, 0); // Rotate 90° around Y-axis

        assertArrayEquals(new double[]{0.0, 0.0, -1.0}, result.getCoordinates(), 1e-9, "homogenousRotate failed.");
    }

    /**
     * Tests the homogenousScale method.
     */
    @Test
    public void testHomogenousScale() {
        Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
        Vector3D result = vector.homogenousScale(2.0, 3.0, 4.0);

        assertArrayEquals(new double[]{2.0, 6.0, 12.0}, result.getCoordinates(), "homogenousScale failed.");
    }
}
