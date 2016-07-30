package com.diploma.form.windows.additionally.calculate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label mainLabel;
    private Boolean operator = false;
    private Boolean start = true;

    @FXML
    private void actionNumber(ActionEvent event) {
        if(mainLabel.getText().equals("0") || mainLabel.getText().equals("Infinity")){
            mainLabel.setText("");
        }
        operator = false;
        String st = ((Button) event.getSource()).getText();
        mainLabel.setText(mainLabel.getText() + st);
    }

    @FXML
    private void actionOperator(ActionEvent event) {
        if (((Button) event.getSource()).getText().equals("=")) {
            if (operator){
                removeLast();
                operator = false;
            }
            outResult(Model.calculate(mainLabel.getText()));
            return;
        }
        if (!operator) {
            operator = true;
            String st = ((Button) event.getSource()).getText();
            mainLabel.setText(mainLabel.getText() + st);
        }
    }

    private void outResult(Double db) {
        if (db % 1 != 0) {
            mainLabel.setText(String.valueOf(db));
        } else {
            Integer rs = db.intValue();
            mainLabel.setText(String.valueOf(rs));
        }
    }

    @FXML
    private void removeLast(){
        String text = mainLabel.getText();
        if(text.length() != 0)
        mainLabel.setText(text.substring(0, text.length() - 1));
    }

    @FXML
    private void clear(){
        mainLabel.setText("");
    }
}
