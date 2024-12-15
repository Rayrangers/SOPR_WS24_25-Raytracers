package rayrangers.raytracer.math;

/**
 * Represents cumulated transformation matrix.
 */
public class TrafoMatrix {
    /**
     * Array of all matrix elements.
     */
    private double[] elements = new double[16];

    /**
     * Class constructor specifying the translation parameters, rotation angles and
     * scaling factors.
     * Creates the corresponding cumulated transformation matrix:
     * CTM = RMX3 * RMX2 * RMX1 * SM * TM
     * (CTM = cumulated transformation matrix,
     * RM = rotation matrix, SM = scaling matrix, TM = translation matrix)
     * 
     * @param translX1  translation in x1-direction
     * @param translX2  translation in x2-direction
     * @param translX3  translation in x3-direction
     * @param angleX1   rotation angle around x1-axis
     * @param angleX2   rotation angle around x2-axis
     * @param angleX3   rotation angle around x3-axis
     * @param scalingX1 scaling factor in x1-direction
     * @param scalingX2 scaling factor in x2-direction
     * @param scalingX3 scaling factor in x3-direction
     */
    public TrafoMatrix(
            double translX1, double translX2, double translX3,
            double angleX1, double angleX2, double angleX3,
            double scalingX1, double scalingX2, double scalingX3) {
        TrafoMatrix scTr = createTranslationScalingTrafoMatrix(translX1, translX2, translX3,
                scalingX1, scalingX2, scalingX3);

        if (angleX1 != 0) {
            TrafoMatrix rotX1 = getRotationX1(angleX1);
            scTr = rotX1.matrMult(scTr);
        }

        if (angleX2 != 0) {
            TrafoMatrix rotX2 = getRotationX2(angleX2);
            scTr = rotX2.matrMult(scTr);
        }

        if (angleX3 != 0) {
            TrafoMatrix rotX3 = getRotationX3(angleX3);
            scTr = rotX3.matrMult(scTr);
        }

        this.elements = scTr.elements;
    }

    /**
     * Private constructor only used internally for Rotation matrices.
     * 
     * @param elements
     */
    private TrafoMatrix(double[] elements) {
        this.elements = elements;
    }

    /**
     * Returns the value of an element at the specified position.
     * 
     * @param i Column position
     * @param j Row position
     * @return Value of element
     */
    public double getElement(int i, int j) {
        return elements[j * 4 + i];
    }

    /**
     * Multiplies this transformation matrix by another transformation matrix.
     *
     * @param m The transformation matrix to multiply with.
     * @return A new TrafoMatrix instance representing the result.
     */
    private TrafoMatrix matrMult(TrafoMatrix m) {
        double[] result = new double[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[j * 4 + i] = this.getElement(i, 0) * m.getElement(0, j)
                        + this.getElement(i, 1) * m.getElement(1, j)
                        + this.getElement(i, 2) * m.getElement(2, j);
            }
        }
        return new TrafoMatrix(result);
    }

    /**
     * Internal helper method:
     * 
     * Returns a translation and scaling matrix.
     * <!-- @formatter:off -->
     * [ sX1 0 0 tX1 ]
     * [ 0 sX2 0 tX2 ]
     * [ 0 0 sX3 tX3 ]
     * [ 0 0 0 1 ]
     * <!-- @formatter:on -->
     *
     * @param translX1
     * @param translX2
     * @param translX3
     * @param scalingX1
     * @param scalingX2
     * @param scalingX3
     * @return A new TrafoMatrix instance representing the translation and scaling.
     */
    private TrafoMatrix createTranslationScalingTrafoMatrix(double translX1, double translX2, double translX3,
            double scalingX1, double scalingX2, double scalingX3) {
        return new TrafoMatrix(new double[] {
                scalingX1, 0, 0, translX1,
                0, scalingX2, 0, translX2,
                0, 0, scalingX3, translX3,
                0, 0, 0, 1 });
    }

    /**
     * Internal helper method:
     * 
     * Returns a rotation matrix around the x1-axis (x-axis).
     * <!-- @formatter:off -->
     * [ 1 0 0 0 ]
     * [ 0 cos(φ) -sin(φ) 0 ]
     * [ 0 sin(φ) cos(φ) 0 ]
     * [ 0 0 0 1 ]
     * <!-- @formatter:on -->
     *
     * @param phi The rotation angle in radians.
     * @return A new TrafoMatrix instance representing the rotation.
     */
    private TrafoMatrix getRotationX1(double phi) {
        phi = Math.toRadians(phi);
        return new TrafoMatrix(
                new double[] {
                        1, 0, 0, 0,
                        0, Math.cos(phi), -Math.sin(phi), 0,
                        0, Math.sin(phi), Math.cos(phi), 0,
                        0, 0, 0, 1 });
    }

    /**
     * Internal helper method:
     * 
     * Returns a rotation matrix around the x2-axis (y-axis).
     * * <!-- @formatter:on -->
     * [  cos(φ)  0  sin(φ)  0 ]
     * [   0     1     0    0 ]
     * [ -sin(φ) 0 cos(φ) 0 ]
     * [ 0 0 0 1 ]
     * * <!-- @formatter:off -->
     * 
     * @param phi The rotation angle in radians.
     * @return A new TrafoMatrix instance representing the rotation.
     */
    private TrafoMatrix getRotationX2(double phi) {
        phi = Math.toRadians(phi);
        return new TrafoMatrix(
                new double[] {
                        Math.cos(phi), 0, Math.sin(phi), 0,
                        0, 1, 0, 0,
                        -Math.sin(phi), 0, Math.cos(phi), 0,
                        0, 0, 0, 1 });
    }

    /**
     * Internal helper method:
     * 
     * Returns a rotation matrix around the x3-axis (z-axis).
     * <!-- @formatter:off -->
     * [ cos(φ) -sin(φ)  0  0 ]
     * [ sin(φ)  cos(φ)  0  0 ]
     * [   0       0     1  0 ]
     * [   0       0     0  1 ]
     * <!-- @formatter:on -->
     * 
     * @param phi The rotation angle in radians.
     * @return A new TrafoMatrix instance representing the rotation.
     */
    private TrafoMatrix getRotationX3(double phi) {
        phi = Math.toRadians(phi);
        return new TrafoMatrix(
                new double[] {
                        1, 0, 0, 0,
                        0, Math.cos(phi), -Math.sin(phi), 0,
                        0, Math.sin(phi), Math.cos(phi), 0,
                        0, 0, 0, 1 });
    }
}