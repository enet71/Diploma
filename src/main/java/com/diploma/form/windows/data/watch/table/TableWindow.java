package com.diploma.form.windows.data.watch.table;

import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.scene.layout.BorderPane;


public class TableWindow implements DoubleWindow {
    private BorderPane leftBorderPane = new BorderPane();
    private BorderPane rightBorderPane = new BorderPane();

    public TableWindow() {
        ControllerLeft controllerLeft = new ControllerLeft("fxml/dataTableLeft.fxml");
        ControllerRight controllerRight = new ControllerRight("fxml/dataTableRight.fxml");
        controllerLeft.setController(controllerRight);
        controllerRight.setController(controllerLeft);
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
