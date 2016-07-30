package com.diploma.form.windows.analysis.data;


import com.diploma.dataBase.Connect;
import com.diploma.form.windows.AbstractWindow;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerLeft extends AbstractWindow {
    public CheckBox pieCheckBox;
    public CheckBox areaCheckBox;
    public ComboBox analysComboBox;
    private ResultSet resultSet;

    public ControllerLeft(String path) {
        super(path);
    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connect connect = new Connect();
            resultSet = connect.getStatement().executeQuery("SELECT NAME FROM ANALYSIS");
            while (resultSet.next()) {
                analysComboBox.getItems().add(resultSet.getString(1));
            }


            analysComboBox.setOnAction(event -> {
                try {
                    resultSet = connect.getStatement().executeQuery("SELECT ID FROM ANALYSIS WHERE NAME = '" + analysComboBox.getSelectionModel().getSelectedItem() + "'");
                    resultSet.next();
                    DataWindow.controllerRight.setAnalyse(resultSet.getInt(1));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });


        } catch (SQLException e) {
            e.printStackTrace();
        }


        pieCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            DataWindow.controllerRight.addPieChart();
        });

        areaCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            DataWindow.controllerRight.addAreaChart();
        });
    }
}
