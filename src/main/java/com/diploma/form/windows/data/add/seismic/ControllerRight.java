package com.diploma.form.windows.data.add.seismic;

import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    public TextField regionField;
    public TextField nameField;
    public TextField phoneField;
    public TextField mailField;

    public ControllerRight(String path) {
        super(path);
    }

    @FXML
    private void clear(){
        regionField.clear();
        nameField.clear();
        phoneField.clear();
        mailField.clear();
    }

    @FXML
    private void add() throws SQLException, IOException {
        ModelSeismic.insertSeismic(regionField.getText(), nameField.getText(), phoneField.getText(), mailField.getText());
        SeismicWindow.controllerLeft.refreshListSeismic();
    }


    public void initialize(URL location, ResourceBundle resources) {
        SeismicWindow.controllerLeft.refreshListSeismic();
    }

    @FXML
    private void region(){
        SeismicWindow.controllerLeft.refreshListSeismicRegion();
    }

    public void selectRegion(String id){
        regionField.setText("" + id);
    }
}
