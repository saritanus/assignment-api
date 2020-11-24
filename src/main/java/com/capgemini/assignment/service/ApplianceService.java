package com.capgemini.assignment.service;

import com.capgemini.assignment.model.Appliance;
import com.capgemini.assignment.repository.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApplianceService {
     Appliance createAppliance(Appliance appliance);
     Appliance updateAppliance(Appliance appliance);
     Appliance findAppliance(Appliance appliance);
     Appliance findApplianceById(String applianceId);
     List<Appliance> getAllAppliance();
     void deleteAppliance(String applianceId);
     void deleteAllAppliance();
}
