package com.diploma.form.windows.analysis.data;


import com.diploma.dataBase.Command;
import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.DataAnalys;
import com.diploma.dataBase.tables.TypeData;
import com.diploma.form.windows.AbstractWindow;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ControllerRight extends AbstractWindow {

    @FXML
    private ScrollPane mainScrollPane;
    @FXML
    private ComboBox<String> analysisComboBox;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TextField valueTextField;
    @FXML
    private HBox mainHBox;

    private boolean areaChart = false;
    private boolean pieChart = false;
    private AreaChart<Number, Number> ac;
    private PieChart pc;
    private TableView<DataAnalys> tableView;
    private int analyse = 0;

    public ControllerRight(String path) {
        super(path);
    }

    public int getAnalyse() {
        return analyse;
    }

    public void setAnalyse(int analyse) {
        this.analyse = analyse;
        refreshData();
    }

    public void initialize() {
        createTable();
        createComboBox();
    }

    private void createComboBox() {
        try {
            analysisComboBox.getItems().clear();
            typeComboBox.getItems().clear();
            Connect connect = new Connect();
            ResultSet resultSet = connect.getStatement().executeQuery("SELECT NAME FROM ANALYSIS");
            while (resultSet.next()) {
                analysisComboBox.getItems().add(resultSet.getString(1));
            }
            resultSet = connect.getStatement().executeQuery("SELECT NAME FROM TYPEDATA");
            while (resultSet.next()) {
                typeComboBox.getItems().add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshData() {
        createTable();
        addPieChart();
        addPieChart();
        addAreaChart();
        addAreaChart();
    }

    @FXML
    private void deleteData() {
        try {
            DataAnalys data = tableView.getSelectionModel().getSelectedItem();
            Connect connect = new Connect();
            connect.getStatement().executeUpdate("DELETE FROM DATAANALYS WHERE ID = " + data.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshData();
    }

    @FXML
    private void insertData() {
        try {
            Connect connect = new Connect();
            ResultSet resultSet;

            resultSet = connect.getStatement().executeQuery("SELECT ID FROM ANALYSIS WHERE NAME = '" + analysisComboBox.getSelectionModel().getSelectedItem() + "'");
            resultSet.next();
            int analys = resultSet.getInt(1);

            resultSet = connect.getStatement().executeQuery("SELECT ID FROM TYPEDATA WHERE NAME = '" + typeComboBox.getSelectionModel().getSelectedItem() + "'");
            resultSet.next();
            int type = resultSet.getInt(1);


            resultSet = connect.getStatement().executeQuery("" +
                    "SELECT MAX(INDX) " +
                    "FROM DATAANALYS " +
                    "INNER JOIN ANALYSIS ON ANALYSIS.ID = DATAANALYS.ANALYSIS " +
                    "WHERE ANALYSIS.NAME = '" + analysisComboBox.getSelectionModel().getSelectedItem() + "' and TYPEDATA = " + type);
            resultSet.next();
            int indx = 1;
            if (resultSet.getString(1) != null) {
                indx = Integer.parseInt(resultSet.getString(1)) + 1;
            }


            connect.getStatement().executeUpdate("" +
                    "INSERT INTO DATAANALYS " +
                    "VALUES (1," + analys + "," + type + ",'" + valueTextField.getText() + "','" + indx + "')");
            connect.closeConnect();
            refreshData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        mainHBox.getChildren().remove(tableView);
        tableView = new TableView<>();

        List<DataAnalys> list;
        if (getAnalyse() != 0) {
            Query query = Command.getEm().createQuery("select a FROM DataAnalys as a where a.analysis.id = " + analyse);
            list = query.getResultList();
        } else {
            list = Command.select(DataAnalys.class);
        }


        TableColumn<DataAnalys, Integer> tableColumnId = new TableColumn<>("Ид");
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<DataAnalys, String> tableColumnAnalys = new TableColumn<>("Анализ");
        tableColumnAnalys.setCellValueFactory(new PropertyValueFactory<>("analysString"));

        TableColumn<DataAnalys, String> tableColumnType = new TableColumn<>("Тип");
        tableColumnType.setCellValueFactory(new PropertyValueFactory<>("typeDataString"));

        TableColumn<DataAnalys, String> tableColumnValue = new TableColumn<>("Значние");
        tableColumnValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<DataAnalys, String> tableColumnIndex = new TableColumn<>("Индекс");
        tableColumnIndex.setCellValueFactory(new PropertyValueFactory<>("index"));

        ObservableList<DataAnalys> observableList = FXCollections.observableArrayList(list);

        tableView.setItems(observableList);
        tableView.getColumns().add(tableColumnId);
        tableView.getColumns().add(tableColumnAnalys);
        tableView.getColumns().add(tableColumnType);
        tableView.getColumns().add(tableColumnValue);
        tableView.getColumns().add(tableColumnIndex);
        tableView.setMinWidth(350);
        mainHBox.getChildren().add(tableView);
    }

    public void addPieChart() {
        if (pieChart) {
            mainHBox.getChildren().remove(pc);
            pieChart = false;
        } else {
            try {
                Connect connect = new Connect();
                ResultSet resultSet;
                if (analyse != 0) {
                    resultSet = connect.getStatement().executeQuery("" +
                            "SELECT COUNT(DATAANALYS.ID),TYPEDATA.NAME,DATAANALYS.TYPEDATA " +
                            "FROM DATAANALYS " +
                            "INNER JOIN TYPEDATA ON TYPEDATA.ID = DATAANALYS.TYPEDATA " +
                            "WHERE ANALYSIS = " + getAnalyse() +
                            "GROUP BY DATAANALYS.TYPEDATA, TYPEDATA.NAME");
                } else {
                    resultSet = connect.getStatement().executeQuery("" +
                            "SELECT COUNT(DATAANALYS.ID),TYPEDATA.NAME,DATAANALYS.TYPEDATA " +
                            "FROM DATAANALYS " +
                            "INNER JOIN TYPEDATA ON TYPEDATA.ID = DATAANALYS.TYPEDATA " +
                            "GROUP BY DATAANALYS.TYPEDATA, TYPEDATA.NAME");
                }

                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

                while (resultSet.next()) {
                    pieChartData.add(new PieChart.Data(resultSet.getString(2), resultSet.getDouble(1)));
                }


                pc = new PieChart(pieChartData);
                pc.setMinWidth(400);
                mainHBox.getChildren().add(pc);
                pieChart = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addAreaChart() {
        if (areaChart) {
            mainHBox.getChildren().remove(ac);
            areaChart = false;

        } else {
            List<DataAnalys> listData;
            List<String> listTypes;

            Query query;
            if (analyse != 0) {
                query = Command.getEm().createQuery("select distinct a.typeData.name FROM DataAnalys as a where a.analysis.id = " + analyse + " and a.typeData.abbreviation = 'int'");
                listTypes = query.getResultList();

                query = Command.getEm().createQuery("select a from DataAnalys as a where a.analysis.id = " + analyse + " and a.typeData.abbreviation = 'int'");
                listData = query.getResultList();
            } else {
                query = Command.getEm().createQuery("select distinct a.typeData.name from DataAnalys as a where a.typeData.abbreviation = 'int'");
                listTypes = query.getResultList();

                query = Command.getEm().createQuery("select a from DataAnalys as a where a.typeData.abbreviation = 'int'");
                listData = query.getResultList();
            }

            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();
            ac = new AreaChart<>(xAxis, yAxis);
            ac.setPrefWidth(mainHBox.getWidth());

            for (int i = 0; i < listTypes.size(); i++) {
                XYChart.Series series = new XYChart.Series();
                series.setName(listTypes.get(i));

                for (int j = 0; j < listData.size(); j++) {
                    if (listData.get(j).getTypeData().getName().equals(listTypes.get(i))) {
                        series.getData().add(new XYChart.Data(listData.get(j).getIndex(), Integer.parseInt(listData.get(j).getValue())));
                    }
                }
                ac.getData().add(series);
            }
            System.out.println("EXIT");
            mainHBox.getChildren().add(ac);
            areaChart = true;
        }
    }


    @FXML
    private void createAnalys() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setHeaderText("Создать анализ");

        ButtonType loginButtonType = new ButtonType("Enter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField username = new TextField();
        username.setPromptText("Name");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(username, 1, 0);


        dialog.getDialogPane().setContent(grid);

        Platform.runLater(username::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return username.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            try {
                Connect connect = new Connect();
                connect.getStatement().executeUpdate("INSERT INTO ANALYSIS (NAME) VALUES ('" + usernamePassword + "')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            createComboBox();
        });
    }

    @FXML
    private void createTypeData() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setHeaderText("Создать тип");

        ButtonType loginButtonType = new ButtonType("Enter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField username = new TextField();
        username.setPromptText("Название");
        PasswordField password = new PasswordField();
        password.setPromptText("Аббревиатура");

        grid.add(new Label("Название:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Аббревиатура:"), 0, 1);
        grid.add(password, 1, 1);


        dialog.getDialogPane().setContent(grid);

        Platform.runLater(username::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            Command.insert(new TypeData(1, usernamePassword.getKey(), usernamePassword.getValue()));
            createComboBox();
        });
    }

    @FXML
    private void deleteAnalys() {
        try {
            Connect connect = new Connect();
            connect.getStatement().executeUpdate("DELETE FROM ANALYSIS WHERE NAME = '" + analysisComboBox.getSelectionModel().getSelectedItem() + "'");
            connect.closeConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createComboBox();
    }

    @FXML
    private void deleteTypeData() {
        try {
            Connect connect = new Connect();
            connect.getStatement().executeUpdate("DELETE FROM TYPEDATA WHERE NAME = '" + typeComboBox.getSelectionModel().getSelectedItem() + "'");
            connect.closeConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createComboBox();

    }
}
