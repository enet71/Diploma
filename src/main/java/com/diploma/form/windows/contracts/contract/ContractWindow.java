package com.diploma.form.windows.contracts.contract;

import com.diploma.form.windows.ControllerLeft;
import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ContractWindow implements DoubleWindow{
    private BorderPane rightBorderPane = new BorderPane();
    private BorderPane leftBorderPane = new BorderPane();
    public static ControllerLeft controllerLeft;
    public static ControllerRight controllerRight;

    public ContractWindow() throws IOException {
        controllerLeft = new ControllerLeft("fxml/panelLeft.fxml");
        controllerRight = new ControllerRight("fxml/contractRight.fxml");
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
