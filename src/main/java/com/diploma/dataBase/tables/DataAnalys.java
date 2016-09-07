package com.diploma.dataBase.tables;

import javax.persistence.*;

@Entity
public class DataAnalys implements Table{
    @Id
    private Integer id;
    @OneToOne
    @JoinColumn(name = "analysis")
    private Analysis analysis;
    @OneToOne
    @JoinColumn(name = "typedata")
    private TypeData typeData;
    private String value;
    @Column(name = "indx")
    private Integer index;

    public DataAnalys() {
    }

    public DataAnalys(Integer id, Analysis analysis, TypeData typeData, String value, Integer index) {
        this.id = id;
        this.analysis = analysis;
        this.typeData = typeData;
        this.value = value;
        this.index = index;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    public TypeData getTypeData() {
        return typeData;
    }

    public void setTypeData(TypeData typeData) {
        this.typeData = typeData;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setAnalysString(String s){
        getAnalysis().setName(s);
    }

    public String getAnalysString(){
        return getAnalysis().getName();
    }

    public void setTypeDataString(String s){
        getTypeData().setName(s);
    }

    public String getTypeDataString(){
        return getTypeData().getName();
    }
}
