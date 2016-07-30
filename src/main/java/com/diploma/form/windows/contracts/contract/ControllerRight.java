package com.diploma.form.windows.contracts.contract;


import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow{
    public TextField clientField;
    public TextField regionField;
    public TextArea offerField;
    public TextField paymentField;

    public ControllerRight(String path) {
        super(path);
    }



    @FXML
    private void clear(){
        clientField.clear();
        regionField.clear();
        offerField.clear();
        paymentField.clear();
    }

    @FXML
    private void add() throws SQLException, IOException {
        ModelContract.insertContract(clientField.getText(), regionField.getText(), offerField.getText(), paymentField.getText());
        ContractWindow.controllerLeft.refreshListContract();
    }


    @FXML
    private void client(){
        ContractWindow.controllerLeft.refreshListContractClient();
    }

    @FXML
    private void region(){
        ContractWindow.controllerLeft.refreshListContractRegion();

    }


    public void selectClient(String id){
        clientField.setText("" + id);
    }

    public void selectRegion(String id){
        regionField.setText("" + id);
    }
}
