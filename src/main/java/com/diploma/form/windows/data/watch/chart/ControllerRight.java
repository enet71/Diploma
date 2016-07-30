package com.diploma.form.windows.data.watch.chart;

import com.diploma.dataBase.tables.Wave;
import com.diploma.form.windows.AbstractWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;


public class ControllerRight extends AbstractWindow {
    Rectangle rect;
    private XYChart.Series seriesEnergyP;
    private XYChart.Series seriesEnergyS;
    private XYChart.Series seriesDepthP;
    private XYChart.Series seriesDepthS;
    private XYChart.Series seriesMagnitudeP;
    private XYChart.Series seriesMagnitudeS;
    private CategoryAxis X = new CategoryAxis();
    private NumberAxis Y = new NumberAxis();
    @FXML
    private LineChart lineChart;
    @FXML
    private BorderPane borderPane;
    @FXML
    private ScrollPane scrollPane;
    public ControllerRight(String path) {
        super(path);
    }
    public void initialize(URL location, ResourceBundle resources) {
        //createLineChart();
        /*X = new CategoryAxis();
        Y = new NumberAxis();
        lineChart = new LineChart(X,Y);
        X.setVisible(false);
        borderPane.setCenter(lineChart);*/
    }

    public void addLineChart() {
        rect = new Rectangle(0, 0);
        rect.setVisible(false);


        ObservableList<String> l = FXCollections.observableArrayList();
        ArrayList<Wave> lst = Model.selectWave();
        Set set = new TreeSet<>();
        for (Wave aLst : lst) {
            set.add(aLst.getTimeWave());
        }
        l.addAll(set);

        X.setTickLabelRotation(90);
        X.setCategories(l);


        ObservableList list = lineChart.getData();
        lineChart = new LineChart(X, Y);

        scrollPane.setContent(lineChart);
        lineChart.setData(list);


        final double SCALE_DELTA = 1.1;
        lineChart.setOnScroll(event -> {
            event.consume();

            if (event.getDeltaY() == 0) {
                return;
            }


            double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;
            lineChart.setScaleX(lineChart.getScaleX() * scaleFactor);
            lineChart.setScaleY(lineChart.getScaleY() * scaleFactor);
        });

        lineChart.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    lineChart.setScaleX(1.0);
                    lineChart.setScaleY(1.0);
                }
            }
        });
    }


    public void createEnergyP() {
        if (lineChart.getData().contains(seriesEnergyP)) {
            lineChart.getData().remove(seriesEnergyP);
        } else {
            ArrayList<Wave> list = Model.selectPWave();
            addLineChart();
            seriesEnergyP = new XYChart.Series();

            for (int i = 0; i < list.size(); i++) {
                seriesEnergyP.getData().add(new XYChart.Data(list.get(i).getTimeWave(), Integer.valueOf(list.get(i).getEnergy())));
            }
            seriesEnergyP.setName("P - Energy");
            lineChart.getData().add(seriesEnergyP);
        }
    }

    public void createDepthP() {
        if (lineChart.getData().contains(seriesDepthP)) {
            lineChart.getData().remove(seriesDepthP);
        } else {
            ArrayList<Wave> list = Model.selectPWave();
            addLineChart();
            seriesDepthP = new XYChart.Series();
            for (int i = 0; i < list.size(); i++) {
                seriesDepthP.getData().add(new XYChart.Data(list.get(i).getTimeWave(), Integer.valueOf(list.get(i).getDepthWave())));
            }
            seriesDepthP.setName("P - Depth");
            lineChart.getData().add(seriesDepthP);
        }
    }

    public void createMagnitudeP() {
        if (lineChart.getData().contains(seriesMagnitudeP)) {
            lineChart.getData().remove(seriesMagnitudeP);
        } else {
            ArrayList<Wave> list = Model.selectPWave();
            addLineChart();
            seriesMagnitudeP = new XYChart.Series();
            for (int i = 0; i < list.size(); i++) {
                seriesMagnitudeP.getData().add(new XYChart.Data(list.get(i).getTimeWave(), Integer.valueOf(list.get(i).getMagnitude())));
            }
            seriesMagnitudeP.setName("P - Magnitude");
            lineChart.getData().add(seriesMagnitudeP);
        }
    }

    public void createEnergyS() {
        if (lineChart.getData().contains(seriesEnergyS)) {
            lineChart.getData().remove(seriesEnergyS);
        } else {
            ArrayList<Wave> list = Model.selectSWave();
            addLineChart();

            seriesEnergyS = new XYChart.Series();
            for (int i = 0; i < list.size(); i++) {
                seriesEnergyS.getData().add(new XYChart.Data(list.get(i).getTimeWave(), Integer.valueOf(list.get(i).getEnergy())));
            }
            seriesEnergyS.setName("S - Energy");
            lineChart.getData().add(seriesEnergyS);
        }
    }

    public void createDepthS() {
        if (lineChart.getData().contains(seriesDepthS)) {
            lineChart.getData().remove(seriesDepthS);
        } else {
            ArrayList<Wave> list = Model.selectSWave();
            addLineChart();

            seriesDepthS = new XYChart.Series();
            for (int i = 0; i < list.size(); i++) {
                seriesDepthS.getData().add(new XYChart.Data(list.get(i).getTimeWave(), Integer.valueOf(list.get(i).getDepthWave())));
            }
            seriesDepthS.setName("S - Depth");
            lineChart.getData().add(seriesDepthS);
        }
    }

    public void createMagnitudeS() {
        if (lineChart.getData().contains(seriesMagnitudeS)) {
            lineChart.getData().remove(seriesMagnitudeS);
        } else {
            ArrayList<Wave> list = Model.selectSWave();
            addLineChart();

            seriesMagnitudeS = new XYChart.Series();
            for (int i = 0; i < list.size(); i++) {
                seriesMagnitudeS.getData().add(new XYChart.Data(list.get(i).getTimeWave(), Integer.valueOf(list.get(i).getMagnitude())));
            }
            seriesMagnitudeS.setName("S - Magnitude");
            lineChart.getData().add(seriesMagnitudeS);
        }
    }
}
