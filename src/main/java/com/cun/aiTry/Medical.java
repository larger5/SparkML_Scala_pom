package com.cun.aiTry;

public class Medical {

    private Integer id;

    private String name;

    private String symptom;

    private Double label;

    public Medical(Integer id, String name, String symptom, Double label) {
        this.id = id;
        this.name = name;
        this.symptom = symptom;
        this.label = label;
    }

    public Medical(Integer id) {
        this.id = id;
    }

    public Medical() {
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

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public Double getLabel() {
        return label;
    }

    public void setLabel(Double label) {
        this.label = label;
    }
}
