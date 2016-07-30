package com.diploma.form.windows.bookKeeping.chart;

import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.scene.layout.BorderPane;


public class BookKeepingChartWindow implements DoubleWindow {
    private BorderPane rightBorderPane = new BorderPane();
    private BorderPane leftBorderPane = new BorderPane();
    public static ControllerLeft controllerLeft;
    public static ControllerRight controllerRight;
    public BookKeepingChartWindow(){
        controllerLeft = new ControllerLeft("fxml/chartLeft.fxml");
        controllerRight = new ControllerRight("fxml/chartRight.fxml");
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
