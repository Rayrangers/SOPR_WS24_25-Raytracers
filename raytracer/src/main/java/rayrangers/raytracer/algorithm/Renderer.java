package rayrangers.raytracer.algorithm;

import java.awt.Color;
import java.util.UUID;

import rayrangers.raytracer.math.Vector3D;
import rayrangers.raytracer.view.Pixel;
import rayrangers.raytracer.view.ViewPane;
import rayrangers.raytracer.world.Camera;
import rayrangers.raytracer.world.Scene;

/**
 * 
 */
public class Renderer {

    /**
     * 
     */
    private Scene scene;

    /**
     * 
     */
    private Camera camera;

    /**
     * 
     */
    private ViewPane viewpane;

    /**
     * 
     */
    private Shader shader = new Shader();

    /**
     * 
     */
    public Renderer(Scene scene, UUID cameraUUID) {
        this.scene = scene;
        camera = scene.getCameras().get(cameraUUID);
        viewpane = camera.getViewPane();
    }

    /**
     * 
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
     * 
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
     * 
     */
    private Vector3D computeRayDirection(Pixel pixel) {
        Vector3D u = camera.getU();
        Vector3D v = camera.getV();
        Vector3D w = camera.getW();
        double d = camera.getPaneDistance();
        return w.mult(-d).add(u.mult(pixel.getU())).add(v.mult(pixel.getV())); // Ray direction formula: −d * w + pixel.u * u + pixel.v * v
    }
}
