package com.capgemini.assignment.service;

import com.capgemini.assignment.enums.Status;
import com.capgemini.assignment.model.Appliance;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ApplicationServiceIntegrationTest {

    @Autowired
    ApplianceService applianceService;

    @Test
    public void create_appliance_test() {
        Appliance appliance = new Appliance("test-serial-number-1",
                "test-model-1", "test-brand-1", "01-11-2020", Status.NEW,false);

        Appliance actual = applianceService.createAppliance(appliance);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(appliance, actual);
    }

    @Test
    public void update_appliance_test() {
        Appliance appliance = new Appliance("test-serial-number-1",
                "test-model-1", "test-brand-1", "01-11-2020", Status.NEW,false);

        Appliance created = applianceService.createAppliance(appliance);
        created.setStatus(Status.OLD);
        Appliance updated = applianceService.updateAppliance(created);
        Assert.assertNotNull(updated);
        Assert.assertNotNull(updated.getId());
        Assert.assertEquals(Status.OLD,updated.getStatus());
    }

    @Test
    public void get_all_appliance_test(){
        Appliance appliance1= new Appliance("test-serial-number-1",
                "test-model-1", "test-brand-1", "01-11-2020", Status.NEW,false);

        Appliance appliance2 = new Appliance("test-serial-number-2",
                "test-model-2", "test-brand-2", "01-11-2020", Status.NEW,false);
        applianceService.createAppliance(appliance1);
        applianceService.createAppliance(appliance2);
        List<Appliance> applianceList = applianceService.getAllAppliance();
        Assert.assertNotNull(applianceList);
        Assert.assertEquals(2,applianceList.size());

    }
}
