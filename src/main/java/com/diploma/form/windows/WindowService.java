package com.diploma.form.windows;

import com.diploma.form.windows.normalWindows.bookKeeping.balance.BalanceControllerLeft;
import com.diploma.form.windows.normalWindows.bookKeeping.balance.BalanceControllerRight;
import com.diploma.form.windows.bookKeeping.chart.BalanceChartControllerLeft;
import com.diploma.form.windows.bookKeeping.chart.BalanceChartControllerRight;
import com.diploma.form.windows.normalWindows.bookKeeping.keeping.KeepingControllerLeft;
import com.diploma.form.windows.normalWindows.bookKeeping.keeping.KeepingControllerRight;
import com.diploma.form.windows.normalWindows.bookKeeping.type.TypeControllerLeft;
import com.diploma.form.windows.normalWindows.bookKeeping.type.TypeControllerRight;
import com.diploma.form.windows.contracts.client.ClientControllerLeft;
import com.diploma.form.windows.contracts.client.ClientControllerRight;
import com.diploma.form.windows.doubleWindow.AbstractDoubleWindow;
import com.diploma.form.windows.doubleWindow.DoubleWindow;
import com.diploma.form.windows.maps.MapControllerLeft;
import com.diploma.form.windows.maps.MapControllerRight;
import com.diploma.form.windows.normalWindows.data.ground.GroundControllerLeft;
import com.diploma.form.windows.normalWindows.data.ground.GroundControllerRight;
import com.diploma.form.windows.normalWindows.data.region.RegionControllerLeft;
import com.diploma.form.windows.normalWindows.data.region.RegionControllerRight;
import com.diploma.form.windows.normalWindows.data.seismic.SeismicControllerLeft;
import com.diploma.form.windows.normalWindows.data.seismic.SeismicControllerRight;
import com.diploma.form.windows.normalWindows.data.sensor.SensorControllerLeft;
import com.diploma.form.windows.normalWindows.data.sensor.SensorControllerRight;

public class WindowService {
    public static DoubleWindow createRegion() {
        return new AbstractDoubleWindow(new RegionControllerLeft(), new RegionControllerRight(), "Регион");
    }

    public static DoubleWindow createSeismic() {
        return new AbstractDoubleWindow(new SeismicControllerLeft(), new SeismicControllerRight(), "Сейсмостанция");
    }

    public static DoubleWindow createSensor() {
        return new AbstractDoubleWindow(new SensorControllerLeft(), new SensorControllerRight(), "Датчик");
    }

    public static DoubleWindow createGround() {
        return new AbstractDoubleWindow(new GroundControllerLeft(), new GroundControllerRight(), "Грунт");
    }

    public static DoubleWindow createTypeBalance() {
        return new AbstractDoubleWindow(new TypeControllerLeft(), new TypeControllerRight(), "Тип");
    }

    public static DoubleWindow createTypeKeeping() {
        return new AbstractDoubleWindow(new KeepingControllerLeft(), new KeepingControllerRight(), "Период");
    }

    public static DoubleWindow createBalance() {
        return new AbstractDoubleWindow(new BalanceControllerLeft(), new BalanceControllerRight(), "Баланс");
    }

    public static DoubleWindow createBalanceChart() {
        return new AbstractDoubleWindow(new BalanceChartControllerLeft(), new BalanceChartControllerRight(), "График");
    }

    public static DoubleWindow createClient() {
        return new AbstractDoubleWindow(new ClientControllerLeft(), new ClientControllerRight(), "Клиент");
    }

    public static DoubleWindow createMap() {
        return new AbstractDoubleWindow(new MapControllerLeft(), new MapControllerRight(), "Карта");
    }








    /*public static DoubleWindow createWaveChart(){
        return new AbstractDoubleWindow(waveChartLeft, waveChartRight, "График волн");
    }*/
}
