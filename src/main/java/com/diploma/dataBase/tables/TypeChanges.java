package com.diploma.dataBase.tables;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TypeChanges implements Table{
    @Id
    private Integer id;
    private String name;

    public TypeChanges(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypeChanges() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
