package com.diploma.dataBase.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipceLink")
@StaticMetamodel(Contract.class)
public class Contract_ {
    public static volatile SingularAttribute<Contract,Integer> id;
    public static volatile SingularAttribute<Contract,Client> client;
    public static volatile SingularAttribute<Contract,Region> region;
    public static volatile SingularAttribute<Contract,String> offer;
    public static volatile SingularAttribute<Contract,Integer> payment;
    public static volatile SingularAttribute<Contract,Character> confirm;
}
