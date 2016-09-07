package com.diploma.form.windows.normalWindows.data.sensor;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Ground;
import com.diploma.dataBase.tables.Seismic;
import com.diploma.dataBase.tables.Sensor;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.leftWindow.LeftElement;
import com.diploma.form.windows.leftWindow.LeftStaticElements;
import com.diploma.form.windows.leftWindow.LeftWindowed;
import com.diploma.staticField.StaticFields;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;


public class SensorControllerLeft extends AbstractWindow implements LeftWindowed {

    public SensorControllerLeft() {
        super(StaticFields.LEFTFIELD);
    }

    @Override
    public void initialize() {
        refreshList();
    }

    @Override
    public void refreshList() {
        ArrayList<Sensor> list = getAccordionList(Sensor.class);
        for (Sensor element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                Command.delete(Sensor.class, element.getId());
                refreshList();
            };

            LeftElement leftElement = LeftStaticElements.getSensor(element);
            leftElement.setDelete(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }

    public void refreshListSeismic() {
        ArrayList<Seismic> list = getAccordionList(Seismic.class);
        for (Seismic element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                refreshList();
                ((SensorControllerRight)otherController).setSeismic(element);
            };

            LeftElement leftElement = LeftStaticElements.getSeismic(element);
            leftElement.setSelect(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }

    public void refreshListGround() {
        ArrayList<Ground> list = getAccordionList(Ground.class);
        for (Ground element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                refreshList();
                ((SensorControllerRight)otherController).setGround(element);
            };

            LeftElement leftElement = LeftStaticElements.getGround(element);
            leftElement.setSelect(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }
}
