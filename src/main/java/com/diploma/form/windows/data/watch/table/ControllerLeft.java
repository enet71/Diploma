package com.diploma.form.windows.data.watch.table;

import com.diploma.dataBase.enums.EnumWave;
import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerLeft extends AbstractWindow {
    @FXML
    private CheckBox checkBoxId;
    @FXML
    private CheckBox checkBoxSensor;
    @FXML
    private CheckBox checkBoxTime;
    @FXML
    private CheckBox checkBoxDepth;
    @FXML
    private CheckBox checkBoxEnergy;
    @FXML
    private CheckBox checkBoxType;
    @FXML
    private CheckBox checkBoxMagnitude;

    @FXML
    private TextField depthFieldMax;
    @FXML
    private TextField energyFieldMax;
    @FXML
    private TextField magnitudeFieldMax;
    @FXML
    private TextField depthFieldMin;
    @FXML
    private TextField energyFieldMin;
    @FXML
    private TextField magnitudeFieldMin;

    public ControllerLeft(String path) {
        super(path);
    }
    private ControllerRight controllerRight;

    public void setController(ControllerRight controllerRight){
        this.controllerRight = controllerRight;
    }

    public void initialize(URL location, ResourceBundle resources) {
        action();

        depthFieldMax.textProperty().addListener((observable, oldValue, newValue) -> {
            controllerRight.setMaxDepth(Integer.parseInt(newValue));
            try {
                controllerRight.editColumn(EnumWave.DEPTHWAVE);
                controllerRight.editColumn(EnumWave.DEPTHWAVE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        energyFieldMax.textProperty().addListener((observable, oldValue, newValue) -> {
            controllerRight.setMaxEnergy(Integer.parseInt(newValue));
            try {
                controllerRight.editColumn(EnumWave.ENERGY);
                controllerRight.editColumn(EnumWave.ENERGY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        magnitudeFieldMax.textProperty().addListener((observable, oldValue, newValue) -> {
            controllerRight.setMaxMagnitude(Integer.parseInt(newValue));
            try {
                controllerRight.editColumn(EnumWave.MAGNITUDE);
                controllerRight.editColumn(EnumWave.MAGNITUDE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        depthFieldMin.textProperty().addListener((observable, oldValue, newValue) -> {
            controllerRight.setMinDepth(Integer.parseInt(newValue));
            try {
                controllerRight.editColumn(EnumWave.DEPTHWAVE);
                controllerRight.editColumn(EnumWave.DEPTHWAVE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        energyFieldMin.textProperty().addListener((observable, oldValue, newValue) -> {
            controllerRight.setMinEnergy(Integer.parseInt(newValue));
            try {
                controllerRight.editColumn(EnumWave.ENERGY);
                controllerRight.editColumn(EnumWave.ENERGY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        magnitudeFieldMin.textProperty().addListener((observable, oldValue, newValue) -> {
            controllerRight.setMinMagnitude(Integer.parseInt(newValue));
            try {
                controllerRight.editColumn(EnumWave.MAGNITUDE);
                controllerRight.editColumn(EnumWave.MAGNITUDE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void action() {
        checkBoxSensor.setOnAction(event -> {
            try {
                controllerRight.editColumn(EnumWave.SENSOR);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        checkBoxTime.setOnAction(event -> {
            try {
                controllerRight.editColumn(EnumWave.TIMEWAVE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        checkBoxDepth.setOnAction(event -> {
            try {
                controllerRight.editColumn(EnumWave.DEPTHWAVE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        checkBoxEnergy.setOnAction(event -> {
            try {
                controllerRight.editColumn(EnumWave.ENERGY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        checkBoxType.setOnAction(event -> {
            try {
                controllerRight.editColumn(EnumWave.TYPEWAVE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        checkBoxMagnitude.setOnAction(event -> {
            try {
                controllerRight.editColumn(EnumWave.MAGNITUDE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
