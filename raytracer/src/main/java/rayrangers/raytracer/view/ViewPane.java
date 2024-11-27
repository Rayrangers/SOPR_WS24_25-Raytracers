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
    private int resX;

    /**
     * Resolution in y-direction.
     */
    private int resY;

    /**
     * Physical width of the viewpane.
     */
    private double paneWidth;

    /**
     * Physical height of the viewpane.
     */
    private double paneHeight;

    /**
     * Distance between two pixels in u-direction (up).
     */
    private double deltaU;

    /**
     * Distance between two pixels in v-direction (righthand).
     */
    private double deltaV;

    /**
     * Array of pixels.
     */
    private Pixel[] pixels;

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
        // Calculate the viewpane height
        updatePaneHeight();
    }

    /**
     * Calculates the value of the pane height to ensure pixels are squared.
     */
    private void updatePaneHeight() {
        paneHeight = paneWidth / getAspectRatio();
        calculateDeltas(); // Update deltas for new pane dimensions
        calculatePixelCenters(); // Update pixels with new centers
    }

    /**
     * Calculates deltaU and deltaV.
     */
    private void calculateDeltas() {
        // TODO: Implement
    }

    /**
     * Calculates the centers of all pixels.
     */
    private void calculatePixelCenters() {
        // TODO: Implement
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
     * Sets a new resolution of the pane and adjusts the height accordingly.
     * 
     * @param x New resolution in x-direction
     * @param y New resolution in y-direction
     */
    public void setResolution(int x, int y) {
        resX = x;
        resY = y;
        updatePaneHeight(); // Ensure pixels are not "streched"/"squished"
    }

    public int getResX() {
        return resX;
    }

    public int getResY() {
        return resY;
    }

    public double getPaneWidth() {
        return paneWidth;
    }

    public double getPaneHeight() {
        return paneHeight;
    }

    public Pixel[] getPixels() {
        return pixels;
    }

}
