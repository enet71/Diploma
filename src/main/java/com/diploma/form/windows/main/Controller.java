package com.diploma.form.windows.main;

import com.diploma.dataBase.Connect;
import com.diploma.dataLoad.Export;
import com.diploma.dataLoad.Import;
import com.diploma.form.windows.WindowService;
import com.diploma.form.windows.additionally.converter.ConverterWindow;
import com.diploma.form.windows.analysis.charts.PieChartWindow;
import com.diploma.form.windows.analysis.data.DataWindow;
import com.diploma.form.windows.contracts.confirm.ConfirmWindow;
import com.diploma.form.windows.contracts.contract.ContractWindow;
import com.diploma.form.windows.contracts.select.SelectWindow;
import com.diploma.form.windows.data.watch.table.TableWindow;
import com.diploma.form.windows.doubleWindow.DoubleWindow;
import com.diploma.form.windows.doubleWindow.DoubleWindowTab;
import com.diploma.form.windows.htmlReader.htmlReaderWindow;
import com.diploma.staticField.SettingFields;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Controller {
    public ToolBar topToolBar;
    public Button buttonLineChart;
    public TabPane tabPane;
    public BorderPane borderPaneLeft;
    public SplitPane splitPaneMain;
    public Label connectDB;
    public Label connectInternet;
    public Button tableButton;
    public Button chartButton;
    public Button groundButton;
    public Button seismicButton;
    public Button regionButton;
    public Button sensorButton;
    public TabPane mainTabPain;
    public HBox mainBottomPanel;
    public Button converter;
    public Button translate;
    public Tab map;
    public BorderPane mainBorderPane;
    private WindowService windowService;

    @FXML
    public void initialize() {
        localization();

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (tabPane.getTabs().size() != 0) {
                        setBorderPaneLeft(((DoubleWindowTab) newValue).getDoubleWindow().getLeftBorderPane());
                    } else {
                        borderPaneLeft.setCenter(null);
                    }
                }
        );

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(SettingFields.RECONNECTDB), event -> {
            Connect connect = new Connect();
            if (connect.getConnectStatus()) {
                connectDB.setText(" успешно");
                connectDB.setStyle("-fx-text-fill: #17b73d");
                connect.closeConnect();
            } else {
                connectDB.setText(" неудачно");
                connectDB.setStyle("-fx-text-fill: #b73b3a");

            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline timeNetConnect = new Timeline(new KeyFrame(Duration.millis(SettingFields.RECONNECTINTERNET), event -> {
            if (Model.checkInternetConnection()) {
                connectInternet.setText(" успешно");
                connectInternet.setStyle("-fx-text-fill: #17b73d");
                converter.setDisable(false);
                translate.setDisable(false);
                map.setDisable(false);

            } else {
                connectInternet.setText(" неудачно");
                connectInternet.setStyle("-fx-text-fill: #b73b3a");
                converter.setDisable(true);
                translate.setDisable(true);
                map.setDisable(true);
            }
        }));
        timeNetConnect.setCycleCount(Animation.INDEFINITE);
        timeNetConnect.play();
    }


    private WindowService createWindow() {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        WindowService windowService = container.instance().select(WindowService.class).get();
        //weld.shutdown();
        return windowService;
    }

    @FXML
    private void createGround() throws IOException {
        addDoubleWindow(WindowService.createGround());
    }

    @FXML
    private void createSensor() throws IOException {
       addDoubleWindow(WindowService.createSensor());
    }

    @FXML
    private void createSeismic() throws IOException {
        addDoubleWindow(WindowService.createSeismic());
    }

    @FXML
    private void createRegion() throws IOException {
        addDoubleWindow(WindowService.createRegion());
    }

    @FXML
    private void dataTable() {
        addDoubleWindow(new TableWindow());
    }

    @FXML
    void dataChart() {
        //addDoubleWindow(WindowService.getWindowService().createWaveChart());
    }

    @FXML
    void bookKeepingChart() {
        addDoubleWindow(WindowService.createBalanceChart());
    }

    @FXML
    void createContract() throws IOException {
        addDoubleWindow(new ContractWindow(), "Контракт");
    }

    @FXML
    void createConverter() throws IOException {
        addDoubleWindow(new ConverterWindow(), "Конвертер");
    }

    @FXML
    void createMap() throws IOException {
        addDoubleWindow(WindowService.createMap());
    }

    @FXML
    void createAnalysData() throws IOException {
        addDoubleWindow(new DataWindow(), "Данные");
    }

    @FXML
    void createClient() throws IOException {
        addDoubleWindow(WindowService.createClient());
    }

    @FXML
    void createKeeping() throws IOException {
        addDoubleWindow(WindowService.createTypeKeeping());
    }

    @FXML
    void createTypeChanges() throws IOException {
        addDoubleWindow(WindowService.createTypeBalance());
    }

    @FXML
    void createBalance() throws IOException {
        addDoubleWindow(WindowService.createBalance());
    }

    @FXML
    void createPieChartData() throws IOException {
        addDoubleWindow(new PieChartWindow(), "График");
    }

    @FXML
    void createConfirmContract() throws IOException {
        addDoubleWindow(new ConfirmWindow(), "Подтвердить контракт");
    }

    @FXML
    void createSelectContract() throws IOException {
        addDoubleWindow(new SelectWindow(), "Подтвержденные контракты");
    }

    @FXML
    void createHtml() throws IOException {
        addDoubleWindow(new htmlReaderWindow(), "Информационная база");
    }

    /*private void addDoubleWindow(DoubleWindow doubleWindow) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setHeaderText("Введите название");
        Optional<String> result = dialog.showAndWait();
        String name = null;

        if (result.isPresent()) {
            name = result.get();
        }

        if (name != null) {
            addDoubleWindow(doubleWindow, name);
        }
    }*/

    private void addDoubleWindow(DoubleWindow doubleWindow) {
        DoubleWindowTab tab = new DoubleWindowTab(doubleWindow.getName(), doubleWindow, tabPane);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
        setBorderPaneLeft(tab.getDoubleWindow().getLeftBorderPane());

        for (Tab t : tabPane.getTabs()) {
            if (((DoubleWindowTab) t).getDoubleWindow().getRightBorderPane().getCenter() == null) {
                tabPane.getTabs().remove(t);
                break;
            }
        }
    }

    private void addDoubleWindow(DoubleWindow doubleWindow, String name) {
        DoubleWindowTab tab = new DoubleWindowTab(name, doubleWindow, tabPane);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
        setBorderPaneLeft(tab.getDoubleWindow().getLeftBorderPane());

    }

    private void setBorderPaneLeft(BorderPane borderPane) {
       /* if (borderPane != null && splitPaneMain.getItems().size() == 1) {
            borderPaneLeft.setCenter(borderPane);
            splitPaneMain.getItems().add(0, borderPaneLeft);
        } else if (borderPane != null) {*/
        borderPaneLeft.setCenter(borderPane);
        /*} else if (splitPaneMain.getItems().size() > 1) {
            System.out.println(splitPaneMain.getItems());
            splitPaneMain.getItems().remove(0);
        }*/
    }

    @FXML
    private void importDataFile() throws IOException, SQLException {
        Stage stage = (Stage) tabPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("data", "*.data")
        );

        File file = fileChooser.showOpenDialog(stage);
        Import.dataFile(file);
    }

    @FXML
    private void exportDataFile() throws IOException, SQLException {
        Stage stage = (Stage) tabPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("data(*.data)", "*.data")
        );
        File file = fileChooser.showSaveDialog(stage);
        Export.dataFile(file);
    }

    @FXML
    private void exportExcelFile() throws IOException, SQLException {
        Stage stage = (Stage) tabPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("excel(*.xls)", "*.xls")
        );
        File file = fileChooser.showSaveDialog(stage);
        Export.excelFile(file);
    }

    @FXML
    private void openSetting() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/setting.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void openMail() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/SendMail.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void openCalculate() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("/form/windows/additionally/calculate/sample.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    private void localization() {
        /*chartButton.setText(Localization.CHART);
        groundButton.setText(Localization.GROUND);
        regionButton.setText(Localization.REGION);
        seismicButton.setText(Localization.SEISMIC);
        sensorButton.setText(Localization.SENSOR);
        tableButton.setText(Localization.TABLE);*/
    }
}
