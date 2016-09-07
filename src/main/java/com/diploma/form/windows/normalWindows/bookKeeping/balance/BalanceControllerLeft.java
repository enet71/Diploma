package com.diploma.form.windows.normalWindows.bookKeeping.balance;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.BalanceChanges;
import com.diploma.dataBase.tables.BookKeeping;
import com.diploma.dataBase.tables.TypeChanges;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.leftWindow.LeftElement;
import com.diploma.form.windows.leftWindow.LeftStaticElements;
import com.diploma.form.windows.leftWindow.LeftWindowed;
import com.diploma.staticField.StaticFields;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class BalanceControllerLeft extends AbstractWindow implements LeftWindowed {
    public BalanceControllerLeft() {
        super(StaticFields.LEFTFIELD);
    }

    @Override
    public void initialize() {
        refreshList();
    }

    @Override
    public void refreshList() {
        ArrayList<BalanceChanges> list = getAccordionList(BalanceChanges.class);
        for (BalanceChanges element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                Command.delete(BalanceChanges.class, element.getId());
                refreshList();
            };
            LeftElement leftElement = LeftStaticElements.getBalance(element);
            leftElement.setDelete(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }

    public void refreshListType() {
        ArrayList<TypeChanges> list = getAccordionList(TypeChanges.class);
        for (TypeChanges element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                ((BalanceControllerRight) otherController).setType(element);
                refreshList();
            };
            LeftElement leftElement = LeftStaticElements.getTypeBalance(element);
            leftElement.setSelect(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }

    public void refreshListKeeping() {
        ArrayList<BookKeeping> list = getAccordionList(BookKeeping.class);
        for (BookKeeping element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                ((BalanceControllerRight) otherController).setKeeping(element);
                refreshList();
            };
            LeftElement leftElement = LeftStaticElements.getKeepingBalance(element);
            leftElement.setSelect(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }
}
