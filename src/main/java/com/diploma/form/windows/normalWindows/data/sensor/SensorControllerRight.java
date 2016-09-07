package com.diploma.form.windows.normalWindows.data.sensor;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Ground;
import com.diploma.dataBase.tables.Seismic;
import com.diploma.dataBase.tables.Sensor;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.RightWindowed;
import javafx.scene.control.TextField;


public class SensorControllerRight extends AbstractWindow implements RightWindowed {

    public TextField seismicField;
    public TextField groundField;
    public TextField nameField;
    public TextField lngField;
    public TextField latField;
    private Seismic seismic;
    private Ground ground;

    public SensorControllerRight() {
        super("fxml/sensorRight.fxml");
    }

    @Override
    public void clear() {
        seismicField.clear();
        groundField.clear();
        nameField.clear();
        lngField.clear();
        latField.clear();
    }

    @Override
    public void add() {
        Command.insert(new Sensor(1, seismic, ground, nameField.getText(), Double.parseDouble(lngField.getText()), Double.parseDouble(latField.getText())));
        ((SensorControllerLeft) otherController).refreshList();
    }

    public void getSeismic() {
        ((SensorControllerLeft) otherController).refreshListSeismic();
    }

    public void setSeismic(Seismic seismic) {
        this.seismic = seismic;
        seismicField.setText(seismic.getName());
    }

    public void getGround() {
        ((SensorControllerLeft) otherController).refreshListGround();
    }

    public void setGround(Ground ground) {
        this.ground = ground;
        groundField.setText(ground.getName());
    }

}
