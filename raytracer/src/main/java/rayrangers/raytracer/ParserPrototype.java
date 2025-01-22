package rayrangers.raytracer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import rayrangers.raytracer.algorithm.Renderer;
import rayrangers.raytracer.parser.SceneParser;
import rayrangers.raytracer.view.ViewPane;
import rayrangers.raytracer.world.Scene;

/**
 * Prototype of the raytracer utilizing the JSON parser for the scene specification based on Quicktype.
 */
public class ParserPrototype {

    /**
     * Main method.
     * 
     * @param args string arguments
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Scene scene = null;
        UUID cameraUUID = null;
        try {
            scene = SceneParser.parseScene("artifacts/scene.json");
            cameraUUID = scene.getCameras().keySet().iterator().next();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (scene != null && cameraUUID != null) {
            
            ViewPane viewPane = scene.getCameras().get(cameraUUID).getViewPane();
            
            Renderer renderer = new Renderer(scene, cameraUUID);
            renderer.render();
            
            long end = System.currentTimeMillis();
            System.out.printf("Total runtime for rendering: %f s%n", (end - start) / 1000.0);

            BufferedImage bufferedImage = new BufferedImage(viewPane.getResX(), viewPane.getResY(),
                    BufferedImage.TYPE_INT_RGB);
            for (int j = 0; j < viewPane.getResY(); j++) {
                for (int i = 0; i < viewPane.getResX(); i++) {
                    bufferedImage.setRGB(i, j, viewPane.getPixelAt(i, j).getColor().getRGB());
                }
            }

            try {
                File output = new File("artifacts/prototype.png");
                ImageIO.write(bufferedImage, "png", output);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
