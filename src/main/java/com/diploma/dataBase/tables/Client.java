package com.diploma.dataBase.tables;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Client implements Table{
    @Id
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String middleName;

    public Client(Integer id, String name, String surname, String middleName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }

    public Client() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middlename) {
        this.middleName = middlename;
    }
}
