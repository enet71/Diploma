package com.diploma.form.windows.normalWindows.data.region;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Region;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.leftWindow.LeftElement;
import com.diploma.form.windows.leftWindow.LeftStaticElements;
import com.diploma.form.windows.leftWindow.LeftWindowed;
import com.diploma.staticField.StaticFields;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class RegionControllerLeft extends AbstractWindow implements LeftWindowed {
    public RegionControllerLeft() {
        super(StaticFields.LEFTFIELD);
    }

    public void initialize() {
        refreshList();
    }


    public void refreshList() {
        accordion.getPanes().clear();
        ArrayList<Region> list = getAccordionList(Region.class);

        for (Region element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                Command.delete(Region.class, element.getId());
                refreshList();
            };
            LeftElement leftElement = LeftStaticElements.getRegion(element);
            leftElement.setDelete(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }
}
