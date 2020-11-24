package com.capgemini.assignment.repository;

import com.capgemini.assignment.enums.Status;
import com.capgemini.assignment.model.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance,String> {

    Appliance findBySerialNo(String serialNo);

    List<Appliance>  findByBrand(String brand);

    List<Appliance>  findByModel(String model);

    List<Appliance> findByStatus(Status status);

    @Query("select appliance from Appliance appliance where serialNo=:slNo and model=:model and brand=:brand")
    Appliance findAppliance(String slNo,String model, String brand);

    @Query("delete Appliance a where a.id in" +
            "(select a.id from Appliance a where a.status!='NEW')")
    Appliance deleteAllAppliance(String status);
}