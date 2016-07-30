package com.diploma.dataBase.tables;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ground {
    @Id
    private Integer id;
    private String name;

    public Ground(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ground() {
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
