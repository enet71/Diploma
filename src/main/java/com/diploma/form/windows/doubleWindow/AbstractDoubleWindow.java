package com.diploma.form.windows.doubleWindow;

import com.diploma.form.windows.AbstractWindow;
import javafx.scene.layout.BorderPane;

public class AbstractDoubleWindow implements DoubleWindow {
    protected BorderPane leftBorderPane = new BorderPane();
    protected BorderPane rightBorderPane = new BorderPane();
    private String name = "Tab";

    public AbstractDoubleWindow(AbstractWindow leftBorderPane, AbstractWindow rightBorderPane) {
        this.leftBorderPane.setCenter(leftBorderPane);
        this.rightBorderPane.setCenter(rightBorderPane);
        leftBorderPane.setController(rightBorderPane);
        rightBorderPane.setController(leftBorderPane);
    }

    public AbstractDoubleWindow(AbstractWindow leftBorderPane, AbstractWindow rightBorderPane, String name) {
        this.leftBorderPane.setCenter(leftBorderPane);
        this.rightBorderPane.setCenter(rightBorderPane);
        leftBorderPane.setController(rightBorderPane);
        rightBorderPane.setController(leftBorderPane);
        this.name = name;
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
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
