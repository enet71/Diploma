package com.diploma.form.windows.data.watch.table;

import com.diploma.dataBase.enums.EnumWave;
import com.diploma.dataBase.tables.Wave;
import com.diploma.form.windows.AbstractWindow;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import com.diploma.localization.Start;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    TableView<WavePrivate> table;
    @FXML
    private ScrollPane scrollPane;
    private ObservableList<WavePrivate> observableList;
    private EnumSet<EnumWave> set = EnumSet.of(EnumWave.ID);

    private int maxDepth = Integer.MAX_VALUE;
    private int minDepth = Integer.MIN_VALUE;
    private int maxEnergy = Integer.MAX_VALUE;
    private int minEnergy = Integer.MIN_VALUE;
    private int maxMagnitude = Integer.MAX_VALUE;
    private int minMagnitude = Integer.MIN_VALUE;

    private ControllerLeft controllerLeft;

    public void setController(ControllerLeft controllerLeft){
        this.controllerLeft = controllerLeft;
    }
    public ControllerRight(String path) {
        super(path);
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public int getMinDepth() {
        return minDepth;
    }

    public void setMinDepth(int minDepth) {
        this.minDepth = minDepth;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getMinEnergy() {
        return minEnergy;
    }

    public void setMinEnergy(int minEnergy) {
        this.minEnergy = minEnergy;
    }

    public int getMaxMagnitude() {
        return maxMagnitude;
    }

    public void setMaxMagnitude(int maxMagnitude) {
        this.maxMagnitude = maxMagnitude;
    }

    public int getMinMagnitude() {
        return minMagnitude;
    }
    //private Set set = new HashSet<>();

    public void setMinMagnitude(int minMagnitude) {
        this.minMagnitude = minMagnitude;
    }

    public void initialize(URL location, ResourceBundle resources) {
        createTable();
    }


    /**
     * Обновляет данные в таблице.
     * Поочередно обновляет каждый столбец из списка включенных столбцов.
     *
     * @throws SQLException
     */
    public void refreshTable() throws SQLException {
        for (EnumWave element : set) {
            editColumn(element);
        }
    }

    private void createTable() {
        table = new TableView<>();
        observableList = FXCollections.observableArrayList();
        TableColumn<WavePrivate, String> tableColumnId = new TableColumn<>("Ид");
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        try {
            ArrayList<Wave> waves = Model.selectRegion();
            for (Wave element : waves) {
                //observableList.add(new WavePrivate(element.getId(), null, null, null, null, null, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(observableList);
        table.getColumns().add(tableColumnId);
        scrollPane.setContent(table);
    }

    public void editColumn(EnumWave sql) throws SQLException {
        String name = Start.TABLENAME.getName(sql);
        boolean remove = false;
        for (int i = 0; i < table.getColumns().size(); i++) {
            if (table.getColumns().get(i).getText().equals(name)) {
                table.getColumns().remove(i);
                remove = true;
                set.remove(sql);
            }
        }
        if (!remove) {
            TableColumn<WavePrivate, String> tableColumn = new TableColumn<>(name);
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(sql.name()));
            ArrayList<String> list = Model.selectRegion(sql.name());
            set.add(sql);

            for (int i = 0; i < table.getItems().size(); i++) {
                switch (sql) {
                    case ID:
                        observableList.get(i).setId(list.get(i));
                        break;
                    case SENSOR:
                        observableList.get(i).setSENSOR(list.get(i));
                        break;
                    case TIMEWAVE:
                        observableList.get(i).setTIMEWAVE(list.get(i));
                        break;
                    case DEPTHWAVE:
                        observableList.get(i).setDEPTHWAVE(list.get(i));
                        colorColumn(tableColumn,maxDepth,minDepth);
                        break;
                    case ENERGY:
                        observableList.get(i).setENERGY(list.get(i));
                        colorColumn(tableColumn,maxEnergy,minEnergy);
                        break;
                    case TYPEWAVE:
                        observableList.get(i).setTYPEWAVE(list.get(i));
                        break;
                    case MAGNITUDE:
                        observableList.get(i).setMAGNITUDE(list.get(i));
                        colorColumn(tableColumn,maxMagnitude,minMagnitude);
                        break;
                }
            }
            table.getColumns().add(tableColumn);
        }
    }

    private void colorColumn(TableColumn<WavePrivate, String> tableColumn,int max,int min){
        tableColumn.setCellFactory(new Callback<TableColumn<WavePrivate, String>, TableCell<WavePrivate, String>>() {
            @Override
            public TableCell<WavePrivate, String> call(TableColumn<WavePrivate, String> param) {
                return new TableCell<WavePrivate, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            if (Integer.parseInt(item) > max) {
                                this.setStyle("-fx-background-color: rgba(237, 0, 2, 0.18)");
                            } else if (Integer.parseInt(item) < min) {
                                this.setStyle("-fx-background-color: rgba(0, 237, 1, 0.18)");
                            }
                            setText(item);
                        }
                    }

                };
            }
        });
    }

    /**
     * Класс для колонок таблицы.
     */
    public class WavePrivate {
        private final SimpleStringProperty id;
        private final SimpleStringProperty SENSOR;
        private final SimpleStringProperty TIMEWAVE;
        private final SimpleStringProperty DEPTHWAVE;
        private final SimpleStringProperty ENERGY;
        private final SimpleStringProperty TYPEWAVE;
        private final SimpleStringProperty MAGNITUDE;

        public WavePrivate(String id, String SENSOR, String TIMEWAVE, String DEPTHWAVE, String ENERGY, String TYPEWAVE, String MAGNITUDE) {
            this.id = new SimpleStringProperty(id);
            this.SENSOR = new SimpleStringProperty(SENSOR);
            this.TIMEWAVE = new SimpleStringProperty(TIMEWAVE);
            this.DEPTHWAVE = new SimpleStringProperty(DEPTHWAVE);
            this.ENERGY = new SimpleStringProperty(ENERGY);
            this.TYPEWAVE = new SimpleStringProperty(TYPEWAVE);
            this.MAGNITUDE = new SimpleStringProperty(MAGNITUDE);
        }

        public String getId() {
            return id.get();
        }

        public void setId(String id) {
            this.id.set(id);
        }

        public String getSENSOR() {
            return SENSOR.get();
        }

        public void setSENSOR(String SENSOR) {
            this.SENSOR.set(SENSOR);
        }

        public String getTIMEWAVE() {
            return TIMEWAVE.get();
        }

        public void setTIMEWAVE(String TIMEWAVE) {
            this.TIMEWAVE.set(TIMEWAVE);
        }

        public String getDEPTHWAVE() {
            return DEPTHWAVE.get();
        }

        public void setDEPTHWAVE(String DEPTHWAVE) {
            this.DEPTHWAVE.set(DEPTHWAVE);
        }

        public String getENERGY() {
            return ENERGY.get();
        }

        public void setENERGY(String ENERGY) {
            this.ENERGY.set(ENERGY);
        }

        public String getTYPEWAVE() {
            return TYPEWAVE.get();
        }

        public void setTYPEWAVE(String TYPEWAVE) {
            this.TYPEWAVE.set(TYPEWAVE);
        }

        public String getMAGNITUDE() {
            return MAGNITUDE.get();
        }

        public void setMAGNITUDE(String MAGNITUDE) {
            this.MAGNITUDE.set(MAGNITUDE);
        }
    }

    public class DoubleName {
        private final String ruName;
        private final String enName;

        public DoubleName(String ruName, String enName) {
            this.ruName = ruName;
            this.enName = enName;
        }

        public String getRuName() {
            return ruName;
        }

        public String getEnName() {
            return enName;
        }
    }
}
