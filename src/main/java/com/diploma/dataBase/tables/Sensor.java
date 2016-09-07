package com.diploma.dataBase.tables;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Sensor implements Table{
    @Id
    private Integer id;
    @OneToOne
    @JoinColumn(name = "seismic")
    private Seismic seismic;
    @OneToOne
    @JoinColumn(name = "ground")
    private Ground ground;
    private String name;
    private Double lng;
    private Double lat;

    public Sensor(Integer id, Seismic seismic, Ground ground, String name, Double lng, Double lat) {
        this.id = id;
        this.seismic = seismic;
        this.ground = ground;
        this.name = name;
        this.lng = lng;
        this.lat = lat;
    }

    public Sensor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Seismic getSeismic() {
        return seismic;
    }

    public void setSeismic(Seismic seismic) {
        this.seismic = seismic;
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
