package com.diploma.dataBase.tables;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Contract {
    @Id
    private Integer id;
    @OneToOne
    private Client client;
    @OneToOne
    private Region region;
    private String offer;
    private Integer payment;
    private Character confirm;

    public Contract(Integer id, Client client, Region region, String offer, Integer payment, Character confirm) {
        this.id = id;
        this.client = client;
        this.region = region;
        this.offer = offer;
        this.payment = payment;
        this.confirm = confirm;
    }

    public Contract() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Character getConfirm() {
        return confirm;
    }

    public void setConfirm(Character confirm) {
        this.confirm = confirm;
    }
}
