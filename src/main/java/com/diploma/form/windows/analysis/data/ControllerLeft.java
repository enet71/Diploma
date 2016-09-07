package com.diploma.form.windows.analysis.data;


import com.diploma.form.windows.AbstractWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.sql.ResultSet;

public class ControllerLeft extends AbstractWindow {
    public CheckBox pieCheckBox;
    public CheckBox areaCheckBox;
    public ComboBox analysComboBox;
    private ResultSet resultSet;

    public ControllerLeft(String path) {
        super(path);
    }

    //FIXME Edit comboBox
    public void initialize() {
        ObservableList<String> list = FXCollections.observableList(Model.getAnalysisNames());
        analysComboBox.setItems(list);

        analysComboBox.setOnAction(event -> {
            DataWindow.controllerRight.setAnalyse(Model.getSelectAnalysisID(analysComboBox.getSelectionModel().getSelectedItem().toString()));
        });

        pieCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            DataWindow.controllerRight.addPieChart();
        });

        areaCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            DataWindow.controllerRight.addAreaChart();
        });
    }
}
