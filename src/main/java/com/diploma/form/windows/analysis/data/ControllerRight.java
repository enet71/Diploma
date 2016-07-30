package com.diploma.form.windows.analysis.data;


import com.diploma.dataBase.Connect;
import com.diploma.form.windows.AbstractWindow;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

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
    private TableView<Data> tableView;
    private int analyse = 0;

    public ControllerRight(String path) {
        super(path);
    }

    public int getAnalyse() {
        return analyse;
    }

    public void setAnalyse(int analyse) {
        this.analyse = analyse;
        refresh();
    }

    public void initialize(URL location, ResourceBundle resources) {
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

    public void refresh(){
        createTable();
        addPieChart();
        addPieChart();
        addAreaChart();
        addAreaChart();
    }

    @FXML
    private void deleteData(){
        try {
            Data data = tableView.getSelectionModel().getSelectedItem();
            Connect connect = new Connect();
            connect.getStatement().executeUpdate("DELETE FROM DATAANALYS WHERE ID = " + data.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }
        refresh();
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
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        mainHBox.getChildren().remove(tableView);
        tableView = new TableView<>();
        ArrayList<Data> arrayList = new ArrayList<>();

        try {
            Connect connect = new Connect();
            ResultSet resultSet;
            if(getAnalyse() != 0) {
                resultSet = connect.getStatement().executeQuery("" +
                        "SELECT DATAANALYS.ID,ANALYSIS.NAME,TYPEDATA.NAME,DATAANALYS.VALUE,DATAANALYS.INDX " +
                        "FROM DATAANALYS " +
                        "INNER JOIN ANALYSIS ON ANALYSIS.ID = DATAANALYS.ANALYSIS " +
                        "INNER JOIN TYPEDATA ON TYPEDATA.ID = DATAANALYS.TYPEDATA " +
                        "WHERE DATAANALYS.ANALYSIS = " + getAnalyse());
            }else {
                resultSet = connect.getStatement().executeQuery("" +
                        "SELECT DATAANALYS.ID,ANALYSIS.NAME,TYPEDATA.NAME,DATAANALYS.VALUE,DATAANALYS.INDX " +
                        "FROM DATAANALYS " +
                        "INNER JOIN ANALYSIS ON ANALYSIS.ID = DATAANALYS.ANALYSIS " +
                        "INNER JOIN TYPEDATA ON TYPEDATA.ID = DATAANALYS.TYPEDATA ");
            }

            while (resultSet.next()) {
                arrayList.add(new Data(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5)));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        TableColumn<Data, String> tableColumnId = new TableColumn<>("Ид");
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Data, String> tableColumnAnalys = new TableColumn<>("Анализ");
        tableColumnAnalys.setCellValueFactory(new PropertyValueFactory<>("analys"));

        TableColumn<Data, String> tableColumnType = new TableColumn<>("Тип");
        tableColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Data, String> tableColumnValue = new TableColumn<>("Значние");
        tableColumnValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<Data, String> tableColumnIndex = new TableColumn<>("Индекс");
        tableColumnIndex.setCellValueFactory(new PropertyValueFactory<>("index"));

        ObservableList<Data> observableList = FXCollections.observableArrayList(arrayList);

        tableView.setItems(observableList);
        tableView.getColumns().add(tableColumnId);
        tableView.getColumns().add(tableColumnAnalys);
        tableView.getColumns().add(tableColumnType);
        tableView.getColumns().add(tableColumnValue);
        tableView.getColumns().add(tableColumnIndex);
        tableView.setMinWidth(350);
        mainHBox.getChildren().add(tableView);
    }

    public void addBarChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Country");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        series1.getData().add(new XYChart.Data("austria", 25601.34));
        series1.getData().add(new XYChart.Data("brazil", 20148.82));
        series1.getData().add(new XYChart.Data("france", 10000));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("2004");
        series2.getData().add(new XYChart.Data("austria", 57401.85));
        series2.getData().add(new XYChart.Data("brazil", 41941.19));
        series2.getData().add(new XYChart.Data("france", 45263.37));

        bc.getData().addAll(series1, series2);
        mainHBox.getChildren().addAll(bc);
    }

    public void addPieChart() {
        if (pieChart) {
            mainHBox.getChildren().remove(pc);
            pieChart = false;
        } else {
            try {
                Connect connect = new Connect();
                ResultSet resultSet;
                if(getAnalyse() != 0) {
                    resultSet = connect.getStatement().executeQuery("" +
                            "SELECT COUNT(DATAANALYS.ID),TYPEDATA.NAME,DATAANALYS.TYPEDATA " +
                            "FROM DATAANALYS " +
                            "INNER JOIN TYPEDATA ON TYPEDATA.ID = DATAANALYS.TYPEDATA " +
                            "WHERE ANALYSIS = " + getAnalyse() +
                            "GROUP BY DATAANALYS.TYPEDATA, TYPEDATA.NAME");
                }else {
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
            ArrayList<DataAnalys> listData = new ArrayList<>();
            ArrayList<String> listTypes = new ArrayList<>();

            try {
                Connect connect = new Connect();
                ResultSet resultSet;
                if(getAnalyse() != 0) {
                    resultSet = connect.getStatement().executeQuery("" +
                            "SELECT DISTINCT (TYPEDATA.NAME) " +
                            "FROM dataanalys " +
                            "INNER JOIN TYPEDATA ON TYPEDATA.ID = DATAANALYS.TYPEDATA " +
                            "WHERE ANALYSIS = " + getAnalyse() + " AND TYPEDATA.ABBREVIATION = 'int'");
                    while (resultSet.next()) {
                        listTypes.add(resultSet.getString(1));
                    }

                    resultSet = connect.getStatement().executeQuery("" +
                            "SELECT INDX,VALUE,TYPEDATA.NAME " +
                            "FROM DATAANALYS " +
                            "INNER JOIN TYPEDATA ON TYPEDATA.ID = DATAANALYS.TYPEDATA " +
                            "WHERE ANALYSIS = " + getAnalyse() + " AND TYPEDATA.ABBREVIATION = 'int'");
                }else {
                    resultSet = connect.getStatement().executeQuery("" +
                            "SELECT DISTINCT (TYPEDATA.NAME) " +
                            "FROM dataanalys " +
                            "INNER JOIN TYPEDATA ON TYPEDATA.ID = DATAANALYS.TYPEDATA " +
                            "WHERE TYPEDATA.ABBREVIATION = 'int'");
                    while (resultSet.next()) {
                        listTypes.add(resultSet.getString(1));
                    }

                    resultSet = connect.getStatement().executeQuery("" +
                            "SELECT INDX,VALUE,TYPEDATA.NAME " +
                            "FROM DATAANALYS " +
                            "INNER JOIN TYPEDATA ON TYPEDATA.ID = DATAANALYS.TYPEDATA " +
                            "WHERE TYPEDATA.ABBREVIATION = 'int'");
                }
                    while (resultSet.next()) {
                    listData.add(new DataAnalys(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            final NumberAxis xAxis = new NumberAxis();
            final NumberAxis yAxis = new NumberAxis();
            ac = new AreaChart<>(xAxis, yAxis);
            ac.setPrefWidth(mainHBox.getWidth());

            for (int i = 0; i < listTypes.size(); i++) {
                XYChart.Series series = new XYChart.Series();
                series.setName(listTypes.get(i));

                for (int j = 0; j < listData.size(); j++) {
                    System.out.println(listData.get(j).getType());
                    System.out.println(listTypes.get(i));
                    if (listData.get(j).getType().equals(listTypes.get(i))) {
                        series.getData().add(new XYChart.Data(listData.get(j).getIndex(), listData.get(j).getValue()));
                        System.out.println(listData.get(j).getIndex() + listData.get(j).getValue());
                    }
                }
                ac.getData().add(series);
            }
            mainHBox.getChildren().add(ac);
            areaChart = true;
        }
    }


    @FXML
    private void createAnalys() {
        // Create the custom dialog.
        Dialog<String> dialog = new Dialog<>();
        dialog.setHeaderText("Создать анализ");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Enter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField username = new TextField();
        username.setPromptText("Name");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(username, 1, 0);


        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(username::requestFocus);

        // Convert the result to a username-password-pair when the login button is clicked.
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
// Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setHeaderText("Создать тип");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Enter", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
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

        // Request focus on the username field by default.
        Platform.runLater(username::requestFocus);

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            try {
                Connect connect = new Connect();
                connect.getStatement().executeUpdate("INSERT INTO TYPEDATA (NAME,ABBREVIATION) VALUES ('" + usernamePassword.getKey() + "','" + usernamePassword.getValue() + "')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public class DataAnalys {
        int index;
        int value;
        String type;

        public DataAnalys(int index, int value, String type) {
            this.index = index;
            this.value = value;
            this.type = type;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public class Data {
        String id;
        String analys;
        String type;
        String value;
        int index;

        public Data(String id, String analys, String type, String value, int index) {
            this.id = id;
            this.analys = analys;
            this.type = type;
            this.value = value;
            this.index = index;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAnalys() {
            return analys;
        }

        public void setAnalys(String analys) {
            this.analys = analys;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
