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
import io.qt.core.QTimer;
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
