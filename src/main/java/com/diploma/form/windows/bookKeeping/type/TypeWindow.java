package com.diploma.form.windows.bookKeeping.type;

import com.diploma.form.windows.ControllerLeft;
import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.scene.layout.BorderPane;

public class TypeWindow implements DoubleWindow {

    public static ControllerLeft controllerLeft;
    public static ControllerRight controllerRight;
    private BorderPane leftBorderPane = new BorderPane();
    private BorderPane rightBorderPane = new BorderPane();


    public TypeWindow() {
        controllerLeft = new ControllerLeft("fxml/panelLeft.fxml");
        controllerRight = new ControllerRight("fxml/typeRight.fxml");
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
