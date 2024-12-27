package rayrangers.raytracer.gui;

import io.qt.core.QFile;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QPushButton;
import io.qt.widgets.QStackedWidget;
import io.qt.widgets.QToolButton;
import io.qt.widgets.QWidget;
import io.qt.widgets.QMessageBox;
import io.qt.widgets.tools.QUiLoader;
import rayrangers.raytracer.Prototype;

/**
 * Loads the design information for the GUI provided by the UI file.
 */
public class Loader extends QMainWindow {
    private QUiLoader loader;
    private QFile uiFile;
    private QWidget ui;

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

        // Load start button
        QToolButton startButton = centralWidget.findChild(QToolButton.class, "playToolButton");
        if (startButton != null) {
            startButton.clicked.connect(() -> {
                System.out.println("Starting Prototype");
                Prototype.main(new String[]{});
            });
        } else {
            System.out.println("Start Button not found.");
        }
    }
} 
