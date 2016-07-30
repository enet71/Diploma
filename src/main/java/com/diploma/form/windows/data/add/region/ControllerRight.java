package com.diploma.form.windows.data.add.region;

import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.Right;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

@RegionQ
@Right
public class ControllerRight extends AbstractWindow {
    public TextField nameField;
    public TextField addressField;


    public ControllerRight() {
        super("fxml/regionRight.fxml");
    }

    public ControllerRight(String path) {
        super(path);
    }

    @FXML
    private void clear() {
        nameField.clear();
        addressField.clear();
    }

    @FXML
    private void add() throws SQLException, IOException {
        ModelRegion.insertRegion(nameField.getText(), addressField.getText());
        ((ControllerLeft) otherController).refreshListRegion();
    }
}
