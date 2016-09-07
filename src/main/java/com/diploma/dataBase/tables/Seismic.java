package com.diploma.dataBase.tables;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Seismic implements Table{
    @Id
    private Integer id;
    @OneToOne
    @JoinColumn(name = "region")
    private Region region;
    private String name;
    private String phone;
    @Email
    private String mail;

    public Seismic(Integer id, Region region, String name, String phone, String mail) {
        this.id = id;
        this.region = region;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
    }

    public Seismic() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
