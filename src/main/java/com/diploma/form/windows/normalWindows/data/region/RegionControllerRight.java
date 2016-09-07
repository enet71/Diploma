package com.diploma.form.windows.normalWindows.data.region;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Region;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.RightWindowed;
import javafx.scene.control.TextField;

public class RegionControllerRight extends AbstractWindow implements RightWindowed {
    public TextField nameField;
    public TextField addressField;

    public RegionControllerRight() {
        super("fxml/regionRight.fxml");
    }

    public void clear() {
        nameField.clear();
        addressField.clear();
    }

    public void add() {
        Command.insert(new Region(1, nameField.getText(), addressField.getText()));
        ((RegionControllerLeft) otherController).refreshList();
    }
}
