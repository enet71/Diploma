package com.diploma.form.windows.additionally.converter;

import com.diploma.converter.Converter;
import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    @FXML
    private TextField input;
    @FXML
    private TextField result;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<String> comboBoxUah;
    @FXML
    private RadioButton buy;
    @FXML
    private RadioButton sale;
    @FXML
    private Button enter;


    public ControllerRight(String path) {
        super(path);
    }

    public void initialize(URL location, ResourceBundle resources) {
        comboBox.getItems().addAll("RUR", "EUR", "USD", "BTC");
        comboBox.getSelectionModel().selectFirst();
        comboBoxUah.getItems().addAll("UAH");
        comboBoxUah.getSelectionModel().selectFirst();
        buy.setSelected(true);
        input.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!Objects.equals(newValue, "") && input.isFocused()) {
                result.setDisable(true);
            } else if(input.isFocused()){
                result.setDisable(false);
                result.setText("");
            }
        });

        result.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!Objects.equals(newValue, "") && result.isFocused()) {
                input.setDisable(true);
            } else if(result.isFocused()){
                input.setDisable(false);
                input.setText("");
            }
        });

        enter.setOnAction(event -> {
            try {
                if (input.isDisable()) {
                    if (comboBox.getSelectionModel().isSelected(0) && buy.isSelected()) {
                        input.setText(String.valueOf(Converter.RUBUAHBuy(Double.valueOf(result.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(0) && sale.isSelected()) {
                        input.setText(String.valueOf(Converter.RUBUAHSale(Double.valueOf(result.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(1) && buy.isSelected()) {
                        input.setText(String.valueOf(Converter.EURUAHBuy(Double.valueOf(result.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(1) && sale.isSelected()) {
                        input.setText(String.valueOf(Converter.EURUAHSale(Double.valueOf(result.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(2) && buy.isSelected()) {
                        input.setText(String.valueOf(Converter.USDUAHBuy(Double.valueOf(result.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(2) && sale.isSelected()) {
                        input.setText(String.valueOf(Converter.USDUAHSale(Double.valueOf(result.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(3) && buy.isSelected()) {
                        input.setText(String.valueOf(Converter.BTCUAHBuy(Double.valueOf(result.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(3) && sale.isSelected()) {
                        input.setText(String.valueOf(Converter.BTCUAHSale(Double.valueOf(result.getText()))));
                    }
                } else {
                    if (comboBox.getSelectionModel().isSelected(0) && buy.isSelected()) {
                        result.setText(String.valueOf(Converter.UAHRUBBuy(Double.valueOf(input.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(0) && sale.isSelected()) {
                        result.setText(String.valueOf(Converter.UAHRUBSale(Double.valueOf(input.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(1) && buy.isSelected()) {
                        result.setText(String.valueOf(Converter.UAHEURBuy(Double.valueOf(input.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(1) && sale.isSelected()) {
                        result.setText(String.valueOf(Converter.UAHEURSale(Double.valueOf(input.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(2) && buy.isSelected()) {
                        result.setText(String.valueOf(Converter.UAHUSDBuy(Double.valueOf(input.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(2) && sale.isSelected()) {
                        result.setText(String.valueOf(Converter.UAHUSDSale(Double.valueOf(input.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(3) && buy.isSelected()) {
                        result.setText(String.valueOf(Converter.UAHBTCBuy(Double.valueOf(input.getText()))));
                    } else if (comboBox.getSelectionModel().isSelected(3) && sale.isSelected()) {
                        result.setText(String.valueOf(Converter.UAHBTCSale(Double.valueOf(input.getText()))));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Неправильный формат ввода");
            }
        });
    }
}
