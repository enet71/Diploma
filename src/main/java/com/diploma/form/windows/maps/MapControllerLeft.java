package com.diploma.form.windows.maps;


import com.diploma.dataBase.Command;
import com.diploma.dataBase.enums.EnumSensor;
import com.diploma.dataBase.tables.Sensor;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.localization.Start;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;

public class MapControllerLeft extends AbstractWindow {
    boolean isShowSensor = false;
    @FXML
    private ComboBox seismicComboBox;

    public MapControllerLeft() {
        super("fxml/mapLeft.fxml");
    }

    public void initialize() {
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


        ArrayList<Sensor> listSensor;
        listSensor = Command.select(Sensor.class);
        for (Sensor element : listSensor) {
            addToList(element);
        }

    }

    @FXML
    public void switchSatellite() {
        ((MapControllerRight)otherController).googleMap.switchSatellite();
    }

    @FXML
    public void switchRoadmap() {
        ((MapControllerRight)otherController).googleMap.switchRoadmap();
    }

    @FXML
    public void switchHybrid() {
        ((MapControllerRight)otherController).googleMap.switchHybrid();
    }

    @FXML
    public void switchTerrain() {
        ((MapControllerRight)otherController).googleMap.switchTerrain();
    }

    public void refreshList() {
        accordion.getPanes().clear();

        ArrayList<Sensor> list = Command.select(Sensor.class);
        for (Sensor element : list) {
            addToList(element);
            ((MapControllerRight)otherController).googleMap.setDarkMarkerIcon(element.getId());
        }
    }


    public void refreshList(String id) {
        refreshList();
        accordion.getPanes().clear();
        try {
            ArrayList<Sensor> list = Model.selectSensor(id);
            for (Sensor element : list) {
                addToList(element);
                ((MapControllerRight)otherController).googleMap.setLightMarkerIcon(element.getId());
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
                ((MapControllerRight)otherController).googleMap.startJumping(element.getId());
                buttonShow.setText("Отключить");
                isShowSensor = true;
            } else {
                ((MapControllerRight)otherController).googleMap.stopJumping(element.getId());
                buttonShow.setText("Показать");
                isShowSensor = false;
            }
        });
    }
}
