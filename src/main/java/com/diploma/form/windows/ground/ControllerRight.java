package com.diploma.form.windows.ground;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Ground;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.Right;
import com.diploma.form.windows.RightWindowed;
import javafx.scene.control.TextField;

@GroundQ
@Right
public class ControllerRight extends AbstractWindow implements RightWindowed {
    public TextField nameField;


    public ControllerRight() {
        super("fxml/groundRight.fxml");
    }

    public void clear() {
        nameField.clear();
    }

    public void add() {
        Command.insert(new Ground(1, nameField.getText()));
        ((ControllerLeft) otherController).refreshList();
    }


}
