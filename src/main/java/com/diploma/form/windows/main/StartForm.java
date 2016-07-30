package com.diploma.form.windows.main;


import com.diploma.converter.Converter;
import com.diploma.dataBase.Connect;
import com.diploma.form.windows.preLoader.PreLoader;
import com.diploma.initialize.jsonParser.Load;
import com.diploma.initialize.jsonParser.Save;
import com.diploma.localization.Start;
import com.diploma.staticField.SettingFields;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartForm extends Application implements PreLoader.ShowForm {
    Stage primaryStage;

    @Override
    public void init() throws Exception {
        SettingFields.CONNECT = "Загрузка данных";
        notifyPreloader(new Preloader.ProgressNotification(0));
        Load.load();
        notifyPreloader(new Preloader.ProgressNotification(0.1));
        Converter.updateData();
        notifyPreloader(new Preloader.ProgressNotification(0.2));
        Start.RU();
        notifyPreloader(new Preloader.ProgressNotification(0.3));
        SettingFields.CONNECT = "Подключение к базе данных";
        notifyPreloader(new Preloader.ProgressNotification(0.5));
        Connect connect = new Connect();

        if (connect.getConnectStatus()) {
            SettingFields.CONNECT = "Подключение: успешно";
        } else {
            SettingFields.CONNECT = "Подключение: ошибка";
        }
        notifyPreloader(new Preloader.ProgressNotification(0.8));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainWindow7.fxml"));
        primaryStage.setTitle("Seismic Survey");
        Scene scene = new Scene(root, SettingFields.WIDTH, SettingFields.HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/MainTabPane.css");
        notifyPreloader(new Preloader.ProgressNotification(1));
        notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));


        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            SettingFields.WIDTH = newSceneWidth.intValue();
            Save.save();
        });
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            SettingFields.HEIGHT = newSceneHeight.intValue();
            Save.save();
        });

    }


    @Override
    public void show() {
        primaryStage.show();
    }
}
