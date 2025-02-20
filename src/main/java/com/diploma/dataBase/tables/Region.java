package com.diploma.dataBase.tables;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Region implements Table{
    @Id
    private Integer id;
    private String name;
    private String address;

    public Region(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Region() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
