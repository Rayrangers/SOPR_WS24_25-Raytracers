package rayrangers.raytracer.view;

import java.awt.Color;

/**
 * Represents a pixel in the viewpane in view coordinates.
 */
public class Pixel {

    /**
     * Color of the pixel.
     */
    private Color color;

    /**
     * Coordinate of the pixel in u-direction (righthand).
     */
    private final double u;

    /**
     * Coordinate of the pixel in v-direction (up).
     */
    private final double v;

    /**
     * Constructs a pixel with the given coordinates in u- and v-direction.
     * 
     * @param u pixel coordinate in u-direction
     * @param v pixel coordinate in v-direction
     */
    public Pixel(double u, double v) {
        this.color = null;
        this.u = u;
        this.v = v;
    }

    /**
     * Sets the color of the pixel.
     * 
     * @param color color to set the pixel to
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of the pixel.
     * 
     * @return color of the pixel
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the pixel coordinate in u-direction.
     * 
     * @return pixel coordinate in u-direction
     */
    public double getU() {
        return u;
    }

    /**
     * Returns the pixel coordinate in v-direction.
     * 
     * @return pixel coordinate in v-direction
     */
    public double getV() {
        return v;
    }
}
