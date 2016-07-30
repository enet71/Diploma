package com.diploma.dataBase.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipceLink")
@StaticMetamodel(BalanceChanges.class)
public class BalanceChanges_ {
    public static volatile SingularAttribute<BalanceChanges, Integer> id;
    public static volatile SingularAttribute<BalanceChanges, Integer> val;
    public static volatile SingularAttribute<BalanceChanges, BookKeeping> bookkeeping;
    public static volatile SingularAttribute<BalanceChanges, TypeChanges> typeChanges;
    public static volatile SingularAttribute<BalanceChanges, String> index;
}
