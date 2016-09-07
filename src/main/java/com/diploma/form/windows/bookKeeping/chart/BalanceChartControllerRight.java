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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BalanceChartControllerRight extends AbstractWindow {
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

    public BalanceChartControllerRight() {
        super("fxml/chartRight.fxml");
    }

    public void initialize() {
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
        /*ArrayList<String> listDate = new ArrayList<>();
        List<BookKeeping> bookKeepingList = Command.select(BookKeeping.class);

        for (BookKeeping element : bookKeepingList) {
            listDate.add(element.getDateBegin() + " - " + element.getDateEnd());
        }*/


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
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                if (listBalance.get(j).getType().equals(listType)) {
                    series.getData().add(new XYChart.Data<String, Number>(listBalance.get(j).getBegin() + " - " + listBalance.get(j).getEnd(), Integer.parseInt(listBalance.get(j).getVal())));
                }
            }
            sbc.getData().add(series);
        }







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
