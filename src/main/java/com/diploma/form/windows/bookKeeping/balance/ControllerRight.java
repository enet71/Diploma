package com.diploma.form.windows.bookKeeping.balance;

import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    public TextField valueField;
    public TextField bookKeepingField;
    public TextField typeField;

    public ControllerRight(String path) {
        super(path);
    }

    @FXML
    private void clear(){
        valueField.clear();
        bookKeepingField.clear();
        typeField.clear();
    }

    @FXML
    private void add() throws SQLException, IOException {
        ModelBalance.insertBalance(valueField.getText(), bookKeepingField.getText(), typeField.getText());
        BalanceWindow.controllerLeft.refreshListBalance();
    }


    public void initialize(URL location, ResourceBundle resources) {
        BalanceWindow.controllerLeft.refreshListBalance();
    }

    @FXML
    private void keeping(){
        BalanceWindow.controllerLeft.refreshListBalanceBookKeeping();
    }

    @FXML
    private void type(){
        BalanceWindow.controllerLeft.refreshListBalanceType();
    }

    public void selectKeeping(String id){
        bookKeepingField.setText("" + id);
    }

    public void selectType(String id){
        typeField.setText("" + id);
    }
}
