package com.diploma.form.windows.data.add.region;

import com.diploma.dataBase.tables.Region;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.Left;
import com.diploma.form.windows.LeftElement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

@RegionQ
@Left
public class ControllerLeft extends AbstractWindow {
    private AbstractWindow controllerRight;

    public ControllerLeft() {
        super("fxml/panelLeft.fxml");
    }

    public void setController(AbstractWindow controllerRight) {
        this.controllerRight = controllerRight;
    }

    public void initialize() {
        refreshListRegion();
    }


    public void refreshListRegion() {
        accordion.getPanes().clear();
        ArrayList<Region> list = ModelRegion.selectRegion();

        for (Region element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                ModelRegion.delete(element.getId());
                refreshListRegion();
            };

            LeftElement leftElement = new LeftElement(element.getName());
            leftElement.addLabel(String.valueOf(element.getId()));
            leftElement.addLabel(element.getAddress());
            leftElement.setDelete(eventHandler);

            accordion.getPanes().add(leftElement.getTitledPane());
        }
    }
}
