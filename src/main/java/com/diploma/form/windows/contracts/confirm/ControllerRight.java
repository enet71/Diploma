package com.diploma.form.windows.contracts.confirm;

import com.diploma.dataBase.Connect;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.contracts.contract.ModelContract;
import com.diploma.form.windows.contracts.select.SelectWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    @FXML
    private VBox mainVBox;
    private Connect connect;

    public ControllerRight(String path) {
        super(path);
    }

    public void initialize(URL location, ResourceBundle resources) {
       createList();
    }

    public void createList(){
        mainVBox.getChildren().clear();
        ArrayList<Contract> list = new ArrayList<>();
        try {
            connect = new Connect();
            ResultSet resultSet = connect.getStatement().executeQuery("" +
                    "SELECT CONTRACT.ID,CLIENT.NAME,CLIENT.SURNAME,CLIENT.MIDDLENAME,REGION.NAME,OFFER,PAYMENT,CONFIRM " +
                    "FROM CONTRACT " +
                    "INNER JOIN CLIENT ON CLIENT.ID = CONTRACT.CLIENT " +
                    "INNER JOIN REGION ON REGION.ID = CONTRACT.REGION " +
                    "WHERE CONFIRM = 'F'");
            while (resultSet.next()) {
                list.add(new Contract(resultSet.getString(1), resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            final String id = list.get(i).getId();
            VBox vBox = new VBox();
            Label label;
            TextArea textArea;
            vBox.getChildren().add(label = new Label("Ид: " + list.get(i).getId()));
            label.setStyle("-fx-text-fill: #1f232b; -fx-font-size: 13;");
            vBox.getChildren().add(label = new Label("Клиент: " + list.get(i).getClient()));
            label.setStyle("-fx-text-fill: #1f232b; -fx-font-size: 13;");

            vBox.getChildren().add(label = new Label("Регион: " + list.get(i).getRegion()));
            label.setStyle("-fx-text-fill: #1f232b; -fx-font-size: 13;");

            vBox.getChildren().add(label = new Label("Условия"));
            label.setStyle("-fx-text-fill: #1f232b; -fx-font-size: 13;");

            vBox.getChildren().add(textArea = new TextArea(list.get(i).getOffer()));
            label.setStyle("-fx-text-fill: #1f232b; -fx-font-size: 13;");
            textArea.setEditable(false);
            textArea.setPrefHeight(150);
            textArea.setMinHeight(150);
            vBox.getChildren().add(label = new Label("Оплата: " + list.get(i).getPayment()));
            label.setStyle("-fx-text-fill: #1f232b; -fx-font-size: 13;");

            vBox.setStyle("-fx-background-color: rgba(250, 245, 249, 1); -fx-padding: 10,10,10,10; -fx-effect: dropshadow(two-pass-box , darkgray, 6, 0.0 , 2, 4);     ");
            mainVBox.setStyle("-fx-background-color: rgb(225, 221, 224);");
            HBox hBox = new HBox();
            Button enter = new Button("Подтвердить");
            Button delete = new Button("Удалить");
            hBox.getChildren().add(enter);
            hBox.getChildren().add(delete);
            hBox.setSpacing(15);
            vBox.getChildren().add(hBox);
            mainVBox.getChildren().add(vBox);

            enter.setOnAction(event -> {
                try {
                    connect.getStatement().executeUpdate("UPDATE CONTRACT SET CONFIRM = 'T' WHERE ID = " + id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                createList();
            });
            delete.setOnAction(event -> {
                try {
                    ModelContract.delete(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                SelectWindow.controllerRight.createList();
                createList();
            });
        }
    }


    public class Contract {
        String id;
        String client;
        String region;
        String offer;
        String payment;
        String confirm;

        public Contract(String id, String client, String region, String offer, String payment, String confirm) {
            this.id = id;
            this.client = client;
            this.region = region;
            this.offer = offer;
            this.payment = payment;
            this.confirm = confirm;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getOffer() {
            return offer;
        }

        public void setOffer(String offer) {
            this.offer = offer;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getConfirm() {
            return confirm;
        }

        public void setConfirm(String confirm) {
            this.confirm = confirm;
        }
    }

}
