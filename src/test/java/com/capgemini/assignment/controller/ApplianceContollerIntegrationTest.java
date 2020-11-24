package com.capgemini.assignment.controller;

import com.capgemini.assignment.enums.Status;
import com.capgemini.assignment.model.Appliance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplianceControllerIntegrationTest {

    @Autowired
    ApplianceController applianceController;

    @Test
    public void testCreateAppliance()
    {
        Appliance appliance = new Appliance("test-serial-no","test-model","test-brand","01/11/2020", Status.NEW,false);
       // String outcome = applianceController.processCreateAppliance(appliance);
    }
}
