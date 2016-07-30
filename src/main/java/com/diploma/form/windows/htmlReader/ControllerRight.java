package com.diploma.form.windows.htmlReader;

import com.diploma.form.windows.AbstractWindow;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    public ScrollPane scrollPane;
    public ControllerRight(String path) {
        super(path);
    }

    public void initialize(URL location, ResourceBundle resources) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getClassLoader().getResource("presentation/pres.html").toExternalForm());
        scrollPane.setContent(webView);
    }
}
