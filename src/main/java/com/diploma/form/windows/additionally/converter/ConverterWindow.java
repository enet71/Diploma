package com.diploma.form.windows.additionally.converter;

import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.scene.layout.BorderPane;

public class ConverterWindow implements DoubleWindow {
    public static ControllerRight controllerRight;
    private BorderPane rightBorderPane = new BorderPane();

    public ConverterWindow() {
        controllerRight = new ControllerRight("fxml/converterW.fxml");
        rightBorderPane.setCenter(controllerRight);
    }

    @Override
    public BorderPane getLeftBorderPane() {
        return null;
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
