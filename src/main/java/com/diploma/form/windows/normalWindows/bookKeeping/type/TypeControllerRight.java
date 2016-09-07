package com.diploma.form.windows.normalWindows.bookKeeping.type;


import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.TypeChanges;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.RightWindowed;
import javafx.scene.control.TextField;

public class TypeControllerRight extends AbstractWindow implements RightWindowed {
    public TextField nameField;


    public TypeControllerRight() {
        super("fxml/typeRight.fxml");
    }


    @Override
    public void clear() {
        nameField.clear();
    }

    @Override
    public void add() {
        Command.insert(new TypeChanges(1,nameField.getText()));
        ((TypeControllerLeft) otherController).refreshList();
    }
}
