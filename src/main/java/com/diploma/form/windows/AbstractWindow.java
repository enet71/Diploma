package com.diploma.form.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public abstract class AbstractWindow extends BorderPane{
    public AbstractWindow otherController;
    @FXML
    public Accordion accordion;

    public AbstractWindow(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(path));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setController(AbstractWindow controller) {
        otherController = controller;
    }
}
