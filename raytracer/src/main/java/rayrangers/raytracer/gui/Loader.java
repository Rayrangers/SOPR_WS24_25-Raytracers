package rayrangers.raytracer.gui;

import io.qt.core.QFile;
import io.qt.core.QIODevice;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QPushButton;
import io.qt.widgets.QWidget;
import io.qt.widgets.QMessageBox;
import io.qt.widgets.tools.QUiLoader;

/**
 * Loads the design information for the GUI provided by the UI file.
 */
public class Loader extends QMainWindow {
    private QUiLoader loader;
    private QFile uiFile;
    private QWidget ui;
    private QWidget centralWidget;

    public Loader() {
        loader = new QUiLoader();
        
        uiFile = new QFile("frontend/mainGui.ui");
        // System.out.println("UI file path: " + uiFile.fileName());    // Only for debbuging
        if (!uiFile.open(QIODevice.OpenModeFlag.ReadOnly)) {
            System.out.println("Failed to open UI file.");
            return;
        }

        ui = loader.load(uiFile, this);
        uiFile.close();
        if (ui == null) {
            System.out.println("Failed to load UI.");
            return;
        } else {
            System.out.println("UI loaded successfully.");
        }
        
        centralWidget = ui.findChild(QWidget.class, "centralwidget");
        if (centralWidget != null) {
            System.out.println("Central widget found.");
            setCentralWidget(centralWidget);
        } else {
            System.out.println("Central widget not found.");
            return;
        }

        QPushButton objectPlusButton = centralWidget.findChild(QPushButton.class, "object_plus_Button");
        if (objectPlusButton != null) {
            System.out.println("objectPlusButton was found!");
            objectPlusButton.clicked.connect(() -> {
                System.out.println("Test Button Clicked");
                QMessageBox.information(this, "Info", "Button Clicked!");
            });
        } else {
            System.out.println("Test Button not found.");
        }
    }
} 
