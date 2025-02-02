package rayrangers.raytracer.gui;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import io.qt.gui.QColor;
import io.qt.gui.QImage;
import rayrangers.raytracer.algorithm.Renderer;
import rayrangers.raytracer.math.TrafoMatrix;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.parser.ObjParser;
import rayrangers.raytracer.parser.SceneParser;
import rayrangers.raytracer.view.ViewPane;
import rayrangers.raytracer.world.Camera;
import rayrangers.raytracer.world.Entity;
import rayrangers.raytracer.world.LightSource;
import rayrangers.raytracer.world.Scene;

/**
 * Provides the functionality for the GUI.
 */
public class Worker {

    // Variables for the results
    private static double renderTime;
    private static int objectCount;
    private static int lightSourceCount;
    private static int raysCount;

    /** 
     * List of entities added to a scene.
     */
    public static List<Entity> entities = new LinkedList<>();

    /**
     * List of light sources added to a scene.
     */
    public static List<LightSource> lightSources = new LinkedList<>();


    /**
     * This method adds a given entity to the list of entities.
     * 
     * @param filePath to the requested entity
     * @throws Exception
     */
    public static void addEntity(String filePath) throws Exception {
        Entity entity = ObjParser.parseObjFile(filePath);
        entities.addLast(entity);
    }

    /**
     * This method performs the transformations of an entity.
     */
    public static void transformEntity() {
        Entity entity = entities.getLast();
        // do specified transformation
        entities.addLast(entity);
    }

    /**
     * This method starts the rendering process with a custom scene from a JSON-file.
     * 
     * @param filePath to the JSON-file
     */
    public static QImage renderJSON(String filePath) {
        QImage result;
        long start = System.currentTimeMillis();

        // Parse the scene from the JSON-file
        Scene scene = null;
        UUID cameraUUID = null;
        try {
            scene = SceneParser.parseScene(filePath);
            cameraUUID = scene.getCameras().keySet().iterator().next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (scene != null && cameraUUID != null) {
            ViewPane viewPane = scene.getCameras().get(cameraUUID).getViewPane();
            Renderer renderer = new Renderer(scene, cameraUUID);
            renderer.render();

            long end = System.currentTimeMillis();
            renderTime = (end - start) / 1000.0;
            System.out.printf("Total runtime for rendering: %f s%n", renderTime);

            // just for testing
            //objectCount = entities.size();
            //System.out.println("Number of objects: " + objectCount);

            //lightSourceCount = lightSources.size();
            //System.out.println("Number of light sources: " + lightSourceCount);

            //raysCount = Renderer.getRayCount();
            //System.out.println("Total number of rays: " + raysCount);

            // Create a QImage
            int width = viewPane.getResX();
            int height = viewPane.getResY();
            result = new QImage(width, height, QImage.Format.Format_RGB32);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgbColor = viewPane.getPixelAt(x, y).getColor().getRGB();
                    QColor color = new QColor(rgbColor);
                    result.setPixel(x, y, color.rgb());
                }
            }
            return result;
        }
        return result = new QImage();
    }

    /**
     * This method invokes the prototype for the GUI (only for testing).
     */
    public static QImage invokePrototype(Camera camera) throws Exception {
        long start = System.currentTimeMillis();
        Scene scene = new Scene(Color.BLACK);

        // Build the world
        // Camera camera = new Camera(new Vertex3D(400, 25, 0), 0, 90, 0, 75, 100, 2000, 2000);
        LightSource lightSource1 = new LightSource(0.15, new Vertex3D(300, 250, 200), Color.WHITE);
        LightSource lightSource2 = new LightSource(0.15, new Vertex3D(300, 50, 0), Color.WHITE);

        // Add an entity (tuna)
        Entity tuna = ObjParser.parseObjFile("examples/tuna/tuna-low.obj");
        TrafoMatrix tmTuna = new TrafoMatrix(0, 150, 0, -90, 0, 0, 1, 1, 1);
        tuna.transform(tmTuna);

        // Add an entity (teapot)
        Entity teapot = ObjParser.parseObjFile("examples/teapot/Teapot.obj");
        TrafoMatrix tmTea = new TrafoMatrix(-50, -100, 10, -90, 10, -33, 1, 1, 1);
        teapot.transform(tmTea);

        // Add camera to the scene
        scene.addCamera(camera);

        // Add light sources to the scene
        scene.addLightSource(lightSource1);
        scene.addLightSource(lightSource2);

        // Add light sources to the list
        lightSources.add(lightSource1);
        lightSources.add(lightSource2);

        // Add entities to the scene
        scene.addEntity(tuna);
        scene.addEntity(teapot);

        // Add entities to the list
        if (scene.getEntities().containsValue(tuna)) {
            entities.add(tuna);
            System.out.println("Tuna added to the list of entities.");
        } else {
            System.out.println("Tuna not added to the list of entities.");
        }

        if (scene.getEntities().containsValue(teapot)) {
            entities.add(teapot);
            System.out.println("Teapot added to the list of entities.");
        } else {
            System.out.println("Teapot not added to the list of entities.");
        }

        // Render the scene
        Renderer renderer = new Renderer(scene, camera.getUuid());
        ViewPane viewPane = camera.getViewPane();
        renderer.render();

        // Create a QImage
        int width = viewPane.getResX();
        int height = viewPane.getResY();
        QImage result = new QImage(width, height, QImage.Format.Format_RGB32);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                int rgbColor = viewPane.getPixelAt(x, y).getColor().getRGB();
                QColor color = new QColor(rgbColor);
                result.setPixel(x, y, color.rgb());
            }
        }

        // Print out the results in console
        long end = System.currentTimeMillis();
        renderTime = (end - start) / 1000.0;
        System.out.printf("Total runtime for rendering: %f s%n", renderTime);

        objectCount = entities.size();
        System.out.println("Number of objects: " + objectCount);

        lightSourceCount = lightSources.size();
        System.out.println("Number of light sources: " + lightSourceCount);

        raysCount = Renderer.getRayCount();
        System.out.println("Total number of rays: " + raysCount);
        
        return result;
    }

    /**
     * Returns the time it took to render a given scene.
     * @return (double) renderTime
     */
    public static double getRenderTime() {
        return renderTime;
    }

    /**
     * Returns the number of objects in the rendered scene.
     * @return (int) objectCount
     */
    public static int getObjectsCount() {
        return objectCount;
    }

    /**
     * Returns the number of light sources in the rendered scene.
     * @return (int) lightSourceCount
     */
    public static int getLightSourcesCount() {
        return lightSourceCount;
    }

    /**
     * Returns the number of rays.
     * @return (int) raysCount
     */
    public static int getRaysCount() {
        return raysCount;
    }
}
