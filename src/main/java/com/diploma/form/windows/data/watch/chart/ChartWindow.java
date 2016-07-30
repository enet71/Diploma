package com.diploma.form.windows.data.watch.chart;

import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.scene.layout.BorderPane;


public class ChartWindow implements DoubleWindow {
    private BorderPane rightBorderPane = new BorderPane();
    private BorderPane leftBorderPane = new BorderPane();
    public static ControllerLeft controllerLeft;
    public static ControllerRight controllerRight;
    public ChartWindow(){
        controllerLeft = new ControllerLeft("fxml/dataChartLeft.fxml");
        controllerRight = new ControllerRight("fxml/dataChartRight.fxml");
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
