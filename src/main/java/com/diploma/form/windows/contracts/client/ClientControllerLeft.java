package com.diploma.form.windows.contracts.client;


import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.Client;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.leftWindow.LeftElement;
import com.diploma.form.windows.leftWindow.LeftStaticElements;
import com.diploma.form.windows.leftWindow.LeftWindowed;
import com.diploma.staticField.StaticFields;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class ClientControllerLeft extends AbstractWindow implements LeftWindowed {
    public ClientControllerLeft() {
        super(StaticFields.LEFTFIELD);
    }

    @Override
    public void initialize() {
        refreshList();
    }

    @Override
    public void refreshList() {
        accordion.getPanes().clear();
        ArrayList<Client> list = getAccordionList(Client.class);

        for (Client element : list) {
            EventHandler<ActionEvent> eventHandler = event -> {
                Command.delete(Client.class, element.getId());
                refreshList();
            };
            LeftElement leftElement = LeftStaticElements.getClient(element);
            leftElement.setDelete(eventHandler);
            addToAccordionList(leftElement.getTitledPane());
        }
    }
}
