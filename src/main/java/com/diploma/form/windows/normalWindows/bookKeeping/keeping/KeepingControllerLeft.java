package com.diploma.form.windows.normalWindows.bookKeeping.keeping;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.BookKeeping;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.leftWindow.LeftElement;
import com.diploma.form.windows.leftWindow.LeftStaticElements;
import com.diploma.form.windows.leftWindow.LeftWindowed;
import com.diploma.staticField.StaticFields;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class KeepingControllerLeft extends AbstractWindow implements LeftWindowed {

    public KeepingControllerLeft() {
        super(StaticFields.LEFTFIELD);
    }

    @Override
    public void initialize() {
        refreshList();
    }

    @Override
    public void refreshList() {
        ArrayList<BookKeeping> list = getAccordionList(BookKeeping.class);
        for (BookKeeping element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                Command.delete(BookKeeping.class, element.getId());
                refreshList();
            };
            LeftElement leftElement = LeftStaticElements.getKeepingBalance(element);
            leftElement.setDelete(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }
}
