package com.diploma.form.windows.doubleWindow;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DoubleWindowTab extends Tab {
    private DoubleWindow doubleWindow;
    private Button windowButton = new Button();

    public DoubleWindowTab(DoubleWindow doubleWindow) {
        this(null, doubleWindow);
    }

    public DoubleWindowTab(String text, DoubleWindow doubleWindow) {
        this(text, null, doubleWindow);
    }

    public DoubleWindowTab(String text, DoubleWindow doubleWindow, TabPane tabPane) {
        this(text, null, doubleWindow, tabPane);
    }

    public DoubleWindowTab(String text, Node content, DoubleWindow doubleWindow) {
        this(text, content, doubleWindow, null);
    }

    public DoubleWindowTab(String text, Node content, DoubleWindow doubleWindow, TabPane tabPane) {
        super(text, content);
        this.doubleWindow = doubleWindow;
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(doubleWindow.getRightBorderPane());
        HBox hBox = new HBox();
        hBox.getChildren().add(windowButton);
        borderPane.setBottom(hBox);
        borderPane.getBottom().setStyle("-fx-background-color: #455a64;");
        this.setContent(borderPane);
        openWindow(text, tabPane);
    }

    private void openWindow(String text, TabPane tabPane) {
        Image imageDecline = new Image(getClass().getClassLoader().getResourceAsStream("image/cursor_drag_arrow.png"));
        windowButton.setStyle("-fx-background-color: rgba(255, 0, 0, 0); -fx-focus-color: #455A64; -fx-faint-focus-color: #455A64; -fx-border-color:#455A64");
        windowButton.setGraphic(new ImageView(imageDecline));

        windowButton.setOnAction(event -> {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root);
            root.setCenter(doubleWindow.getRightBorderPane());
            root.setLeft(doubleWindow.getLeftBorderPane());

            Stage stage = new Stage();
            stage.setAlwaysOnTop(true);
            stage.setScene(scene);
            stage.show();

            tabPane.getTabs().remove(this);
            stage.iconifiedProperty().addListener((ov, t, t1) -> {
                tabPane.getTabs().add(new DoubleWindowTab(text, doubleWindow, tabPane));
                stage.close();
            });

        });
    }

    public DoubleWindow getDoubleWindow() {
        return doubleWindow;
    }
}
