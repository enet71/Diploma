package com.diploma.localization.ru.add;

import com.diploma.dataBase.enums.EnumSensor;
import com.diploma.localization.getName;

public class Sensor implements getName {
    @Override
    public String getName(Enum enm) {
        if (enm == EnumSensor.ID) {
            return "Ид";
        } else if (enm == EnumSensor.SEISMIC) {
            return "Сейсмостанция";
        } else if (enm == EnumSensor.GROUND) {
            return "Почва";
        } else if (enm == EnumSensor.NAME) {
            return "Название";
        } else if (enm == EnumSensor.LNG) {
            return "Долгота";
        } else if (enm == EnumSensor.LAT) {
            return "Широта";
        }

        return null;
    }
}
