package com.diploma.form.windows.doubleWindow;

import javafx.scene.layout.BorderPane;

public interface DoubleWindow {
    BorderPane getLeftBorderPane();
    BorderPane getRightBorderPane();
    String getName();
}
