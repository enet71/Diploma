package com.diploma.form.windows.bookKeeping.keeping;


import com.diploma.form.windows.ControllerLeft;
import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.scene.layout.BorderPane;

public class KeepingWindow implements DoubleWindow {
    public static ControllerLeft controllerLeft;
    public static ControllerRight controllerRight;
    private BorderPane leftBorderPane = new BorderPane();
    private BorderPane rightBorderPane = new BorderPane();


    public KeepingWindow(){
        controllerLeft = new ControllerLeft("fxml/panelLeft.fxml");
        controllerRight = new ControllerRight("fxml/keepingRight.fxml");
        leftBorderPane.setCenter(controllerLeft);
        rightBorderPane.setCenter(controllerRight);
    }
    @Override
    public BorderPane getLeftBorderPane() {
        return leftBorderPane;
    }

    @Override
    public BorderPane getRightBorderPane() {
        return rightBorderPane;
    }

    @Override
    public String getName() {
        return null;
    }
}
