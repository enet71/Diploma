package com.diploma.dataBase.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipceLink")
@StaticMetamodel(Seismic.class)
public class Seismic_ {
    public static volatile SingularAttribute<Seismic,Integer> id;
    public static volatile SingularAttribute<Seismic,Region> region;
    public static volatile SingularAttribute<Seismic,String> name;
    public static volatile SingularAttribute<Seismic,String> phone;
    public static volatile SingularAttribute<Seismic,String> mail;
}
