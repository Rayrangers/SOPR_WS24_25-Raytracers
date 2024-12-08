package rayrangers.raytracer;

import java.awt.Color;

import rayrangers.raytracer.world.Scene;
// import rayrangers.raytracer.world.Triangle;
import rayrangers.raytracer.world.Camera;
import rayrangers.raytracer.world.Entity;
import rayrangers.raytracer.world.LightSource;
// import rayrangers.raytracer.world.Face;
import rayrangers.raytracer.algorithm.Renderer;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.parser.ObjParser;
import rayrangers.raytracer.view.ViewPane;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

import javax.imageio.ImageIO;

public class Prototype {
    public static void main(String[] args) throws Exception {

        Scene scene = new Scene(Color.BLACK);
        Camera camera = new Camera(new Vertex3D(0, 25, 400), 0, 0, 0, 50, 100, 1000, 1000);
        ViewPane viewPane = camera.getViewPane();

        scene.addCamera(camera);
        Entity teapot = ObjParser.parseObjFile("examples/teapot/Teapot.obj");
        // List<Vertex3D> vlist = new ArrayList<>();
        // vlist.add(new Vertex3D(100, 0, 0));
        // vlist.add(new Vertex3D(0, 100, 0));
        // vlist.add(new Vertex3D(0, 0, 0));

        // Face f = new Triangle(null, null, vlist);

        // List<Face> faces = new ArrayList<>();
        // faces.add(f);

        // Entity triangleEnt = new Entity(null, faces, vlist);
        // scene.addEntity(triangleEnt);

        scene.addEntity(teapot);

        LightSource lightSource1 = new LightSource(0.15, new Vertex3D(0, 50, 200), Color.WHITE);
        scene.addLightSource(lightSource1);

        Renderer renderer = new Renderer(scene, camera.getUuid());
        renderer.render();

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
