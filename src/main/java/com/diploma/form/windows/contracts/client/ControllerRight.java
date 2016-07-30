package com.diploma.form.windows.contracts.client;


import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    public TextField nameField;
    public TextField lastNameField;
    public TextField middleNameField;

    public ControllerRight(String path) {
        super(path);
    }

    @FXML
    private void clear() {
        nameField.clear();
        lastNameField.clear();
        middleNameField.clear();

    }

    @FXML
    private void add() throws SQLException, IOException {
        ModelClient.insertClient(nameField.getText(), lastNameField.getText(), middleNameField.getText());
        ClientWindow.controllerLeft.refreshListClient();
    }


    public void initialize(URL location, ResourceBundle resources) {
        ClientWindow.controllerLeft.refreshListClient();
    }
}
