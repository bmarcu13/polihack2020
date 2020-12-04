package com.example.polihack2020bylos.DoctorApp.AddPatient;

import java.io.Serializable;

public class Patient implements Serializable {
    private Long patientId;
    private String patientName;
    private Integer patientAge;
    private String patientSex;
    private String patientBloodType;
    private String patientSymptomesDescription;
    private String patientTreatment;

    public Patient(Long patientId, String patientName, Integer patientAge, String patientSex, String patientBloodType, String patientSymptomesDescription, String patientTreatment) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientSex = patientSex;
        this.patientBloodType = patientBloodType;
        this.patientSymptomesDescription = patientSymptomesDescription;
        this.patientTreatment = patientTreatment;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientBloodType() {
        return patientBloodType;
    }

    public void setPatientBloodType(String patientBloodType) {
        this.patientBloodType = patientBloodType;
    }

    public String getPatientSymptomesDescription() {
        return patientSymptomesDescription;
    }

    public void setPatientSymptomesDescription(String patientSymptomesDescription) {
        this.patientSymptomesDescription = patientSymptomesDescription;
    }

    public String getPatientTreatment() {
        return patientTreatment;
    }

    public void setPatientTreatment(String patientTreatment) {
        this.patientTreatment = patientTreatment;
    }
}
