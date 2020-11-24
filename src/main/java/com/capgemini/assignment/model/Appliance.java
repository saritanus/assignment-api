package com.capgemini.assignment.model;

import com.capgemini.assignment.enums.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="appliance")
public class Appliance {

    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name="id", length=36, nullable = false)
    private String id;

    @Column(name="serial_number",nullable = false,length = 32)
    private String serialNo;

    @Column(name="brand",nullable = false,length = 32)
    private String brand;

    @Column(name="model",nullable = false,length = 32)
    private String model;

    @Column(name="purchase_date",nullable = false)
    private String purchaseDate;

    @Column(name="status",nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="is_deleted",nullable = false)
    private boolean isDeleted;

    public Appliance(String serialNo, String brand, String model, String purchaseDate, Status status,boolean isDeleted) {
        this.serialNo = serialNo;
        this.brand = brand;
        this.model = model;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.isDeleted = isDeleted;
    }

    public Appliance() {

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appliance appliance = (Appliance) o;
        return Objects.equals(id, appliance.id) &&
                Objects.equals(serialNo, appliance.serialNo) &&
                Objects.equals(brand, appliance.brand) &&
                Objects.equals(model, appliance.model) &&
                status == appliance.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serialNo, brand, model, status);
    }
}
