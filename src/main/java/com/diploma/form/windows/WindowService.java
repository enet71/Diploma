package com.diploma.form.windows;

import com.diploma.form.windows.data.add.region.RegionQ;
import com.diploma.form.windows.doubleWindow.AbstractDoubleWindow;
import com.diploma.form.windows.doubleWindow.DoubleWindow;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;

public class WindowService {
    private static Weld weld;
    private static WeldContainer container;
    private static WindowService windowService;
    @Inject
    @RegionQ
    @Left
    private AbstractWindow regionLeft;
    @Inject
    @RegionQ
    @Right
    private AbstractWindow regionRight;



    public static void createWindowService() {
        weld = new Weld();
        container = weld.initialize();
        windowService = container.instance().select(WindowService.class).get();
        weld.shutdown();
    }

    public static WindowService getWindowService() {
        return windowService;
    }

    public DoubleWindow createRegion() {
        return new AbstractDoubleWindow(regionLeft, regionRight, "Регион");
    }
}
