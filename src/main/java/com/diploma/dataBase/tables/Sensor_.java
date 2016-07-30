package com.diploma.dataBase.tables;


import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipceLink")
@StaticMetamodel(Sensor.class)
public class Sensor_ {
    public static volatile SingularAttribute<Sensor,Integer> id;
    public static volatile SingularAttribute<Sensor,Seismic> seismic;
    public static volatile SingularAttribute<Sensor,Ground> ground;
    public static volatile SingularAttribute<Sensor,String> name;
    public static volatile SingularAttribute<Sensor,Double> lng;
    public static volatile SingularAttribute<Sensor,Double> lat;

}
