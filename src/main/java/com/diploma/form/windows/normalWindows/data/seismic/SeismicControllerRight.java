package com.diploma.form.windows.normalWindows.data.seismic;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Region;
import com.diploma.dataBase.tables.Seismic;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.RightWindowed;
import javafx.scene.control.TextField;


public class SeismicControllerRight extends AbstractWindow implements RightWindowed {
    public TextField regionField;
    public TextField nameField;
    public TextField phoneField;
    public TextField mailField;
    private Region region;

    public SeismicControllerRight() {
        super("fxml/seismicRight.fxml");
    }


    public void clear() {
        regionField.clear();
        nameField.clear();
        phoneField.clear();
        mailField.clear();
    }

    public void add() {
        Command.insert(new Seismic(1, region, nameField.getText(), phoneField.getText(), mailField.getText()));
        ((SeismicControllerLeft) otherController).refreshList();
    }

    public void getRegion() {
        ((SeismicControllerLeft) otherController).refreshListRegion();
    }

    public void setRegion(Region region) {
        this.region = region;
        regionField.setText(region.getName());
    }

}
