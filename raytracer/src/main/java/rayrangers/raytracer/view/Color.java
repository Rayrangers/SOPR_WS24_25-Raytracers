package rayrangers.raytracer.view;

/**
 * Represents a color with three RGB values.
 */
public class Color {

    /**
     * Array of RGB values.
     */
    private int[] rgb;

    /**
     * Class constructor specifying the RGB values.
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    public Color(int r, int g, int b) {
        rgb = new int[] { r, g, b };
    }

    /**
     *  Returns the RGB values of the color as an array.
     *  @return array of RGB values
     */
    public int[] getRGB() {
        return rgb;
    }

    /**
     * Sets the RGB values of the color.
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    public void setRGB(int r, int g, int b) {
        rgb = new int[] { r, g, b };
    }
}
