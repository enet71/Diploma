package com.diploma.form.windows.normalWindows.data.seismic;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Region;
import com.diploma.dataBase.tables.Seismic;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.leftWindow.LeftElement;
import com.diploma.form.windows.leftWindow.LeftStaticElements;
import com.diploma.form.windows.leftWindow.LeftWindowed;
import com.diploma.staticField.StaticFields;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;


public class SeismicControllerLeft extends AbstractWindow implements LeftWindowed {
    public SeismicControllerLeft() {
        super(StaticFields.LEFTFIELD);
    }

    @Override
    public void initialize() {
        refreshList();
    }

    @Override
    public void refreshList() {
        ArrayList<Seismic> list = getAccordionList(Seismic.class);

        for (Seismic element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                Command.delete(Seismic.class, element.getId());
                refreshList();
            };

            LeftElement leftElement = LeftStaticElements.getSeismic(element);
            leftElement.setDelete(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }

    public void refreshListRegion() {
        ArrayList<Region> list = getAccordionList(Region.class);
        for (Region element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                ((SeismicControllerRight) otherController).setRegion(element);
                refreshList();
            };

            LeftElement leftElement = LeftStaticElements.getRegion(element);
            leftElement.setSelect(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }
}
