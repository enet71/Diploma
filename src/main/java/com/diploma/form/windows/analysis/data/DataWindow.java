package com.diploma.form.windows.analysis.data;

import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.scene.layout.BorderPane;

public class DataWindow implements DoubleWindow {
    public static ControllerLeft controllerLeft;
    public static ControllerRight controllerRight;
    private BorderPane leftBorderPane = new BorderPane();
    private BorderPane rightBorderPane = new BorderPane();


    public DataWindow(){
        controllerLeft = new ControllerLeft("fxml/dataLeft.fxml");
        controllerRight = new ControllerRight("fxml/dataRight.fxml");
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

