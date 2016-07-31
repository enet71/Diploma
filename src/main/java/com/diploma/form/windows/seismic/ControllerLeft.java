package com.diploma.form.windows.seismic;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Region;
import com.diploma.dataBase.tables.Seismic;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.Left;
import com.diploma.form.windows.LeftElement;
import com.diploma.form.windows.LeftWindowed;
import com.diploma.staticField.StaticFields;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

@SeismicQ
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
        ArrayList<Seismic> list = Command.select(Seismic.class);

        for (Seismic element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                Command.delete(Seismic.class,element.getId());
                refreshList();
            };

            LeftElement leftElement = new LeftElement(element.getName());
            leftElement.addLabel(String.valueOf(element.getId()));
            leftElement.addLabel(element.getRegion().getName());
            leftElement.addLabel(element.getPhone());
            leftElement.addLabel(element.getMail());
            leftElement.setDelete(eventHandler);
            accordion.getPanes().add(leftElement.getTitledPane());
        }
    }

    public void refreshListRegion(){
        accordion.getPanes().clear();
        ArrayList<Region> list = Command.select(Region.class);

        for (Region element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                ((ControllerRight)otherController).setRegion(element);
                refreshList();
            };

            LeftElement leftElement = new LeftElement(element.getName());
            leftElement.addLabel(String.valueOf(element.getId()));
            leftElement.addLabel(element.getAddress());
            leftElement.setSelect(eventHandler);

            accordion.getPanes().add(leftElement.getTitledPane());
        }
    }
}
