package rayrangers.raytracer.gui;

import io.qt.core.QFile;
import io.qt.widgets.QComboBox;
import io.qt.widgets.QFileDialog;
import io.qt.widgets.QFileDialog.Result;
import io.qt.widgets.QGraphicsPixmapItem;
import io.qt.widgets.QGraphicsScene;
import io.qt.widgets.QGraphicsView;
import io.qt.widgets.QListView;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QPushButton;
import io.qt.widgets.QSlider;
import io.qt.widgets.QSpinBox;
import io.qt.widgets.QStackedWidget;
import io.qt.widgets.QToolButton;
import io.qt.widgets.QWidget;
import io.qt.widgets.tools.QUiLoader;
import io.qt.core.QTimer;
import io.qt.core.Qt.AspectRatioMode;
import io.qt.gui.QImage;
import io.qt.gui.QPixmap;
import io.qt.widgets.QProgressBar;

/**
 * Loads the design information for the GUI provided by the UI file.
 */
public class Loader extends QMainWindow {
    private QUiLoader loader;
    private QFile uiFile;
    private QWidget ui;
    private QImage image;
    private QProgressBar progressBar;
    private QTimer timer;
    private int progressValue;

    /**
     * Builds the GUI based on the 'mainGUI.ui' file.
     */
    public Loader() {
        loader = new QUiLoader();
        uiFile = new QFile("frontend/mainGui.ui"); // Load 'mainGUI.ui' file
        ui = loader.load(uiFile, this);
        uiFile.close();


        // Starting point to load all the different UI elements -------------------------------------------------------------------------------------
        // Load menu bar
        QWidget menu = ui.findChild(QWidget.class, "menubar");
        setMenuWidget(menu);
        
        // Load main window
        QStackedWidget centralWidget = ui.findChild(QStackedWidget.class, "stackedWidget");
        centralWidget.setCurrentIndex(0);
        setCentralWidget(centralWidget);
        setWindowTitle("Main Window");


        // Load the UI elements of the main window --------------------------------------------------------------------------------------------------
        // Elements for "Objects-Configuration"
        QListView objecListView = centralWidget.findChild(QListView.class, "objectListView");
        QPushButton addObjectButton = centralWidget.findChild(QPushButton.class, "object_plus_Button");

        // Elements for "Light-Configuration"
        QListView ligthListView = centralWidget.findChild(QListView.class, "lightListView");
        QPushButton addLightButton = centralWidget.findChild(QPushButton.class, "light_plus_Button");

        // Elements for "Camera-Configuration"
        QSpinBox cameraPosX = centralWidget.findChild(QSpinBox.class, "pos_x_spinBox");
        QSpinBox cameraPosY = centralWidget.findChild(QSpinBox.class, "pos_y_spinBox");
        QSpinBox cameraPosZ = centralWidget.findChild(QSpinBox.class, "pos_z_spinBox");
        QSpinBox cameraRoll = centralWidget.findChild(QSpinBox.class, "ang_roll_spinBox");
        QSpinBox cameraPitch = centralWidget.findChild(QSpinBox.class, "ang_pitch_spinBox");
        QSpinBox cameraYaw = centralWidget.findChild(QSpinBox.class, "ang_yaw_spinBox");
        QSpinBox cameraDistance = centralWidget.findChild(QSpinBox.class, "distance_spinBox");
        QSlider cameraFov = centralWidget.findChild(QSlider.class, "fowHorizontalSlider");

        // Elements for "Rendering-Configuration"
        QSpinBox renderingResWidth = centralWidget.findChild(QSpinBox.class, "res_width_spinBox");          // also for camera needed
        QSpinBox renderingResHeight = centralWidget.findChild(QSpinBox.class, "res_height_spinBox");        // also for camera needed
        QComboBox renderingPandeWidth = centralWidget.findChild(QComboBox.class, "comboBox_pane_width");    // also for camera needed
        QComboBox renderingAntiAliasing = centralWidget.findChild(QComboBox.class, "comboBox_anti_aliasing");
        QComboBox renderingShading = centralWidget.findChild(QComboBox.class, "comboBox_shading");

        QPushButton resultButton = centralWidget.findChild(QPushButton.class, "resultButton");
        QGraphicsView mainGraphicsView = centralWidget.findChild(QGraphicsView.class, "graphicsView_main_window");
        QToolButton startButton = centralWidget.findChild(QToolButton.class, "playToolButton");
        QToolButton importButton = centralWidget.findChild(QToolButton.class, "importToolButton");
        QToolButton exportButton = centralWidget.findChild(QToolButton.class, "exportToolButton");
        progressBar = centralWidget.findChild(QProgressBar.class, "ProgressBar_main");


        // Load the UI elements of the object configuration window ----------------------------------------------------------------------------------



        // Load the UI elements of the light configuration window -----------------------------------------------------------------------------------



        // Load the UI elements of the result window ------------------------------------------------------------------------------------------------


        
        // Add functionality to the UI elements of the main window ----------------------------------------------------------------------------------
        // Enable 'addObjectButton' (switches to the object configuration window and opens file picker)
        addObjectButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(1);
            setWindowTitle("Object Configuration");
            Result<String> fileName = QFileDialog.getOpenFileName(centralWidget, tr("Open Object-File"), "examples/");
        });

        // Enable 'addLightButton' (switches to the light configuration window)
        addLightButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(2);
            setWindowTitle("Light Configuration");
        });

        // Enable 'resultButton' (switches to the result window)
        resultButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(3);
            setWindowTitle("Result Window");
        });

        // Enable 'importButton' (import a scene in JSON-format)
        importButton.clicked.connect(() -> {
            System.out.println("Import Button clicked");
        });




        // Jump back to main window after object configuration
        QPushButton objectDoneButton = centralWidget.findChild(QPushButton.class, "saveButton_conf");
        objectDoneButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(0);
            setWindowTitle("Main Window");
        });

        // Jump back to main window after light configuration
        QPushButton lightDoneButton = centralWidget.findChild(QPushButton.class, "saveButton_conf_2");
        lightDoneButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(0);
            setWindowTitle("Main Window");
        });

        // Jump back to main window from result window
        QPushButton sceneButton = centralWidget.findChild(QPushButton.class, "sceneButton_result");
        sceneButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(0);
            setWindowTitle("Main Window");
        });

        

        // Load result graphics view (slot for image)
        QGraphicsView resultGraphicsView = centralWidget.findChild(QGraphicsView.class, "graphicsView_5");
        QGraphicsScene scene = new QGraphicsScene(resultGraphicsView);


        // Initialize timer
        timer = new QTimer(this);
        // TODO: Fix this
        timer.timeout.connect(this, "updateProgressBar()");

        // Enable 'startButton'
        if (startButton != null) {
            startButton.clicked.connect(() -> {
                startProgressBar();
                startButton.setEnabled(false);
                new Thread(() -> {
                    System.out.println("Starting Prototype main");
                    try {
                        image = Worker.invokePrototype();
                        System.out.println("Prototype main finished");
                        QGraphicsPixmapItem item = new QGraphicsPixmapItem(QPixmap.fromImage(image));
                        scene.clear();
                        scene.addItem(item);
                        resultGraphicsView.setScene(scene);
                        resultGraphicsView.fitInView(item, AspectRatioMode.KeepAspectRatioByExpanding);
                        startButton.setEnabled(true);
                        timer.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            });
        } else {
            System.out.println("Start Button not found.");
        }
    }


    private void startProgressBar() {
        progressValue = 0;
        progressBar.setValue(progressValue);
        timer.start(1000);
    }

    // TODO: No Progess, just timer
     private void updateProgressBar() {
        progressValue += 1;
        progressBar.setValue(progressValue);
        if (progressValue >= 100) {
            timer.stop();
        }
    }
}
