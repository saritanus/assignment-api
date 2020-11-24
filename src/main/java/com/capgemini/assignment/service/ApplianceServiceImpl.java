package com.capgemini.assignment.service;

import com.capgemini.assignment.enums.Status;
import com.capgemini.assignment.model.Appliance;
import com.capgemini.assignment.repository.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplianceServiceImpl implements ApplianceService{

    @Autowired
    private ApplianceRepository applianceRepository;
    @Override
    public Appliance createAppliance(Appliance appliance) {
       return applianceRepository.save(appliance);
    }

    @Override
    public Appliance updateAppliance(Appliance appliance) {
        return applianceRepository.save(appliance);
    }

    @Override
    public Appliance findAppliance(Appliance appliance) {
        return applianceRepository.findAppliance(appliance.getSerialNo(),appliance.getModel(),appliance.getBrand());
    }

    @Override
    public Appliance findApplianceById(String applianceId) {
        Optional<Appliance> appliance = applianceRepository.findById(applianceId);
        if(appliance.isPresent()){
            return appliance.get();
        };
        return null;
    }

    @Override
    public List<Appliance> getAllAppliance() {
        return applianceRepository.findAll();
    }

    @Override
    public void deleteAppliance(String applianceId) {
        applianceRepository.deleteById(applianceId);
    }

    @Override
    public void deleteAllAppliance() {

            List<Appliance> oldApplianceList = applianceRepository.findByStatus(Status.OLD);
            List<Appliance> unusedApplianceList = applianceRepository.findByStatus(Status.UNUSED);
            List<Appliance> soldApplianceList = applianceRepository.findByStatus(Status.SOLD);

            if (oldApplianceList.size() > 0) {
                for (Appliance appliance : oldApplianceList) {
                    applianceRepository.deleteById(appliance.getId());
                }
            }

            if (unusedApplianceList.size() > 0) {
                for (Appliance appliance : unusedApplianceList) {
                    applianceRepository.deleteById(appliance.getId());
                }
            }

            if (soldApplianceList.size() > 0) {
                for (Appliance appliance : soldApplianceList) {
                    applianceRepository.deleteById(appliance.getId());
                }
            }


    }
}
