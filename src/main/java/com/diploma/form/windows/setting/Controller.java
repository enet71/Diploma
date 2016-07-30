package com.diploma.form.windows.setting;

import com.diploma.initialize.jsonParser.Save;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.diploma.staticField.SettingFields;

import javax.xml.bind.JAXBException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField serverField;
    public TextField portField;
    public TextField reconnectField;
    public TextField widthField;
    public CheckBox saveSizeField;
    public TextField heightField;
    public ComboBox<String> languageComboBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serverField.setText(SettingFields.SERVER);
        portField.setText(SettingFields.PORT);
        reconnectField.setText(String.valueOf(SettingFields.RECONNECTDB));
        widthField.setText(String.valueOf(SettingFields.WIDTH));
        saveSizeField.setSelected(true);
        heightField.setText(String.valueOf(SettingFields.HEIGHT));
        languageComboBox.getItems().addAll("ru", "ua", "en");
        languageComboBox.getSelectionModel().select(0);
    }

    @FXML
    private void save() throws JAXBException {
        SettingFields.LANGUAGE = languageComboBox.getValue();
        SettingFields.PORT = portField.getText();
        SettingFields.SERVER = serverField.getText();
        SettingFields.WIDTH = Integer.parseInt(widthField.getText());
        SettingFields.HEIGHT = Integer.parseInt(heightField.getText());
        SettingFields.RECONNECTDB = Integer.parseInt(reconnectField.getText());
        Save.save();
        Stage stage = (Stage) serverField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void close() {
        Stage stage = (Stage) serverField.getScene().getWindow();
        stage.close();
    }
}
