package com.diploma.dataBase.tables;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Wave implements Table{
    @Id
    private Integer id;
    @OneToOne
    @JoinColumn(name = "sensor")
    private Sensor sensor;
    @Temporal(TemporalType.DATE)
    private Date timeWave;
    private Integer depthWave;
    private Integer energy;
    private Character typeWave;
    private Integer magnitude;

    public Wave(Integer id, Sensor sensor, Date timeWave, Integer depthWave, Integer energy, Character typeWave, Integer magnitude) {
        this.id = id;
        this.sensor = sensor;
        this.timeWave = timeWave;
        this.depthWave = depthWave;
        this.energy = energy;
        this.typeWave = typeWave;
        this.magnitude = magnitude;
    }

    public Wave() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Date getTimeWave() {
        return timeWave;
    }

    public void setTimeWave(Date timeWave) {
        this.timeWave = timeWave;
    }

    public Integer getDepthWave() {
        return depthWave;
    }

    public void setDepthWave(Integer depthWave) {
        this.depthWave = depthWave;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Character getTypeWave() {
        return typeWave;
    }

    public void setTypeWave(Character typeWave) {
        this.typeWave = typeWave;
    }

    public Integer getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Integer magnitude) {
        this.magnitude = magnitude;
    }
}
