package com.diploma.main;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FirstPreLoader extends Preloader {
    ProgressBar bar;
    Stage stage;

    private Scene createPreLoaderScene() {
        bar = new ProgressBar();
        BorderPane p = new BorderPane();
        VBox vBox = new VBox();
        vBox.getChildren().add(bar);


        p.setCenter(vBox);
        return new Scene(p, 300, 150);
    }

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setScene(createPreLoaderScene());
        stage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        bar.setProgress(pn.getProgress());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }
}
