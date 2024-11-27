package rayrangers.raytracer.view;

import java.awt.Color;

import rayrangers.raytracer.math.Vertex3D;

/**
 * Represents a pixel in the viewpane.
 */
public class Pixel {

    /**
     * Color of the pixel.
     */
    private Color color;

    /**
     * Center point of the pixel.
     */
    private Vertex3D center;

    /**
     * Class constructor specifying the color and the centerof the pixel.
     * 
     * @param color  color of the pixel
     * @param center center of the pixel
     */
    public Pixel(Color color, Vertex3D center) {
        this.color = color;
        this.center = center;
    }

    /**
     * Returns the center of the pixel as a vertex.
     * 
     * @return center of the pixel as vertex
     */
    public Vertex3D getCenter() {
        return center;
    }

    /**
     * Sets the center of the pixel.
     * 
     * @param center center to set pixel to
     */
    public void setCenter(Vertex3D center) {
        this.center = center;
    }

    /**
     * Sets the color of the pixel.
     * 
     * @param color Color to set pixel to
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of the pixel.
     * 
     * @return Color of the pixel
     */
    public Color getColor() {
        return color;
    }
}
