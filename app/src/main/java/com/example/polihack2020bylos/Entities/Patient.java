package com.example.polihack2020bylos.Entities;

import java.util.ArrayList;

public class Patient{


    private String id;
    private String name;
    private Integer age;
    private String sex;
    private String blood_type;
    private ArrayList<String> symptomps;
    private ArrayList<String> medication;
    private ArrayList<String> side_effects;
    private boolean got_better;

    private Patient(){}

    public Patient(String id, String name, Integer age, String sex, String blood_type, ArrayList<String> symptomps, ArrayList<String> medication, ArrayList<String> side_effects, boolean got_better) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.blood_type = blood_type;
        this.symptomps = symptomps;
        this.medication = medication;
        this.side_effects = side_effects;
        this.got_better = got_better;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public ArrayList<String> getSymptomps() {
        return symptomps;
    }

    public void setSymptomps(ArrayList<String> symptomps) {
        this.symptomps = symptomps;
    }

    public ArrayList<String> getmedication() {
        return medication;
    }

    public void setmedication(ArrayList<String> medication) {
        this.medication = medication;
    }

    public ArrayList<String> getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(ArrayList<String> side_effects) {
        this.side_effects = side_effects;
    }

    public boolean isgot_better() {
        return got_better;
    }

    public void setgot_better(boolean got_better) {
        this.got_better = got_better;
    }
}
