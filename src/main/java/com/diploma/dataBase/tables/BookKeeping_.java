package com.diploma.dataBase.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

@Generated("EclipceLink")
@StaticMetamodel(BookKeeping.class)
public class BookKeeping_ {
    public static volatile SingularAttribute<BookKeeping, Integer> id;
    public static volatile SingularAttribute<BookKeeping, Date> dateBegin;
    public static volatile SingularAttribute<BookKeeping, Date> dateEnd;
}
