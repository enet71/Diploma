package com.diploma.form.windows.data.watch.chart;

import com.diploma.dataLoad.Export;
import com.diploma.form.windows.AbstractWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerLeft extends AbstractWindow {
    @FXML
    private CheckBox checkBoxEnergyPWave;
    @FXML
    private CheckBox checkBoxMagnitudePWave;
    @FXML
    private CheckBox checkBoxDepthPWave;
    @FXML
    private CheckBox checkBoxEnergySWave;
    @FXML
    private CheckBox checkBoxMagnitudeSWave;
    @FXML
    private CheckBox checkBoxDepthSWave;
    @FXML
    private VBox vBoxSensor;

    public ControllerLeft(String path) {
        super(path);
    }

    public void initialize(URL location, ResourceBundle resources) {
        waveAction();
        selectSensor();


    }


    private void selectSensor() {
        ArrayList<String> list = Model.selectSensorId();
        ObservableList<String> options = FXCollections.observableArrayList();
        options.addAll(list);
        ComboBox<String> comboBox = new ComboBox<>(options);
        vBoxSensor.getChildren().add(comboBox);


        comboBox.setOnAction(event -> {
            Model.SENSOR = comboBox.getSelectionModel().getSelectedItem();
            if(checkBoxEnergyPWave.isSelected()){
                ChartWindow.controllerRight.createEnergyP();
                ChartWindow.controllerRight.createEnergyP();
            }

            if(checkBoxEnergySWave.isSelected()){
                ChartWindow.controllerRight.createEnergyS();
                ChartWindow.controllerRight.createEnergyS();
            }

            if(checkBoxMagnitudePWave.isSelected()){
                ChartWindow.controllerRight.createMagnitudeP();
                ChartWindow.controllerRight.createMagnitudeP();
            }

            if(checkBoxMagnitudeSWave.isSelected()){
                ChartWindow.controllerRight.createMagnitudeS();
                ChartWindow.controllerRight.createMagnitudeS();
            }

            if(checkBoxDepthPWave.isSelected()){
                ChartWindow.controllerRight.createDepthP();
                ChartWindow.controllerRight.createDepthP();
            }

            if(checkBoxDepthSWave.isSelected()){
                ChartWindow.controllerRight.createDepthS();
                ChartWindow.controllerRight.createDepthS();
            }

           /*
            Class cls = ControllerRight.class;

            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().contains("remove")) {
                    try {
                        method.invoke(BookKeepingChartWindow.controllerRight);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }


            for (Method method : cls.getDeclaredMethods()) {
                if (method.getName().contains("create")) {
                    try {
                        method.invoke(BookKeepingChartWindow.controllerRight);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }*/

        });


    }

    private void waveAction() {
        checkBoxEnergyPWave.selectedProperty().addListener((observable, oldValue, newValue) -> {
                ChartWindow.controllerRight.createEnergyP();
        });

        checkBoxMagnitudePWave.selectedProperty().addListener((observable, oldValue, newValue) -> {
                ChartWindow.controllerRight.createMagnitudeP();
        });

        checkBoxDepthPWave.selectedProperty().addListener((observable, oldValue, newValue) -> {
                ChartWindow.controllerRight.createDepthP();
        });
        checkBoxEnergySWave.selectedProperty().addListener((observable, oldValue, newValue) -> {
                ChartWindow.controllerRight.createEnergyS();
        });

        checkBoxMagnitudeSWave.selectedProperty().addListener((observable, oldValue, newValue) -> {
            ChartWindow.controllerRight.createMagnitudeS();
        });

        checkBoxDepthSWave.selectedProperty().addListener((observable, oldValue, newValue) -> {
                ChartWindow.controllerRight.createDepthS();
        });
    }

    @FXML
    private void export() throws IOException {
        Stage stage = (Stage) vBoxSensor.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("image(*.png)", "*.png")
        );
        File file = fileChooser.showSaveDialog(stage);
        Export.imageFile(file,ChartWindow.controllerRight);
    }
}
