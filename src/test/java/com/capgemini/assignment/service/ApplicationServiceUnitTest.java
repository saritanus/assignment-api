package com.capgemini.assignment.service;

import com.capgemini.assignment.enums.Status;
import com.capgemini.assignment.model.Appliance;
import com.capgemini.assignment.repository.ApplianceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ApplicationServiceUnitTest {

    @Mock
    private ApplianceRepository applianceRepository;

    @InjectMocks
    private ApplianceService applianceService =new ApplianceServiceImpl();;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void create_appliance_test()
    {
        Appliance aMockappliance = new Appliance("test-serial-number-1",
                "test-model-1", "test-brand-1", "01-11-2020", Status.NEW,false);

        when(applianceRepository.save(any(Appliance.class))).thenReturn(aMockappliance);
        Appliance newAppliance =applianceService.createAppliance(aMockappliance);
        Assert.assertEquals(aMockappliance,newAppliance);
    }

    @Test
    public void update_appliance_test(){
        Appliance aMockappliance = new Appliance("test-serial-number-1",
                "test-model-1", "test-brand-1", "01-11-2020", Status.OLD,false);
        when(applianceRepository.save(any(Appliance.class))).thenReturn(aMockappliance);
        Appliance updatedAppliance =applianceService.updateAppliance(aMockappliance);
        Assert.assertEquals(aMockappliance,updatedAppliance);

    }

    @Test
    public void get_all_appliance_test(){
        Appliance appliance1 = new Appliance("test-serial-number-1",
                "test-model-1", "test-brand-1", "01-11-2020", Status.NEW,false);
        Appliance appliance2 = new Appliance("test-serial-number-2",
                "test-model-2", "test-brand-2", "01-11-2020", Status.OLD,false);
        List<Appliance> applianceList = new ArrayList<>();
        applianceList.add(appliance1);
        applianceList.add(appliance2);
        when(applianceRepository.findAll()).thenReturn(applianceList);
        List<Appliance> actualList =applianceService.getAllAppliance();
        Assert.assertEquals(applianceList.size(),actualList.size());
    }
}
