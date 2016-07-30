package com.diploma.form.windows.analysis.charts;

import com.diploma.dataBase.Connect;
import com.diploma.form.windows.AbstractWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    public ScrollPane scrollPane;

    public ControllerRight(String path) {
        super(path);
    }

    public void initialize(URL location, ResourceBundle resources) {


        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.getStatement().executeQuery("" +
                    "SELECT COUNT(DATAANALYS.ID),TYPEDATA.NAME,DATAANALYS.TYPEDATA FROM DATAANALYS INNER JOIN TYPEDATA ON TYPEDATA.ID = DATAANALYS.TYPEDATA GROUP BY DATAANALYS.TYPEDATA, TYPEDATA.NAME");


            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

            while (resultSet.next()) {
                pieChartData.add(new PieChart.Data(resultSet.getString(2), resultSet.getDouble(1)));
            }


            final PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Imported Fruits");
            scrollPane.setContent(chart);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
