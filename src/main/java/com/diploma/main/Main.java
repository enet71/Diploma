package com.diploma.main;

import com.sun.javafx.application.LauncherImpl;
import com.diploma.form.windows.main.StartForm;
import com.diploma.form.windows.preLoader.PreLoader;

public class Main {
    public static void main(String[] args){
        LauncherImpl.launchApplication(StartForm.class, PreLoader.class, args);
    }


}
