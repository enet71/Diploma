package com.diploma.form.windows.mail;

import com.diploma.dataLoad.mail.Mail;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.mail.MessagingException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField filePath;
    public TextField password;
    public TextField login;
    public TextField theme;
    public TextField who;
    public TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void openFile(){
        Stage stage = (Stage) who.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        filePath.setText(fileChooser.showOpenDialog(stage).getPath());
    }

    @FXML
    private void sendMail() throws MessagingException {
        String mail = who.getText();
        String theme = this.theme.getText();
        String login = this.login.getText();
        String password = this.password.getText();
        String text = textArea.getText();
        String path = this.filePath.getText();
        if(login.contains("@gmail")){
            Mail.SendGMail(login, password,mail , theme, path, text);
        }else if(login.contains("@mail")){
            Mail.SendMailRu(login, password,mail , theme, path, text);
        }else if(login.contains("@yandex")){
            Mail.SendYandex(login, password,mail , theme, path, text);
        }
    }
}
