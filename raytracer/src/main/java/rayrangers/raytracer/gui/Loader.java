package rayrangers.raytracer.gui;

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
import rayrangers.raytracer.Prototype;
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
                        Prototype.main(new String[]{});
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
}
