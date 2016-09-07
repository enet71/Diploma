package com.diploma.form.windows.contracts.client;


import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Client;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.RightWindowed;
import javafx.scene.control.TextField;

public class ClientControllerRight extends AbstractWindow implements RightWindowed {
    public TextField nameField;
    public TextField lastNameField;
    public TextField middleNameField;

    public ClientControllerRight() {
        super("fxml /clientRight.fxml");
    }

    @Override
    public void clear() {
        nameField.clear();
        lastNameField.clear();
        middleNameField.clear();

    }

    @Override
    public void add() {
        Command.insert(new Client(1, nameField.getText(), lastNameField.getText(),middleNameField.getText()));
        ((ClientControllerLeft) otherController).refreshList();
    }



}
