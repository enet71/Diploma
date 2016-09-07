package com.diploma.dataBase.tables;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class BookKeeping implements Table{
    @Id
    private Integer id;
    @Temporal(TemporalType.DATE)
    private Date dateBegin;
    @Temporal(TemporalType.DATE)
    private Date dateEnd;

    public BookKeeping() {
    }

    public BookKeeping(Integer id, Date dateBegin, Date dateEnd) {
        this.id = id;
        dateBegin = dateBegin;
        dateEnd = dateEnd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        dateEnd = dateEnd;
    }
}
