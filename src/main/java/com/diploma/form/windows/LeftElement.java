package com.diploma.form.windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class LeftElement {
    private TitledPane titledPane = new TitledPane();
    private VBox vBox = new VBox(5);

    public LeftElement(String name) {
        titledPane = new TitledPane(name, vBox);
    }

    public void addLabel(String name) {
        vBox.getChildren().add(new Label(name));
    }

    public void addElement(Node node) {
        vBox.getChildren().add(node);
    }

    public void setDelete(EventHandler<ActionEvent> eventHandler) {
        Button button = new Button("Удалить");
        vBox.getChildren().add(button);
        button.setOnAction(eventHandler);
    }

    public void setEdit() {

    }

    public TitledPane getTitledPane() {
        return titledPane;
    }
}
