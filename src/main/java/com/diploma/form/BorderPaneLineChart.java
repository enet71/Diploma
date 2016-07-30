package com.diploma.form;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class BorderPaneLineChart extends BorderPane {
    BorderPaneLineChart(Node node) {
        this.setCenter(node);
    }

    public static BorderPane getPane() {
        return new BorderPaneLineChart(new Button("Hello"));
    }
}
