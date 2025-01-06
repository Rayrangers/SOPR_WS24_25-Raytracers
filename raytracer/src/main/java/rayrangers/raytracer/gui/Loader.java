package rayrangers.raytracer.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import io.qt.core.QFile;
import io.qt.widgets.QGraphicsPixmapItem;
import io.qt.widgets.QGraphicsScene;
import io.qt.widgets.QGraphicsView;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QPushButton;
import io.qt.widgets.QStackedWidget;
import io.qt.widgets.QToolButton;
import io.qt.widgets.QWidget;
import io.qt.widgets.tools.QUiLoader;
import rayrangers.raytracer.algorithm.Renderer;
import rayrangers.raytracer.math.TrafoMatrix;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.parser.ObjParser;
import rayrangers.raytracer.view.ViewPane;
import rayrangers.raytracer.world.Camera;
import rayrangers.raytracer.world.Entity;
import rayrangers.raytracer.world.LightSource;
import rayrangers.raytracer.world.Scene;
import io.qt.core.QTimer;
import io.qt.gui.QPixmap;
import io.qt.widgets.QProgressBar;

/**
 * Loads the design information for the GUI provided by the UI file.
 */
public class Loader extends QMainWindow {
    private QUiLoader loader;
    private QFile uiFile;
    private QWidget ui;
    private QProgressBar progressBar;
    private QTimer timer;
    private int progressValue;

    /**
     * Builds the GUI based on the 'mainGUI.ui' file.
     */
    public Loader() {
        loader = new QUiLoader();
        // Load 'mainGUI.ui' file
        uiFile = new QFile("frontend/mainGui.ui");
        ui = loader.load(uiFile, this);
        uiFile.close();
        
        // Load main window
        QStackedWidget centralWidget = ui.findChild(QStackedWidget.class, "stackedWidget");
        centralWidget.setCurrentIndex(0);
        setCentralWidget(centralWidget);
        setWindowTitle("MainWindow");
        
        // Load menu bar
        QWidget menu = ui.findChild(QWidget.class, "menubar");
        setMenuWidget(menu);


        // Load result view
        QPushButton resultButton = centralWidget.findChild(QPushButton.class, "resultButton");
        resultButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(3);
            setWindowTitle("ResultWindow");
        });

        // Load result graphics view (slot for image)
        QGraphicsView resultGraphicsView = centralWidget.findChild(QGraphicsView.class, "graphicsView_5");
        QGraphicsScene scene = new QGraphicsScene(resultGraphicsView);

        // Load scene view (main window from result view)
        QPushButton sceneButton = centralWidget.findChild(QPushButton.class, "sceneButton_result");
        sceneButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(0);
            setWindowTitle("MainWindow");
        });



        // Load progress bar
        progressBar = centralWidget.findChild(QProgressBar.class, "ProgressBar_main");
        
        // Initialize timer
        timer = new QTimer(this);
        timer.timeout.connect(this, "updateProgressBar()");
        
        // Load start button
        QToolButton startButton = centralWidget.findChild(QToolButton.class, "playToolButton");
        if (startButton != null) {
            startButton.clicked.connect(() -> {
                startProgressBar();
                new Thread(() -> {
                    System.out.println("Starting Prototype main");
                    try {
                        runPrototype();
                        System.out.println("Prototype main finished");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
                QPixmap prototype = new QPixmap();
                prototype.load(":/artifacts/prototpye.png");
                QGraphicsPixmapItem item = new QGraphicsPixmapItem(prototype);
                scene.clear();
                scene.addItem(item);
                resultGraphicsView.show();
            });
        } else {
            System.out.println("Start Button not found.");
        }
    }

    private void startProgressBar() {
        progressValue = 0;
        progressBar.setValue(progressValue);
        timer.start(600);
    }

    private void updateProgressBar() {
        progressValue += 1;
        progressBar.setValue(progressValue);
        if (progressValue >= 100) {
            timer.stop();
        }
    }

    private static void runPrototype() throws Exception {
        Scene scene = new Scene(Color.BLACK);
        Camera camera = new Camera(new Vertex3D(0, 25, 400), 0, 0, 0, 75, 100, 2000, 2000);
        ViewPane viewPane = camera.getViewPane();

        scene.addCamera(camera);
        Entity teapot = ObjParser.parseObjFile("examples/teapot/Teapot.obj");
        Entity tuna = ObjParser.parseObjFile("examples/tuna/tuna-low.obj");

        TrafoMatrix tmTea = new TrafoMatrix(-50, -100, 10, -90, 10, -33, 1, 1, 1);
        teapot.transform(tmTea);

        TrafoMatrix tmTuna = new TrafoMatrix(0, 150, 0, -90, 0, 0, 1, 1, 1);
        tuna.transform(tmTuna);

        scene.addEntity(teapot);
        scene.addEntity(tuna);

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
