package rayrangers.raytracer;

import io.qt.widgets.QApplication;
import rayrangers.raytracer.gui.Loader;

public class Main {
    public static void main(String[] args) {
        QApplication.initialize(args);

        Loader window = new Loader();
        window.show();

        QApplication.exec();
        QApplication.shutdown();
    }
}