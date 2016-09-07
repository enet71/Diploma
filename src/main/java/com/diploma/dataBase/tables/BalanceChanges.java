package com.diploma.dataBase.tables;

import javax.persistence.*;

@Entity
public class BalanceChanges implements Table{
    @Id
    private Integer id;
    private Integer val;
    @OneToOne
    @JoinColumn(name = "bookkeeping")
    private BookKeeping bookkeeping;
    @OneToOne
    @JoinColumn(name = "typeChanges")
    private TypeChanges typeChanges;
    @Column(name = "indx")
    private String index;

    public BalanceChanges() {
    }

    public BalanceChanges(Integer id, Integer val, BookKeeping bookkeeping, TypeChanges typeChanges, String index) {
        this.id = id;
        this.val = val;
        this.bookkeeping = bookkeeping;
        this.typeChanges = typeChanges;
        this.index = index;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public BookKeeping getBookkeeping() {
        return bookkeeping;
    }

    public void setBookkeeping(BookKeeping bookkeeping) {
        this.bookkeeping = bookkeeping;
    }

    public TypeChanges getTypeChanges() {
        return typeChanges;
    }

    public void setTypeChanges(TypeChanges typeChanges) {
        this.typeChanges = typeChanges;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}

