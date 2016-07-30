package com.diploma.form.windows.bookKeeping.type;


import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    public TextField nameField;


    public ControllerRight(String path) {
        super(path);
    }

    @FXML
    private void clear() {
        nameField.clear();

    }

    @FXML
    private void add() throws SQLException, IOException {
        ModelType.insertType(nameField.getText());
        TypeWindow.controllerLeft.refreshListTypeChanges();
    }


    public void initialize(URL location, ResourceBundle resources) {
        TypeWindow.controllerLeft.refreshListTypeChanges();
    }
}
