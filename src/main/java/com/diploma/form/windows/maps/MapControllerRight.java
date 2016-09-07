package com.diploma.form.windows.maps;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Sensor;
import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.util.ArrayList;

public class MapControllerRight extends AbstractWindow {
    public GoogleMap googleMap;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ScrollPane scrollPane;

    public MapControllerRight() {
        super("fxml/mapRight.fxml");
    }

    public void initialize() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        googleMap = new GoogleMap(width, height);

        ArrayList<Sensor> arrayList = Command.select(Sensor.class);
        for (Sensor anArrayList : arrayList) {
            googleMap.createMarker(anArrayList.getLat(), anArrayList.getLng(), anArrayList.getId(), String.valueOf(anArrayList.getSeismic().getId()),anArrayList.getName());
        }

        scrollPane.setContent(googleMap);

    }
}
