package com.diploma.form.windows.bookKeeping.keeping;

import com.diploma.form.windows.AbstractWindow;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerRight extends AbstractWindow {
    public DatePicker checkInDatePicker;
    public DatePicker checkOutDatePicker;


    public ControllerRight(String path) {
        super(path);
    }


    @FXML
    private void add() throws SQLException, IOException {
        ModelKeeping.insertKeeping(checkInDatePicker.getValue().toString(), checkOutDatePicker.getValue().toString());
        KeepingWindow.controllerLeft.refreshListKeeping();
    }


    public void initialize(URL location, ResourceBundle resources) {
        KeepingWindow.controllerLeft.refreshListKeeping();


        final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(
                                        checkInDatePicker.getValue().plusDays(1))
                                        ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }
                            }
                        };
                    }
                };
        checkOutDatePicker.setDayCellFactory(dayCellFactory);
    }
}
