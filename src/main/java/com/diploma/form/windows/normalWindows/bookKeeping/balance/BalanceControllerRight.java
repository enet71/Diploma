package com.diploma.form.windows.normalWindows.bookKeeping.balance;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.tables.BalanceChanges;
import com.diploma.dataBase.tables.BookKeeping;
import com.diploma.dataBase.tables.TypeChanges;
import com.diploma.form.windows.AbstractWindow;
import com.diploma.form.windows.RightWindowed;
import javafx.scene.control.TextField;

public class BalanceControllerRight extends AbstractWindow implements RightWindowed {
    public TextField valueField;
    public TextField bookKeepingField;
    public TextField typeField;
    public BookKeeping bookKeeping;
    public TypeChanges typeChanges;

    public BalanceControllerRight() {
        super("fxml/balanceRight.fxml");
    }

    @Override
    public void clear() {
        valueField.clear();
        bookKeepingField.clear();
        typeField.clear();
    }

    @Override
    public void add() {
        Command.insert(new BalanceChanges(1, Integer.parseInt(valueField.getText()), bookKeeping, typeChanges, "1"));
        ((BalanceControllerLeft) otherController).refreshList();
    }

    public void getKeeping() {
        ((BalanceControllerLeft) otherController).refreshListKeeping();
    }

    public void setKeeping(BookKeeping bookKeeping) {
        this.bookKeeping = bookKeeping;
        bookKeepingField.setText("" + bookKeeping.getDateBegin() + " - " + bookKeeping.getDateEnd());
    }

    public void getType() {
        ((BalanceControllerLeft) otherController).refreshListType();
    }

    public void setType(TypeChanges typeChanges) {
        this.typeChanges = typeChanges;
        typeField.setText(typeChanges.getName());
    }
}
