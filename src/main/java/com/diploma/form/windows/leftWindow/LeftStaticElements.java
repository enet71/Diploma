package com.diploma.form.windows.leftWindow;

import com.diploma.dataBase.tables.*;

public class LeftStaticElements {
    public static LeftElement getSeismic(Seismic element) {
        LeftElement leftElement = new LeftElement(element.getName());
        leftElement.addLabel(String.valueOf(element.getId()));
        leftElement.addLabel(element.getRegion().getName());
        leftElement.addLabel(element.getPhone());
        leftElement.addLabel(element.getMail());
        return leftElement;
    }

    public static LeftElement getGround(Table elementT) {
        Ground element = (Ground)elementT;
        LeftElement leftElement = new LeftElement(element.getName());
        leftElement.addLabel(String.valueOf(element.getId()));
        leftElement.addLabel(element.getName());
        return leftElement;
    }

    public static LeftElement getRegion(Region element) {
        LeftElement leftElement = new LeftElement(element.getName());
        leftElement.addLabel(String.valueOf(element.getId()));
        leftElement.addLabel(element.getAddress());
        return leftElement;
    }

    public static LeftElement getSensor(Sensor element) {
        LeftElement leftElement = new LeftElement(element.getName());
        leftElement.addLabel(String.valueOf(element.getId()));
        leftElement.addLabel(element.getSeismic().getName());
        leftElement.addLabel(element.getGround().getName());
        leftElement.addLabel(element.getName());
        leftElement.addLabel(String.valueOf(element.getLng()));
        leftElement.addLabel(String.valueOf(element.getLat()));
        return leftElement;
    }

    public static LeftElement getBalance(BalanceChanges element){
        LeftElement leftElement = new LeftElement(String.valueOf(element.getId()));
        leftElement.addLabel("" + element.getVal());
        leftElement.addLabel("" + element.getBookkeeping().getDateBegin() + element.getBookkeeping().getDateEnd());
        leftElement.addLabel(element.getTypeChanges().getName());
        return leftElement;
    }

    public static LeftElement getTypeBalance(TypeChanges element){
        LeftElement leftElement = new LeftElement(element.getName());
        leftElement.addLabel(String.valueOf(element.getId()));
        leftElement.addLabel(element.getName());
        return leftElement;
    }

    public static LeftElement getKeepingBalance(BookKeeping element){
        LeftElement leftElement = new LeftElement("" + element.getDateBegin() + element.getDateEnd());
        leftElement.addLabel(String.valueOf(element.getId()));
        return leftElement;
    }

    public static LeftElement getClient(Client element){
        LeftElement leftElement = new LeftElement("" + element.getName() + element.getSurname());
        leftElement.addLabel(String.valueOf(element.getId()));
        leftElement.addLabel(element.getName());
        leftElement.addLabel(element.getSurname());
        leftElement.addLabel(element.getMiddleName());
        return leftElement;
    }
}
