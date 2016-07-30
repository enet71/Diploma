package com.diploma.form.windows.htmlReader;

import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.scene.layout.BorderPane;

public class htmlReaderWindow implements DoubleWindow{
    public static ControllerLeft controllerLeft;
    public static ControllerRight controllerRight;
    private BorderPane leftBorderPane = new BorderPane();
    private BorderPane rightBorderPane = new BorderPane();


    public htmlReaderWindow(){
        controllerLeft = new ControllerLeft("fxml/htmlReaderLeft.fxml");
        controllerRight = new ControllerRight("fxml/htmlReaderRight.fxml");
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
