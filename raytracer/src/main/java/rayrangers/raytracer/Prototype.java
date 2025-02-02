package rayrangers.raytracer;

import java.awt.Color;

import rayrangers.raytracer.world.Scene;
//import rayrangers.raytracer.world.Triangle;
import rayrangers.raytracer.world.Camera;
import rayrangers.raytracer.world.Entity;
import rayrangers.raytracer.world.LightSource;
//import rayrangers.raytracer.world.Face;
import rayrangers.raytracer.algorithm.Renderer;
import rayrangers.raytracer.math.TrafoMatrix;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.parser.ObjParser;
import rayrangers.raytracer.view.ViewPane;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;

import javax.imageio.ImageIO;

/**
 * Prototype of the raytracer.
 */
public class Prototype {

    /**
     * Main method.
     * 
     * @param args string arguments
     * @throws Exception if OBJ or MTL file cannot be parsed
     */
    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        Scene scene = new Scene(Color.BLACK);

        //Camera camera = new Camera(new Vertex3D(0, 25, 400), 0, 0, 0, 75, 100, 2000, 2000);
        Camera camera = new Camera(new Vertex3D(200, 150, 100), -30, 90, 0, 75, 100, 2000, 2000);
        ViewPane viewPane = camera.getViewPane();

        scene.addCamera(camera);
        //Entity teapot = ObjParser.parseObjFile("examples/teapot/Teapot.obj");
        //Entity tuna = ObjParser.parseObjFile("examples/tuna/tuna-low.obj");
        //Entity water = ObjParser.parseObjFile("examples/water-molecule/water-molecule.obj");
        Entity teapotChess = ObjParser.parseObjFile("examples/teapot-chess/teapotonchess.obj");

        TrafoMatrix tmTea = new TrafoMatrix(-50, -100, 10, 0, 0, 0, 1, 1, 1);
        teapotChess.transform(tmTea);

        //TrafoMatrix tmTea = new TrafoMatrix(-50, -100, 10, -90, 10, -33, 1, 1, 1);
        //teapot.transform(tmTea);

        //TrafoMatrix tmTuna = new TrafoMatrix(80, 70, 100, -90, 0, 0, 1, 1, 1);
        //tuna.transform(tmTuna);

        scene.addEntity(teapotChess);
        //scene.addEntity(tuna);
        scene.addEntity(teapotChess);

        LightSource lightSource1 = new LightSource(0.15, new Vertex3D(450, 250, 350), Color.WHITE);
        //LightSource lightSource2 = new LightSource(0.35, new Vertex3D(400, 400, 400), Color.WHITE);
        scene.addLightSource(lightSource1);
        //scene.addLightSource(lightSource2);

        Renderer renderer = new Renderer(scene, camera.getUuid());
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
