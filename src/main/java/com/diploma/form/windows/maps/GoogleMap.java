package com.diploma.form.windows.maps;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class GoogleMap extends Parent {

    private JSObject doc;
    private EventHandler<MapEvent> onMapLatLngChanged;
    private WebView webView;
    private WebEngine webEngine;
    private boolean ready;

    public GoogleMap(int width, int height) {
        initMap();
        initCommunication();
        switchHybrid();
        setWidth(width);
        setHeight(height);
    }

    private void initMap() {
        webView = new WebView();
        webEngine = webView.getEngine();
        webEngine.load(getClass().getClassLoader().getResource("map/map.html").toExternalForm());
        ready = false;
        webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                ready = true;
                getChildren().add(webView);
            }
        });
    }

    private void initCommunication() {
        webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                doc = (JSObject) webEngine.executeScript("window");
                doc.setMember("app", GoogleMap.this);
            }
        });
    }

    private void invokeJS(final String str) {
        if (ready) {
            doc.eval(str);
        } else {
            webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    doc.eval(str);
                }
            });
        }
    }

    public void setOnMapLatLngChanged(EventHandler<MapEvent> eventHandler) {
        onMapLatLngChanged = eventHandler;
    }

    public void handle(double lat, double lng) {
        if (onMapLatLngChanged != null) {
            MapEvent event = new MapEvent(this, lat, lng);
            onMapLatLngChanged.handle(event);
        }
    }

    public void setMarkerPosition(double lat, double lng) {
        String sLat = Double.toString(lat);
        String sLng = Double.toString(lng);
        invokeJS("setMarkerPosition(" + sLat + ", " + sLng + ")");
    }

    public void setMapCenter(double lat, double lng) {
        String sLat = Double.toString(lat);
        String sLng = Double.toString(lng);
        invokeJS("setMapCenter(" + sLat + ", " + sLng + ")");
    }

    public void switchSatellite() {
        invokeJS("switchSatellite()");
    }

    public void switchRoadmap() {
        invokeJS("switchRoadmap()");
    }

    public void switchHybrid() {
        invokeJS("switchHybrid()");
    }

    public void switchTerrain() {
        invokeJS("switchTerrain()");
    }

    public void startJumping() {
        invokeJS("startJumping()");
    }

    public void startJumping(int n) {
        invokeJS("startJumping(" + n + ")");
    }

    public void stopJumping() {
        invokeJS("stopJumping()");
    }

    public void stopJumping(int n) {
        invokeJS("stopJumping(" + n + ")");
    }

    public void setLightMarkerIcon(int n) {
        invokeJS("setLightMarkerIcon(" + n + ")");
    }

    public void setDarkMarkerIcon(int n) {
        invokeJS("setDarkMarkerIcon(" + n + ")");
    }

    public void createMarker(double lng, double lat, int n, String seismic, String name) {
        invokeJS("createMarker(" + lng + "," + lat + "," + n + "," + seismic + ",'" + name + "')");
    }

    public void clear() {
        invokeJS("clear");
    }

    public void setHeight(double h) {
        webView.setPrefHeight(h);
    }

    public void setWidth(double w) {
        webView.setPrefWidth(w);
    }

    public ReadOnlyDoubleProperty widthProperty() {
        return webView.widthProperty();
    }
}