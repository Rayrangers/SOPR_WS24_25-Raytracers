package rayrangers.raytracer;

import java.awt.Color;

import rayrangers.raytracer.world.Scene;
import rayrangers.raytracer.world.Camera;
import rayrangers.raytracer.algorithm.Renderer;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.view.ViewPane;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Prototype {
    public static void main(String[] args) {
        Scene scene = new Scene(Color.BLACK);
        Camera camera = new Camera(new Vertex3D(0, 10, 50), 0, 0, 0, 25, 100, 100, 100);
        ViewPane viewPane = camera.getViewPane();
        
        scene.addCamera(camera);
        // TODO: Add entity

        Renderer renderer = new Renderer(scene, camera.getUuid());
        renderer.render();

        BufferedImage bufferedImage = new BufferedImage(viewPane.getResX(), viewPane.getResY(), BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < viewPane.getResX(); i++) {
            for (int j = 0; j < viewPane.getResY(); j++) {
                if (j < 10) {
                    viewPane.getPixelAt(i, j).setColor(Color.RED);
                }
                bufferedImage.setRGB(i, j, viewPane.getPixelAt(i, j).getColor().getRGB());
            }
        }
        
        try {
            File output = new File("artifacts/prototype.png");
            ImageIO.write(bufferedImage, "png", output);
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
