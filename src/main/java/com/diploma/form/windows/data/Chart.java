package com.diploma.form.windows.data;

import com.diploma.form.windows.doubleWindow.DoubleWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Chart implements DoubleWindow {
    private BorderPane leftBorderPane = new BorderPane();
    private BorderPane rightBorderPane = new BorderPane();
    private NumberAxis X = new NumberAxis();
    private NumberAxis Y = new NumberAxis();
    private LineChart lineChart = new LineChart(X, Y);
    private XYChart.Series series = new XYChart.Series();


    public Chart() {
        rightBorderPane.setCenter(lineChart);
        try {
            leftBorderPane.setCenter(FXMLLoader.load(this.getClass().getResource("/form/windows/data/watch/table/tableLeft.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 10; i++) {
            addToChart();
        }



    }
    @Override
    public BorderPane getLeftBorderPane() {
        return leftBorderPane;
    }

    @Override
    public BorderPane getRightBorderPane() {
        return rightBorderPane;
    }

    @Override
    public String getName() {
        return null;
    }


    private void addToChart() {
        lineChart = new LineChart(X, Y);
        series.getData().add(new XYChart.Data(Math.random() * 100, Math.random() * 100));
        lineChart.getData().add(series);
        rightBorderPane.setCenter(lineChart);

    }
}
