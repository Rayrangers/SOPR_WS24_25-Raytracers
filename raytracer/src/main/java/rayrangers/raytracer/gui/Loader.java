package rayrangers.raytracer.gui;

import io.qt.core.QFile;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QPushButton;
import io.qt.widgets.QStackedWidget;
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

        // Currently not working
        // QPushButton objectPlusButton = centralWidget.findChild(QPushButton.class, "playToolButton");
        // if (objectPlusButton != null) {
        //     System.out.println("Test button was found!");
        //     objectPlusButton.clicked.connect(() -> {
        //         System.out.println("Test Button Clicked");
        //         QMessageBox.information(this, "Info", "Button Clicked!");
        //     });
        // } else {
        //     System.out.println("Test Button not found.");
        // }
    }
} 
