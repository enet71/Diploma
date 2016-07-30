package com.diploma.dataBase.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated("EclipceLink")
@StaticMetamodel(Wave.class)
public class Wave_ {
    public static volatile SingularAttribute<Wave,Integer> id;
    public static volatile SingularAttribute<Wave,Sensor> sensor;
    public static volatile SingularAttribute<Wave,Date> timeWave;
    public static volatile SingularAttribute<Wave,Integer> depthWave;
    public static volatile SingularAttribute<Wave,Integer> energy;
    public static volatile SingularAttribute<Wave,Character> typeWave;
    public static volatile SingularAttribute<Wave,Integer> magnitude;

}
