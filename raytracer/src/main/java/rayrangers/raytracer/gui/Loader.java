package rayrangers.raytracer.gui;

import io.qt.core.QFile;
import io.qt.widgets.QButtonGroup;
import io.qt.widgets.QComboBox;
import io.qt.widgets.QFileDialog;
import io.qt.widgets.QFileDialog.Result;
import io.qt.widgets.QGraphicsPixmapItem;
import io.qt.widgets.QGraphicsScene;
import io.qt.widgets.QGraphicsView;
import io.qt.widgets.QListView;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QMenu;
import io.qt.widgets.QMenuBar;
import io.qt.widgets.QPushButton;
import io.qt.widgets.QSlider;
import io.qt.widgets.QSpinBox;
import io.qt.widgets.QStackedWidget;
import io.qt.widgets.QToolButton;
import io.qt.widgets.QWidget;
import io.qt.widgets.tools.QUiLoader;
import io.qt.core.QTimer;
import io.qt.core.Qt.AspectRatioMode;
import io.qt.gui.QAction;
import io.qt.gui.QImage;
import io.qt.gui.QPixmap;
import io.qt.widgets.QProgressBar;
import io.qt.widgets.QLineEdit;

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
        QMenuBar menuBar = ui.findChild(QMenuBar.class, "menubar");
        setMenuBar(menuBar);

        QMenu fileMenu = menuBar.findChild(QMenu.class, "menuDatei");
        
        QAction newScene = ui.findChild(QAction.class, "actionNeue_Szene");
        fileMenu.addAction(newScene);
        
        
        // Load main window
        QStackedWidget centralWidget = ui.findChild(QStackedWidget.class, "stackedWidget");
        centralWidget.setCurrentIndex(0);
        setCentralWidget(centralWidget);
        setWindowTitle("Main Window");


        // Load the UI elements of the main window --------------------------------------------------------------------------------------------------
        // Elements for "Objects-Configuration"
        QListView mainObjectListView = centralWidget.findChild(QListView.class, "objectListView");
        QPushButton addObjectButton = centralWidget.findChild(QPushButton.class, "object_plus_Button");

        // Elements for "Light-Configuration"
        QListView mainLigthListView = centralWidget.findChild(QListView.class, "lightListView");
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
        // Elements for object and light list and 'objGraphicsView'
        QListView objObjectListView = centralWidget.findChild(QListView.class, "objectListView_conf");
        QListView objLigthListView = centralWidget.findChild(QListView.class, "lightListView_conf");
        QGraphicsView objGraphicsView = centralWidget.findChild(QGraphicsView.class, "graphicsView_obj");

        // Elements for "Object Position"
        QSpinBox objectPosX = centralWidget.findChild(QSpinBox.class, "pos_x_spinBox_conf");
        QSpinBox objectPosY = centralWidget.findChild(QSpinBox.class, "pos_y_spinBox_conf");
        QSpinBox objectPosZ = centralWidget.findChild(QSpinBox.class, "pos_z_spinBox_conf");

        // Elements for "Object Scaling"
        QSpinBox objectScaleX = centralWidget.findChild(QSpinBox.class, "scal_x_spinBox");
        QSpinBox objectScaleY = centralWidget.findChild(QSpinBox.class, "scal_y_spinBox");
        QSpinBox objectScaleZ = centralWidget.findChild(QSpinBox.class, "scal_z_spinBox");

        // Elements for "Object Rotation"
        QSpinBox objectRotateX = centralWidget.findChild(QSpinBox.class, "rot_x_spinBox");
        QSpinBox objectRotateY = centralWidget.findChild(QSpinBox.class, "rot_y_spinBox");
        QSpinBox objectRotateZ = centralWidget.findChild(QSpinBox.class, "rot_z_spinBox");

        // "Done Button" for object configuration
        QPushButton objectDoneButton = centralWidget.findChild(QPushButton.class, "saveButton_conf");


        // Load the UI elements of the light configuration window -----------------------------------------------------------------------------------
        // Elements for object and light list and 'ligGraphicsView'
        QListView ligObjectListView = centralWidget.findChild(QListView.class, "objectListView_conf_2");
        QListView ligLigthListView = centralWidget.findChild(QListView.class, "lightListView_conf_2");
        QGraphicsView ligGraphicsView = centralWidget.findChild(QGraphicsView.class, "graphicsView_lig");

        // Elements for "Light Position"
        QSpinBox lightPosX = centralWidget.findChild(QSpinBox.class, "pos_x_spinBox_conf_light");
        QSpinBox lightPosY = centralWidget.findChild(QSpinBox.class, "pos_y_spinBox_conf_light");
        QSpinBox lightPosZ = centralWidget.findChild(QSpinBox.class, "pos_z_spinBox_conf_light");

        // Elements for "Light Intensity" and "Light Color"
        QSpinBox lightIntensity = centralWidget.findChild(QSpinBox.class, "spinBox_light_intensity");
        QComboBox lightColor = centralWidget.findChild(QComboBox.class, "comboBox_light_color");

        // "Done Button" for light configuration
        QPushButton lightDoneButton = centralWidget.findChild(QPushButton.class, "saveButton_conf_2");


        // Load the UI elements of the result window ------------------------------------------------------------------------------------------------
        QLineEdit numberRays = centralWidget.findChild(QLineEdit.class, "rays_lineEdit");
        QLineEdit numberObjects = centralWidget.findChild(QLineEdit.class, "objectsLineEdit_info");
        QLineEdit numberLightSource = centralWidget.findChild(QLineEdit.class, "lightSourceLineEdit_info");
        QLineEdit renderTime = centralWidget.findChild(QLineEdit.class, "renderTimeLineEdit_info");
        QLineEdit qualtiyInfo = centralWidget.findChild(QLineEdit.class, "qualityLineEdit_info");
        QToolButton deleteButton = centralWidget.findChild(QToolButton.class, "deleteButton");
        QToolButton exportButtonResult = centralWidget.findChild(QToolButton.class, "exportButton_result");
        QPushButton sceneButton = centralWidget.findChild(QPushButton.class, "sceneButton_result");
        // Load result graphics view (slot for image)
        QGraphicsView resultGraphicsView = centralWidget.findChild(QGraphicsView.class, "graphicsView_5");
        QGraphicsScene scene = new QGraphicsScene(resultGraphicsView);

        
        // Add functionality to the UI elements of the main window ----------------------------------------------------------------------------------
        // Switches to the object configuration window and opens file picker
        addObjectButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(1);
            setWindowTitle("Object Configuration");
            Result<String> fileName = QFileDialog.getOpenFileName(centralWidget, tr("Open Object-File"), "examples/");
        });

        // Switches to the light configuration window
        addLightButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(2);
            setWindowTitle("Light Configuration");
        });

        // Switches to the result window
        resultButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(3);
            setWindowTitle("Result Window");
        });

        // Imports a scene in JSON-format
        importButton.clicked.connect(() -> {
            System.out.println("Import Button clicked");
        });


        // Add functionality to the UI elements of the object configuration window ------------------------------------------------------------------
        // Saves the current configuration and switches to the main window
        objectDoneButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(0);
            setWindowTitle("Main Window");
        });


        // Add functionality to the UI elements of the light configuration window -------------------------------------------------------------------
        // Saves the current configuration and switches to the main window
        lightDoneButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(0);
            setWindowTitle("Main Window");
        });

        // Jump back to main window from result window
        QPushButton sceneButton = centralWidget.findChild(QPushButton.class, "sceneButton_result");
        // Switch back to main window
        sceneButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(0);
            setWindowTitle("Main Window");
        });



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
