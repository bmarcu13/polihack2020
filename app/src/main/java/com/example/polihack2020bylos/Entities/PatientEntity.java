package com.example.polihack2020bylos.Entities;

import java.util.ArrayList;

public class PatientEntity {

    private Integer age;
    private String bloodType;
    private String name;
    private String sex;
    private ArrayList<String> medication;
    private ArrayList<String> sideEffects;
    private ArrayList<String> symptomps;

    public PatientEntity() {
    }

    public PatientEntity(Integer age, String bloodType, String name, String sex, ArrayList<String> medication, ArrayList<String> sideEffects, ArrayList<String> symptomps) {
        this.age = age;
        this.bloodType = bloodType;
        this.name = name;
        this.sex = sex;
        this.medication = medication;
        this.sideEffects = sideEffects;
        this.symptomps = symptomps;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ArrayList<String> getMedication() {
        return medication;
    }

    public void setMedication(ArrayList<String> medication) {
        this.medication = medication;
    }

    public ArrayList<String> getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(ArrayList<String> sideEffects) {
        this.sideEffects = sideEffects;
    }

    public ArrayList<String> getSymptomps() {
        return symptomps;
    }

    public void setSymptomps(ArrayList<String> symptomps) {
        this.symptomps = symptomps;
    }
}
