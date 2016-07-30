package com.diploma.form.windows.data.add.sensor;

import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {

    public TextField seismicField;
    public TextField groundField;
    public TextField nameField;
    public TextField lngField;
    public TextField latField;
    public Button addButton;

    public ControllerRight(String path) {
        super(path);
    }

    @FXML
    private void clear(){
        seismicField.clear();
        groundField.clear();
        nameField.clear();
        lngField.clear();
        latField.clear();
    }

    public void initialize(URL location, ResourceBundle resources) {
        SensorWindow.controllerLeft.refreshListSensor();
        add();
    }

    @FXML
    private void ground(){
        SensorWindow.controllerLeft.refreshListSensorGround();
    }

    @FXML
    private void seismic(){
        SensorWindow.controllerLeft.refreshListSensorSeismic();
    }

    public void selectGround(String id){
        groundField.setText("" + id);
    }

    public void selectSeismic(String id){
        seismicField.setText("" + id);
    }


    public void add(){
        addButton.setText("Добавить");
        addButton.setOnAction(event -> {
            try {
                ModelSensor.insertSensor(seismicField.getText(), groundField.getText(), nameField.getText(), lngField.getText(), latField.getText());
                SensorWindow.controllerLeft.refreshListSensor();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    public void edit(String id){
        addButton.setText("Редактировать");
        addButton.setOnAction(event -> {
            try {
                ModelSensor.editSensor(seismicField.getText(), groundField.getText(), nameField.getText(), lngField.getText(), latField.getText(),id);
                SensorWindow.controllerLeft.refreshListSensor();
                add();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
