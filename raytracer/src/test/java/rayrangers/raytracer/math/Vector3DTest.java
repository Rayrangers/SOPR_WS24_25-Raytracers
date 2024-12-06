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
     * Tests the translate method.
     */
    @Test
    public void testTranslate() {
        Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
        vector.translate(4.0, 5.0, 6.0);  // Translate by (4, 5, 6)

        assertArrayEquals(new double[]{5.0, 7.0, 9.0}, vector.getCoordinates(), "Translation failed.");
    }

    /**
     * Tests the rotateX method.
     */
    @Test
    public void testRotateX() {
        Vector3D vector = new Vector3D(1.0, 0.0, 0.0);
        vector.rotateX(90);  // Rotate 90° around X-axis

        assertArrayEquals(new double[]{1.0, 0.0, 0.0}, vector.getCoordinates(), 1e-9, "Rotation around X failed.");
    }

    /**
     * Tests the rotateY method.
     */
    @Test
    public void testRotateY() {
        Vector3D vector = new Vector3D(1.0, 0.0, 0.0);
        vector.rotateY(90);  // Rotate 90° around Y-axis

        assertArrayEquals(new double[]{0.0, 0.0, -1.0}, vector.getCoordinates(), 1e-9, "Rotation around Y failed.");
    }

    /**
     * Tests the rotateZ method.
     */
    @Test
    public void testRotateZ() {
        Vector3D vector = new Vector3D(1.0, 0.0, 0.0);
        vector.rotateZ(90);  // Rotate 90° around Z-axis

        assertArrayEquals(new double[]{0.0, 1.0, 0.0}, vector.getCoordinates(), 1e-9, "Rotation around Z failed.");
    }

    /**
     * Tests the scale method.
     */
    @Test
    public void testScale() {
        Vector3D vector = new Vector3D(1.0, 2.0, 3.0);
        vector.scale(2.0, 3.0, 4.0);  // Scale by (2, 3, 4)

        assertArrayEquals(new double[]{2.0, 6.0, 12.0}, vector.getCoordinates(), "Scaling failed.");
    }
}
