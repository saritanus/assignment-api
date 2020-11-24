package com.capgemini.assignment.controller;

import com.capgemini.assignment.domain.ApplianceDAO;
import com.capgemini.assignment.enums.Status;
import com.capgemini.assignment.model.Appliance;
import com.capgemini.assignment.service.ApplianceService;
import com.capgemini.assignment.service.ApplianceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/appliances")
public class ApplianceController {

    @Autowired
    ApplianceService service;

    public ApplianceController(){
        service = new ApplianceServiceImpl();
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<?> create(@Valid  @RequestBody ApplianceDAO applianceDAO)
    {
        try {
            Appliance appliance = new Appliance(applianceDAO.getSerialNo(),applianceDAO.getBrand(),
                    applianceDAO.getModel(),applianceDAO.getPurchaseDate(),
                    Status.valueOf(applianceDAO.getStatus()),applianceDAO.isDeleted());
            boolean isExist = service.findAppliance(appliance) != null;
            if(isExist)
            {
                return new ResponseEntity<>("Appliance already exists",HttpStatus.CONFLICT);
            }
            service.createAppliance(appliance);
        }catch(Exception e){
            return new ResponseEntity<>("Error occurred during appliance creation",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Appliance created",HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid  @RequestBody ApplianceDAO applianceDAO)
    {
      try{
          Appliance appliance = new Appliance(applianceDAO.getSerialNo(),applianceDAO.getBrand(),
                  applianceDAO.getModel(),applianceDAO.getPurchaseDate(), Status.valueOf(applianceDAO.getStatus()),applianceDAO.isDeleted());
          appliance.setId(applianceDAO.getId());
          boolean isExist = service.findApplianceById(appliance.getId()) == null;
          if(isExist)
          {
              return new ResponseEntity<>("Appliance does not exist",HttpStatus.NOT_FOUND);
          }
          service.updateAppliance(appliance);
        }catch(Exception e){
            return new ResponseEntity<>("Error occurred while updating appliance",HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>("Appliance Updated",HttpStatus.OK);
    }

    @RequestMapping(path="{id}",method=RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id")  String id)
    {
        try {
            if (id == null || id.isEmpty()) {
                return new ResponseEntity<>("Invalid id", HttpStatus.BAD_REQUEST);
            }
            boolean isExist = service.findApplianceById(id)==null;
            if(isExist){
                return new ResponseEntity<>("Appliance not found", HttpStatus.NOT_FOUND);
            }
            service.deleteAppliance(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/all",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteAll()
    {
        try {
            service.deleteAllAppliance();
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Appliance>> getAll() {
       List<Appliance> applianceList = service.getAllAppliance();
       return ResponseEntity.ok(applianceList);
    }


}
