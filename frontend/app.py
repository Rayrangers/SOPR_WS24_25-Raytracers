import sys
from PyQt6 import QtWidgets, uic
from pathlib import Path

class SimpleApp(QtWidgets.QMainWindow):
    def __init__(self):
        super().__init__()
        uic.loadUi('frontend/mainGui.ui', self)
        print(Path().cwd())

        self.object_plus_Button.clicked.connect(self.showConfigurationPage)
        self.sceneButton_result.clicked.connect(self.showMainPage)
        self.saveButton_conf.clicked.connect(self.showMainPage)
        self.resultButton.clicked.connect(self.showResultPage)
        self.resultButton_conf.clicked.connect(self.showResultPage)

    def showConfigurationPage(self):
        self.stackedWidget.setCurrentIndex(1) 

    def showMainPage(self):
        self.stackedWidget.setCurrentIndex(0) 

    def showResultPage(self):
        self.stackedWidget.setCurrentIndex(2)

if __name__ == "__main__":
    app = QtWidgets.QApplication(sys.argv)
    window = SimpleApp()
    window.show()
    sys.exit(app.exec())
