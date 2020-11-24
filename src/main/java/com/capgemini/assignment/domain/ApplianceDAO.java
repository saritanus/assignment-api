package com.capgemini.assignment.domain;

import com.sun.istack.NotNull;

import javax.validation.constraints.Size;

public class ApplianceDAO {
    @NotNull
    @Size(max=36)
    private String id;
    @NotNull
    @Size(max=32, message="Maximum length of Serial Number is 32.")
    private String serialNo;
    @NotNull
    @Size(max=32, message="Maximum length of Model is 32.")
    private String model;
    @NotNull
    @Size(max=32, message="Maximum length of Brand is 32.")
    private String brand;
    @NotNull
    private String status;
    @NotNull
    private String purchaseDate;

    private boolean isDeleted;

    public ApplianceDAO(String serialNo, String model, String brand, String status, String purchaseDate, boolean isDeleted) {
        this.serialNo = serialNo;
        this.model = model;
        this.brand = brand;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.isDeleted = isDeleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
