package rayrangers.raytracer.algorithm;

import java.awt.Color;
import java.util.UUID;

import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.view.Pixel;
import rayrangers.raytracer.view.ViewPane;
import rayrangers.raytracer.world.Camera;
import rayrangers.raytracer.world.Scene;

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
    private Shader shader = new Shader();

    /**
     * Class constructor specifiying the scene and the UUID of the camera.
     * 
     * @param scene scene to be rendered 
     * @param cameraUUId UUId of the camera the scene is rendered from
     */
    public Renderer(Scene scene, UUID cameraUUID) {
        this.scene = scene;
        camera = scene.getCameras().get(cameraUUID);
        viewpane = camera.getViewPane();
    }

    /**
     * Renders the scene.
     */
    public void render() {
        for (int j = 0; j < viewpane.getResY(); j++) {
            for (int i = 0; i < viewpane.getResY(); i++) {
                Pixel p = viewpane.getPixelAt(i, j);
                Ray viewRay = new Ray(camera.getWorldPosition(), computeRayDirection(p));
                p.setColor(traceRay(viewRay));
            }
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
    private Vector3D computeRayDirection(Pixel pixel) {
        Vector3D u = camera.getU();
        Vector3D v = camera.getV();
        Vector3D w = camera.getW();
        double d = camera.getPaneDistance();
        return w.mult(-d).add(u.mult(pixel.getU())).add(v.mult(pixel.getV())); // Ray direction formula: −d * w + pixel.u * u + pixel.v * v
    }
}
