package com.diploma.form.windows.ground;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Ground;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.Left;
import com.diploma.form.windows.LeftElement;
import com.diploma.form.windows.LeftWindowed;
import com.diploma.staticField.StaticFields;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

@GroundQ
@Left
public class ControllerLeft extends AbstractWindow implements LeftWindowed{

    public ControllerLeft() {
        super(StaticFields.LEFTFIELD);
    }


    @Override
    public void initialize() {
        refreshList();
    }

    @Override
    public void refreshList() {
        accordion.getPanes().clear();
        ArrayList<Ground> list = Command.select(Ground.class);

        for (Ground element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                Command.delete(Ground.class,element.getId());
                refreshList();
            };

            LeftElement leftElement = new LeftElement(element.getName());
            leftElement.addLabel(String.valueOf(element.getId()));
            leftElement.addLabel(element.getName());
            leftElement.setDelete(eventHandler);

            accordion.getPanes().add(leftElement.getTitledPane());
        }
    }
}
