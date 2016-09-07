package com.diploma.form.windows.normalWindows.data.ground;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Ground;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.leftWindow.LeftElement;
import com.diploma.form.windows.leftWindow.LeftStaticElements;
import com.diploma.form.windows.leftWindow.LeftWindowed;
import com.diploma.staticField.StaticFields;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;


public class GroundControllerLeft extends AbstractWindow implements LeftWindowed {

    public GroundControllerLeft() {
        super(StaticFields.LEFTFIELD);
    }


    @Override
    public void initialize() {
        refreshList();
    }

    @Override
    public void refreshList() {
        ArrayList<Ground> list = getAccordionList(Ground.class);

        for (Ground element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                Command.delete(Ground.class, element.getId());
                refreshList();
            };

            LeftElement leftElement = LeftStaticElements.getGround(element);
            leftElement.setDelete(eventHandler);

            addToAccordionList(leftElement.getTitledPane());
        }
    }
}
