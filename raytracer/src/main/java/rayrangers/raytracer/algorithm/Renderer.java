package rayrangers.raytracer.algorithm;

import java.awt.Color;
import java.util.UUID;

import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.view.Pixel;
import rayrangers.raytracer.view.ViewPane;
import rayrangers.raytracer.world.Camera;
import rayrangers.raytracer.world.Scene;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Represents a renderer using the raytracing algorithm.
 */
public class Renderer {

    /**
     * Scene to be rendered.
     */
    private Scene scene;

    /**
     * Camera the scene is rendered from.
     */
    private Camera camera;

    /**
     * Viewpane the scene is rendered to.
     */
    private ViewPane viewpane;

    /**
     * Shader used to render the scene.
     */
    private Shader shader;

    private static int rayCount = 0;

    /**
     * Class constructor specifiying the scene and the UUID of the camera.
     * 
     * @param scene scene to be rendered 
     * @param cameraUUID UUId of the camera the scene is rendered from
     */
    public Renderer(Scene scene, UUID cameraUUID) {
        this.scene = scene;
        camera = scene.getCameras().get(cameraUUID);
        viewpane = camera.getViewPane();
        shader = new Shader(scene);
    }

    /**
     * Renders the scene.
     */
    public void render() {
        // Get number of available processors and create a thread pool of that size
        int numCores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numCores);

        // Get resolution of viewpane 
        int resX = viewpane.getResX();
        int resY = viewpane.getResY();

        // Get camera position
        Vertex3D cameraPos = camera.getWorldPosition();
        Vector3D u = camera.getU();
        Vector3D v = camera.getV();
        Vector3D w = camera.getW();
        double d = camera.getPaneDistance();

        // Iterate over all pixels in viewpane
        for (int j = 0; j < resY; j++) {
            for (int i = 0; i < resX; i++) {
                // Indices must be final variables for lambda
                final int x = i;
                final int y = j;
                
                // Submit Runnable for each ray to thread pool
                executor.execute(() -> {
                    Pixel p = viewpane.getPixelAt(x, y);
                    Ray viewRay = new Ray(cameraPos, computeRayDirection(p, u, v, w, d));
                    // Measure Ray Count
                    rayCount++;
                    p.setColor(traceRay(viewRay));
                });
            }
        }
        executor.shutdown(); // Request an orderly shutdown such that no new tasks will be accepted
        try {
            // Blocks until all tasks have completed execution
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Traces a specified ray and returns the color of the related pixel.
     * 
     * @param viewRay ray to be traced 
     * @return color of the related pixel 
     */
    private Color traceRay(Ray viewRay) {
        HitRecord record = new HitRecord();
        // Initial values for interval [t0,t1]: 
        // t0 = 0, t1 = infinity
        if (scene.hit(viewRay, 0, Double.MAX_VALUE, record))
            return shader.calculatePixelColor(record);
        return scene.getBackgroundColor();
    }

    /**
     * Computes the direction of a ray going through a specified pixel.
     * 
     * @param pixel pixel the ray is going through
     * @return ray direction as a vector
     */
    private Vector3D computeRayDirection(Pixel pixel, Vector3D u, Vector3D v, Vector3D w, double d) {
        return w.mult(-d).add(u.mult(pixel.getU())).add(v.mult(pixel.getV())); // Ray direction formula: −d * w + pixel.u * u + pixel.v * v
    }

    public static int getRayCount() {
        return rayCount;
    }
}
