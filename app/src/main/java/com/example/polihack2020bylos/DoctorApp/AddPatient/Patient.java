package com.example.polihack2020bylos.DoctorApp.AddPatient;

import java.util.ArrayList;

public class Patient{


    private String id;
    private String name;
    private Integer age;
//    private String sex;
//    private String blood_type;
//    private ArrayList<String> symptomes;
//    private ArrayList<String> treatment;
//    private ArrayList<String> side_effects;
//    private boolean feeling_better;

    private Patient(){}

    public Patient(String id, String name, Integer age, String sex, String blood_type, ArrayList<String> symptomes, ArrayList<String> treatment, ArrayList<String> side_effects, boolean feeling_better) {
        this.id = id;
        this.name = name;
        this.age = age;
//        this.sex = sex;
//        this.blood_type = blood_type;
//        this.symptomes = symptomes;
//        this.treatment = treatment;
//        this.side_effects = side_effects;
//        this.feeling_better = feeling_better;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

//    public String getSex() {
//        return sex;
//    }
//
//    public void setSex(String sex) {
//        this.sex = sex;
//    }
//
//    public String getBlood_type() {
//        return blood_type;
//    }
//
//    public void setBlood_type(String blood_type) {
//        this.blood_type = blood_type;
//    }
//
//    public ArrayList<String> getSymptomes() {
//        return symptomes;
//    }
//
//    public void setSymptomes(ArrayList<String> symptomes) {
//        this.symptomes = symptomes;
//    }
//
//    public ArrayList<String> getTreatment() {
//        return treatment;
//    }
//
//    public void setTreatment(ArrayList<String> treatment) {
//        this.treatment = treatment;
//    }
//
//    public ArrayList<String> getSide_effects() {
//        return side_effects;
//    }
//
//    public void setSide_effects(ArrayList<String> side_effects) {
//        this.side_effects = side_effects;
//    }
//
//    public boolean isFeeling_better() {
//        return feeling_better;
//    }
//
//    public void setFeeling_better(boolean feeling_better) {
//        this.feeling_better = feeling_better;
//    }
}
