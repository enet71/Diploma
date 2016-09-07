package com.diploma.form.windows.normalWindows.data.ground;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Ground;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.RightWindowed;
import javafx.scene.control.TextField;

public class GroundControllerRight extends AbstractWindow implements RightWindowed {
    public TextField nameField;


    public GroundControllerRight() {
        super("fxml/groundRight.fxml");
    }

    public void clear() {
        nameField.clear();
    }

    public void add() {
        Command.insert(new Ground(1, nameField.getText()));
        ((GroundControllerLeft) otherController).refreshList();
    }


}
