package rayrangers.raytracer.gui;

import io.qt.core.QFile;
import io.qt.core.QFileInfo;
import io.qt.core.QStringList;
import io.qt.core.QStringListModel;
import io.qt.core.QUrl;
import io.qt.core.Qt.AspectRatioMode;
import io.qt.gui.QAction;
import io.qt.gui.QDesktopServices;
import io.qt.gui.QImage;
import io.qt.gui.QPixmap;
import io.qt.widgets.QApplication;
import io.qt.widgets.QComboBox;
import io.qt.widgets.QDoubleSpinBox;
import io.qt.widgets.QFileDialog;
import io.qt.widgets.QFileDialog.Result;
import io.qt.widgets.QGraphicsPixmapItem;
import io.qt.widgets.QGraphicsScene;
import io.qt.widgets.QGraphicsView;
import io.qt.widgets.QLineEdit;
import io.qt.widgets.QListView;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QMenu;
import io.qt.widgets.QMenuBar;
import io.qt.widgets.QMessageBox;
import io.qt.widgets.QProgressBar;
import io.qt.widgets.QPushButton;
import io.qt.widgets.QSlider;
import io.qt.widgets.QSpinBox;
import io.qt.widgets.QStackedWidget;
import io.qt.widgets.QToolButton;
import io.qt.widgets.QWidget;
import io.qt.widgets.tools.QUiLoader;
import rayrangers.raytracer.math.Vertex3D;
import rayrangers.raytracer.world.Camera;

/**
 * Loads the design information for the GUI provided by the UI file.
 */
public class Loader extends QMainWindow {
    private QUiLoader loader;
    private QFile uiFile;
    private QWidget ui;
    private QImage image;
    private QProgressBar progressBar;

    private QDoubleSpinBox cameraPosX;
    private QDoubleSpinBox cameraPosY;
    private QDoubleSpinBox cameraPosZ;
    private QDoubleSpinBox cameraRoll;
    private QDoubleSpinBox cameraPitch;
    private QDoubleSpinBox cameraYaw;
    private QDoubleSpinBox cameraDistance;
    private QSlider cameraFov;

    /** 
     * List with file names of the objects. 
     */
    private QStringList objectList = new QStringList();

    /**
     * Model for list views.
     */
    private QStringListModel modelForObjectList = new QStringListModel();

    /**
     * Builds the GUI based on the 'mainGUI.ui' file.
     */
    public Loader() {
        loader = new QUiLoader();
        uiFile = new QFile("frontend/mainGui.ui"); // Load 'mainGUI.ui' file
        ui = loader.load(uiFile, this);
        uiFile.close();


        // Starting point to load all the different UI elements (main window) -----------------------------------------------------------------------
        QStackedWidget centralWidget = ui.findChild(QStackedWidget.class, "stackedWidget");
        centralWidget.setCurrentIndex(0);
        setCentralWidget(centralWidget);
        setWindowTitle("Main Window");


        // Load menu bar and its elements -----------------------------------------------------------------------------------------------------------
        QMenuBar menuBar = ui.findChild(QMenuBar.class, "menubar");
        setMenuBar(menuBar);

        // Load menu "file"
        QMenu fileMenu = menuBar.findChild(QMenu.class, "menuDatei");
        
        // Create actions for "file"
        QAction newScene = new QAction(tr("New Scene"), this);
        QAction importScene = new QAction(tr("Import Scene"), this);
        QAction saveScene = new QAction(tr("Export Scene"), this);
        QAction closeApp = new QAction(tr("Close Raytracer"), this);

        // Add actions to the menu "file"
        fileMenu.addAction(newScene);
        fileMenu.addAction(importScene);
        fileMenu.addAction(saveScene);
        fileMenu.addAction(closeApp);

        // Load menu "edit"
        QMenu editMenu = menuBar.findChild(QMenu.class, "menuBearbeiten");

        // Create actions for "edit"
        QAction undo = new QAction(tr("Undo"), this);
        QAction redo = new QAction(tr("Redo"), this);

        // Add actions to the menu "edit"
        editMenu.addAction(undo);
        editMenu.addAction(redo);

        // Load menu "help"
        QMenu helpMenu = menuBar.findChild(QMenu.class, "menuHilfe");

        // Create actions for "help"
        QAction about = new QAction(tr("About"), this);
        QAction help = new QAction(tr("Tutorial"), this);

        // Add actions to the menu "help"
        helpMenu.addAction(about);
        helpMenu.addAction(help);


        // Load the UI elements of the main window --------------------------------------------------------------------------------------------------
        // Elements for "Objects-Configuration"
        QListView mainObjectListView = centralWidget.findChild(QListView.class, "objectListView");
        QPushButton addObjectButton = centralWidget.findChild(QPushButton.class, "object_plus_Button");

        // Elements for "Light-Configuration"
        QListView mainLigthListView = centralWidget.findChild(QListView.class, "lightListView");
        QPushButton addLightButton = centralWidget.findChild(QPushButton.class, "light_plus_Button");

        // Elements for "Camera-Configuration" --------------------------------------------------------------------------------------------------
        cameraPosX = centralWidget.findChild(QDoubleSpinBox.class, "pos_x_doubleSpinBox_conf");
        cameraPosY = centralWidget.findChild(QDoubleSpinBox.class, "pos_y_doubleSpinBox_conf");
        cameraPosZ = centralWidget.findChild(QDoubleSpinBox.class, "pos_z_doubleSpinBox_conf");
        cameraRoll = centralWidget.findChild(QDoubleSpinBox.class, "ang_roll_doubleSpinBox");
        cameraPitch = centralWidget.findChild(QDoubleSpinBox.class, "ang_pitch_doubleSpinBox");
        cameraYaw = centralWidget.findChild(QDoubleSpinBox.class, "ang_yaw_doubleSpinBox");
        cameraDistance = centralWidget.findChild(QDoubleSpinBox.class, "distance_doubleSpinBox");
        cameraFov = centralWidget.findChild(QSlider.class, "fowHorizontalSlider");
                
        // Default Values for camera
        cameraPosX.setValue(400.0);
        cameraPosY.setValue(25.0);
        cameraPosZ.setValue(0.0);
        cameraRoll.setValue(0.0);
        cameraPitch.setValue(90.0);
        cameraYaw.setValue(0.0);
        cameraDistance.setValue(75.0);
        cameraFov.setValue(100);


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

        // Initialize progress bar
        progressBar.setTextVisible(true);
        progressBar.setFormat("Ready");
        progressBar.setValue(0);


        // Load the UI elements of the object configuration window ----------------------------------------------------------------------------------
        // Elements for object and light list and 'objGraphicsView'
        QListView objObjectListView = centralWidget.findChild(QListView.class, "objectListView_conf");
        QListView objLigthListView = centralWidget.findChild(QListView.class, "lightListView_conf");
        QGraphicsView objGraphicsView = centralWidget.findChild(QGraphicsView.class, "graphicsView_obj");

        // Elements for "Object Position"
        //QDoubleSpinBox objectPosX = new QDoubleSpinBox();
        QDoubleSpinBox objectPosX = centralWidget.findChild(QDoubleSpinBox.class, "pos_x_doubleSpinBox_conf");
        QDoubleSpinBox objectPosY = centralWidget.findChild(QDoubleSpinBox.class, "pos_y_doubleSpinBox_conf");
        QDoubleSpinBox objectPosZ = centralWidget.findChild(QDoubleSpinBox.class, "pos_z_doubleSpinBox_conf");

        // Elements for "Object Scaling"
        QDoubleSpinBox objectScaleX = centralWidget.findChild(QDoubleSpinBox.class, "scal_x_doubleSpinBox");
        QDoubleSpinBox objectScaleY = centralWidget.findChild(QDoubleSpinBox.class, "scal_y_doubleSpinBox");
        QDoubleSpinBox objectScaleZ = centralWidget.findChild(QDoubleSpinBox.class, "scal_z_doubleSpinBox");

        // Elements for "Object Rotation"
        QDoubleSpinBox objectRotateX = centralWidget.findChild(QDoubleSpinBox.class, "rot_x_doubleSpinBox");
        QDoubleSpinBox objectRotateY = centralWidget.findChild(QDoubleSpinBox.class, "rot_y_doubleSpinBox");
        QDoubleSpinBox objectRotateZ = centralWidget.findChild(QDoubleSpinBox.class, "rot_z_doubleSpinBox");

        // "Done Button" for object configuration
        QPushButton objectDoneButton = centralWidget.findChild(QPushButton.class, "saveButton_conf");


        // Load the UI elements of the light configuration window -----------------------------------------------------------------------------------
        // Elements for object and light list and 'ligGraphicsView'
        QListView ligObjectListView = centralWidget.findChild(QListView.class, "objectListView_conf_2");
        QListView ligLigthListView = centralWidget.findChild(QListView.class, "lightListView_conf_2");
        QGraphicsView ligGraphicsView = centralWidget.findChild(QGraphicsView.class, "graphicsView_lig");

        // Elements for "Light Position"
        QDoubleSpinBox lightPosX = centralWidget.findChild(QDoubleSpinBox.class, "pos_x_doubleSpinBox_conf_light");
        QDoubleSpinBox lightPosY = centralWidget.findChild(QDoubleSpinBox.class, "pos_y_doubleSpinBox_conf_light");
        QDoubleSpinBox lightPosZ = centralWidget.findChild(QDoubleSpinBox.class, "pos_z_doubleSpinBox_conf_light");

        // Elements for "Light Intensity" and "Light Color"
        QDoubleSpinBox lightIntensity = centralWidget.findChild(QDoubleSpinBox.class, "doubleSpinBox");
        QComboBox lightColor = centralWidget.findChild(QComboBox.class, "comboBox_light_color");

        // "Done Button" for light configuration
        QPushButton lightDoneButton = centralWidget.findChild(QPushButton.class, "saveButton_conf_2");


        // Load the UI elements of the result window ------------------------------------------------------------------------------------------------
        // Elements for "Information about Rendering"
        QLineEdit numberRays = centralWidget.findChild(QLineEdit.class, "rays_lineEdit");
        QLineEdit numberObjects = centralWidget.findChild(QLineEdit.class, "objectsLineEdit_info");
        QLineEdit numberLightSource = centralWidget.findChild(QLineEdit.class, "lightSourceLineEdit_info");
        QLineEdit renderTime = centralWidget.findChild(QLineEdit.class, "renderTimeLineEdit_info");
        QLineEdit qualtiyInfo = centralWidget.findChild(QLineEdit.class, "qualityLineEdit_info");

        QToolButton deleteButton = centralWidget.findChild(QToolButton.class, "deleteButton");
        QPushButton exportButtonResult = centralWidget.findChild(QPushButton.class, "exportButton_result");
        QPushButton sceneButton = centralWidget.findChild(QPushButton.class, "sceneButton_result");

        // Element for result (slot for image)
        QGraphicsView resultGraphicsView = centralWidget.findChild(QGraphicsView.class, "graphicsView_5");
        QGraphicsScene scene = new QGraphicsScene(resultGraphicsView);

        
        // Add functionality to the UI elements of the main window ----------------------------------------------------------------------------------
        
        // Switches to the object configuration window and opens file picker
        addObjectButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(1);
            setWindowTitle("Object Configuration");
            Result<String> fileName = QFileDialog.getOpenFileName(centralWidget, tr("Open Object-File"), "examples/", tr("Object Files (*.obj)"));
            Worker.addEntity(fileName.result);

            QFileInfo info = new QFileInfo(fileName.result);
            objectList.add(info.baseName());

            modelForObjectList.setStringList(objectList);
            // Insert list into different 'ListViews'
            mainObjectListView.setModel(modelForObjectList);
            objObjectListView.setModel(modelForObjectList);
            ligObjectListView.setModel(modelForObjectList);
        });

        // Switches to the light configuration window
        addLightButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(2);
            setWindowTitle("Light Configuration");
            
        });

        // Add item to comboBoxes
        renderingAntiAliasing.addItem("Yes");
        renderingAntiAliasing.addItem("No");
        renderingShading.addItem("Yes");
        renderingShading.addItem("No");

        // TODO: Add functionality to the comboBoxes
        renderingShading.setEnabled(false);
        renderingAntiAliasing.setEnabled(false);
        renderingPandeWidth.setEnabled(false);


        // Connect comboBoxes to functions
        renderingAntiAliasing.currentIndexChanged.connect((index) -> {
            if ("Yes".equals(renderingAntiAliasing.currentText())) {
                System.out.println("Antialiasing enabled");
            } else {
                System.out.println("Antialiasing disabled");
            }
        });
        
        renderingShading.currentIndexChanged.connect((index) -> {
            if ("Yes".equals(renderingShading.currentText())) {
                System.out.println("Shading enabled");
            } else {
                System.out.println("Shading disabled");
            }
        });

        // Switches to the result window
        resultButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(3);
            setWindowTitle("Result Window");
        });

        // Imports a scene in JSON-format
        importButton.clicked.connect(() -> {
            Result<String> fileName = QFileDialog.getOpenFileName(centralWidget, tr("Open JSON-File"), "artifacts/", tr("JSON Files (*.json)"));
            QFileInfo path = new QFileInfo(fileName.result);
            startProgressBar();
            startButton.setEnabled(false);
            new Thread(() -> {
                System.out.println(path.filePath());
                Worker.renderJSON(path.filePath());

                double renderTimeResult = Worker.getRenderTime();
                renderTime.setText(String.format("%.2f", renderTimeResult) + "s");

                int objects = Worker.getObjectsCount();
                numberObjects.setText(String.valueOf(objects));

                int lightSource = Worker.getLightSourcesCount();
                numberLightSource.setText(String.valueOf(lightSource));

                int rays = Worker.getRaysCount();
                numberRays.setText(String.valueOf(rays));

                QGraphicsPixmapItem item = new QGraphicsPixmapItem(QPixmap.fromImage(image));
                scene.clear();
                scene.addItem(item);
                resultGraphicsView.setScene(scene);
                resultGraphicsView.fitInView(item, AspectRatioMode.KeepAspectRatioByExpanding);
                startButton.setEnabled(true);
                stopProgressBar();
                progressBar.setTextVisible(true);
                // Set text to "Rendering finished"
                progressBar.setFormat("Rendering finished");
                // Show message box that rendering is finished
                QMessageBox.information(this, "Finished", "Raytracing finished!");
                // Wait 5 seconds before setting text to "Ready" again
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setFormat("Ready");
            }).start();
        });


        // Add functionality to the UI elements of the object configuration window ------------------------------------------------------------------
        
        objectPosX.valueChanged.connect(() -> {
            System.out.println("Test");
        });
        
        
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


        // Add functionality to the UI elements of the result window --------------------------------------------------------------------------------
        // Switches back to the main window
        sceneButton.clicked.connect(() -> {
            centralWidget.setCurrentIndex(0);
            setWindowTitle("Main Window");
        });

        deleteButton.clicked.connect(() -> {
            scene.clear();
            resultGraphicsView.setScene(scene);
        });

        /**
         * Exports the rendered image as a PNG file.
         */

        exportButtonResult.clicked.connect(() -> {
            if (image == null) {
                QMessageBox.critical(this, "Error", "No image was rendered yet.");
            } else {
                Result<String> result = QFileDialog.getSaveFileName(
            this,
            tr("Export Image"),
            "artifacts/",
            tr("PNG-File (*.png)")
        );

        if (result != null && result.result != null && !result.result.isEmpty()) {
            String fileName = result.result;
            
            // Check if the file name ends with ".png"
            if (!fileName.toLowerCase().endsWith(".png")) {
                fileName += ".png";
            }
            // Save the image
            boolean saved = image.save(fileName, "PNG");
            // Show a message box with the result of saving the image
            if (!saved) {
                QMessageBox.critical(this, "Error", "Error while saving the image.");
            } else {
                QMessageBox.information(this, "Success", "The image was saved successfully.");
            }
            } else {
                QMessageBox.warning(this, "Cancelled", "The image was not saved.");
            }
        }
        });


        // Add functionality to the UI elements of the menu bar -------------------------------------------------------------------------------------
        // "File" -> "New Scene"
        newScene.triggered.connect(() -> {
            System.out.println("New Scene");
        });

        // "File" -> "Import Scene"
        importScene.triggered.connect(() -> {
            System.out.println("Import Scene is not yet implemented :(");
        });

        // "File" -> "Export Scene"
        saveScene.triggered.connect(() -> {
            System.out.println("Export Scene is not yet implemented :(");
        });

        // "File" -> "Close App"
        closeApp.triggered.connect(() -> {
            // Perform multiple actions
            System.out.println("Closing application");
            QApplication.quit();
        });

        // "Edit" -> "Undo"
        undo.triggered.connect(() -> {
            System.out.println("Undo is not yet implemented :(");
        });

        // "Edit" -> "Redo"
        redo.triggered.connect(() -> {
            System.out.println("Redo is not yet implemented :(");
        });

        // "Help" -> "About"
        about.triggered.connect(() -> {
            QMessageBox aboutBox = new QMessageBox(this);
            aboutBox.setWindowTitle("About");
            aboutBox.setText("Version: 1.0\nDeveloped by: Team Raytracers\nContributors: Jan Birkert, Chantal Deusch, Adrian Desiderato, Jan Gaschler, David Geri, Paulina Pyczot, Lisa Korntheuer");
            aboutBox.exec();
        });

        // "Help" -> "Tutorial"
        help.triggered.connect(() -> {
            int answer = QMessageBox.question(
            this,
            "Tutorial - GitHub-Wiki",
            "This will open your browser.\n Proceed?",
            QMessageBox.StandardButton.Yes,
            QMessageBox.StandardButton.No
            );
            if (answer == QMessageBox.StandardButton.Yes.value()) {
                QDesktopServices.openUrl(new QUrl("https://github.com/Rayrangers/SOPR_WS24_25-Raytracers/wiki"));
            }
        });


        // Enable 'startButton'
        if (startButton != null) {
            startButton.clicked.connect(() -> {
                startProgressBar();
                startButton.setEnabled(false);
                new Thread(() -> {
                    System.out.println("Starting Prototype main");
                    Camera camera = getCameraFromGUI();
                    try {
                        image = Worker.invokePrototype(camera);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Prototype main finished");

                    double renderTimeResult = Worker.getRenderTime();
                    renderTime.setText(String.format("%.2f", renderTimeResult) + "s");

                    int objects = Worker.getObjectsCount();
                    numberObjects.setText(String.valueOf(objects));

                    int lightSource = Worker.getLightSourcesCount();
                    numberLightSource.setText(String.valueOf(lightSource));

                    int rays = Worker.getRaysCount();
                    numberRays.setText(String.valueOf(rays));

                    QGraphicsPixmapItem item = new QGraphicsPixmapItem(QPixmap.fromImage(image));
                    scene.clear();
                    scene.addItem(item);
                    resultGraphicsView.setScene(scene);
                    resultGraphicsView.fitInView(item, AspectRatioMode.KeepAspectRatioByExpanding);
                    startButton.setEnabled(true);
                    stopProgressBar();
                    progressBar.setTextVisible(true);
                    // Set text to "Rendering finished"
                    progressBar.setFormat("Rendering finished");
                    // Show message box that rendering is finished
                    QMessageBox.information(this, "Finished", "Raytracing finished!");
                    // Wait 5 seconds before setting text to "Ready" again
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.setFormat("Ready");
                }).start();
            });
        } else {
            System.out.println("Start Button not found.");
        }
    }

    /**
     * Starts the progress bar.
     */

    private void startProgressBar() {
        progressBar.setMinimum(0);
        progressBar.setMaximum(0);
    }

    /**
     * Stops the progress bar and sets the text to "Ready".
     */

     private void stopProgressBar() {
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
    }

    private Camera getCameraFromGUI() {
        Vertex3D position = new Vertex3D(
            cameraPosX.value(),
            cameraPosY.value(),
            cameraPosZ.value()
        );
        double roll = cameraRoll.value();
        double pitch = cameraPitch.value();
        double yaw = cameraYaw.value();
        double distance = cameraDistance.value();

        return new Camera(position, roll, pitch, yaw, distance, 100, 2000, 2000);
    }
    
}
