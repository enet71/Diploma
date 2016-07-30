package com.diploma.form.windows.preLoader;

import com.diploma.dataBase.Connect;
import com.diploma.form.windows.WindowService;
import com.diploma.initialize.jsonParser.Save;
import javafx.application.Preloader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.diploma.staticField.SettingFields;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class PreLoader extends Preloader {
    private ShowForm show;
    private Stage stage;
    private ProgressBar bar;
    private boolean noLoadingProgress = true;
    private Label label = new Label(SettingFields.CONNECT);
    private VBox loading;
    private TextField loginField;
    private PasswordField passwordField;
    private Button enterButton;
    private Label validEnter;
    private Label labelEntry;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.initStyle(StageStyle.UNDECORATED);
        Image img = new Image("image/preload.png");
        ImageView imgView = new ImageView(img);
        loading = new VBox();
        loading.setStyle("-fx-background-color: #455a64;");
        loading.setAlignment(Pos.CENTER);
        loading.setMaxWidth(Region.USE_PREF_SIZE);
        loading.setMaxHeight(Region.USE_PREF_SIZE);
        loading.getChildren().add(imgView);
        loading.getChildren().add(bar = new ProgressBar());
        loading.getChildren().add(new HBox(label));
        labelEntry = new Label("");
        loading.getChildren().add(new HBox(labelEntry));
        loading.getChildren().add(loginField = new TextField());
        loading.getChildren().add(passwordField = new PasswordField());
        loading.getChildren().add(validEnter = new Label());
        loading.getChildren().add(enterButton = new Button("Войти"));
        validEnter.setVisible(false);
        loginField.setMaxWidth(300);
        loginField.setDisable(true);
        loginField.setPromptText("Логин");
        passwordField.setDisable(true);
        passwordField.setPromptText("Пароль");
        passwordField.setMaxWidth(300);
        enterButton.setDisable(true);
        enterButton.setPrefWidth(300);
        bar.setPrefWidth(420);
        BorderPane root = new BorderPane(loading);
        Scene scene = new Scene(root, 421, 300);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.getScene().getStylesheets().add("css/PreLoader.css");
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        /*if (stateChangeNotification.getType() == Type.BEFORE_START) {
            preloaderStage.hide();
        }*/
        show = (ShowForm) stateChangeNotification.getApplication();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        //application loading progress is rescaled to be first 50%
        //Even if there is nothing to dataFile 0% and 100% events can be
        // delivered
        /*if (pn.getProgress() != 1.0 || !noLoadingProgress) {
            bar.setProgress(pn.getProgress() / 2);
            if (pn.getProgress() > 0) {
                noLoadingProgress = false;
            }
        }*/
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification pn) {
        if (pn instanceof ProgressNotification) {
            double v = ((ProgressNotification) pn).getProgress();
            bar.setProgress(v);
            label.setText(SettingFields.CONNECT);
        } else if (pn instanceof StateChangeNotification) {
            labelEntry.setText("Последний вход: " + SettingFields.ENTRY);
            loginField.setDisable(false);
            passwordField.setDisable(false);
            enterButton.setDisable(false);
            enterButton.setOnAction(event -> {
                SettingFields.LOGIN = loginField.getText();
                SettingFields.PASSWORD = passwordField.getText();
                Connect connect = new Connect();
                if (connect.getConnectStatus()) {
                    WindowService.createWindowService();
                    show.show();
                    stage.hide();
                } else {
                    validEnter.setVisible(true);
                    validEnter.setText("Неправильый логин или пароль");
                }
            });

            entry();
        }
    }

    private void entry() {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/m/yyyy HH:mm:ss");
        String date = simpleDateFormat.format(calendar.getTime());
        SettingFields.ENTRY = date;
        Save.save();
    }

    public interface ShowForm {
        void show();
    }
}
