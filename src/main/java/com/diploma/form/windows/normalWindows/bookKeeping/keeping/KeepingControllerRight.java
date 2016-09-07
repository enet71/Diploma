package com.diploma.form.windows.normalWindows.bookKeeping.keeping;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.BookKeeping;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.RightWindowed;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.Date;

public class KeepingControllerRight extends AbstractWindow implements RightWindowed {
    public DatePicker checkInDatePicker;
    public DatePicker checkOutDatePicker;


    public KeepingControllerRight() {
        super("fxml/keepingRight.fxml");
    }


    @Override
    public void clear() {

    }

    @Override
    public void add() {
        Command.insert(new BookKeeping(1, new Date(checkInDatePicker.getValue().toEpochDay()), new Date(checkOutDatePicker.getValue().toEpochDay())));
        ((KeepingControllerLeft) otherController).refreshList();
    }


    public void initialize() {
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
