package com.diploma.form.windows.analysis.data;


import com.diploma.dataBase.Command;

import javax.persistence.Query;
import java.util.List;

public class Model {
    public static int getSelectAnalysisID(String name){
        Query query = Command.getEm().createQuery("select a.id FROM Analysis as a WHERE a.name = '" + name + "'");
        return (Integer) query.getResultList().get(0);
    }

    public static List<String> getAnalysisNames(){
        Query query = Command.getEm().createQuery("select a.name FROM Analysis as a");
        return query.getResultList();
    }

}
