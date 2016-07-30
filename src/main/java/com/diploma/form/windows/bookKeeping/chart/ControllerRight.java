package com.diploma.form.windows.bookKeeping.chart;

import com.diploma.dataBase.Connect;
import com.diploma.form.windows.AbstractWindow;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerRight extends AbstractWindow {
    /*private NumberAxis X = new NumberAxis();
    private NumberAxis Y = new NumberAxis();
    private LineChart lineChart = new LineChart(X, Y);
    private XYChart.Series series = new XYChart.Series();*/

    private XYChart.Series series = new XYChart.Series();
    private XYChart.Series series1 = new XYChart.Series();
    private XYChart.Series series2 = new XYChart.Series();
    private XYChart.Series series3 = new XYChart.Series();
    private XYChart.Series seriesMagnitudeP;
    private XYChart.Series seriesMagnitudeS;
    private CategoryAxis X = new CategoryAxis();
    private NumberAxis Y = new NumberAxis();
    @FXML
    private StackedBarChart sbc;

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
        createEnergyP();
    }

    public void addLineChart() {

    }


    public void createEnergyP() {
        ArrayList<String> listDate = new ArrayList<>();
        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM BOOKKEEPING");
            while (resultSet.next()) {
                listDate.add(resultSet.getString(2) + " - " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<String> listTypes = new ArrayList<>();
        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.getStatement().executeQuery("SELECT NAME FROM TYPECHANGES");
            while (resultSet.next()) {
                listTypes.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<ColumnChart> listBalance = new ArrayList<>();
        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.getStatement().executeQuery("" +
                    "SELECT bl.ID,bl.VAL,bk.DATEBEGIN,bk.DATEEND,tc.NAME " +
                    "FROM BALANCECHANGES bl " +
                    "INNER JOIN BOOKKEEPING bk ON bk.ID = bl.BOOKKEEPING " +
                    "INNER JOIN TYPECHANGES tc ON tc.ID = bl.TYPECHANGES");
            while (resultSet.next()) {
                listBalance.add(new ColumnChart(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }
            System.out.println(listBalance.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < listBalance.size(); i++) {
            System.out.println(listBalance.get(i));
        }
        System.out.println("==========");

        X = new CategoryAxis();
        Y = new NumberAxis();
        series1 = new XYChart.Series();
        series2 = new XYChart.Series();
        series3 = new XYChart.Series();
        sbc = new StackedBarChart(X, Y);
        sbc.setTitle("Сумарная прибыль");
        X.setLabel("Период");
        X.setCategories(FXCollections.observableArrayList(listDate));
        Y.setLabel("Значение");


        for (String listType : listTypes) {
            XYChart.Series series = new XYChart.Series();
            series.setName(listType);
            for (int j = 0; j < listBalance.size(); j++) {
                System.out.println(listBalance.get(j));
                if (listBalance.get(j).getType().equals(listType)) {
                    series.getData().add(new XYChart.Data<String, Number>(listBalance.get(j).getBegin() + " - " + listBalance.get(j).getEnd(), Integer.parseInt(listBalance.get(j).getVal())));
                }
            }
            sbc.getData().add(series);
        }





        /*series1.setName("Договор");
        series1.getData().add(new XYChart.Data<String, Number>("2016.03.17 - 2016.04.17", 25601.34));
        series1.getData().add(new XYChart.Data<String, Number>("2016.04.17 - 2016.05.17", 20148.82));
        series1.getData().add(new XYChart.Data<String, Number>("2016.05.17 - 2016.06.17", 10000));
        series1.getData().add(new XYChart.Data<String, Number>("2016.06.17 - 2016.07.17", 35407.15));
        series1.getData().add(new XYChart.Data<String, Number>("2016.07.17 - 2016.08.17", 12000));
        series2.setName("Спонсор");
        series2.getData().add(new XYChart.Data<String, Number>("2016.03.17 - 2016.04.17", 57401.85));
        series2.getData().add(new XYChart.Data<String, Number>("2016.04.17 - 2016.05.17", 41941.19));
        series2.getData().add(new XYChart.Data<String, Number>("2016.05.17 - 2016.06.17", 45263.37));
        series2.getData().add(new XYChart.Data<String, Number>("2016.06.17 - 2016.07.17", 117320.16));
        series2.getData().add(new XYChart.Data<String, Number>("2016.07.17 - 2016.08.17", 14845.27));
        series3.setName("Дополнительно");
        series3.getData().add(new XYChart.Data<String, Number>("2016.03.17 - 2016.04.17", 45000.65));
        series3.getData().add(new XYChart.Data<String, Number>("2016.04.17 - 2016.05.17", 44835.76));
        series3.getData().add(new XYChart.Data<String, Number>("2016.05.17 - 2016.06.17", 18722.18));
        series3.getData().add(new XYChart.Data<String, Number>("2016.06.17 - 2016.07.17", 17557.31));
        series3.getData().add(new XYChart.Data<String, Number>("2016.07.17 - 2016.08.17", 92633.68));
        sbc.getData().addAll(series1, series2, series3);*/

        scrollPane.setContent(sbc);
    }

    class ColumnChart {
        private String id;
        private String val;
        private String begin;
        private String end;
        private String type;

        public ColumnChart(String id, String val, String begin, String end, String type) {
            this.id = id;
            this.val = val;
            this.begin = begin;
            this.end = end;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }

        public String getBegin() {
            return begin;
        }

        public void setBegin(String begin) {
            this.begin = begin;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "ColumnChart{" +
                    "id='" + id + '\'' +
                    ", val='" + val + '\'' +
                    ", begin='" + begin + '\'' +
                    ", end='" + end + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }


}
