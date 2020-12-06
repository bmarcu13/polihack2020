package com.example.polihack2020bylos.Entities;

public class Medication {
    private Long medicationId;
    private String medicationName;
    private Double medicationPrice;
    private Integer medicationQuantity;
    private String medicationDescription;

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public Double getMedicationPrice() {
        return medicationPrice;
    }

    public void setMedicationPrice(Double medicationPrice) {
        this.medicationPrice = medicationPrice;
    }

    public Integer getMedicationQuantity() {
        return medicationQuantity;
    }

    public void setMedicationQuantity(Integer medicationQuantity) {
        this.medicationQuantity = medicationQuantity;
    }

    public String getMedicationDescription() {
        return medicationDescription;
    }

    public void setMedicationDescription(String medicationDescription) {
        this.medicationDescription = medicationDescription;
    }
}
