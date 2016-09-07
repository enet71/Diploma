package com.diploma.form.windows.bookKeeping.chart;

import com.diploma.dataLoad.Export;
import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class BalanceChartControllerLeft extends AbstractWindow {
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
    private Button exportButton;

    public BalanceChartControllerLeft() {
        super("fxml/chartLeft.fxml");
    }

    public void initialize() {
        waveAction();
        selectSensor();


    }

    private void selectSensor() {

    }

    private void waveAction() {

    }

    @FXML
    private void export() throws IOException {
        Stage stage = (Stage) exportButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("image(*.png)", "*.png")
        );
        File file = fileChooser.showSaveDialog(stage);
        Export.imageFile(file, otherController);
    }
}
