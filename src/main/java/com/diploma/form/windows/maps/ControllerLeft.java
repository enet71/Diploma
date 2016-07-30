package com.diploma.form.windows.maps;


import com.diploma.dataBase.enums.EnumSensor;
import com.diploma.dataBase.tables.Sensor;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.data.add.sensor.ModelSensor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import com.diploma.localization.Start;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerLeft extends AbstractWindow {
    boolean isShowSensor = false;
    @FXML
    private ComboBox seismicComboBox;

    public ControllerLeft(String path) {
        super(path);
    }

    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> list = Model.selectSeismicId();
        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll(list);
        seismicComboBox.getItems().add("Все датчики");
        seismicComboBox.getItems().addAll(options);
        seismicComboBox.getSelectionModel().selectFirst();

        seismicComboBox.setOnAction(event -> {
            if (seismicComboBox.getSelectionModel().isSelected(0)) {
                refreshList();
            } else {
                refreshList(String.valueOf(seismicComboBox.getSelectionModel().getSelectedItem()));
            }
        });


        try {
            ArrayList<Sensor> listSensor;
            listSensor = ModelSensor.selectSensor();
            for (Sensor element : listSensor) {
                addToList(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void switchSatellite() {
        MapsWindow.controllerRight.googleMap.switchSatellite();
    }

    @FXML
    public void switchRoadmap() {
        MapsWindow.controllerRight.googleMap.switchRoadmap();
    }

    @FXML
    public void switchHybrid() {
        MapsWindow.controllerRight.googleMap.switchHybrid();
    }

    @FXML
    public void switchTerrain() {
        MapsWindow.controllerRight.googleMap.switchTerrain();
    }

    public void refreshList() {
        accordion.getPanes().clear();
        try {
            ArrayList<Sensor> list = ModelSensor.selectSensor();
            for (Sensor element : list) {
                addToList(element);
                //MapsWindow.controllerRight.googleMap.setDarkMarkerIcon(Integer.parseInt(element.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void refreshList(String id) {
        refreshList();
        accordion.getPanes().clear();
        try {
            ArrayList<Sensor> list = Model.selectSensor(id);
            for (Sensor element : list) {
                addToList(element);
                //MapsWindow.controllerRight.googleMap.setLightMarkerIcon(Integer.parseInt(element.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addToList(Sensor element) {
        VBox vBox = new VBox();
        Button buttonShow = new Button("Показать");
        vBox.getChildren().add(new Label(Start.SENSORNAME.getName(EnumSensor.ID) + ": " + element.getId()));
        vBox.getChildren().add(new Label(Start.SENSORNAME.getName(EnumSensor.SEISMIC) + ": " + element.getSeismic()));
        vBox.getChildren().add(new Label(Start.SENSORNAME.getName(EnumSensor.GROUND) + ": " + element.getGround()));
        vBox.getChildren().add(new Label(Start.SENSORNAME.getName(EnumSensor.NAME) + ": " + element.getName()));
        vBox.getChildren().add(new Label(Start.SENSORNAME.getName(EnumSensor.LNG) + ": " + element.getLng()));
        vBox.getChildren().add(new Label(Start.SENSORNAME.getName(EnumSensor.LAT) + ": " + element.getLat()));
        vBox.getChildren().add(buttonShow);
        TitledPane titledPane = new TitledPane(element.getName(), vBox);
        accordion.getPanes().add(titledPane);


        buttonShow.setOnAction(event -> {
            if (!isShowSensor) {
               //MapsWindow.controllerRight.googleMap.startJumping(Integer.parseInt(element.getId()));
                buttonShow.setText("Отключить");
                isShowSensor = true;
            } else {
                //MapsWindow.controllerRight.googleMap.stopJumping(Integer.parseInt(element.getId()));
                buttonShow.setText("Показать");
                isShowSensor = false;
            }
        });
    }
}
