package com.diploma.localization.ru;

import com.diploma.dataBase.enums.EnumWave;
import com.diploma.localization.getName;

public class TableNameRU implements getName {

    @Override
    public String getName(Enum enm) {
        if (enm == EnumWave.ID) {
            return "Ид";
        } else if (enm == EnumWave.SENSOR) {
            return "Датчик";
        } else if (enm == EnumWave.TIMEWAVE) {
            return "Время";
        } else if (enm == EnumWave.DEPTHWAVE) {
            return "Глубина";
        } else if (enm == EnumWave.ENERGY) {
            return "Энергия";
        } else if (enm == EnumWave.TYPEWAVE) {
            return "Тип";
        } else if (enm == EnumWave.MAGNITUDE) {
            return "Магнитуда";
        }

        return null;
    }
}
