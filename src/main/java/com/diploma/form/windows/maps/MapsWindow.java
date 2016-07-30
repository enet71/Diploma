package com.diploma.form.windows.maps;

import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class MapsWindow implements DoubleWindow {
    private BorderPane rightBorderPane = new BorderPane();
    private BorderPane leftBorderPane = new BorderPane();
    public static ControllerLeft controllerLeft;
    public static ControllerRight controllerRight;

    public MapsWindow() throws IOException {
        controllerLeft = new ControllerLeft("fxml/mapLeft.fxml");
        controllerRight = new ControllerRight("fxml/mapRight.fxml");
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
