package com.diploma.form.windows.additionally.calculate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Model {
    public static Double calculate(String expression) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        Double res;

        try {
            res = (Double) engine.eval(expression + " * 1.0");
        } catch (ScriptException e) {
            res = 0.0;
        }

        return res;
    }
}
