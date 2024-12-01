package rayrangers.raytracer.view;

/**
 * Representation of a viewpane.
 * 
 * Inspired by
 * https://github.com/UtkuOktay/Ray-Tracer/blob/main/src/main/java/com/render/Camera.java
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
    private final double bottom;

    /**
     * Class constructor specifying the resolution and the pane width.
     * Only paneWidth is passed to ensure that the pixels are squared.
     * 
     * @param resX      resolution in x-direction
     * @param resY      resolution in y-direction
     * @param paneWidth viewpane width
     */
    public ViewPane(int resX, int resY, double paneWidth) {
        this.resX = resX;
        this.resY = resY;
        this.paneWidth = paneWidth;
        // Fill pixel array
        pixels = new Pixel[resX * resY];
        for (int i = 0; i < resX; i++) {
            for (int j = 0; j < resY; j++) {
                // Create Pixel at position (i,j) and set u and v accordingly
                pixels[i * resX + j] = new Pixel(calculateU(i, j), calculateV(i, j));
            }
        }
        paneHeight = paneWidth / getAspectRatio(); // Calculate pane height to ensure pixels are squared
        left = -paneWidth / 2;
        bottom = -paneHeight / 2;
    }

    /**
     * Calculates the coordinate in u-direction
     * of the pixel at position (i,j).
     * 
     * @return pixel coordinate in u-direction
     */
    private double calculateU(int i, int j) {
        return left + paneWidth * (i + 0.5) / resX;
    }

    /**
     * Calculates the coordinate in v-direction
     * of the pixel at position (i,j).
     * 
     * @return pixel coordinate in v-direction
     */
    private double calculateV(int i, int j) {
        return bottom + paneHeight * (j + 0.5) / resY;
    }

    /**
     * Returns the aspect ratio of the viewpane.
     * 
     * @return Aspect ratio as decimal,
     *         e.g. 1920px / 1080px = 16/9 = 1,777777777777778
     */
    public double getAspectRatio() {
        return resX / resY;
    }

    /**
     * Returns horizontal resolution of pane in pixels.
     * 
     * @return Horizontal resolution of pane in pixels
     */
    public int getResX() {
        return resX;
    }

    /**
     * Returns vertical resolution of pane in pixels.
     * 
     * @return Vertical resolution of pane in pixels
     */
    public int getResY() {
        return resY;
    }

    /**
     * Returns the width of the viewpane.
     * 
     * @return pane width
     */
    public double getPaneWidth() {
        return paneWidth;
    }

    /**
     * Returns the height of the viewpane.
     * 
     * @return pane height
     */
    public double getPaneHeight() {
        return paneHeight;
    }

    /**
     * Returns the pixels of the viewpane as array..1080px
     * 
     * @return pixel array
     */
    public Pixel[] getPixels() {
        return pixels;
    }

    /**
     * Returns a Pixel at the specified position on the two-dimensional ViewPane.
     * 
     * @param i Horizontal index of Pixel on ViewPane
     * @param j Vertical index of Pixel on ViewPane
     * @return Pixel
     */
    public Pixel getPixelAt(int i, int j) {
        return pixels[i * resX + j];
    }
}
