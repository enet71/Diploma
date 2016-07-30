package com.diploma.form.windows.data.add.ground;


import com.diploma.form.windows.doubleWindow.DoubleWindow;
import com.diploma.form.windows.ControllerLeft;
import javafx.scene.layout.BorderPane;

public class GroundWindow implements DoubleWindow {
    public static ControllerLeft controllerLeft;
    public static ControllerRight controllerRight;
    private BorderPane leftBorderPane = new BorderPane();
    private BorderPane rightBorderPane = new BorderPane();


    public GroundWindow(){
        controllerLeft = new ControllerLeft("fxml/panelLeft.fxml");
        controllerRight = new ControllerRight("fxml/groundRight.fxml");
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
