package com.diploma.form.windows.normalWindows.bookKeeping.type;


import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.TypeChanges;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.leftWindow.LeftElement;
import com.diploma.form.windows.leftWindow.LeftStaticElements;
import com.diploma.form.windows.leftWindow.LeftWindowed;
import com.diploma.staticField.StaticFields;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class TypeControllerLeft extends AbstractWindow implements LeftWindowed {
    public TypeControllerLeft() {
        super(StaticFields.LEFTFIELD);
    }

    @Override
    public void initialize() {
        refreshList();
    }

    @Override
    public void refreshList() {
        ArrayList<TypeChanges> list = getAccordionList(TypeChanges.class);
        for (TypeChanges element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                Command.delete(TypeChanges.class, element.getId());
                refreshList();
            };
            LeftElement leftElement = LeftStaticElements.getTypeBalance(element);
            leftElement.setDelete(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }
}
