package com.capgemini.assignment.repository;

import com.capgemini.assignment.enums.Status;
import com.capgemini.assignment.model.Appliance;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplianceRepositoryUnitTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ApplianceRepository applianceRepository;

    @Test
    public void it_should_save_appliance() {
        Appliance appliance = new Appliance("test-serial-number", "test-brand", "test-model", "01/11/2020", Status.NEW,false);
        appliance = applianceRepository.saveAndFlush(appliance);
        Optional<Appliance> actualAppliance = applianceRepository.findById(appliance.getId());
        if (actualAppliance.isPresent()) {
            Assert.assertEquals(appliance, actualAppliance.get());
        }
    }

    @Test
    public void it_should_update_appliance() {
        Appliance appliance = new Appliance("test-serial-number", "test-brand", "test-model","01-11-2020", Status.NEW,false);
        appliance = entityManager.persistAndFlush(appliance);
        appliance.setStatus(Status.OLD);
        Appliance updatedAppliance = applianceRepository.save(appliance);
        Assert.assertTrue(appliance.getStatus().equals(updatedAppliance.getStatus()) && appliance.getId().equals(updatedAppliance.getId()));
    }

    @Test
    public void it_should_delete_appliance() {
        Appliance appliance = new Appliance("test-serial-number", "test-brand", "test-model", "01-11-2020", Status.NEW,false);
        appliance = entityManager.persistAndFlush(appliance);

        applianceRepository.deleteById(appliance.getId());
        Optional<Appliance> actualAppliance = applianceRepository.findById(appliance.getId());
        Assert.assertFalse(actualAppliance.isPresent());
    }

    @Test
    public void it_should_find_appliance_by_id() {
        Appliance appliance = new Appliance("test-serial-number", "test-brand", "test-model", "01-11-2020", Status.NEW,false);
        appliance = entityManager.persistAndFlush(appliance);

        applianceRepository.deleteById(appliance.getId());
        Optional<Appliance> actualAppliance = applianceRepository.findById(appliance.getId());
        if (actualAppliance.isPresent()) {
            Assert.assertEquals(appliance, actualAppliance.get());
        }
    }

    @Test
    public  void it_should_find_appliance_by_serial_no(){
        Appliance appliance = new Appliance("test-serial-number", "test-brand", "test-model", "01-11-2020", Status.NEW,false);
        appliance = entityManager.persistAndFlush(appliance);

        Appliance actual = applianceRepository.findBySerialNo(appliance.getSerialNo());
        Assert.assertEquals(appliance, actual);
    }

    @Test
    public  void it_should_find_appliances_by_brand(){
        Appliance appliance1 = new Appliance("test-serial-number1", "test-brand", "test-model", "01-11-2020", Status.NEW,false);
        appliance1 = entityManager.persistAndFlush(appliance1);

        Appliance appliance2 = new Appliance("test-serial-number2", "test-brand", "test-model", "01-11-2020", Status.NEW,false);
        entityManager.persistAndFlush(appliance2);
        List<Appliance> actual = applianceRepository.findByModel(appliance1.getBrand());
        Assert.assertEquals(actual.size(), 2);
    }

    @Test
    public  void it_should_find_appliances_by_model(){
        Appliance appliance1 = new Appliance("test-serial-number1", "test-brand", "test-model", "01-11-2020", Status.NEW,false);
        appliance1 = entityManager.persistAndFlush(appliance1);

        Appliance appliance2 = new Appliance("test-serial-number2", "test-brand", "test-model","01-11-2020", Status.NEW,false);
        entityManager.persistAndFlush(appliance2);
        List<Appliance> actual = applianceRepository.findByModel(appliance1.getModel());
        Assert.assertEquals(actual.size(), 2);
    }

    @Test
    public  void it_should_find_appliances_by_status(){
        Appliance appliance1 = new Appliance("test-serial-number1", "test-brand", "test-model", "01-11-2020", Status.NEW,false);
        appliance1 = entityManager.persistAndFlush(appliance1);

        Appliance appliance2 = new Appliance("test-serial-number2", "test-brand", "test-model", "01-11-2020", Status.NEW,false);
        appliance2 = entityManager.persistAndFlush(appliance2);


        List<Appliance> actualList = applianceRepository.findByStatus(Status.NEW);
        Assert.assertEquals(actualList.size(), 2);
    }

    @Test
    public void it_should_delete_appliances_by_status_old() {
        Date now = new Date();
        Appliance appliance1 = new Appliance("test-serial-number1", "test-brand", "test-model", "01-11-2020", Status.NEW,false);
        entityManager.persistAndFlush(appliance1);

        Appliance appliance2 = new Appliance("test-serial-number1", "test-brand", "test-model", "01-11-2020", Status.OLD,false);
        entityManager.persistAndFlush(appliance2);

        List<Appliance> appliances = applianceRepository.findByStatus(Status.OLD);
        for (Appliance appliance:appliances) {
            applianceRepository.delete(appliance);
        }

        List<Appliance> actual = applianceRepository.findAll();
        Assert.assertEquals(actual.size(),1);

    }
}
