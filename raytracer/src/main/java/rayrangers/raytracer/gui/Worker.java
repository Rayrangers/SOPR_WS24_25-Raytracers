package rayrangers.raytracer.gui;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import io.qt.gui.QColor;
import io.qt.gui.QImage;
import rayrangers.raytracer.algorithm.Renderer;
import rayrangers.raytracer.math.TrafoMatrix;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.parser.ObjParser;
import rayrangers.raytracer.view.ViewPane;
import rayrangers.raytracer.world.Camera;
import rayrangers.raytracer.world.Entity;
import rayrangers.raytracer.world.LightSource;
import rayrangers.raytracer.world.Scene;;

/**
 * Provides the functionality for the GUI.
 */
public class Worker {

    /** 
     * List of entities added to a scene.
     */
    public static List<Entity> entities = new LinkedList<>();


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
     * This method invokes the prototype for the GUI (only for testing).
     */
    public static QImage invokePrototype() throws Exception {
        long start = System.currentTimeMillis();
        Scene scene = new Scene(Color.BLACK);

        // Build the world
        Camera camera = new Camera(new Vertex3D(400, 25, 0), 0, 90, 0, 75, 100, 2000, 2000);
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

        scene.addCamera(camera);
        scene.addLightSource(lightSource1);
        scene.addLightSource(lightSource2);
        scene.addEntity(tuna);
        scene.addEntity(teapot);

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
        long end = System.currentTimeMillis();
        System.out.printf("Total runtime for rendering: %f s%n", (end - start) / 1000.0);
        
        return result;
    }

}
