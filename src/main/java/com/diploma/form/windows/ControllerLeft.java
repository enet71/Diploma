package com.diploma.form.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;

public class ControllerLeft extends AbstractWindow {
    @FXML
    public Accordion accordion;

    public ControllerLeft(String path) {
        super(path);
    }





    public void refreshListGround() {
        /*accordion.getPanes().clear();
        try {
            ArrayList<Ground> list = ModelGround.selectGround();
            for (Ground element : list) {
                VBox vBox = new VBox(5);
                Button button = new Button("Удалить");
                vBox.getChildren().add(new Label(element.getId()));
                vBox.getChildren().add(new Label(element.getName()));
                vBox.getChildren().add(button);
                TitledPane titledPane = new TitledPane(element.getName(), vBox);
                accordion.getPanes().add(titledPane);
                button.setOnAction(event -> {
                    try {
                        ModelGround.delete(element.getId());
                        refreshListGround();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public void refreshListRegion() {
        /*accordion.getPanes().clear();
        ArrayList<Region> list = ModelRegion.selectRegion();
        for (Region element : list) {
            VBox vBox = new VBox(5);
            Button button = new Button("Удалить");
            vBox.getChildren().add(new Label(element.getId()));
            vBox.getChildren().add(new Label(element.getAddress()));
            vBox.getChildren().add(button);
            TitledPane titledPane = new TitledPane(element.getName(), vBox);
            accordion.getPanes().add(titledPane);
            button.setOnAction(event -> {
                ModelRegion.delete(element.getId());
                refreshListRegion();
            });

        }*/
    }

    public void refreshListSeismicRegion() {
       /* accordion.getPanes().clear();
        ArrayList<Region> list = ModelRegion.selectRegion();
        for (Region element : list) {
            VBox vBox = new VBox();
            Button buttonSelect = new Button("Выбрать");
            vBox.getChildren().add(new Label(element.getId()));
            vBox.getChildren().add(new Label(element.getName()));
            vBox.getChildren().add(new Label(element.getAddress()));
            vBox.getChildren().add(buttonSelect);
            TitledPane titledPane = new TitledPane(element.getName(), vBox);
            accordion.getPanes().add(titledPane);

            buttonSelect.setOnAction(event -> {
                SeismicWindow.controllerRight.selectRegion(element.getId());
                refreshListSeismic();
            });

        }*/
    }

    public void refreshListSeismic() {
       /* accordion.getPanes().clear();
        try {
            ArrayList<Seismic> list = ModelSeismic.selectSeismic();
            for (Seismic element : list) {
                VBox vBox = new VBox(5);
                Button button = new Button("Удалить");
                vBox.getChildren().add(new Label(element.getId()));
                vBox.getChildren().add(new Label(element.getRegion()));
                vBox.getChildren().add(new Label(element.getName()));
                vBox.getChildren().add(new Label(element.getPhone()));
                vBox.getChildren().add(new Label(element.getMail()));
                vBox.getChildren().add(button);
                TitledPane titledPane = new TitledPane(element.getName(), vBox);
                accordion.getPanes().add(titledPane);
                button.setOnAction(event -> {
                    try {
                        ModelSeismic.delete(element.getId());
                        refreshListSeismic();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public void refreshListSensor() {
        /*accordion.getPanes().clear();
        try {
            ArrayList<Sensor> list = ModelSensor.selectSensor();
            for (Sensor element : list) {
                Connect connect = new Connect();
                ResultSet resultSet = connect.getStatement().executeQuery("SELECT NAME FROM SEISMIC WHERE ID = '" + element.getSeismic() + "'");
                resultSet.next();
                String seismic = resultSet.getString(1);
                resultSet = connect.getStatement().executeQuery("SELECT NAME FROM GROUND WHERE ID = '" + element.getGround() + "'");
                resultSet.next();
                String ground = resultSet.getString(1);


                VBox vBox = new VBox(5);
                Button buttonEdit = new Button("Редактировать");
                Button buttonDelete = new Button("Удалить");
                vBox.getChildren().add(new Label("ИД: " + element.getId()));
                vBox.getChildren().add(new Label("Сейсмостанция: " + seismic));
                vBox.getChildren().add(new Label("Почва: " + ground));
                vBox.getChildren().add(new Label("Название: " + element.getName()));
                vBox.getChildren().add(new Label("Долгота: " + element.getLng()));
                vBox.getChildren().add(new Label("Широта: " + element.getLat()));
                vBox.getChildren().add(buttonEdit);
                vBox.getChildren().add(buttonDelete);
                TitledPane titledPane = new TitledPane(element.getName(), vBox);
                accordion.getPanes().add(titledPane);
                buttonDelete.setOnAction(event -> {
                    try {
                        ModelSensor.delete(element.getId());
                        refreshListRegion();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

                buttonEdit.setOnAction(event -> {
                    SensorWindow.controllerRight.edit(element.getId());
                    SensorWindow.controllerRight.seismicField.setText(element.getSeismic());
                    SensorWindow.controllerRight.groundField.setText(element.getGround());
                    SensorWindow.controllerRight.nameField.setText(element.getName());
                    SensorWindow.controllerRight.lngField.setText(element.getLng());
                    SensorWindow.controllerRight.latField.setText(element.getLat());
                });

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public void refreshListSensorSeismic() {
        /*accordion.getPanes().clear();
        try {
            ArrayList<Seismic> list = ModelSeismic.selectSeismic();
            for (Seismic element : list) {
                VBox vBox = new VBox();
                Button buttonSelect = new Button("Выбрать");
                vBox.getChildren().add(new Label(element.getId()));
                vBox.getChildren().add(new Label("" + element.getRegion()));
                vBox.getChildren().add(new Label("" + element.getName()));
                vBox.getChildren().add(new Label(element.getPhone()));
                vBox.getChildren().add(new Label(element.getMail()));
                vBox.getChildren().add(buttonSelect);
                TitledPane titledPane = new TitledPane(element.getName(), vBox);
                accordion.getPanes().add(titledPane);

                buttonSelect.setOnAction(event -> {
                    SensorWindow.controllerRight.selectSeismic(element.getId());
                    refreshListSensor();
                });

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public void refreshListSensorGround() {
       /* accordion.getPanes().clear();
        try {
            ArrayList<Ground> list = ModelGround.selectGround();
            for (Ground element : list) {
                VBox vBox = new VBox();
                Button buttonSelect = new Button("Выбрать");
                vBox.getChildren().add(new Label("ИД: " + element.getId()));
                vBox.getChildren().add(new Label("Название: " + element.getName()));
                vBox.getChildren().add(buttonSelect);
                TitledPane titledPane = new TitledPane(element.getName(), vBox);
                accordion.getPanes().add(titledPane);

                buttonSelect.setOnAction(event -> {
                    SensorWindow.controllerRight.selectGround(element.getId());
                    refreshListSensor();
                });

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    /*public void refreshListClient() {
        accordion.getPanes().clear();
        List<Client> list = ModelClient.selectClient();
        for (Client element : list) {
            VBox vBox = new VBox(5);
            Button button = new Button("Удалить");
            vBox.getChildren().add(new Label(String.valueOf(element.getId())));
            vBox.getChildren().add(new Label(element.getName()));
            vBox.getChildren().add(new Label(element.getSurname()));
            vBox.getChildren().add(new Label(element.getMiddleName()));
            vBox.getChildren().add(button);
            TitledPane titledPane = new TitledPane(element.getName(), vBox);
            accordion.getPanes().add(titledPane);
            button.setOnAction(event -> {
                try {
                    ModelClient.delete(String.valueOf(element.getId()));
                    refreshListClient();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        }
    }*/

    public void refreshListContract() {
        /*accordion.getPanes().clear();
        ArrayList<Contract> list = ModelContract.selectContract();
        for (Contract element : list) {
            VBox vBox = new VBox(5);
            Button button = new Button("Удалить");
            vBox.getChildren().add(new Label(element.getId()));
            vBox.getChildren().add(new Label(element.getClient()));
            vBox.getChildren().add(new Label(element.getRegion()));
            vBox.getChildren().add(new Label(element.getPayment()));
            vBox.getChildren().add(button);
            TitledPane titledPane = new TitledPane(element.getId(), vBox);
            accordion.getPanes().add(titledPane);
            button.setOnAction(event -> {
                try {
                    ModelContract.delete(element.getId());
                    refreshListContract();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        }*/
    }

    public void refreshListContractClient() {
       /* accordion.getPanes().clear();
        List<Client> list = ModelClient.selectClient();
        for (Client element : list) {
            VBox vBox = new VBox();
            Button buttonSelect = new Button("Выбрать");
            vBox.getChildren().add(new Label(String.valueOf(element.getId())));
            vBox.getChildren().add(new Label(element.getName()));
            vBox.getChildren().add(buttonSelect);
            TitledPane titledPane = new TitledPane(element.getName(), vBox);
            accordion.getPanes().add(titledPane);

            buttonSelect.setOnAction(event -> {
                ContractWindow.controllerRight.selectClient(String.valueOf(element.getId()));
                refreshListContract();
            });

        }*/
    }

    public void refreshListContractRegion() {
        /*accordion.getPanes().clear();
        ArrayList<Region> list = ModelRegion.selectRegion();
        for (Region element : list) {
            VBox vBox = new VBox();
            Button buttonSelect = new Button("Выбрать");
            vBox.getChildren().add(new Label(element.getId()));
            vBox.getChildren().add(new Label(element.getName()));
            vBox.getChildren().add(new Label(element.getAddress()));
            vBox.getChildren().add(buttonSelect);
            TitledPane titledPane = new TitledPane(element.getName(), vBox);
            accordion.getPanes().add(titledPane);

            buttonSelect.setOnAction(event -> {
                ContractWindow.controllerRight.selectRegion(element.getId());
                refreshListContract();
            });

        }*/
    }

    public void refreshListBalance() {
        /*accordion.getPanes().clear();
        ArrayList<BalanceChanges> list = ModelBalance.selectBalance();
        for (BalanceChanges element : list) {
            VBox vBox = new VBox(5);
            Button button = new Button("Удалить");
            vBox.getChildren().add(new Label(String.valueOf(element.getId())));
            vBox.getChildren().add(new Label(String.valueOf(element.getVal())));
            vBox.getChildren().add(new Label(String.valueOf(element.getBookkeeping())));
            vBox.getChildren().add(new Label(String.valueOf(element.getTypeChanges())));
            vBox.getChildren().add(new Label(element.getIndex()));
            vBox.getChildren().add(button);
            TitledPane titledPane = new TitledPane(String.valueOf(element.getId()), vBox);
            accordion.getPanes().add(titledPane);
            button.setOnAction(event -> {
                try {
                    ModelBalance.deleteBalance(String.valueOf(element.getId()));
                    refreshListContract();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        }*/
    }

    public void refreshListBalanceBookKeeping() {
       /* accordion.getPanes().clear();
        ArrayList<BookKeeping> list = ModelKeeping.selectKeeping();
        for (BookKeeping element : list) {
            VBox vBox = new VBox();
            Button buttonSelect = new Button("Выбрать");
            vBox.getChildren().add(new Label(element.getId()));
            vBox.getChildren().add(new Label(element.getDateBegin()));
            vBox.getChildren().add(new Label(element.getDateEnd()));
            vBox.getChildren().add(buttonSelect);
            TitledPane titledPane = new TitledPane(element.getId(), vBox);
            accordion.getPanes().add(titledPane);

            buttonSelect.setOnAction(event -> {
                BalanceWindow.controllerRight.selectKeeping(element.getId());
                refreshListBalance();
            });

        }*/
    }

    public void refreshListBalanceType() {
      /*  accordion.getPanes().clear();
        ArrayList<TypeChanges> list = ModelType.selectType();
        for (TypeChanges element : list) {
            VBox vBox = new VBox();
            Button buttonSelect = new Button("Выбрать");
            vBox.getChildren().add(new Label(element.getId()));
            vBox.getChildren().add(new Label(element.getName()));
            vBox.getChildren().add(buttonSelect);
            TitledPane titledPane = new TitledPane(element.getId(), vBox);
            accordion.getPanes().add(titledPane);

            buttonSelect.setOnAction(event -> {
                BalanceWindow.controllerRight.selectType(element.getId());
                refreshListBalance();
            });

        }*/
    }

    public void refreshListKeeping() {
       /* accordion.getPanes().clear();
        ArrayList<BookKeeping> list = ModelKeeping.selectKeeping();
        for (BookKeeping element : list) {
            VBox vBox = new VBox(5);
            Button button = new Button("Удалить");
            vBox.getChildren().add(new Label(element.getId()));
            vBox.getChildren().add(new Label(element.getDateBegin()));
            vBox.getChildren().add(new Label(element.getDateEnd()));
            vBox.getChildren().add(button);
            TitledPane titledPane = new TitledPane(element.getId(), vBox);
            accordion.getPanes().add(titledPane);
            button.setOnAction(event -> {
                try {
                    ModelKeeping.deleteKeeping(element.getId());
                    refreshListKeeping();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        }*/
    }

    public void refreshListTypeChanges() {
       /* accordion.getPanes().clear();
        ArrayList<TypeChanges> list = ModelType.selectType();
        for (TypeChanges element : list) {
            VBox vBox = new VBox(5);
            Button button = new Button("Удалить");
            vBox.getChildren().add(new Label(element.getId()));
            vBox.getChildren().add(new Label(element.getName()));
            vBox.getChildren().add(button);
            TitledPane titledPane = new TitledPane(element.getName(), vBox);
            accordion.getPanes().add(titledPane);
            button.setOnAction(event -> {
                try {
                    ModelType.deleteType(element.getId());
                    refreshListTypeChanges();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        }*/
    }
}
