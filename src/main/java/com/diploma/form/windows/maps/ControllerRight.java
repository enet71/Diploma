package com.diploma.form.windows.maps;

import com.diploma.dataBase.tables.Sensor;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.data.add.sensor.ModelSensor;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    public GoogleMap googleMap;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ScrollPane scrollPane;

    public ControllerRight(String path) {
        super(path);
    }

    public void initialize(URL location, ResourceBundle resources) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        googleMap = new GoogleMap(width, height);

        try {
            ArrayList<Sensor> arrayList = ModelSensor.selectSensor();
            for (Sensor anArrayList : arrayList) {
                //googleMap.createMarker(Double.valueOf(anArrayList.getLat()), Double.valueOf(anArrayList.getLng()), Integer.parseInt(anArrayList.getId()),anArrayList.getSeismic(),anArrayList.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scrollPane.setContent(googleMap);

    }
}
