package rayrangers.raytracer.view;

/**
 * Represents a viewpane a scene is rendered to.
 * 
 * Inspired by
 * @see <a href="https://github.com/UtkuOktay/Ray-Tracer/blob/main/src/main/java/com/render/Camera.java">UtkuOktay</a>
 * 
 */
public class ViewPane {

    /**
     * Resolution in x-direction.
     */
    private final int resX;

    /**
     * Resolution in y-direction.
     */
    private final int resY;

    /**
     * Physical width of the viewpane.
     */
    private final double paneWidth;

    /**
     * Physical height of the viewpane.
     */
    private final double paneHeight;

    /**
     * One-dimensional array of pixels
     * representing a two-dimensional grid with i columns and j rows.
     */
    private final Pixel[] pixels;

    /**
     * U-coordinate of the upper (or bottom) left edge of the image
     * in the view coordinate system.
     */
    private final double left;

    /**
     * V-coordinate of the upper left (or right) edge of the image
     * in the view coordinate system.
     */
    private final double top;

    /**
     * Constructs a viewpane with the given resolution and width.
     * Passing the width only without the height ensures that the pixels are squared after construction.
     * 
     * @param resX resolution in x-direction
     * @param resY resolution in y-direction
     * @param paneWidth width of the viewpane
     */
    public ViewPane(int resX, int resY, double paneWidth) {
        this.resX = resX;
        this.resY = resY;
        this.paneWidth = paneWidth;
        paneHeight = paneWidth / getAspectRatio(); // Calculate the height of the viewpane to ensure the pixels are squared
        left = -paneWidth / 2;
        top = paneHeight / 2;
        // Fill the pixel array
        pixels = new Pixel[resX * resY];
        for (int j = 0; j < resY; j++) {
            for (int i = 0; i < resX; i++) {
                // Create a pixel at position (i,j) and set u and v accordingly
                pixels[j * resX + i] = new Pixel(calculateU(i), calculateV(j));
            }
        }
    }

    /**
     * Calculates the coordinate in u-direction
     * of the pixel at position (i,j).
     * 
     * @param i pixel position i
     * @return pixel coordinate in u-direction
     */
    private double calculateU(int i) {
        return left + paneWidth * (i + 0.5) / resX;
    }

    /**
     * Calculates the coordinate in v-direction
     * of the pixel at position (i,j).
     * 
     * @param j pixel position j
     * @return pixel coordinate in v-direction
     */
    private double calculateV(int j) {
        return top - paneHeight * (j + 0.5) / resY;
    }

    /**
     * Returns the aspect ratio of the viewpane as decimal number,
     * e.g. 1920px / 1080px = 16/9 = 1,777777777777778.
     * 
     * @return aspect ratio as decimal number     
     */
    public double getAspectRatio() {
        return resX / resY;
    }

    /**
     * Returns the horizontal resolution of the viewpane in pixels.
     * 
     * @return horizontal resolution of the viewpane in pixels
     */
    public int getResX() {
        return resX;
    }

    /**
     * Returns the vertical resolution of the viewpane in pixels.
     * 
     * @return vertical resolution of the viewpane in pixels
     */
    public int getResY() {
        return resY;
    }

    /**
     * Returns the width of the viewpane.
     * 
     * @return viewpane width
     */
    public double getPaneWidth() {
        return paneWidth;
    }

    /**
     * Returns the height of the viewpane.
     * 
     * @return viewpane height
     */
    public double getPaneHeight() {
        return paneHeight;
    }

    /**
     * Returns the pixels of the viewpane as array.
     * 
     * @return pixel array
     */
    public Pixel[] getPixels() {
        return pixels;
    }

    /**
     * Returns a pixel at the specified position on the two-dimensional viewpane.
     * 
     * @param i horizontal index of the pixel on the viewpane
     * @param j vertical index of the pixel on the viewpane
     * @return pixel
     */
    public Pixel getPixelAt(int i, int j) {
        return pixels[j * resX + i];
    }
}
